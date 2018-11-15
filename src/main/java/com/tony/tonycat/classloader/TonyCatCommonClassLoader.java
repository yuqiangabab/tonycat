package com.tony.tonycat.classloader;
/**
 * tonycat公用类加载器
 * @author tw
 *
 */
public class TonyCatCommonClassLoader extends ClassLoader {
//	private static final String 
	
	@Override
	protected Class<?> findClass(String arg0) throws ClassNotFoundException {
		return super.findClass(arg0);
	}
}
