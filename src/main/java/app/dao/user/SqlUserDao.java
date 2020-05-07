package app.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.mindrot.jbcrypt.BCrypt;

import app.model.User;

public class SqlUserDao implements UserDao {
	
	private Connection connection;

	public SqlUserDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public long insert(User user) {
		if (this.checkUserExist(user)) {
			return -1;
		}
		String sql = "INSERT INTO user (first_name, last_name, password, email_address, is_admin) VALUES (?, ?, ?, ?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
			//to check password -> boolean auth = BCrypt.checkpw(user.getPassword(), hashed password in db)
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
	
	@Override
	public User checkLogin(String email, String password) {
		boolean accepted = false;
		String sql = "SELECT * from user WHERE email_address = \'" + email + "\'";
		try {
			//PreparedStatement statement = connection.prepareStatement(sql);
			//statement.setString(1, email);
			Statement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery(sql);
			
			if (rs.next()) {
				accepted = BCrypt.checkpw(password, rs.getString("password"));

				if (accepted) {
					return this.mapUser(rs);		
				}
			} 
			
			return null;
			
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return null;
	}	
	
	private boolean checkUserExist(User user) {
		String sql = "SELECT * FROM user where email_address = ?";
		boolean exists = false;
		try {
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getEmailAddress());
			ResultSet rs = statement.executeQuery();
			
			if (rs.next()) {
				exists = true;
			}
			else {
				exists = false;
			}
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return exists;
	}
	
	private User mapUser(ResultSet rs) throws SQLException {
		User user = new User(rs.getString("first_name"), rs.getString("last_name"), rs.getString("password"), rs.getString("email_address"), rs.getInt("is_admin") == 1 ? true : false);
		user.setId(rs.getInt("id"));
		rs.close();
		return user;
	}
}
