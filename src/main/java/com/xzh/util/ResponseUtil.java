package com.xzh.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;



public class ResponseUtil {

	public static void write(HttpServletResponse response,Object o)throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println(o.toString());
		out.flush();
		out.close();
	}
	
	/**
	 * 导出excel
	 * @param response
	 * @param wb
	 * @param fileName
	 * @throws Exception
	 */
//	public static void export(HttpServletResponse response,Workbook wb,String fileName)throws Exception{
//		response.setHeader("Content-Disposition", "attachment;filename="+new String(fileName.getBytes("utf-8"),"iso8859-1"));
//		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
//		OutputStream out=response.getOutputStream();
//		wb.write(out);
//		out.flush();
//		out.close();
//	}
}
