package com.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.dao.UserDao;
import com.db.model.UserInfo;

@Service
public class UserServiceImpl implements UserService {
	UserDao  userDao;
	
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

	public UserInfo getCurrUserInfo() {
		return userDao.getCurrUserInfo();
	}

}
