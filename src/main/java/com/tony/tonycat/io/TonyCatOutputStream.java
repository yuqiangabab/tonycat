package com.tony.tonycat.io;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
/**
 * 继承ServletOutputStream供response使用
 * 使用适配器模式
* <p>Title: TonyCatOutputStream</p>  
* <p>Description: </p>  
* @author tony.tian  
* @date 2018年11月7日
 */
public class TonyCatOutputStream extends ServletOutputStream {
	private OutputStream os;
	public TonyCatOutputStream(OutputStream os) {
		this.os = os;
	}
	
	@Override
	public void write(byte[] b) throws IOException {
		os.write(b);
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		os.write(b, off, len);
	}

	@Override
	public void flush() throws IOException {
		os.flush();
	}

	@Override
	public void close() throws IOException {
		os.close();
	}

	@Override
	public boolean isReady() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setWriteListener(WriteListener writeListener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void write(int b) throws IOException {
		// TODO Auto-generated method stub

	}
	

}
