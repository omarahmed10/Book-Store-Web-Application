package com.db.dao;

import java.util.List;

import com.db.model.UserInfo;

public interface UserDao {

	public List<UserInfo> listUsers();

	public void editUser(UserInfo user);

	public void addNewUser(UserInfo user);

	public UserInfo getCurrUserInfo();
}
