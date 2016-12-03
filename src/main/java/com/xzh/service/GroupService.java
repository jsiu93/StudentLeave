package com.xzh.service;

import java.util.List;
import java.util.Map;

import com.xzh.entity.Group;

public interface GroupService {

	public List<Group> find(Map<String, Object> map);
}
