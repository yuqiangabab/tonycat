package com.tony.tonycat.io;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
/**
 * 继承ServletInputStream供request使用
 * 使用适配器模式
* <p>Title: TonyCatInputStream</p>  
* <p>Description: </p>  
* @author tony.tian  
* @date 2018年11月7日
 */
public class TonyCatInputStream extends ServletInputStream {
	private InputStream is;
	public TonyCatInputStream(InputStream is) {
		this.is = is;
	}
	
	@Override
	public int read(byte[] b) throws IOException {
		return is.read(b);
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		return is.read(b, off, len);
	}

	@Override
	public long skip(long n) throws IOException {
		return is.skip(n);
	}

	@Override
	public int available() throws IOException {
		return is.available();
	}

	@Override
	public void close() throws IOException {
		is.close();
	}

	@Override
	public synchronized void mark(int readlimit) {
		is.mark(readlimit);
	}

	@Override
	public synchronized void reset() throws IOException {
		is.reset();
	}

	@Override
	public boolean markSupported() {
		return is.markSupported();
	}
	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isReady() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setReadListener(ReadListener readListener) {
		// TODO Auto-generated method stub

	}

	@Override
	public int read() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
