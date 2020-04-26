package app.dao.user;

import app.model.User;

public interface UserDao {

	long insert(User user);

	void update(User user);
	
	User checkLogin(String email, String password);
	
}
