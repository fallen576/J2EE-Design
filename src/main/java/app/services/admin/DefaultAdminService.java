package app.services.admin;

import app.model.Vehicle;
import app.services.vehicle.VehicleService;

public class DefaultAdminService implements AdminService {
	
	private VehicleService vehicleService;

	public DefaultAdminService(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	@Override
	public Vehicle insertVehicle(Vehicle vehicle) {
		return vehicleService.insert(vehicle);
	}

	@Override
	public void updateVehicle(Vehicle vehicle) {
		vehicleService.update(vehicle);
	}

	@Override
	public void deleteVehicle(long vehicleId) {
		vehicleService.delete(vehicleId);
	}

}
