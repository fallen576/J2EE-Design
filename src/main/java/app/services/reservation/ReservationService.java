package app.services.reservation;

import app.model.Reservation;
import app.model.User;
import app.model.VehicleCategory;

public interface ReservationService {
	
	void confirmReservation(User user, Reservation reservation, VehicleCategory category);

}
