package com.tony.tonycat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.tony.tonycat.exception.BlankRequestException;
import com.tony.tonycat.request.TonyCatRequestProcess;
import com.tony.tonycat.response.TonyCatResponseProcess;
import com.tony.tonycat.web.WebInformation;
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
			Servlet servlet = WebInformation.getServletByUri(httpServletRequest.getRequestURI());
			try {
				servlet.service(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			socket.close();
		}
	}
}
