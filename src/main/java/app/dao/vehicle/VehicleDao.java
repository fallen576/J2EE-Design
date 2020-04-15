package app.dao.vehicle;

import java.util.List;

import app.model.Vehicle;
import app.model.VehicleType;

public interface VehicleDao {

	List<Vehicle> findByType(VehicleType type);

}
