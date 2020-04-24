package app.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import app.dao.user.SqlUserDao;
import app.dao.user.UserDao;
import app.model.User;
import app.services.user.DefaultUserService;
import app.services.user.UserService;
//import javax.servlet.http.HttpSession;
import app.utils.ServletUtils;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet("/account")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService;
	private UserDao userDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() throws ServletException {
		Connection connection = ServletUtils.connectToSqlDatabase();
		
		userDao = new SqlUserDao(connection);
		userService = new DefaultUserService(userDao);
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 HttpSession session = request.getSession();

		String createAccount = (String) request.getParameter("createAccount");

		// User is looking to create a new account
		if (createAccount.equals("true")) {
			response.sendRedirect(request.getContextPath() + "/createAccount.jsp");
		} else if (createAccount.equals("done")) { 
			//user created account, now store info in DB and set user session.
			
			String firstName = (String) request.getParameter("firstName");
			String lastName = (String) request.getParameter("lastName");
			String password = (String) request.getParameter("password");
			String email = (String) request.getParameter("email");
			
			User user = new User(firstName, lastName, password, email);
			session.setAttribute("user", user);
			userService.insert(firstName, lastName, password, email);
			
			
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		}
	}

}
