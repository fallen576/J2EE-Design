package app.dao.vehicle;

import java.util.List;

import app.model.Vehicle;
import app.model.VehicleCategory;

public interface VehicleDao {

	List<Vehicle> findByType(VehicleCategory type);

}
