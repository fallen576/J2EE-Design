package app.dao.reservation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.model.Reservation;

public class SqlReservationDao implements ReservationDao {

	private Connection connection;
	
	public SqlReservationDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public long insert(Reservation reservation) {
		String sql = "INSERT INTO reservation (confirmation_number, vehicle_id, pickup_location, dropoff_location, "
				+ "pickup_date, dropoff_date) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, reservation.getConfirmationNumber());
			statement.setLong(2, reservation.getVehicleId());
			statement.setString(3, reservation.getPickupLocation());
			statement.setString(4, reservation.getDropoffLocation());
			statement.setDate(5, new Date(reservation.getPickupDate().getTime()));
			statement.setDate(6, new Date(reservation.getDropoffDate().getTime()));
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
	public List<String> getConfirmationNumbers() {
		String sql = "SELECT confirmation_number FROM reservation";
		List<String> confirmationNumbers = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				confirmationNumbers.add(rs.getString("confirmation_number"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return confirmationNumbers;
	}

	@Override
	public List<Reservation> findByUserId(long userId) {
		String sql = "SELECT * FROM reservation WHERE reservation_id IN "
				+ "(SELECT reservation_id FROM user_reservation WHERE user_id = ?)";
		List<Reservation> reservations = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, userId);
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				reservations.add(this.mapReservation(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reservations;
	}

	@Override
	public List<Reservation> findByVehicleIds(List<Long> vehicleIds) {
		StringBuilder params = new StringBuilder();
		vehicleIds.forEach(id -> params.append("?,"));
		params.deleteCharAt(params.length() - 1);
		String sql = "SELECT * FROM reservation WHERE vehicle_id in (" + params.toString() + ")";
		List<Reservation> reservations = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			for (int i = 0; i < vehicleIds.size(); i++) {
				statement.setLong(i + 1, vehicleIds.get(i));
			}
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				reservations.add(this.mapReservation(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reservations;
	}
	
	private Reservation mapReservation(ResultSet rs) throws SQLException {
		Reservation reservation = new Reservation();
		reservation.setReservationId(rs.getInt("reservation_id"));
		reservation.setConfirmationNumber(rs.getString("confirmation_number"));
		reservation.setVehicleId(rs.getInt("vehicle_id"));
		reservation.setPickupDate(rs.getDate("pickup_date"));
		reservation.setPickupLocation(rs.getString("pickup_location"));
		reservation.setDropoffDate(rs.getDate("dropoff_date"));
		reservation.setDropoffLocation(rs.getString("dropoff_location"));
		return reservation;
	}
	
}
