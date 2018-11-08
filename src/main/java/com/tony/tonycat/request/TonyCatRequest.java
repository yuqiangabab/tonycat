package com.tony.tonycat.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;

import com.tony.tonycat.util.EnumerationAdapter;
/**
 * 实现request接口
* <p>Title: TonyCatRequest</p>  
* <p>Description: </p>  
* @author tony.tian  
* @date 2018年11月7日
 */
public class TonyCatRequest implements HttpServletRequest {
	protected Map<String,Object> attributeMap = new HashMap<String,Object>();
	protected String characterEncoding;
	protected String contentLength;
	protected String contentType;
	protected ServletInputStream servletInputStream;
	protected Map<String,String[]> parameterMap = new HashMap<String,String[]>();
	protected String protocol;
	protected String scheme;
	protected String serverName;
	protected int serverPort;
	protected BufferedReader reader;
	protected String remoteAddr;
	protected String remoteHost;
	protected Locale locale;
	protected boolean secure;
	protected RequestDispatcher  requestDispatcher;
	protected String realPath;
	protected String remotePort;
	protected String localName;
	protected String localAddr;
	protected int localPort;
	protected ServletContext servletContext;
	protected AsyncContext  asyncContext;
	protected DispatcherType  dispatcherType;
	protected String authType;
	protected  Cookie[] cookies;
	protected Map<String,Object> headers;
	protected String method;
	protected String pathInfo;
	protected String pathTranslated;
	protected String contextPath;
	protected String queryString;
	protected String remoteUser;
	protected Principal principal;
	protected String requestedSessionId;
	protected String requestURI;
	protected String requestURL;
	protected String servletPath;
	protected HttpSession httpSession;
	protected Collection<Part>  parts;
	/**
	 * 通过名称获取单个属性值
	 * <p>Title: getAttribute</p>  
	 * <p>Description: </p>  
	 * @param name
	 * @return  
	 * @see javax.servlet.ServletRequest#getAttribute(java.lang.String)
	 */
	@Override
	public Object getAttribute(String name) {
		return attributeMap.get(name);
	}
	/**
	 * 获取属性名集合
	 * <p>Title: getAttributeNames</p>  
	 * <p>Description:用适配器解决属性集类型不匹配 </p>  
	 * @return  
	 * @see javax.servlet.ServletRequest#getAttributeNames()
	 */
	@Override
	public Enumeration<String> getAttributeNames() {
		Set<String> nameSets = attributeMap.keySet();
		Iterator<String> it = nameSets.iterator();
		EnumerationAdapter ea = new EnumerationAdapter(it);
		return ea;
	}
	/**
	 * 获取请求编码
	 * <p>Title: getCharacterEncoding</p>  
	 * <p>Description: </p>  
	 * @return  
	 * @see javax.servlet.ServletRequest#getCharacterEncoding()
	 */
	@Override
	public String getCharacterEncoding() {
		return this.characterEncoding;
	}
	@Override
	public void setCharacterEncoding(String env) throws UnsupportedEncodingException {
	}
	@Override
	public int getContentLength() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public long getContentLengthLong() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ServletInputStream getInputStream() throws IOException {
		return this.servletInputStream;
	}
	@Override
	public String getParameter(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Enumeration<String> getParameterNames() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String[] getParameterValues(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Map<String, String[]> getParameterMap() {
		return this.parameterMap;
	}
	@Override
	public String getProtocol() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getScheme() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getServerName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getServerPort() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public BufferedReader getReader() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getRemoteAddr() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getRemoteHost() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setAttribute(String name, Object o) {
		// TODO Auto-generated method stub

	}
	@Override
	public void removeAttribute(String name) {
		// TODO Auto-generated method stub

	}
	@Override
	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Enumeration<Locale> getLocales() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isSecure() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public RequestDispatcher getRequestDispatcher(String path) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getRealPath(String path) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getRemotePort() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getLocalName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getLocalAddr() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getLocalPort() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public AsyncContext startAsync() throws IllegalStateException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse)
			throws IllegalStateException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isAsyncStarted() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isAsyncSupported() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public AsyncContext getAsyncContext() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public DispatcherType getDispatcherType() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getAuthType() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Cookie[] getCookies() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public long getDateHeader(String name) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getHeader(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Enumeration<String> getHeaders(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Enumeration<String> getHeaderNames() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getIntHeader(String name) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getMethod() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getPathInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getPathTranslated() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getContextPath() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getQueryString() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getRemoteUser() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isUserInRole(String role) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Principal getUserPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getRequestedSessionId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getRequestURI() {
		return this.requestURI;
	}
	@Override
	public StringBuffer getRequestURL() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getServletPath() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public HttpSession getSession(boolean create) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public HttpSession getSession() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String changeSessionId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isRequestedSessionIdValid() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isRequestedSessionIdFromCookie() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isRequestedSessionIdFromURL() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isRequestedSessionIdFromUrl() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void login(String username, String password) throws ServletException {
		// TODO Auto-generated method stub

	}
	@Override
	public void logout() throws ServletException {
		// TODO Auto-generated method stub

	}
	@Override
	public Collection<Part> getParts() throws IOException, ServletException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Part getPart(String name) throws IOException, ServletException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T extends HttpUpgradeHandler> T upgrade(Class<T> handlerClass) throws IOException, ServletException {
		// TODO Auto-generated method stub
		return null;
	}

}
