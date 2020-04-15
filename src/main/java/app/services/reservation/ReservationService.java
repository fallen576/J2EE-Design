package app.services.reservation;

import app.model.Reservation;
import app.model.User;
import app.model.VehicleType;

public interface ReservationService {
	
	void confirmReservation(User user, Reservation reservation, VehicleType vehicleType);

}
