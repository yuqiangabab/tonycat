package com.tony.tonycat.classloader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import com.tony.tonycat.classloader.util.TonyCatClassLoaderUtils;
import com.tony.tonycat.util.TonyCatPathUtils;

/**
 * WEB”√
 * @author tw
 *
 */
public class TonyCatWebClassLoader extends ClassLoader {
	private  Map<String,byte[]> classByteMaps;
	private String classPath;
	public TonyCatWebClassLoader(String webName) {
		String jarPath = TonyCatPathUtils.getWebJspPath(webName);
		classByteMaps = TonyCatClassLoaderUtils.getClassBytesByJarPath(jarPath);
		classPath = TonyCatPathUtils.getWebClassPath(webName);

	}
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] b = null;
		b = this.getClassFileBytes(name);
		if(b == null) {
			throw new ClassNotFoundException(name + "≤ª¥Ê‘⁄");
		}
		Class<?> defineClass = defineClass(name, b, 0, b.length);
		return defineClass;
	}
	private byte[] getClassFileBytes(String name){
		byte[] b = null;
		try {
		 	classPath = classPath + name.replace(".", File.separator) + ".class";
			b = TonyCatClassLoaderUtils.getClassFileBytesByClassPath(classPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(b == null) {
			b = classByteMaps.get(name);
			if(b != null) {
				classByteMaps.remove(name);
			}
		}
		return b;
		
	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
//		TonyCatWebClassLoader tonyCatWebClassLoader = new TonyCatWebClassLoader();
//		Thread.currentThread().setContextClassLoader(tonyCatWebClassLoader);
//		Class clazz = 	Class.forName("Test", false, tonyCatWebClassLoader);
//		Test test = new Test();
//		System.out.println(test.getClass().getClassLoader());
//		test.test();
//		Class clazz = Class.forName("Test");
//		Object test = clazz.newInstance();
//		System.out.println(test.getClass().getClassLoader());
//		Method[] methods = clazz.getDeclaredMethods();
//		for(Method method : methods) {
//			method.invoke(test);
//		}
	}
}
