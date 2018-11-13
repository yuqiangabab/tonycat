package com.tony.tonycat.web.resolver.entity;

import java.util.List;
/**
 * ServletDom节点实体
* Title: ServletDom 
* Description: 
* @author tony.tian  
* @date 2018年11月13日
 */
public class ServletDom {
	private String servletName;
	private String servletClass;
	private List<String> urlPattern;
	public String getServletName() {
		return servletName;
	}
	public void setServletName(String servletName) {
		this.servletName = servletName;
	}
	public String getServletClass() {
		return servletClass;
	}
	public void setServletClass(String servletClass) {
		this.servletClass = servletClass;
	}
	public List<String> getUrlPattern() {
		return urlPattern;
	}
	public void setUrlPattern(List<String> urlPattern) {
		this.urlPattern = urlPattern;
	}
	@Override
	public String toString() {
		return "ServletDom [servletName=" + servletName + ", servletClass=" + servletClass + ", urlPattern="
				+ urlPattern + "]";
	}
	
}
