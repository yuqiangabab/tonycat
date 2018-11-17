package com.tony.tonycat.util;

import java.io.File;
import java.io.IOException;

/**
 * ��ȡ����·���Ĺ�����
* Title: PathUtils 
* Description: 
* @author tony.tian  
* @date 2018��11��17��
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
	 * ��ȡtonycat��·��
	 * Title: getTonyCatRootPath 
	 * Description:   
	 * @return
	 */
	public static String getTonyCatRootPath() {
		return root;
	}
	/**
	 * ��ȡtonycat lib��·��
	 * Title: getTonyCatLibPath 
	 * Description:   
	 * @return
	 */
	public static String getTonyCatLibPath() {
		return getTonyCatRootPath() + File.separator + "lib";
	}
	/**
	 * ��ȡwebapps·��
	 * Title: getWebappsPath 
	 * Description:   
	 * @return
	 */
	public static String getWebappsPath() {
		return getTonyCatRootPath() + File.separator + "webapps";
	}
	/**
	 * ��ȡwebӦ�ø�·��
	 */
	public static String getWebPath(String webName) {
		return getTonyCatRootPath() + File.separator + "webapps" + File.separator + webName;
		
	}
	/**
	 * ��ȡwebinf��·��
	 * Title: getWebInfPath 
	 * Description:   
	 * @return
	 */
	public static String getWebInfPath(String webName) {
		return getWebPath(webName) + File.separator + "WEB-INF";
	}
	/**
	 * ��ȡwebӦ��web.xml·��
	 * Title: getWebXmlPath 
	 * Description:   
	 * @param webName
	 * @return
	 */
	public static String getWebXmlPath(String webName) {
		return getWebInfPath(webName) + File.separator+"web.xml"; 
	}
	/**
	 * ��ȡwebclass��·��
	 * Title: getWebClassPath 
	 * Description:   
	 * @param webName
	 * @return
	 */
	public static String getWebClassPath(String webName) {
		return getWebInfPath(webName) + File.separator + "classes";
	}
	/**
	 * ��ȡweblib·��
	 * Title: getWebLibPath 
	 * Description:   
	 * @param webName
	 * @return
	 */
	public static String getWebLibPath(String webName) {
		return getWebInfPath(webName) + File.separator + "lib";
	}
	/**
	 * ��ȡwebjsp·��
	 * Title: getWebJspPath 
	 * Description:   
	 * @param webName
	 * @return
	 */
	public static String getWebJspPath(String webName) {
		return getWebInfPath(webName) + File.separator + "jsp";
	}
}
