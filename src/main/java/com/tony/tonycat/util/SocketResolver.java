package com.tony.tonycat.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 解析socket,获取请求信息
* <p>Title: SocketResolver</p>  
* <p>Description: </p>  
* @author tony.tian  
* @date 2018年11月7日
 */
public class SocketResolver {
	private Socket socket;
	public SocketResolver(Socket socket) {
		this.socket = socket;
	}
	/**
	 * 获取socket接受到的字符串(byte暂时为1024)，供其他方法调用
	 * <p>Title: getRequestString</p>  
	 * <p>Description: </p>  
	 * @return
	 */
	private String getRequestString() {
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
	 * 获取uri
	 */
	public String getUri() {
		return null;
	}
	/**
	 * 获取
	 */
}
