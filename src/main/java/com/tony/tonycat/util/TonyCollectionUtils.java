package com.tony.tonycat.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 集合工具类
* Title: TonyCollectionUtils 
* Description: 
* @author tony.tian  
* @date 2018年11月13日
 */
public class TonyCollectionUtils {
	/**
	 * 获取空Map
	 * Title: getEmptyMap 
	 * Description:   
	 * @return
	 */
	public static Map getEmptyMap() {
		return new HashMap(16);
	}
	/**
	 * 获取空list
	 * Title: getEmptyList 
	 * Description:   
	 * @return
	 */
	public static List getEmptyList() {
		return new ArrayList();
	}
	/**
	 * 将相同key的值放入Map中的List
	 * Title: putEqulasKey 
	 * Description:   
	 * @param map
	 * @param key
	 * @param value
	 */
	public static void putEqulasKey(Map map,Object key,Object value) {
		if(map.containsKey(key)) {
			List list = (List)map.get(key);
			list.add(value);
		}else {
			List list = getEmptyList();
			list.add(value);
			map.put(key, list);
		}
	}
}
