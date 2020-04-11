package app.services.admin;

import app.dao.admin.AdminDao;

public class DefaultAdminService implements AdminService {
	
	private AdminDao adminDao;

	public DefaultAdminService(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

}
