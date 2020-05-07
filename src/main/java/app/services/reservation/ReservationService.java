package app.services.reservation;

import java.util.List;

import app.model.Reservation;
import app.model.User;
import app.model.Vehicle;
import app.model.VehicleCategory;

public interface ReservationService {
	
	Reservation confirmReservation(User user, Reservation reservation, Vehicle possibleVehicle, VehicleCategory category);
	
	List<Reservation> findUserReservations(User user);
	
	void update(Reservation reservation);

}
