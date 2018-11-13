package com.tony.tonycat.web.resolver;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.tony.tonycat.util.TonyCollectionUtils;
import com.tony.tonycat.web.resolver.entity.ServletDom;
import com.tony.tonycat.web.resolver.entity.WebXmlDom;
/**
 * webxml解析器
* Title: WebXMLResolver 
* Description: 
* @author tony.tian  
* @date 2018年11月13日
 */
public class WebXMLResolver {
	
	public static WebXmlDom resolve(String filePath) {
		return resolver(new File(filePath));
	}
	public static WebXmlDom resolver(File file) {
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			WebXmlDom webXmlDom = resolve(is);
			return webXmlDom;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static WebXmlDom resolve(InputStream is) {
		 SAXReader reader = new SAXReader();
		 WebXmlDom webXmlDom = new WebXmlDom();
		 try {
			Document document = reader.read(is);
			//获取根节点元素对象  
			Element rootNode = document.getRootElement();
			webXmlDom.setServletDoms(getServletDom(rootNode));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return webXmlDom;
	}
	/**
	 * 通过根节点获取servletDom
	 * Title: getServletDom 
	 * Description:   
	 * @param rootNode
	 * @return
	 */
	private static List<ServletDom> getServletDom(Element rootNode) {
		
		List<Element> servletElements = rootNode.elements("servlet");
		List<Element> servletMappingElements = rootNode.elements("servlet-mapping"); 
		Map<String,List<String>> servletMappingMaps = TonyCollectionUtils.getEmptyMap();
		//1.将url-pattern放进map中
		for(Element servletMappingElement : servletMappingElements) {
			String servletName = servletMappingElement.element("servlet-name").getTextTrim();
			String urlPattern = servletMappingElement.element("url-pattern").getTextTrim();
			TonyCollectionUtils.putEqulasKey(servletMappingMaps, servletName, urlPattern);
		}
		List<ServletDom> result = TonyCollectionUtils.getEmptyList();
		//2.map中的url-pattern映射servletName
		for(Element servletElement : servletElements) {
			String servletName = servletElement.element("servlet-name").getTextTrim();
			String servletClass = servletElement.element("servlet-class").getTextTrim();
			List<String> patterns = servletMappingMaps.get(servletName);
			ServletDom servletDom = new ServletDom();
			servletDom.setServletClass(servletClass);
			servletDom.setServletName(servletName);
			servletDom.setUrlPattern(patterns);
			result.add(servletDom);
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(resolve("D:\\apache-tomcat-8.0.46\\webapps\\examples\\WEB-INF/web.xml"));
	}
}
