package com.tony.tonycat.classloader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;

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
	/**
	 * jar中其他资源缓存，如properties
	 */
	private Map<String, URL> resourceByteMaps;
	public TonyCatCommonClassLoader() {
		jarPath = TonyCatPathUtils.getTonyCatLibPath();
		classByteMaps = TonyCatClassLoaderUtils.getClassBytesByJarPath(jarPath);
		resourceByteMaps = TonyCatClassLoaderUtils.getResourcesByJarPath(jarPath);
	
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
	/**
	 * 打破双亲委派
	 * Title: loadClass
	 * Description:
	 * @param name
	 * @return
	 * @throws ClassNotFoundException  
	 * @see java.lang.ClassLoader#loadClass(java.lang.String)
	 */
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
	/**
	 * 重写此方法 加载jar包中的资源
	 * Title: findResource
	 * Description:
	 * @param name
	 * @return  
	 * @see java.lang.ClassLoader#findResource(java.lang.String)
	 */
	@Override
	protected URL findResource(String name) {
		URL url = resourceByteMaps.get(name);
		if(url != null) {
			return url;
		}
		return super.findResource(name);
	}
	@Override
	protected Enumeration<URL> findResources(String name) throws IOException {
		return super.findResources(name);
	}
}
