package app.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ServletUtils {

	public static Connection connectToSqlDatabase() {
		String dbUrl = "jdbc:mysql://sql9.freemysqlhosting.net:3306/sql9333722";
		String username = "sql9333722";
		String password = "QF3TzjpwN2";
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
