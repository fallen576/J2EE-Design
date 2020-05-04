package app.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import app.model.Vehicle;

/**
 * Servlet implementation class SelectServlet
 */
@WebServlet("/select")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		
		
		Long carId = Long.parseLong(request.getParameter("carToSelect"));
		
		List<Vehicle> vehicles = (List<Vehicle>) session.getAttribute("vehicles");	
		Vehicle v = null;
		for (Vehicle vehicle : vehicles) {
			if(carId == vehicle.getId()) {
				v = vehicle;
				break;
			}
		}
		
		session.setAttribute("vehicle", v);
		
		response.sendRedirect(request.getContextPath() + "/checkout.jsp");
		
		
	}

}
