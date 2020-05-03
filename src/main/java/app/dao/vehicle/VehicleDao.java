package app.dao.vehicle;

import java.util.List;

import app.model.Vehicle;
import app.model.VehicleCategory;

public interface VehicleDao {

	List<Vehicle> findByType(VehicleCategory type);

	List<Vehicle> findWithFilter(String filter);
	
	List<Vehicle> findById(String ids);
	
	long insert(Vehicle vehicle);
	
	void update(Vehicle vehicle);
	
	void delete(long vehicleId);

}
