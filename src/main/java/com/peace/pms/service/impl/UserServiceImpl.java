package com.peace.pms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peace.pms.dao.UserDao;
import com.peace.pms.entity.User;
import com.peace.pms.service.UserService;

@Service
public class UserServiceImpl implements UserService  {

	@Autowired
	private UserDao userDao;
	
	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return userDao.login(user);
	}
	
	@Override
	public List<User> getall(){
		return userDao.getall();
	}
	

	@Override
	public int delete(int id) {
		return this.userDao.delete(id); 
	}
	
	@Override
	public List<User> getUserListByCondition(User user)
	{
		return this.userDao.getUserListByCondition(user); 
	}
}
