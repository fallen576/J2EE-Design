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

import app.dao.vehicle.SqlVehicleDao;
import app.dao.vehicle.VehicleDao;
import app.model.Vehicle;
import app.services.vehicle.DefaultVehicleService;
import app.services.vehicle.VehicleService;
import app.utils.ServletUtils;

/**
 * Servlet implementation class RentalServlet
 */
@WebServlet("/rental")
public class RentalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private VehicleService vehicleService;
    
    public void init() throws ServletException {
		Connection connection = ServletUtils.connectToSqlDatabase();
		
		VehicleDao vehicleDao = new SqlVehicleDao(connection);
		vehicleService = new DefaultVehicleService(vehicleDao);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		String filter = (String) request.getParameter("filter");
		String[] categories = null;
		String[] colors = null;
		//the user is coming from the index page, only car type matters
		if (filter.equals("index")) {
			colors = new String[0];
			categories = new String[1];
			categories[0] = request.getParameter("vehicleCategory");
		} else if (filter.equals("panel")) {
			//the user is refining their search from /Select.jsp
			colors = request.getParameterValues("carColor");
			categories = request.getParameterValues("vehicleCategory");
		}

		List<Vehicle> vehicles = vehicleService.filterVehicles(categories, colors);
		session.setAttribute("vehicles", vehicles);
		session.setAttribute("vehicleCategory", categories);
		session.setAttribute("carColor", colors);
		response.sendRedirect(request.getContextPath() + "/select.jsp");
	}

	/** 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);		
	}

}
