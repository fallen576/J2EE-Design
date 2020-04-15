package app.dao.user.reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlUserReservationDao implements UserReservationDao {
	
	private Connection connection;
	
	public SqlUserReservationDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(long userId, long reservationId) {
		String sql = "INSERT INTO user_reservation (user_id, reservation_id) VALUES (?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, userId);
			statement.setLong(2, reservationId);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remove(long reservationId) {
		String sql = "DELETE FROM user_reservation WHERE reservation_id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, reservationId);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
