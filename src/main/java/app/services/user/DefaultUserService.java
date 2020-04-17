package app.services.user;

import app.dao.user.UserDao;
import app.model.User;

public class DefaultUserService implements UserService {
	
	private UserDao userDao;
	
	public DefaultUserService(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User insert(String firstName, String lastName, String password, String emailAddress) {
		User user = new User(firstName, lastName, password, emailAddress);
		user.setId(userDao.insert(user));
		return user;
	}
	
	@Override
	public void update(User user) {
		userDao.update(user);
	}

}
