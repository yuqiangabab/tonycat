package com.tony.tonycat.request;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;

import org.apache.http.client.HttpClient;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import com.tony.tonycat.io.TonyCatInputStream;
/**
 * 用于将sokect封装为request
* <p>Title: TonyCatRequestProcess</p>  
* <p>Description: </p>  
* @author tony.tian  
* @date 2018年11月7日
 */
public class TonyCatRequestProcess {
	private static Socket socket;
	public static ServletRequest ProcessRequest(Socket socket) {
		TonyCatRequestProcess.socket = socket;
		TonyCatRequestSetter tonyCatRequestSetter = new TonyCatRequestSetter();
		tonyCatRequestSetter.setServletInputStream(getServletInputStream());
//		tonyCatRequestSetter.setParameterMap(parameterMap);
		return tonyCatRequestSetter;
	}
	/**
	 * 获取socket接受到的字符串(byte暂时为1024)，供其他方法调用
	 * <p>Title: getRequestString</p>  
	 * <p>Description: </p>  
	 * @return
	 */
	private static String getRequestString() {
		byte[] bt = new byte[1024];
		try {
			InputStream is = socket.getInputStream();
			is.read(bt);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(bt);
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
	private static Map<String,String[]> getParameterMap(Socket socket){
		CloseableHttpClient client = HttpClientBuilder.create().build(); 
//		client.execute(request)
		return null;
		
	}
	
	
	/**
	 * 获取uri
	 */
	public static String getUri() {
		return null;
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
