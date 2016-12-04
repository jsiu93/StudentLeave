package com.xzh.service.impl;

import java.util.List;
import java.util.Map;

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

	public List<User> find(Map<String, Object> map) {
		return userDao.find(map);
	}

	public Long getTotal(Map<String, Object> map) {
		return userDao.getTotal(map);
	}

	public int delete(String id) {
		return userDao.delete(id);
	}

	public int update(User user) {
		return userDao.update(user);
	}

	public int add(User user) {
		return userDao.add(user);
	}

}
