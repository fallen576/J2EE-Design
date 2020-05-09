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
		if (user.getId() == -1) {
			user = null;
		}
		return user;
	}
	
	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public User checkLogin(String email, String password) {
		return userDao.checkLogin(email, password);
	}

}
