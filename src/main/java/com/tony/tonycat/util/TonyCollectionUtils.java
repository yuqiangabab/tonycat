package com.tony.tonycat.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ���Ϲ�����
* Title: TonyCollectionUtils 
* Description: 
* @author tony.tian  
* @date 2018��11��13��
 */
public class TonyCollectionUtils {
	/**
	 * ��ȡ��Map
	 * Title: getEmptyMap 
	 * Description:   
	 * @return
	 */
	public static Map getEmptyMap() {
		return new HashMap(16);
	}
	/**
	 * ��ȡ��list
	 * Title: getEmptyList 
	 * Description:   
	 * @return
	 */
	public static List getEmptyList() {
		return new ArrayList();
	}
	/**
	 * ����ͬkey��ֵ����Map�е�List
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
