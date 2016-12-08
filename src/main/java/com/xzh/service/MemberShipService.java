package com.xzh.service;

import java.util.Map;

import com.xzh.entity.MemberShip;

public interface MemberShipService {

	/**
	 * 用户登录
	 * @param map
	 * @return
	 */
	public MemberShip login(Map<String, Object> map);
	
	/**
	 * 删除指定用户所有角色
	 * @param userId
	 * @return
	 */
	int deleteAllGroupsByUserId(String userId);
	
	int add(MemberShip memberShip);
}
