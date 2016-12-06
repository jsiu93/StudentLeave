package com.xzh.service;

import java.util.List;
import java.util.Map;

import com.xzh.entity.Group;

public interface GroupService {

	public List<Group> find(Map<String, Object> map);

	public Group findById(String id);

	public Long getTotal(Map<String, Object> map);

	public int delete(String id);

	public int update(Group group);

	public int add(Group group);
}
