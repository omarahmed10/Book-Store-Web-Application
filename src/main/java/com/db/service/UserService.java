package com.db.service;

import java.util.List;

import com.db.model.UserInfo;

public interface UserService {

	public List<UserInfo> listUsers();

	public void editUser(UserInfo user);

	public void addNewUser(UserInfo user);

}
