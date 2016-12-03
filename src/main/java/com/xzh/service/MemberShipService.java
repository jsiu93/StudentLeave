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
}
