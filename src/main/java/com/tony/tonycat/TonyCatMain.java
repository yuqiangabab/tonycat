package com.tony.tonycat;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.tony.tonycat.classloader.TonyCatCommonClassLoader;
/**
 * 入口类
* <p>Title: TonyCatMain</p>  
* <p>Description: </p>  
* @author tony.tian  
* @date 2018年11月8日
 */
public class TonyCatMain {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		TonyCatCommonClassLoader classLoader =  new TonyCatCommonClassLoader();
		Thread.currentThread().setContextClassLoader(classLoader);
		Class clazz = Class.forName("com.tony.tonycat.TonyCatServer", true, classLoader);
		Object obj = clazz.newInstance();
		Method method = clazz.getMethod("service");
		method.invoke(obj);
	}
}
