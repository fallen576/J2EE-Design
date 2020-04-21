package app.services.admin;

import app.model.Vehicle;

public interface AdminService {
	
	Vehicle insertVehicle(Vehicle vehicle);
	
	void updateVehicle(Vehicle vehicle);
	
	void deleteVehicle(long vehicleId);

}
