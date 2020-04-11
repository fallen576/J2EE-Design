package app.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ServletUtils {

	public static Connection connectToSqlDatabase() {
		String dbUrl = "";
		String username = "";
		String password = "";
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(dbUrl, username, password);
		} catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	private ServletUtils() {}
	
}
