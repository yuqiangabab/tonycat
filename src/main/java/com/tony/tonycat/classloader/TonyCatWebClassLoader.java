package com.tony.tonycat.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * WEB”√
 * @author tw
 *
 */
public class TonyCatWebClassLoader extends TonyCatCommonClassLoader {
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		String path = "D:";
		byte[] b = null;
		try {
			b = getClassFileBytes(path + name + ".class");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Class<?> defineClass = defineClass(name, b, 0, b.length);
		return defineClass;
	}
	 private byte[] getClassFileBytes(String classFile) throws Exception {
	        FileInputStream fis = new FileInputStream(classFile );
	        FileChannel fileC = fis.getChannel();
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        WritableByteChannel outC = Channels.newChannel(baos);
	        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
	        while (true) {
	            int i = fileC.read(buffer);
	            if (i == 0 || i == -1) {
	                break;
	            }
	            buffer.flip();
	            outC.write(buffer);
	            buffer.clear();
	        }
	        fis.close();
	        return baos.toByteArray();
	    }

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		TonyCatWebClassLoader tonyCatWebClassLoader = new TonyCatWebClassLoader();
		Thread.currentThread().setContextClassLoader(tonyCatWebClassLoader);
		Class clazz = 	Class.forName("Test", false, tonyCatWebClassLoader);
//		Test test = new Test();
//		System.out.println(test.getClass().getClassLoader());
//		test.test();
//		Class clazz = Class.forName("Test");
		Object test = clazz.newInstance();
		System.out.println(test.getClass().getClassLoader());
		Method[] methods = clazz.getDeclaredMethods();
		for(Method method : methods) {
			method.invoke(test);
		}
	}
}
