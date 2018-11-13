package com.tony.tonycat.web.resolver.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * webxmlʵ��
* Title: WebXmlDom 
* Description: 
* @author tony.tian  
* @date 2018��11��13��
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
