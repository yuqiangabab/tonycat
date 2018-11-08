package com.tony.tonycat.response;

import java.io.IOException;
import java.net.Socket;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;

import com.tony.tonycat.io.TonyCatOutputStream;

/**
 * 用于将sokect封装为response
 * Title: TonyCatResponseProcess
 * Description:
 * @author tony.tian
 * @date 2018年11月7日
 */
public class TonyCatResponseProcess {
	private static Socket socket;
	public static ServletResponse ProcessResponse(Socket socket) {
		TonyCatResponseProcess.socket = socket;
		TonyCatResponseSetter tcrs = new TonyCatResponseSetter();
		tcrs.setServletoutputStream(getServletOutStream());
		return tcrs;
	}
	/**
	 * 获取输出流
	 * <p>Title: getServletOutStream</p>  
	 * <p>Description: </p>  
	 * @return
	 */
	private static ServletOutputStream getServletOutStream() {
		TonyCatOutputStream tcos = null;
		try {
			tcos = new TonyCatOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tcos;

	}
}
