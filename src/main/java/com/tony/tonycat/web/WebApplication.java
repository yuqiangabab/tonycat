package com.tony.tonycat.web;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tony.tonycat.util.TonyCollectionUtils;
import com.tony.tonycat.web.resolver.entity.ServletDom;
import com.tony.tonycat.web.resolver.entity.WebXmlDom;
/**
 * webӦ��ʵ��
* Title: WebApplication 
* Description: 
* @author tony.tian  
* @date 2018��11��13��
 */
public class WebApplication {
	/**
	 * webӦ����
	 */
	private String webApplicationName;
	/**
	 * webxml�ڵ���Ϣ
	 */
	private WebXmlDom webXmlDom;
	/**
	 * servlet map,ӳ�䲻ͬ·����servlet
	 */
	private Map<String,Servlet> servletMap = TonyCollectionUtils.getEmptyMap();
	/**
	 * ��������/��Ӧ
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
	 * ��ȡservlet
	 * Title: getServlet 
	 * Description:   
	 * @param pathName
	 * @return
	 */
	private Servlet getServlet(String pathName) {
		//���servletMap����ӳ�䣬��ֱ�ӷ���
		if(servletMap.containsKey(pathName)) {
			return servletMap.get(pathName);
		}
		//�����webxml�л�ȡservlet
		List<ServletDom> servletDoms = webXmlDom.getServletDoms();
		//�������
		for(ServletDom servletDom : servletDoms) {
			for(String urlPattern :servletDom.getUrlPattern()) {
				//�ݲ�֧��ͨ���
				if(pathName.equals(urlPattern)) {
					try {
						Class clazz = Class.forName(servletDom.getServletClass());
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
	public void setWebApplicationName(String webApplicationName) {
		this.webApplicationName = webApplicationName;
	}
	public WebXmlDom getWebXmlDom() {
		return webXmlDom;
	}
	public void setWebXmlDom(WebXmlDom webXmlDom) {
		this.webXmlDom = webXmlDom;
	}
	public static void main(String[] args) {
		System.out.println("/index.dhtml".matches("/index.dhtml"));
	}
}
