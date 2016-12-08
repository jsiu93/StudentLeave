package com.xzh.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xzh.dao.MemberShipDao;
import com.xzh.entity.MemberShip;
import com.xzh.service.MemberShipService;

@Service
public class MemberShipServiceImpl implements MemberShipService{

	@Resource
	private MemberShipDao memberShipDao;
	
	public MemberShip login(Map<String, Object> map) {
		return memberShipDao.login(map);
	}

	public int deleteAllGroupsByUserId(String userId) {
		return memberShipDao.deleteAllGroupsByUserId(userId);
	}

	public int add(MemberShip memberShip) {
		return memberShipDao.add(memberShip);
	}

}
