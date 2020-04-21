package app.dao.vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.model.Vehicle;
import app.model.VehicleCategory;

public class SqlVehicleDao implements VehicleDao {
	
	private Connection connection;
	
	public SqlVehicleDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Vehicle> findByType(VehicleCategory type) {
		String sql = "SELECT * FROM vehicle WHERE category = ?";
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
	
	@Override
	public List<Vehicle> findWithFilter(String filter) {
		String sql = "SELECT * FROM vehicle WHERE " +  filter;
		List<Vehicle> vehicles = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				vehicles.add(this.mapVehicle(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vehicles;
	}

	@Override
	public long insert(Vehicle vehicle) {
		String sql = "INSERT INTO vehicle (color, make, model, category, img_path) VALUES (?, ?, ?, ?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, vehicle.getColor());
			statement.setString(2, vehicle.getMake());
			statement.setString(3, vehicle.getModel());
			statement.setString(4, vehicle.getCategory().name());
			statement.setString(5, vehicle.getImg());
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
	public void update(Vehicle vehicle) {
		String sql = "UPDATE vehicle SET color = ?, make = ?, model = ?, category = ?, img_path = ? WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, vehicle.getColor());
			statement.setString(2, vehicle.getMake());
			statement.setString(3, vehicle.getModel());
			statement.setString(4, vehicle.getCategory().name());
			statement.setString(5, vehicle.getImg());
			statement.setLong(6, vehicle.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(long vehicleId) {
		String sql = "DELETE FROM vehicle WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, vehicleId);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Vehicle mapVehicle(ResultSet rs) throws SQLException {
		Vehicle vehicle = new Vehicle();
		vehicle.setId(rs.getInt("id"));
		vehicle.setColor(rs.getString("color"));
		vehicle.setMake(rs.getString("make"));
		vehicle.setModel(rs.getString("model")); 
		vehicle.setCategory(VehicleCategory.valueOf(rs.getString("category")));
		vehicle.setImg(rs.getString("img_path"));
		return vehicle;
	}

}
