package com.xzh.dao;

import java.util.Map;

import com.xzh.entity.MemberShip;

/**
 * 用户角色关联dao
 * @author JSiu
 *
 */
public interface MemberShipDao {

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
