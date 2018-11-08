package com.tony.tonycat.response;

import java.io.IOException;
import java.net.Socket;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;

import com.tony.tonycat.io.TonyCatOutputStream;

/**
 * ���ڽ�sokect��װΪresponse
 * Title: TonyCatResponseProcess
 * Description:
 * @author tony.tian
 * @date 2018��11��7��
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
	 * ��ȡ�����
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
