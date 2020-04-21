package app.servlet.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import app.model.User;

@WebFilter("/*")
public class SecurityFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession();

		Object userObject = session.getAttribute("user");

		User user = null;
		if (userObject != null) {
			user = (User) userObject;
		}

		if (SecurityUtils.requiresLoggedInUser(request) && user == null) {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		} else if (SecurityUtils.isAdminPage(request) && (user == null || !user.isAdmin())) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User is not authorized to access this page");
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
