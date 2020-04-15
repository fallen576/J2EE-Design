package app.services.user;

import app.model.User;

public interface UserService {

	User insert(String firstName, String lastName, String billingAddress, String emailAddress);

	void update(User user);

}
