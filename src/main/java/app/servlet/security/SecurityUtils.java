package app.servlet.security;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class SecurityUtils {
	
	private static final List<String> SECURE_PAGES = Arrays.asList("/confirm", "/reservation", "/profile");

	public static boolean requiresLoggedInUser(HttpServletRequest request) {
		String url = request.getServletPath();
		if (SECURE_PAGES.stream().anyMatch(securePage -> url.startsWith(securePage))) {
			return true;
		}
		return false;
	}
	
	public static boolean isAdminPage(HttpServletRequest request) {
		String url = request.getServletPath();
		if (url.startsWith("/admin")) {
			return true;
		}
		return false;
	}

}
