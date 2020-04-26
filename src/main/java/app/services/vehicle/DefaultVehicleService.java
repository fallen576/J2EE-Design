package app.services.vehicle;

import java.util.List;

import app.dao.vehicle.VehicleDao;
import app.model.Vehicle;

public class DefaultVehicleService implements VehicleService {
	
	private VehicleDao vehicleDao;
	
	public DefaultVehicleService(VehicleDao vehicleDao) {
		this.vehicleDao = vehicleDao;
	}

	@Override
	public List<Vehicle> filterVehicles(String[] types, String[] colors) {
		StringBuilder categoryFilter = new StringBuilder();
		StringBuilder colorFilter = new StringBuilder();
		
		if (types != null && types.length > 0) {
			categoryFilter.append("(category = \'" + types[0] + "\'");
			
			for (int i = 1; i < types.length; i++) {
				categoryFilter.append(" OR category = " + "\'" + types[i] + "\'"); 
			}
			categoryFilter.append(")");
		}
		
		if (colors != null && colors.length > 0) {
			if (types != null && types.length > 0) {
				colorFilter.append(" AND ");
			}
			
			colorFilter.append("(color = " + "\'" + colors[0] + "\'");
			for (int i = 1; i < colors.length; i++) {
				colorFilter.append(" OR color = " + "\'" + colors[i] + "\'");  
			}
			colorFilter.append(")");
		}
		return vehicleDao.findWithFilter(categoryFilter.append(colorFilter.toString()).toString());
	}

	@Override
	public List<Vehicle> getAllVehicles() {
		return vehicleDao.findWithFilter(null);
	}

	@Override
	public Vehicle insert(Vehicle vehicle) {
		if (vehicle.isValid()) {
			long id = vehicleDao.insert(vehicle);
			vehicle.setId(id);
		}
		return vehicle;
	}

	@Override
	public void update(Vehicle vehicle) {
		if (vehicle.isValid()) {
			vehicleDao.update(vehicle);
		}
	}

	@Override
	public void delete(long vehicleId) {
		vehicleDao.delete(vehicleId);
	}

}
