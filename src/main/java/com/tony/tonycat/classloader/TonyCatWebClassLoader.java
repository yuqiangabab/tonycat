package com.tony.tonycat.classloader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import com.tony.tonycat.classloader.util.TonyCatClassLoaderUtils;
import com.tony.tonycat.util.TonyCatPathUtils;

/**
 * WEB用
 * 
 * @author tw
 *
 */
public class TonyCatWebClassLoader extends ClassLoader {
	private Map<String, byte[]> classByteMaps;
	private String classPath;
	/**
	 * TonyCatCommonClassLoader
	 */
	private ClassLoader parent;
	public TonyCatWebClassLoader(String webName, ClassLoader parentClassLoader) {
		String jarPath = TonyCatPathUtils.getWebLibPath(webName);
		classByteMaps = TonyCatClassLoaderUtils.getClassBytesByJarPath(jarPath);
		classPath = TonyCatPathUtils.getWebClassPath(webName);
		parent = parentClassLoader;

	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] b = null;
		b = this.getClassFileBytes(name);
		if (b == null) {
			throw new ClassNotFoundException(name + "不存在");
		}
		Class<?> defineClass = defineClass(name, b, 0, b.length);
		return defineClass;
	}

	private byte[] getClassFileBytes(String name) {
		byte[] b = null;
		try {
			String path = classPath + File.separator + name.replace(".", File.separator) + ".class";
			b = TonyCatClassLoaderUtils.getClassFileBytesByClassPath(path);
		} catch (Exception e) {
		}
		if (b == null) {
			b = classByteMaps.get(name);
			if (b != null) {
				classByteMaps.remove(name);
			}
		}
		return b;

	}
	/**
	 * 重写loadClass方法， 自己先尝试加载，如果失败就尝试parent加载
	 * Title: loadClass
	 * Description:
	 * @param name
	 * @return
	 * @throws ClassNotFoundException  
	 * @see java.lang.ClassLoader#loadClass(java.lang.String)
	 */
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		Class clazz = null;
			try {
				clazz = super.loadClass(name);
			} catch (Exception e) {
			}
			if(clazz == null) {
				clazz = parent.loadClass(name);
			}
			
		return clazz;

	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		// TonyCatWebClassLoader tonyCatWebClassLoader = new TonyCatWebClassLoader();
		// Thread.currentThread().setContextClassLoader(tonyCatWebClassLoader);
		// Class clazz = Class.forName("Test", false, tonyCatWebClassLoader);
		// Test test = new Test();
		// System.out.println(test.getClass().getClassLoader());
		// test.test();
		// Class clazz = Class.forName("Test");
		// Object test = clazz.newInstance();
		// System.out.println(test.getClass().getClassLoader());
		// Method[] methods = clazz.getDeclaredMethods();
		// for(Method method : methods) {
		// method.invoke(test);
		// }
	}
}
