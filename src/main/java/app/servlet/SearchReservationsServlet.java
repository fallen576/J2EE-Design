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
import app.services.reservation.DefaultReservationService;
import app.services.reservation.ReservationService;
import app.utils.ServletUtils;

/**
 * Servlet implementation class SearchReservations
 */
@WebServlet("/reservation")
public class SearchReservationsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ReservationService reservationService;
       
	public void init() throws ServletException {
		Connection connection = ServletUtils.connectToSqlDatabase();
		
		ReservationDao reservationDao = new SqlReservationDao(connection);
		UserReservationDao userReservationDao = new SqlUserReservationDao(connection);
		VehicleDao vehicleDao = new SqlVehicleDao(connection);
		reservationService = new DefaultReservationService(reservationDao, userReservationDao, vehicleDao);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if (user != null) {
			session.setAttribute("reservations", reservationService.findUserReservations(user));
			response.sendRedirect(request.getContextPath() + "/reservation.jsp");
		}
		else {
			response.sendRedirect(request.getContextPath() + "/authorization.jsp");
		}	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if (user != null) {
			try {
				SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd");
				String location = request.getParameter("location");
				Date pickupDate = format.parse(request.getParameter("pickupDate"));
				Date dropoffDate = format.parse(request.getParameter("dropoffDate"));
				long id = Long.parseLong(request.getParameter("id"));				
				
				Reservation res = new Reservation(location, pickupDate, dropoffDate);
				res.setReservationId(id);
				reservationService.update(res);
				session.setAttribute("reservations", reservationService.findUserReservations(user));
				response.sendRedirect(request.getContextPath() + "/reservation.jsp");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
		else {
			response.sendRedirect(request.getContextPath() + "/authorization.jsp");
		}	
	}
}
