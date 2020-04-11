package app.dao.admin;

import java.sql.Connection;

public class SqlAdminDao implements AdminDao {
	
	private Connection connection;

	public SqlAdminDao(Connection connection) {
		this.connection = connection;
	}
	
	

}
