package com.xzh.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.xzh.util.PageBean;
import com.xzh.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 流程部署管理controller
 * @author JSiu
 *
 */
@Controller
@RequestMapping("/deploy")
public class DeployController {
	
	@Resource
	private RepositoryService repositoryService;
	
	/**
	 * 上传流程部署文件
	 * @param deployFile
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/deploy")
	public String deploy(@RequestParam("deployFile") MultipartFile deployFile, HttpServletResponse response) throws Exception{
		repositoryService.createDeployment()	//创建部署
						.name(deployFile.getOriginalFilename())	//流程名称
						.addZipInputStream(new ZipInputStream(deployFile.getInputStream()))	//添加zip输入流
						.deploy();	//部署
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	
	@RequestMapping("/list")
	public String list(Integer page, Integer rows, String s_name, HttpServletResponse response) throws Exception{
		if(s_name == null){
			s_name = "";
		}
		PageBean pageBean = new PageBean(page, rows);
		
		List<Deployment> deploymentList = repositoryService.createDeploymentQuery()	//创建流程部署查询
						.orderByDeploymenTime().desc()	//根据部署时间降序排列
						.deploymentNameLike("%"+s_name+"%")	//根据部署名称模糊查询
						.listPage(pageBean.getStart(), pageBean.getPageSize());	//返回带分页的结果集合
		
		long total = repositoryService.createDeploymentQuery().deploymentNameLike("%"+s_name+"%").count();	//获取总记录数
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"resources"});
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(deploymentList, jsonConfig);
		
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		
		return null;
	}
	
	@RequestMapping("/delete")
	public String delete(String ids, HttpServletResponse response) throws Exception{
		String idsStr[] = ids.split(",");
		for (int i = 0; i < idsStr.length; i++) {
			repositoryService.deleteDeployment(idsStr[i], true);
		}
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}

}
