package com.xzh.dao;

import java.util.List;
import java.util.Map;

import com.xzh.entity.User;

public interface UserDao {
	
	public User findById(String id);
	
	public List<User> find(Map<String, Object> map);
	
	public Long getTotal(Map<String, Object> map );
	
	int delete(String id);
	
	int update(User user);
	
	int add(User user);

}
