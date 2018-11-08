package com.tony.tonycat.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;
/**
 * web.xml ��Ϣ��
* <p>Title: WebInformation</p>  
* <p>Description: </p>  
* @author tony.tian  
* @date 2018��11��7��
 */
public class WebInformation {
	/**
	 * servlet��uriӳ��map
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
	 * ͨ��uri��ȡservlet(��ʵ���ݲ�����ͨ���)
	 * <p>Title: getServletByUri</p>  
	 * <p>Description: </p>  
	 * @param uri
	 * @return
	 */
	public static Servlet getServletByUri(String uri) {
		return URI_SERVLET_MAPPER.get(uri);	
	}
}
