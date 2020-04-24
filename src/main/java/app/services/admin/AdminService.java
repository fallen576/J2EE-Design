package app.services.admin;

import java.util.List;

import app.model.Vehicle;

public interface AdminService {
	
	Vehicle insertVehicle(Vehicle vehicle);
	
	void updateVehicle(Vehicle vehicle);
	
	void deleteVehicle(long vehicleId);

	List<Vehicle> getAllVehicles();

}
