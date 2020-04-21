package app.servlet;

import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import app.dao.reservation.ReservationDao;
import app.dao.reservation.SqlReservationDao;
import app.dao.vehicle.SqlVehicleDao;
import app.dao.vehicle.VehicleDao;
import app.model.Vehicle;
import app.services.admin.AdminService;
import app.services.admin.DefaultAdminService;
import app.services.vehicle.DefaultVehicleService;
import app.services.vehicle.VehicleService;
import app.utils.ServletUtils;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private AdminService adminService;
	
	public void init() throws ServletException {
		Connection connection = ServletUtils.connectToSqlDatabase();
		VehicleDao vehicleDao = new SqlVehicleDao(connection);
		ReservationDao reservationDao = new SqlReservationDao(connection);
		
		VehicleService vehicleService = new DefaultVehicleService(vehicleDao);
		
		adminService = new DefaultAdminService(vehicleService, reservationDao);
	}
	
	/**
	 * Insert a new vehicle for rental
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		HttpSession session = request.getSession();
		Object genericVehicle = session.getAttribute("vehicle");
		if (genericVehicle != null) {
			Vehicle vehicle = (Vehicle) genericVehicle;
			vehicle = adminService.insertVehicle(vehicle);
			session.setAttribute("vehicle", vehicle);
		}
	}
	
	/**
	 * Update an existing rental vehicle
	 */
	public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		HttpSession session = request.getSession();
		Object genericVehicle = session.getAttribute("vehicle");
		if (genericVehicle != null) {
			Vehicle vehicle = (Vehicle) genericVehicle;
			adminService.updateVehicle(vehicle);
		}
	}
	
	/**
	 * Delete a vehicle for rental
	 */
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		HttpSession session = request.getSession();
		Object genericVehicle = session.getAttribute("vehicleId");
		if (genericVehicle != null) {
			Vehicle vehicle = (Vehicle) genericVehicle;
			adminService.deleteVehicle(vehicle.getId());
		}
	}

}
