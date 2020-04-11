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
		String sql = "INSERT INTO user (name, email_address) VALUES (?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getName());
			statement.setString(2, user.getEmailAddress());
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
		String sql = "UPDATE user SET name = ?, email_address = ? WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getName());
			statement.setString(2, user.getEmailAddress());
			statement.setLong(3, user.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
