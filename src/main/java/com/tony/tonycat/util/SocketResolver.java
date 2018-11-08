package com.tony.tonycat.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * ����socket,��ȡ������Ϣ
* <p>Title: SocketResolver</p>  
* <p>Description: </p>  
* @author tony.tian  
* @date 2018��11��7��
 */
public class SocketResolver {
	private Socket socket;
	public SocketResolver(Socket socket) {
		this.socket = socket;
	}
	/**
	 * ��ȡsocket���ܵ����ַ���(byte��ʱΪ1024)����������������
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
	 * ��ȡuri
	 */
	public String getUri() {
		return null;
	}
	/**
	 * ��ȡ
	 */
}
