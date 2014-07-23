package com.alagezia37.archivedocuments.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.alagezia37.archivedocuments.model.User;
import com.alagezia37.archivedocuments.model.dao.UsersDAO;

@Service("usersService")
public class UsersService {
	private UsersDAO usersDao;
	
	@Autowired
	public void setUsersDao(UsersDAO usersDao) {
		this.usersDao = usersDao;
	}
	
	@Secured("ROLE_ADMIN")
	public List<User> getUsers() {
		return usersDao.getUsers();
	}
	
	public boolean create(User user) {
		return usersDao.create(user);
	}
	
	public boolean login(User user) {
		return validatePassword(user.getPassport(), user.getPassword());
	}
	
	public boolean validatePassword(String passport, String password) {
		boolean correct = false;
		User user = usersDao.getUser(passport);
		correct = !(user == null) ? user.validatePassword(password) : false;
		return correct;
	}

	public boolean exists(String passport) {
		return usersDao.exists(passport);
	}
}