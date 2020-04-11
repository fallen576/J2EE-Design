package app.services.user;

import app.model.User;

public interface UserService {

	User insert(String name, String emailAddress);

	void update(User user);

}
