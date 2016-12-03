package com.xzh.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Maps;
import com.xzh.entity.MemberShip;
import com.xzh.service.MemberShipService;
import com.xzh.service.UserService;
import com.xzh.util.ResponseUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;
	
	@Resource
	private MemberShipService memberShipService;
	
	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String groupId = request.getParameter("groupId");
		
		Map<String, Object> map = Maps.newHashMap();
		map.put("userName", userName);
		map.put("password", password);
		map.put("groupId", groupId);
		
		MemberShip memberShip = memberShipService.login(map);
		
		JSONObject result = new JSONObject();
		if(memberShip == null){
			result.put("success", false);
			result.put("errorInfo", "用户名或密码错误");
		} else {
			result.put("success", true);
			request.getSession().setAttribute("currentMemberShip", memberShip);
		}
		ResponseUtil.write(response, result);
		return null;
	}
	
}
