package com.tony.tonycat.classloader;

import java.io.FileNotFoundException;
import java.util.Map;

import com.tony.tonycat.classloader.util.TonyCatClassLoaderUtils;
import com.tony.tonycat.util.TonyCatPathUtils;

/**
 * tonycat公用类加载器
 * @author tw
 *
 */
public class TonyCatCommonClassLoader extends ClassLoader {
	
	private String jarPath;
	private Map<String,byte[]> classByteMaps;
	public TonyCatCommonClassLoader() {
		jarPath = TonyCatPathUtils.getTonyCatLibPath();
		classByteMaps = TonyCatClassLoaderUtils.getClassBytesByJarPath(jarPath);
	}
	
    
	@Override
	protected Class<?> findClass(String className) throws ClassNotFoundException {
		byte[] b = null;
		try {
			b = getClassFileBytes(className);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Class<?> defineClass = defineClass(className, b, 0, b.length);
		return defineClass;
	}
	 private byte[] getClassFileBytes(String className) throws Exception {
		 byte[] result = classByteMaps.get(className);
		 if(result == null) {
			 throw new FileNotFoundException(className + "不存在");
		 }
		 classByteMaps.remove(className);
		 return result;
	    }
}
