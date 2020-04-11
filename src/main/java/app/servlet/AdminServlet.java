package app.servlet;

import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.dao.admin.AdminDao;
import app.dao.admin.SqlAdminDao;
import app.dao.user.SqlUserDao;
import app.dao.user.UserDao;
import app.services.admin.AdminService;
import app.services.admin.DefaultAdminService;
import app.services.user.DefaultUserService;
import app.services.user.UserService;
import app.utils.ServletUtils;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private AdminService adminService;
	private UserService userService;
	
	public void init() throws ServletException {
		Connection connection = ServletUtils.connectToSqlDatabase();
		AdminDao adminDao = new SqlAdminDao(connection);
		UserDao userDao = new SqlUserDao(connection);

		adminService = new DefaultAdminService(adminDao);
		userService = new DefaultUserService(userDao);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
	}
	
	public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
	}
	
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
	}

}
