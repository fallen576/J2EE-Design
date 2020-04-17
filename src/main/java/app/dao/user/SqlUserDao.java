package app.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.model.User;

public class SqlUserDao implements UserDao {
	
	private Connection connection;

	public SqlUserDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public long insert(User user) {
		String sql = "INSERT INTO user (first_name, last_name, password, email_address, is_admin) VALUES (?, ?, ?, ?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getEmailAddress());
			statement.setBoolean(5, user.isAdmin());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	@Override
	public void update(User user) {
		String sql = "UPDATE user SET first_name = ?, last_name = ?, email_address = ? WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getEmailAddress());
			statement.setLong(4, user.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
