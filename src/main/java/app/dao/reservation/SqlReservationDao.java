package app.dao.reservation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import app.model.Reservation;
import app.model.Vehicle;
import app.model.VehicleCategory;

public class SqlReservationDao implements ReservationDao {

	private Connection connection;
	
	public SqlReservationDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public long insert(Reservation reservation) {
		System.out.println("reservation location: " + reservation.getLocation());
		String sql = "INSERT INTO reservation (confirmation_number, vehicle_id, location, "
				+ "pickup_date, dropoff_date, paid) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, reservation.getConfirmationNumber());
			statement.setLong(2, reservation.getVehicle().getId());
			statement.setString(3, reservation.getLocation());
			statement.setDate(4, new Date(reservation.getPickupDate().getTime()));
			statement.setDate(5, new Date(reservation.getDropoffDate().getTime()));
			statement.setBoolean(6, true);
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
		String sql = "SELECT * FROM reservation res INNER JOIN "
				+ "user_reservation ur ON res.id = ur.reservation_id LEFT JOIN "
				+ "vehicle v ON res.vehicle_id = v.id WHERE ur.user_id = ?";
		List<Reservation> reservations = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, userId);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				Reservation r = this.mapReservation(rs);
				r.setVehicle(this.mapVehicle(rs));
				reservations.add(r);
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
		String sql = "SELECT r.*, v.* FROM reservation r "
				+ "LEFT JOIN vehicle v ON r.vehicle_id = v.id WHERE vehicle_id in (" + params.toString() + ")";
		List<Reservation> reservations = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			for (int i = 0; i < vehicleIds.size(); i++) {
				statement.setLong(i + 1, vehicleIds.get(i));
			}
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				Reservation reservation = this.mapReservation(rs);
				reservation.setVehicle(this.mapVehicle(rs));
				reservations.add(reservation);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reservations;
	}
	
	@Override
	public void update(Reservation reservation) {
		String sql = "UPDATE reservation SET location = ?, pickup_date = ?, dropoff_date = ? WHERE id = ?";
		try {
			LocalDate pickup = reservation.getPickupDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate dropoff = reservation.getDropoffDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, reservation.getLocation());
			statement.setString(2, pickup.toString());
			statement.setString(3, dropoff.toString());
			statement.setLong(4, reservation.getReservationId());
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void cancel(Long reservationId) {
		String sql = "DELETE from reservation WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, reservationId);
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private Reservation mapReservation(ResultSet rs) throws SQLException {
		Reservation reservation = new Reservation();
		reservation.setReservationId(rs.getInt("id"));
		reservation.setConfirmationNumber(rs.getString("confirmation_number"));
		reservation.setLocation(rs.getString("location"));
		reservation.setPickupDate(rs.getDate("pickup_date"));
		reservation.setDropoffDate(rs.getDate("dropoff_date"));
		return reservation;
	}
	
	private Vehicle mapVehicle(ResultSet rs) throws SQLException {
		Vehicle vehicle = new Vehicle();
		vehicle.setId(rs.getInt("id"));
		vehicle.setColor(rs.getString("color"));
		vehicle.setMake(rs.getString("make"));
		vehicle.setModel(rs.getString("model")); 
		vehicle.setCategory(VehicleCategory.valueOf(rs.getString("category")));
		vehicle.setBase64Img(rs.getString("img_base64"));
		vehicle.setCostPerDay(rs.getBigDecimal("cost_per_day").doubleValue());
		return vehicle;
	}

	
	
}
