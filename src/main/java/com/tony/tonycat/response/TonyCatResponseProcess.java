package com.tony.tonycat.response;

import java.io.IOException;
import java.net.Socket;

import javax.servlet.ServletResponse;

import com.tony.tonycat.io.TonyCatOutputStream;
/**
 * ���ڽ�sokect��װΪresponse
* <p>Title: TonyCatResponseProcess</p>  
* <p>Description: </p>  
* @author tony.tian  
* @date 2018��11��7��
 */
public class TonyCatResponseProcess {

	public static ServletResponse ProcessResponse(Socket socket) {
		TonyCatOutputStream tcos = null;
		try {
			 tcos = new TonyCatOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		ServletResponse servletResponse = new TonyCatResponse(tcos); 
		return servletResponse;
	}
}
