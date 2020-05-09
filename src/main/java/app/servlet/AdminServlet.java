package app.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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
import app.utils.ServletUtils;
import app.utils.StringUtils;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private Connection connection;
	private AdminService adminService;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.initializeAdminService();
		
		HttpSession session = request.getSession();
		List<Vehicle> allVehicles = adminService.getAllVehicles();
		session.setAttribute("allVehicles", allVehicles);
		
		this.closeConnection();
		response.sendRedirect(request.getContextPath() + "/admin.jsp");
	}
	
	/**
	 * Insert a new vehicle for rental
	 * @throws IOException 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.initializeAdminService();
		
		HttpSession session = request.getSession();
		String method = request.getParameter("method");
		Vehicle vehicle = this.getVehicleFromRequest(request);
		if (StringUtils.isNotEmpty(method)) {
			if (method.equalsIgnoreCase("PUT")) {
				adminService.updateVehicle(vehicle);
			} else if (method.equalsIgnoreCase("DELETE")) {
				String deleteId = (String) session.getAttribute("vehicleDeleteId");
				adminService.deleteVehicle(Long.parseLong(deleteId));
			}
		} else {
			vehicle = adminService.insertVehicle(vehicle);
		}
		List<Vehicle> allVehicles = adminService.getAllVehicles();
		session.setAttribute("allVehicles", allVehicles);
		
		this.closeConnection();
		response.sendRedirect(request.getContextPath() + "/admin.jsp");
	}
	
	private Vehicle getVehicleFromRequest(HttpServletRequest request) {
		String id = (String) request.getParameter("id");
		String category = (String) request.getParameter("category");
		String make = (String) request.getParameter("make");
		String model = (String) request.getParameter("model");
		String color = (String) request.getParameter("color");
		String base64Img = (String) request.getParameter("base64Img");
		String costPerDay = (String) request.getParameter("costPerDay");
		
		Vehicle vehicle = new Vehicle();
		vehicle.setCategory(VehicleCategory.valueOf(category));
		vehicle.setMake(make);
		vehicle.setModel(model);
		vehicle.setColor(color);
		vehicle.setBase64Img(base64Img);
		vehicle.setCostPerDay(Double.parseDouble(costPerDay));
		
		if (StringUtils.isNotEmpty(id)) {
			vehicle.setId(Long.parseLong(id));
		}
		
		return vehicle;
	}
	
	private void initializeAdminService() {
		this.connection = ServletUtils.connectToSqlDatabase();
		VehicleDao vehicleDao = new SqlVehicleDao(connection);
		ReservationDao reservationDao = new SqlReservationDao(connection);
		
		this.adminService = new DefaultAdminService(new DefaultVehicleService(vehicleDao), reservationDao);
	}
	
	private void closeConnection() {
		try {
			this.connection.close();
		} catch (SQLException e) {}
	}

}
