package com.xzh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xzh.dao.UserDao;
import com.xzh.entity.User;
import com.xzh.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;
	
	public User findById(String id) {
		return userDao.findById(id);
	}

}
