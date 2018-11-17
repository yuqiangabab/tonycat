package com.tony.tonycat.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections4.MapUtils;
/**
 * ����request�ı���ȡrequest��Ϣ
* <p>Title: RequestResolver</p>  
* <p>Description: </p>  
* @author tony.tian  
* @date 2018��11��8��
 */
public class RequestResolver {
	/**
	 * request�ı�����
	 */
	private String[] requestArray;
	
	public RequestResolver(String requestStr) {
		requestArray = requestStr.split("\r\n");
	}
	/**
	 * GET����
	 */
	private String METHOD_GET = "GET";
	/**
	 * POST����
	 */
	private String METHOD_POST = "POST";
	/**
	 * ��ȡuri
	 * <p>Title: getUri</p>  
	 * <p>Description: </p>  
	 * @return
	 */
	public String getUri() {
		String uriAndPar = requestArray[0].split("\\s")[1];
		return uriAndPar.split("\\?")[0];
	}
	public String getContextPath() {
		System.out.println("this.getUri() :" + this.getUri());
		String contextPath = "/" + this.getUri().split("/")[1];
		return contextPath;
	}
	public String getServletPath() {
		String servletPath = "/" + this.getUri().split("/")[2];
		System.out.println("servletPath:" + servletPath);
		return servletPath;
	}
	/**
	 * ��ȡ���󷽷�
	 * <p>Title: getRequestMethod</p>  
	 * <p>Description: </p>  
	 * @return
	 */
	public String getRequestMethod() {
		return requestArray[0].split("\\s")[0];
	}
	/**
	 * ��ȡ���� Ŀǰֻ֧��GET����
	 * <p>Title: getParameterMap</p>  
	 * <p>Description: </p>  
	 * @return
	 */
	public Map<String,String[]> getParameterMap(){
		 Map<String,List<String>> resultMap = new HashMap();
		if(METHOD_GET.equalsIgnoreCase(this.getRequestMethod())) {
			// /index.do?a=1&b=2&c=3
			String uriAndPar = requestArray[0].split("\\s")[1];
			//a=1&b=2&c=3
			String[] uriAndParArry = uriAndPar.split("\\?");
			if(uriAndParArry.length == 1) {
				return MapUtils.EMPTY_SORTED_MAP;
			}
			//a=1 b=2 c=3
			String[] mapperArry = uriAndParArry[1].split("&");
			
			for(String mapper : mapperArry) {
				String[] keyValue = mapper.split("=");
				//�������ͬkey����ӽ�list
				if(resultMap.containsKey(keyValue[0])) {
					resultMap.get(keyValue[0]).add(keyValue[1]);
					continue;
				}
				//�����ͬ��put
				resultMap.put(keyValue[0], new ArrayList() {{
					add(keyValue[1]);
				}});
				
			}
		}
		//�����post������ӵײ�ȡ����
		if(METHOD_POST.equalsIgnoreCase(this.getRequestMethod())) {
			
		}
		
		return this.parseMapStringArry(resultMap);
	}
	/**
	 * listת��������
	 * Title: parseMapStringArry 
	 * Description:   
	 * @param par
	 * @return
	 */
	private Map<String,String[]> parseMapStringArry(Map<String,List<String>> par){
		Map<String,String[]> resultMap = new HashMap<String,String[]>();
		for(Entry<String,List<String>> entry : par.entrySet()) {
			String[] values = new String[ entry.getValue().size()];
			resultMap.put(entry.getKey(),  entry.getValue().toArray(values));
		}
		return resultMap;
		
	}
	
	/**
	 * ������ӷ�������ͷ������������
	 */
}
