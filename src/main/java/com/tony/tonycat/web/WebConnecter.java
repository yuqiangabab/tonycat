package com.tony.tonycat.web;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.tony.tonycat.util.TonyCatPathUtils;
import com.tony.tonycat.util.TonyCollectionUtils;
import com.tony.tonycat.web.resolver.WebXMLResolver;
import com.tony.tonycat.web.resolver.entity.WebXmlDom;

/**
 * web连接器
* Title: WebConnecter 
* Description: 服务器与web应用连接用 
* @author tony.tian  
* @date 2018年11月13日
 */
public class WebConnecter {
	/**
	 * web应用集合,key为应用名
	 */
	private static Map<String,WebApplication> webApplicationMaps = TonyCollectionUtils.getEmptyMap();
	static {
		init();
	}
	/**
	 * 初始化web应用集合
	 * Title: init 
	 * Description:
	 */
	public static void init(){
		File webapps = new File(TonyCatPathUtils.getWebappsPath());
		File[] webs = webapps.listFiles();
		for(File web : webs) {
			WebApplication webApplication = new WebApplication(web.getName());
			webApplicationMaps.put("/" + web.getName(),webApplication);
		}
	}
	/**
	 * 将请求/响应对象交给web应用处理
	 * Title: connect 
	 * Description:   
	 * @param request
	 * @param response
	 */
	public static void connect(HttpServletRequest request,HttpServletResponse response) {
		String requestAppcationPath = request.getContextPath();
//		System.out.println("requestAppcationPath:" + requestAppcationPath);
		WebApplication webApplication = webApplicationMaps.get(requestAppcationPath);
//		System.out.println("webApplication:" + webApplication);
//		System.out.println("webApplicationMaps:" + webApplicationMaps);
		webApplication.excute(request, response);
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(new File("").getCanonicalPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
