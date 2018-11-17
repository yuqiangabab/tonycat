package com.tony.tonycat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tony.tonycat.exception.BlankRequestException;
import com.tony.tonycat.request.TonyCatRequestProcess;
import com.tony.tonycat.response.TonyCatResponseProcess;
import com.tony.tonycat.web.WebConnecter;
/**
 * 入口类
* <p>Title: TonyCatMain</p>  
* <p>Description: </p>  
* @author tony.tian  
* @date 2018年11月8日
 */
public class TonyCatMain {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket =new ServerSocket(10086);
		while(true) {
			Socket socket = serverSocket.accept();
			ServletRequest request = null;
			try {
				request = TonyCatRequestProcess.ProcessRequest(socket);
			} catch (BlankRequestException e1) {
				e1.printStackTrace();
				continue;
			}
			ServletResponse response = TonyCatResponseProcess.ProcessResponse(socket);
			HttpServletRequest httpServletRequest = (HttpServletRequest)request;
			HttpServletResponse httpServletResponse = (HttpServletResponse)response;
			/**
			 * 执行请求
			 */
			WebConnecter.connect(httpServletRequest, httpServletResponse);
			socket.close();
		}
	}
}
