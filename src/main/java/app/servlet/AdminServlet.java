package app.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

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
import app.model.VehicleCategory;
import app.services.admin.AdminService;
import app.services.admin.DefaultAdminService;
import app.services.vehicle.DefaultVehicleService;
import app.services.vehicle.VehicleService;
import app.utils.ServletUtils;
import app.utils.StringUtils;

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
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<Vehicle> allVehicles = adminService.getAllVehicles();
		session.setAttribute("allVehicles", allVehicles);
		response.sendRedirect(request.getContextPath() + "/admin.jsp");
	}
	
	/**
	 * Insert a new vehicle for rental
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		HttpSession session = request.getSession();
		Vehicle vehicle = this.getVehicleFromRequest(request);
		vehicle = adminService.insertVehicle(vehicle);

		List<Vehicle> vehicles = (List<Vehicle>) session.getAttribute("allVehicles");
		vehicles.add(vehicle);
		session.setAttribute("allVehicles", vehicles);
	}
	
	/**
	 * Update an existing rental vehicle
	 */
	public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		Vehicle vehicle = this.getVehicleFromRequest(request);
		adminService.updateVehicle(vehicle);
	}
	
	/**
	 * Delete a vehicle for rental
	 */
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		HttpSession session = request.getSession();
		Object genericVehicle = session.getAttribute("vehicleDeleteId");
		if (genericVehicle != null) {
			Vehicle vehicle = (Vehicle) genericVehicle;
			adminService.deleteVehicle(vehicle.getId());
		}
	}
	
	private Vehicle getVehicleFromRequest(HttpServletRequest request) {
		String id = (String) request.getParameter("id");
		String category = (String) request.getParameter("category");
		String make = (String) request.getParameter("make");
		String model = (String) request.getParameter("model");
		String color = (String) request.getParameter("color");
		String img = (String) request.getParameter("img");
		
		Vehicle vehicle = new Vehicle();
		vehicle.setCategory(VehicleCategory.valueOf(category));
		vehicle.setMake(make);
		vehicle.setModel(model);
		vehicle.setColor(color);
		vehicle.setImg(img);
		
		if (StringUtils.isNotEmpty(id)) {
			vehicle.setId(Long.parseLong(id));
		}
		
		return vehicle;
	}

}
