package app.utils;

public class StringUtils {

	public static boolean isNotEmpty(String value) {
		return value != null && !value.trim().equals("");
	}
	
	public static boolean isEmpty(String value) {
		return value == null || value.trim().equals("");
	}
	
}
