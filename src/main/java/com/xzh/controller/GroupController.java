package com.xzh.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xzh.entity.Group;
import com.xzh.service.GroupService;
import com.xzh.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/group")
public class GroupController {

	@Resource
	private GroupService groupService;
	
	/**
	 * 查询所有角色，用于下拉框
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/groupComboList")
	public String groupComboList(HttpServletResponse response) throws Exception{
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", -1);
		jsonObject.put("name", "请选择角色...");
		
		jsonArray.add(jsonObject);
		
		List<Group> groupList = groupService.find(null);
		JSONArray rows = JSONArray.fromObject(groupList); 
		jsonArray.addAll(rows);
		
		ResponseUtil.write(response, jsonArray);
		
		return null;
	}
}
