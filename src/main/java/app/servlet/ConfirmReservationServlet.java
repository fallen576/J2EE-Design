package app.servlet;

import java.io.IOException;
import java.sql.Connection;
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
import app.model.VehicleType;
import app.services.reservation.DefaultReservationService;
import app.services.reservation.ReservationService;
import app.utils.ServletUtils;

@WebServlet("/confirm")
public class ConfirmReservationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final SimpleDateFormat DATE_TIME_FORMATTER = new SimpleDateFormat("MMM dd, yyyy hh:mm a");

	private ReservationDao reservationDao;
	private UserReservationDao userReservationDao;
	private VehicleDao vehicleDao;
	
	private ReservationService reservationService;
	
	public void init() throws ServletException {
		Connection connection = ServletUtils.connectToSqlDatabase();
		
		reservationDao = new SqlReservationDao(connection);
		userReservationDao = new SqlUserReservationDao(connection);
		vehicleDao = new SqlVehicleDao(connection);
		
		reservationService = new DefaultReservationService(reservationDao, userReservationDao, vehicleDao);		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object userObject = session.getAttribute("user");
		if (userObject != null) {
			String pickupLocation = (String) request.getAttribute("pickupLocation");
			String dropoffLocation = (String) request.getAttribute("dropoffLocation");
			String pickupDateString = (String) request.getAttribute("pickup_date");
			String dropoffDateString = (String) request.getAttribute("dropoff_date");
			String vehicleTypeString = (String) request.getAttribute("vehicleType");
			
			VehicleType vehicleType;
			Date pickupDate;
			Date dropoffDate;
			try {
				pickupDate = DATE_TIME_FORMATTER.parse(pickupDateString);
				dropoffDate = DATE_TIME_FORMATTER.parse(dropoffDateString);
				vehicleType = VehicleType.valueOf(vehicleTypeString);
			} catch (Exception e) {
				throw new RuntimeException("Unable to parse pickup date, dropoff date, and/or vehicle type");
			}
			
			Reservation reservation = new Reservation(pickupLocation, dropoffLocation, pickupDate, dropoffDate);
			User user = (User) userObject;
			reservationService.confirmReservation(user, reservation, vehicleType);			
		}
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

}
