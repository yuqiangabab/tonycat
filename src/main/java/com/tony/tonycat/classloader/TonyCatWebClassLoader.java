package com.tony.tonycat.classloader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.tony.tonycat.classloader.util.TonyCatClassLoaderUtils;
import com.tony.tonycat.util.TonyCatPathUtils;

/**
 * WEB��
 * 
 * @author tw
 *
 */
public class TonyCatWebClassLoader extends ClassLoader {
	/**
	 * jar��class����
	 */
	private Map<String, byte[]> classByteMaps;
	/**
	 * jar��������Դ���棬��properties
	 */
	private Map<String, URL> resourceByteMaps;
	private String classPath;
	/**
	 * TonyCatCommonClassLoader
	 */
	private ClassLoader parent;
	/**
	 * ��ʼ�������������������jar�е�class��resource������
	* Title:
	* Description:
	* @param webName
	* @param parentClassLoader
	 */
	public TonyCatWebClassLoader(String webName, ClassLoader parentClassLoader) {
		String jarPath = TonyCatPathUtils.getWebLibPath(webName);
		classByteMaps = TonyCatClassLoaderUtils.getClassBytesByJarPath(jarPath);
		resourceByteMaps = TonyCatClassLoaderUtils.getResourcesByJarPath(jarPath);
		classPath = TonyCatPathUtils.getWebClassPath(webName);
		parent = parentClassLoader;

	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] b = null;
		b = this.getClassFileBytes(name);
		if (b == null) {
			throw new ClassNotFoundException(name + "������");
		}
		Class<?> defineClass = defineClass(name, b, 0, b.length);
		return defineClass;
	}

	private byte[] getClassFileBytes(String name) {
		byte[] b = null;
		//�ȴ�classpath�²���
		try {
			String path = classPath + File.separator + name.replace(".", File.separator) + ".class";
			b = TonyCatClassLoaderUtils.getClassFileBytesByClassPath(path);
		} catch (Exception e) {
		}
		//�ٴ�jar���в���
		if (b == null) {
			b = classByteMaps.get(name);
			if (b != null) {
				classByteMaps.remove(name);
			}
		}
		return b;

	}
	/**
	 * ��дloadClass������ �Լ��ȳ��Լ��أ����ʧ�ܾͳ���parent����
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
	/**
	 * ��д�˷��� ����jar���е���Դ
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
		return parent.getResource(name);
	}
	@Override
	protected Enumeration<URL> findResources(String name) throws IOException {
		return super.findResources(name);
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
