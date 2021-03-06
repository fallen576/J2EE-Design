package app.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import app.dao.reservation.ReservationDao;
import app.dao.reservation.SqlReservationDao;
import app.dao.user.reservation.SqlUserReservationDao;
import app.dao.user.reservation.UserReservationDao;
import app.dao.vehicle.SqlVehicleDao;
import app.dao.vehicle.VehicleDao;
import app.model.Reservation;
import app.model.User;
import app.model.Vehicle;
import app.model.VehicleCategory;
import app.services.reservation.DefaultReservationService;
import app.services.reservation.ReservationService;
import app.services.vehicle.DefaultVehicleService;
import app.services.vehicle.VehicleService;
import app.utils.ServletUtils;

@WebServlet("/confirm")
public class ConfirmReservationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Connection connection;
	
	private VehicleService vehicleService;
	private ReservationService reservationService;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.initializeServices();
		
		HttpSession session = request.getSession(); 
		Object userObject = session.getAttribute("user");
		String modify = (String) request.getParameter("modify");
		
		//user wants to change car
		if(modify.equals("modifyCar")) {
			response.sendRedirect(request.getContextPath() + "/select.jsp");
			return;
		} 
		//user wants to change itinerary
		else if(modify.equals("modifyItinerary")) {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			return;
		}
		
		if (userObject != null) {
			String location = (String) session.getAttribute("location");
			String vehicleCategoryString = (String) session.getAttribute("category");
			String pickupTime = (String) session.getAttribute("pickupTime");
			pickupTime = pickupTime.replaceFirst("T", " ");
			
			String dropoffTime = (String) session.getAttribute("dropoffTime");
			dropoffTime = dropoffTime.replaceFirst("T", " ");
			
			String id = request.getParameter("carToSelect");
			
			Vehicle vehicle = this.vehicleService.findByIds(id).get(0);
			
			VehicleCategory vehicleCategory;
			Date pickupDate; 
			Date dropoffDate;
			try {
				SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
				pickupDate = format.parse(pickupTime);
				dropoffDate = format.parse(dropoffTime);
				vehicleCategory = VehicleCategory.valueOf(vehicleCategoryString);
			} catch (Exception e) {
				session.setAttribute("errorMessage", "Unable to parse pickup date, dropoff date, and/or vehicle type");
				response.sendRedirect(request.getContextPath() + "/error.jsp");
				return;
			}
			
			Reservation reservation = new Reservation(location, pickupDate, dropoffDate);
			User user = (User) userObject;
			try {
				Reservation confirmedReservation = reservationService.confirmReservation(user, reservation, vehicle, vehicleCategory);
				session.setAttribute("reservation", confirmedReservation);
				session.setAttribute("reservations", reservationService.findUserReservations(user));
				response.sendRedirect(request.getContextPath() + "/reservation.jsp");
			} catch (Exception e) {
				e.printStackTrace();
				session.setAttribute("errorMessage", e.getMessage());
				response.sendRedirect(request.getContextPath() + "/error.jsp");
			} finally {
				this.closeConnection();
			}
		}
		else {
			response.sendRedirect(request.getContextPath() + "/createAccount.jsp");
		}
	}
	
	private void initializeServices() {
		this.connection = ServletUtils.connectToSqlDatabase();
		
		ReservationDao reservationDao = new SqlReservationDao(connection);
		UserReservationDao userReservationDao = new SqlUserReservationDao(connection);
		VehicleDao vehicleDao = new SqlVehicleDao(connection);
		
		this.vehicleService = new DefaultVehicleService(vehicleDao);
		this.reservationService = new DefaultReservationService(reservationDao, userReservationDao, vehicleDao);
	}
	
	private void closeConnection() {
		try {
			this.connection.close();
		} catch (SQLException e) {}
	}

}
