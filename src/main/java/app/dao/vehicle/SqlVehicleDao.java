package app.dao.vehicle;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.model.Vehicle;
import app.model.VehicleCategory;
import app.utils.StringUtils;

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
		String sql = "SELECT * FROM vehicle";
		
		if (StringUtils.isNotEmpty(filter)) {
			sql += " WHERE " + filter;
		}

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
		String sql = "INSERT INTO vehicle (color, make, model, category, img_base64, cost_per_day) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, vehicle.getColor());
			statement.setString(2, vehicle.getMake());
			statement.setString(3, vehicle.getModel());
			statement.setString(4, vehicle.getCategory().name());
			statement.setString(5, vehicle.getBase64Img());
			statement.setBigDecimal(6, new BigDecimal(vehicle.getCostPerDay()));
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
		String sql = "UPDATE vehicle SET color = ?, make = ?, model = ?, category = ?, img_base64 = ?, cost_per_day = ? "
				+ "WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, vehicle.getColor());
			statement.setString(2, vehicle.getMake());
			statement.setString(3, vehicle.getModel());
			statement.setString(4, vehicle.getCategory().name());
			statement.setString(5, vehicle.getBase64Img());
			statement.setBigDecimal(6, new BigDecimal(vehicle.getCostPerDay()));
			statement.setLong(7, vehicle.getId());
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
		vehicle.setBase64Img(rs.getString("img_base64"));
		vehicle.setCostPerDay(rs.getBigDecimal("cost_per_day").doubleValue());
		return vehicle;
	}

}
