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
import app.model.Vehicle;
import app.utils.ServletUtils;

/**
 * Servlet implementation class RentalServlet
 */
@WebServlet("/Rental")
public class RentalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String[] types;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RentalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
			
		Connection con = (Connection) ServletUtils.connectToSqlDatabase();
		
		//the user is coming from the index page, only car type matters
		if (request.getParameter("filter").equals("index")) {
			String carType = request.getParameter("carType");
			
			List<Vehicle> vehicles = (List<Vehicle>) new SqlVehicleDao(con).findByTypeDirect( "\'" + carType +  "\'");
			
			String[] types = new String[1];
			types[0] = carType;
			
			session.setAttribute("vehicles", vehicles);
			session.setAttribute("type", types);
			
		}
		
		//the user is refining their search from /Select.jsp
		if (request.getParameter("filter").equals("panel")) {
			String[] types = request.getParameterValues("type");
			String[] colors = request.getParameterValues("carColor");
			
			String typeQuery = "";
			
			if (types != null) {
				typeQuery = "\'" + types[0] + "\'";
				
				for (int i = 1; i < types.length; i++) {
					typeQuery += " OR category = " + "\'" + types[i] + "\'"; 
				}
			}
			
			String colorQuery = "";
			
			if (colors != null) {
				colorQuery += ") AND ( color = " + "\'" + colors[0] + "\'";
				
				for (int i = 1; i < colors.length; i++) {
					colorQuery += " OR color = " + "\'" + colors[i] + "\'";  
				}
				
				session.setAttribute("carColor", colors);
			}
			else {
				session.setAttribute("carColor", new String[1]);
			}
			
			String query = typeQuery + colorQuery;
			
			List<Vehicle> vehicles = (List<Vehicle>) new SqlVehicleDao(con).findByTypeDirect(typeQuery + colorQuery);
			session.setAttribute("vehicles", vehicles);
			session.setAttribute("type", types);
		}
		
		response.sendRedirect(request.getContextPath() + "/Select.jsp");
	}

	/** 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);		
	}

}
