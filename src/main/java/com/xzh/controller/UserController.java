package com.xzh.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Maps;
import com.xzh.entity.MemberShip;
import com.xzh.entity.User;
import com.xzh.service.MemberShipService;
import com.xzh.service.UserService;
import com.xzh.util.PageBean;
import com.xzh.util.ResponseUtil;
import com.xzh.util.StringUtil;

import net.sf.json.JSONArray;
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
	
	
	/**
	 * 分页查询用户列表
	 * @param page
	 * @param rows
	 * @param s_user
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(Integer page, Integer rows, User s_user, HttpServletResponse response) throws Exception{
		PageBean pageBean = new PageBean(page, rows);
		Map<String, Object> map = Maps.newHashMap();
		map.put("id", StringUtil.formatLike(s_user.getId()));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<User> userList = userService.find(map);
		Long total = userService.getTotal(map);
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(userList);
		
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		
		
		return null;
	}
	
	/**
	 * 添加或修改用户
	 * @param user
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	public String save(User user, HttpServletResponse response, Integer flag) throws Exception{
		int resultTotal = 0;
		if(flag == 1){
			resultTotal = userService.add(user);
		} else {
			resultTotal = userService.update(user);
		}
		
		JSONObject result = new JSONObject();
		if(resultTotal > 0){
			result.put("success", true);
		}else {
			result.put("success", false);
		}
		ResponseUtil.write(response, result);
		
		return null;
	}
	
	/**
	 * 判断用户是否存在
	 * @param userName
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/existUserName")
	public String existUserName(String userName, HttpServletResponse response) throws Exception{
		JSONObject result = new JSONObject();
		if(userService.findById(userName) == null){
			result.put("exist", false);
		} else {
			result.put("exist", true);
		}
		ResponseUtil.write(response, result);
		
		return null;
	}
	
	@RequestMapping("/delete")
	public String delete(String ids, HttpServletResponse response) throws Exception{
		String idsStr[] = ids.split(",");
		for (int i = 0; i < idsStr.length; i++) {
			userService.delete(idsStr[i]);
		}
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	
}
