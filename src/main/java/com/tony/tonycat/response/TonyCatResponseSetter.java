package com.tony.tonycat.response;

import java.io.BufferedReader;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
/**
 * 用于设置TonyCatResponse各个属性
* <p>Title: TonyCatResponseSetter</p>  
* <p>Description: </p>  
* @author tony.tian  
* @date 2018年11月7日
 */
public class TonyCatResponseSetter extends TonyCatResponse {

	public void setServletoutputStream(ServletOutputStream servletoutputStream) {
		super.servletoutputStream = servletoutputStream;
	}
	public void setWriter(String writer) {
		super.writer = writer;
	}
	public void setBufferSize(String bufferSize) {
		super.bufferSize = bufferSize;
	}
	public void setCommitted(boolean committed) {
		super.committed = committed;
	}
	public void setCookies(List<Cookie> cookies) {
		super.cookies = cookies;
	}
	public void setHeader(String header) {
		super.header = header;
	}
	public void setHeaders(Collection<String> headers) {
		super.headers = headers;
	}
	public void setHeaderNames(Collection<String> headerNames) {
		super.headerNames = headerNames;
	}
	
}
