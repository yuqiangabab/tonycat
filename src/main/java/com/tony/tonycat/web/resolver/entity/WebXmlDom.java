package com.tony.tonycat.web.resolver.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * webxml实体
* Title: WebXmlDom 
* Description: 
* @author tony.tian  
* @date 2018年11月13日
 */
public class WebXmlDom {
	private List<ServletDom> servletDoms = new ArrayList<ServletDom>();

	public List<ServletDom> getServletDoms() {
		return servletDoms;
	}

	public void setServletDoms(List<ServletDom> servletDoms) {
		this.servletDoms = servletDoms;
	}

	@Override
	public String toString() {
		return "WebXmlDom [servletDoms=" + servletDoms + "]";
	} 
	
}
