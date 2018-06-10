package com.db.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.dao.UserDao;
import com.db.model.UserInfo;

@Service
public class UserServiceImpl implements UserService {
	UserDao userDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public List<UserInfo> listUsers() {
		return userDao.listUsers();
	}

	public void editUser(UserInfo user) {
		userDao.editUser(user);
	}

	public void addNewUser(UserInfo user) {
		userDao.addNewUser(user);
	}

	public UserInfo getCurrentUserInfo() {
		return userDao.getCurrentUserInfo();
	}

	public void giveCustomerPrivileges(UserInfo user) {
		userDao.giveCustomerPrivileges(user);
	}

	public void giveAdminPrivileges(UserInfo user) {
		userDao.giveAdminPrivileges(user);
	}

	public UserInfo getUser(String userName) throws SQLException {
		return userDao.getUser(userName);
	}

}
