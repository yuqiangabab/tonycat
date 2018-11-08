package com.tony.tonycat.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;
/**
 * web.xml 信息类
* <p>Title: WebInformation</p>  
* <p>Description: </p>  
* @author tony.tian  
* @date 2018年11月7日
 */
public class WebInformation {
	/**
	 * servlet与uri映射map
	 */
	public static Map<String,Servlet> URI_SERVLET_MAPPER = new HashMap<String,Servlet>();
	static {
		Class clazz = null;
		try {
			clazz = Class.forName("com.tonycat.testweb.TestServlet");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			URI_SERVLET_MAPPER.put("/index", (Servlet) clazz.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 通过uri获取servlet(此实现暂不考虑通配符)
	 * <p>Title: getServletByUri</p>  
	 * <p>Description: </p>  
	 * @param uri
	 * @return
	 */
	public static Servlet getServletByUri(String uri) {
		return URI_SERVLET_MAPPER.get(uri);	
	}
}
