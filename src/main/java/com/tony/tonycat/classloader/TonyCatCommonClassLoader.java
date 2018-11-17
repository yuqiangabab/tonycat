package com.tony.tonycat.classloader;

import java.io.FileNotFoundException;
import java.util.Map;

import com.tony.tonycat.classloader.util.TonyCatClassLoaderUtils;
import com.tony.tonycat.util.TonyCatPathUtils;

/**
 * tonycat公用类加载器
 * 
 * @author tw
 *
 */
public class TonyCatCommonClassLoader extends ClassLoader {

	private String jarPath;
	private Map<String, byte[]> classByteMaps;

	public TonyCatCommonClassLoader() {
		jarPath = TonyCatPathUtils.getTonyCatLibPath();
		classByteMaps = TonyCatClassLoaderUtils.getClassBytesByJarPath(jarPath);
		// for(String key :classByteMaps.keySet()) {
		// System.out.println("key:" + key);
		// }
	}

	@Override
	protected Class<?> findClass(String className) throws ClassNotFoundException {
		byte[] b = null;
		try {
			b = getClassFileBytes(className);
		} catch (Exception e) {
		}
		if(b == null) {
			return null;
		}
		Class<?> defineClass = defineClass(className, b, 0, b.length);
		return defineClass;
	}

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {

		// First, check if the class has already been loaded
		Class c = findLoadedClass(name);
		if (c != null) {
			return c;
		}
		try {
			c = findClass(name);
		} catch (ClassNotFoundException e) {
		}
		if (c != null) {
			return c;
		}
		return super.loadClass(name);
	}

	private byte[] getClassFileBytes(String className) throws Exception {
		byte[] result = classByteMaps.get(className);
		if (result == null) {
			throw new FileNotFoundException(className + "不存在");
		}
		classByteMaps.remove(className);
		return result;
	}
}
