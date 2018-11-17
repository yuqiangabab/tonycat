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

public class TonyCatServer {
	
	public void service() throws IOException {
		ServerSocket serverSocket =new ServerSocket(10086);
		while(true) {
			System.out.println("开始接收请求！");
			Socket socket = serverSocket.accept();
			System.out.println("收到请求!");
//			System.out.println("classLoader:" + this.getClass().getClassLoader());
//			System.out.println("classLoaderParent:" + this.getClass().getClassLoader().getParent());
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
