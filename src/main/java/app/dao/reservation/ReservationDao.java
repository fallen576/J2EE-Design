package app.dao.reservation;

import java.util.List;

import app.model.Reservation;

public interface ReservationDao {
	
	long insert(Reservation reservation);
	
	List<String> getConfirmationNumbers();
	
	List<Reservation> findByUserId(long userId);

	List<Reservation> findByVehicleIds(List<Long> vehicleIds);

}
