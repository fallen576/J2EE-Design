package app.services.email;

import app.model.Reservation;
import app.model.User;

public interface EmailService {
	
	void send(User user, Reservation reservation);
	
}
