package app.servlet;

import java.util.*;
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import app.dao.reservation.SqlReservationDao;
import app.dao.vehicle.SqlVehicleDao;
import app.model.Reservation;
import app.model.User;
import app.model.Vehicle;
import app.utils.ServletUtils;

/**
 * Servlet implementation class SearchReservations
 */
@WebServlet("/reservation")
public class SearchReservations extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SqlReservationDao reservationDao;
	SqlVehicleDao vehicleDao;
       
	public void init() throws ServletException {
		Connection connection = ServletUtils.connectToSqlDatabase();
		
		reservationDao = new SqlReservationDao(connection);
		vehicleDao = new SqlVehicleDao(connection);
		
	
		//vehicleService = new DefaultVehicleService(vehicleDao);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if (user != null) {
			List<Reservation> rList = reservationDao.findByUserId(user.getId());
			
			/*
			for (Reservation r : rList) {
				Long vehicleId = r.getVehicleId();
				List<Vehicle> vehicle = vehicleDao.findById(vehicleId+"");
				r.setVehicle(vehicle.get(0));
			}
			*/
			
			session.setAttribute("reservations", rList);
			response.sendRedirect(request.getContextPath() + "/reservation.jsp");
			
		}
		else {
			response.sendRedirect(request.getContextPath() + "/authorization.jsp");
		}
		
		
	}
}
