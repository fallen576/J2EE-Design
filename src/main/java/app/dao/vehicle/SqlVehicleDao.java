package app.dao.vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.model.Vehicle;
import app.model.VehicleType;

public class SqlVehicleDao implements VehicleDao {
	
	private Connection connection;
	
	public SqlVehicleDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Vehicle> findByType(VehicleType type) {
		String sql = "SELECT * FROM vehicle WHERE vehicle_type = ?";
		List<Vehicle> vehicles = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, type.name());
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				vehicles.add(this.mapVehicle(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vehicles;
	}

	private Vehicle mapVehicle(ResultSet rs) throws SQLException {
		Vehicle vehicle = new Vehicle();
		vehicle.setId(rs.getInt("id"));
		vehicle.setColor(rs.getString("color"));
		vehicle.setMake(rs.getString("make"));
		vehicle.setModel(rs.getString("model"));
		vehicle.setType(VehicleType.valueOf(rs.getString("type")));
		return vehicle;
	}

}
