package com.tony.tonycat.request;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.tony.tonycat.exception.BlankRequestException;
import com.tony.tonycat.io.TonyCatInputStream;
import com.tony.tonycat.util.RequestResolver;
/**
 * 用于将sokect封装为request
* <p>Title: TonyCatRequestProcess</p>  
* <p>Description: </p>  
* @author tony.tian  
* @date 2018年11月7日
 */
public class TonyCatRequestProcess {
	private static Socket socket;
	private static RequestResolver requestResolver;
	public static ServletRequest ProcessRequest(Socket socket) throws BlankRequestException {
		TonyCatRequestProcess.socket = socket;
		TonyCatRequestProcess.requestResolver = new RequestResolver(getRequestString());
		TonyCatRequestSetter tonyCatRequestSetter = new TonyCatRequestSetter();
		tonyCatRequestSetter.setServletInputStream(getServletInputStream());
		tonyCatRequestSetter.setParameterMap(getParameterMap());
		tonyCatRequestSetter.setMethod(getRequestMethod());
		tonyCatRequestSetter.setRequestURI(getUri());
		tonyCatRequestSetter.setContextPath(getContextPath());
		tonyCatRequestSetter.setServletPath(getServletPath());
		return tonyCatRequestSetter;
	}
	/**
	 * 获取socket接受到的字符串(byte暂时为1024)，供其他方法调用
	 * <p>Title: getRequestString</p>  
	 * <p>Description: </p>  
	 * @return
	 * @throws BlankRequestException 
	 */
	private static String getRequestString() throws BlankRequestException {
		byte[] bt = new byte[200];
		InputStream is =null;
		BufferedInputStream bis = null;
		try {
			is = socket.getInputStream();
			bis = new BufferedInputStream(is);
			bis.read(bt);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String result = new String(bt);
		if(StringUtils.isBlank(result.trim())) {
			try {
				is.close();
				bis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			throw new BlankRequestException();
		}
		return result;
	}
	/**
	 * 从socket中获取输入流并转换成ServletInputStream
	 * <p>Title: getServletInputStream</p>  
	 * <p>Description: </p>  
	 * @param socket
	 * @return
	 */
	private static ServletInputStream getServletInputStream() {
		//将inputstream转换成servletInputStream
				TonyCatInputStream tcis = null;
				try {
					tcis = new TonyCatInputStream(socket.getInputStream());
				} catch (IOException e) {
					e.printStackTrace();
				}
		return tcis;
	}
	/**
	 * 从socket中解析出请求参数并封装成map
	 * <p>Title: getParameterMap</p>  
	 * <p>Description: </p>  
	 * @param socket
	 * @return
	 */
	private static Map<String,String[]> getParameterMap(){
		return requestResolver.getParameterMap();
	}
	/**
	 * 获取uri
	 */
	public static String getUri() {
		return requestResolver.getUri();
	}
	/**
	 * 获取请求方法
	 * <p>Title: getRequestMethod</p>  
	 * <p>Description: </p>  
	 * @return
	 */
	public static String getRequestMethod() {
		return requestResolver.getRequestMethod();
	}
	/**
	 * 获取应用路径
	 * Title: getContextPath 
	 * Description:   
	 * @return
	 */
	public static String getContextPath() {
		return requestResolver.getContextPath();
	}
	/**
	 * 获取servletpath
	 */
	public static String getServletPath() {
		return requestResolver.getServletPath();
	}
	/**
	 * 获取
	 */
	public static void main(String[] args) {
		String str = "GET /index HTTP/1.1 \r\n" + 
				"User-Agent: Opera/9.80 (Windows NT 6.1; U; Edition IBIS; zh-cn) Presto/2.6.30 Version/10.63 \r\n" + 
				"Host: localhost:81 \r\n" + 
				"Accept: text/html, application/xml;q=0.9, application/xhtml+xml, image/png, image/jpeg, image/gif, image/x-xbitmap, */*;q=0.1 \r\n" + 
				"Accept-Language: zh-CN,zh;q=0.9,en;q=0.8 \r\n" + 
				"Accept-Charset: iso-8859-1, utf-8, utf-16, *;q=0.1 \r\n" + 
				"Accept-Encoding: deflate, gzip, x-gzip, identity, *;q=0 \r\n" + 
				"Connection: Keep-Alive ";
	}
}
