package com.tony.tonycat.request;

import java.io.BufferedReader;
import java.security.Principal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
/**
 * 用于设置TonyCatRequest
* <p>Title: TonyCatRequestSetter</p>  
* <p>Description: </p>  
* @author tony.tian  
* @date 2018年11月7日
 */
public class TonyCatRequestSetter extends TonyCatRequest {
	public void setAttributeMap(Map<String, Object> attributeMap) {
		this.attributeMap = attributeMap;
	}
	public void setContentLength(String contentLength) {
		this.contentLength = contentLength;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public void setServletInputStream(ServletInputStream servletInputStream) {
		this.servletInputStream = servletInputStream;
	}
	public void setParameterMap(Map<String, String[]> parameterMap) {
		this.parameterMap = parameterMap;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}
	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}
	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public void setSecure(boolean secure) {
		this.secure = secure;
	}
	public void setRequestDispatcher(RequestDispatcher requestDispatcher) {
		this.requestDispatcher = requestDispatcher;
	}
	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}
	public void setRemotePort(String remotePort) {
		this.remotePort = remotePort;
	}
	public void setLocalName(String localName) {
		this.localName = localName;
	}
	public void setLocalAddr(String localAddr) {
		this.localAddr = localAddr;
	}
	public void setLocalPort(int localPort) {
		this.localPort = localPort;
	}
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	public void setAsyncContext(AsyncContext asyncContext) {
		this.asyncContext = asyncContext;
	}
	public void setDispatcherType(DispatcherType dispatcherType) {
		this.dispatcherType = dispatcherType;
	}
	public void setAuthType(String authType) {
		this.authType = authType;
	}
	public void setCookies(Cookie[] cookies) {
		this.cookies = cookies;
	}
	public void setHeaders(Map<String, Object> headers) {
		this.headers = headers;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public void setPathInfo(String pathInfo) {
		this.pathInfo = pathInfo;
	}
	public void setPathTranslated(String pathTranslated) {
		this.pathTranslated = pathTranslated;
	}
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	public void setRemoteUser(String remoteUser) {
		this.remoteUser = remoteUser;
	}
	public void setPrincipal(Principal principal) {
		this.principal = principal;
	}
	public void setRequestedSessionId(String requestedSessionId) {
		this.requestedSessionId = requestedSessionId;
	}
	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}
	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}
	public void setServletPath(String servletPath) {
		this.servletPath = servletPath;
	}
	public void setHttpSession(HttpSession httpSession) {
		this.httpSession = httpSession;
	}
	public void setParts(Collection<Part> parts) {
		this.parts = parts;
	}
	
}
