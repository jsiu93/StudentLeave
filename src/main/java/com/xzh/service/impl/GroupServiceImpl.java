package com.xzh.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xzh.dao.GroupDao;
import com.xzh.entity.Group;
import com.xzh.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService{

	@Resource
	private GroupDao groupDao;
	
	public List<Group> find(Map<String, Object> map) {
		return groupDao.find(map);
	}

}
