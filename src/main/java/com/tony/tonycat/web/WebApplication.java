package com.tony.tonycat.web;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.SystemUtils;

import com.tony.tonycat.classloader.TonyCatWebClassLoader;
import com.tony.tonycat.util.TonyCatPathUtils;
import com.tony.tonycat.util.TonyCollectionUtils;
import com.tony.tonycat.web.resolver.WebXMLResolver;
import com.tony.tonycat.web.resolver.entity.ServletDom;
import com.tony.tonycat.web.resolver.entity.WebXmlDom;
/**
 * web应用实体
* Title: WebApplication 
* Description: 
* @author tony.tian  
* @date 2018年11月13日
 */
public class WebApplication {
	/**
	 * web应用名
	 */
	private String webApplicationName;
	/**
	 * webxml节点信息
	 */
	private WebXmlDom webXmlDom;
	/**
	 * servlet map,映射不同路径的servlet
	 */
	private Map<String,Servlet> servletMap = TonyCollectionUtils.getEmptyMap();
	/**
	 * 当前应用类加载器
	 */
	private TonyCatWebClassLoader tonyCatWebClassLoader;
	
	public WebApplication(String webApplicationName) {
		String webXmlPath = TonyCatPathUtils.getWebXmlPath(webApplicationName);
		this.webXmlDom  = WebXMLResolver.resolver(webXmlPath);
		tonyCatWebClassLoader = new TonyCatWebClassLoader(webApplicationName,this.getClass().getClassLoader());
	}
	/**
	 * 处理请求/响应
	 * Title: excute 
	 * Description:   
	 * @param request
	 * @param response
	 */
	public void excute(HttpServletRequest request,HttpServletResponse response) {
		String servletPath = request.getServletPath();
		Servlet servlet = this.getServlet(servletPath);
		try {
			servlet.service(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取servlet
	 * Title: getServlet 
	 * Description:   
	 * @param pathName
	 * @return
	 */
	private Servlet getServlet(String pathName) {
		//如果servletMap中有映射，则直接返回
		if(servletMap.containsKey(pathName)) {
			return servletMap.get(pathName);
		}
		//否则从webxml中获取servlet
		List<ServletDom> servletDoms = webXmlDom.getServletDoms();
		//结束标记
		for(ServletDom servletDom : servletDoms) {
			for(String urlPattern :servletDom.getUrlPattern()) {
				//暂不支持通配符
				if(pathName.equals(urlPattern)) {
					try {
						Class clazz = Class.forName(servletDom.getServletClass(),false,tonyCatWebClassLoader);
						Servlet currentServlet = (Servlet)clazz.newInstance();
						servletMap.put(urlPattern, currentServlet);
						return currentServlet;
					} catch (ClassNotFoundException |InstantiationException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return null;
		
	}
	public String getWebApplicationName() {
		return webApplicationName;
	}
	public WebXmlDom getWebXmlDom() {
		return webXmlDom;
	}
	public static void main(String[] args) {
		System.out.println("/index.dhtml".matches("/index.dhtml"));
	}
	@Override
	public String toString() {
		return "WebApplication [webApplicationName=" + webApplicationName + ", webXmlDom=" + webXmlDom + ", servletMap="
				+ servletMap + ", tonyCatWebClassLoader=" + tonyCatWebClassLoader + "]";
	}
	
	
}
