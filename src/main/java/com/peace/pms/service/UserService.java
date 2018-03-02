package com.peace.pms.service;

import java.util.List;

import com.peace.pms.entity.User;


public interface UserService {

	public User login(User user);
	public List<User> getall();
	public int delete(int id);
	public List<User> getUserListByCondition(User user);
}
