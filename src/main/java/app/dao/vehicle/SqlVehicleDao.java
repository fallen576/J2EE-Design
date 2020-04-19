package app.dao.vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	public List<Vehicle> findByTypeDirect(String type) {
		String sql = "SELECT * FROM vehicle WHERE (category = " +  type + ")";
		System.out.println(sql);
		List<Vehicle> vehicles = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			//statement.setString(1, "\'" + type + "\'");
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				vehicles.add(this.mapVehicle(rs));
			}
		} catch (SQLException e) {
			System.out.println("UHH OHHHHHHHHHHHHHHHHHH " + sql);
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
		//vehicle.setType(VehicleCategory.valueOf(rs.getString("category")));
		vehicle.setType(rs.getString("category"));
		vehicle.setImg(rs.getString("img_path"));
		return vehicle;
	}

}
