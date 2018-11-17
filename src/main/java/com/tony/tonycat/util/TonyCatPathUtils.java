package com.tony.tonycat.util;

import java.io.File;
import java.io.IOException;

/**
 * 获取各种路径的工具类
* Title: PathUtils 
* Description: 
* @author tony.tian  
* @date 2018年11月17日
 */
public class TonyCatPathUtils {
	public static String root;
	static {
		 try {
			root = new File("..").getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取tonycat根路径
	 * Title: getTonyCatRootPath 
	 * Description:   
	 * @return
	 */
	public static String getTonyCatRootPath() {
		return root;
	}
	/**
	 * 获取tonycat lib下路径
	 * Title: getTonyCatLibPath 
	 * Description:   
	 * @return
	 */
	public static String getTonyCatLibPath() {
		return getTonyCatRootPath() + File.separator + "lib";
	}
	/**
	 * 获取webapps路径
	 * Title: getWebappsPath 
	 * Description:   
	 * @return
	 */
	public static String getWebappsPath() {
		return getTonyCatRootPath() + File.separator + "webapps";
	}
	/**
	 * 获取web应用根路径
	 */
	public static String getWebPath(String webName) {
		return getTonyCatRootPath() + File.separator + "webapps" + File.separator + webName;
		
	}
	/**
	 * 获取webinf的路径
	 * Title: getWebInfPath 
	 * Description:   
	 * @return
	 */
	public static String getWebInfPath(String webName) {
		return getWebPath(webName) + File.separator + "WEB-INF";
	}
	/**
	 * 获取web应用web.xml路径
	 * Title: getWebXmlPath 
	 * Description:   
	 * @param webName
	 * @return
	 */
	public static String getWebXmlPath(String webName) {
		return getWebInfPath(webName) + File.separator+"web.xml"; 
	}
	/**
	 * 获取webclass下路径
	 * Title: getWebClassPath 
	 * Description:   
	 * @param webName
	 * @return
	 */
	public static String getWebClassPath(String webName) {
		return getWebInfPath(webName) + File.separator + "classes";
	}
	/**
	 * 获取weblib路径
	 * Title: getWebLibPath 
	 * Description:   
	 * @param webName
	 * @return
	 */
	public static String getWebLibPath(String webName) {
		return getWebInfPath(webName) + File.separator + "lib";
	}
	/**
	 * 获取webjsp路径
	 * Title: getWebJspPath 
	 * Description:   
	 * @param webName
	 * @return
	 */
	public static String getWebJspPath(String webName) {
		return getWebInfPath(webName) + File.separator + "jsp";
	}
}
