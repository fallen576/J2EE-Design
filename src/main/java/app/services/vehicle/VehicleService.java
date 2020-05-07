package app.services.vehicle;

import java.util.Date;
import java.util.List;

import app.model.Vehicle;

public interface VehicleService {
	
	List<Vehicle> filterVehicles(String[] types, String[] colors);
	
	List<Vehicle> filterVehicles(String[] types, String[] colors, String pickup, String dropoff);

	List<Vehicle> getAllVehicles();
	
	Vehicle insert(Vehicle vehicle);
	
	void update(Vehicle vehicle);
	
	void delete(long vehicleId);

}
