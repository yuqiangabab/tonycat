package com.tony.tonycat.classloader.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.tony.tonycat.util.TonyCollectionUtils;
/**
 * ClassLoader������
* Title: TonyCatClassLoaderUtils 
* Description: 
* @author tony.tian  
* @date 2018��11��17��
 */
public class TonyCatClassLoaderUtils {
	private static final String RESOURCE_TYPE_CLASS ="class";
	private static final String RESOURCE_TYPE_OTHER = "other";
	/**
	 *��Դurl
	 */
	private static final String RESOURCE_URL = "jar:file:${path}!/${name}";
	/**
	 * 
	 * Title: getClassBytesByJarPath 
	 * Description:   Ԥ��ȡjar���������class
	 * @param jarPath
	 * @return
	 */
	public static Map<String,byte[]> getClassBytesByJarPath(String jarPath){
		return (Map<String, byte[]>) preReadJarFile(jarPath,RESOURCE_TYPE_CLASS);
		
	}
	 /**
     * ����jar·��������ȡ������Դ
     * Title: getResourcesByPath 
     * Description:   
     * @return
     */
    public static Map<String,URL>  getResourcesByJarPath(String jarPath){
		return (Map<String, URL>) preReadJarFile(jarPath,RESOURCE_TYPE_OTHER);
    	
    }
	/**
	 * ��ȡclass����byte����
	 * Title: getClassFileBytesByClassPath 
	 * Description:   
	 * @param classPath
	 * @return
	 * @throws IOException
	 */
	 public static byte[] getClassFileBytesByClassPath(String classPath) throws IOException {
	        FileInputStream fis = new FileInputStream(classPath);
	        FileChannel fileC = fis.getChannel();
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        WritableByteChannel outC = Channels.newChannel(baos);
	        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
	        while (true) {
	            int i = fileC.read(buffer);
	            if (i == 0 || i == -1) {
	                break;
	            }
	            buffer.flip();
	            outC.write(buffer);
	            buffer.clear();
	        }
	        fis.close();
	        return baos.toByteArray();
	    }
	/**
     * Ԥ��lib����İ�
     */
	private static Map<String,?> preReadJarFile(String jarPath,String resourceType){
        List<File> list = scanDir(jarPath);
        Map<String,?> resultMap = new  HashMap();
        for(File f : list){
            JarFile jar;
            try {
                jar = new JarFile(f);
                readJAR(jar,resultMap,resourceType);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultMap;
    }
	  /**
     * ɨ��lib���������jar��
     * @return
     */
    private static List<File> scanDir(String jarPath) {
        List<File> list = new ArrayList<File>();
        File[] files = new File(jarPath).listFiles();
        if(files == null) {
        	return TonyCollectionUtils.getEmptyList();
        }
        for (File f : files) {
            if (f.isFile() && f.getName().endsWith(".jar"))
                list.add(f);
        }
        return list;
    }
    /**
     * ����resourceType��ȡһ��jar���ڵ�class�ļ�����resource�������ڵ�ǰ��������map��
     * @param jar
     * @throws IOException
     */
    private static void readJAR(JarFile jar,Map resultMap,String resourceType) throws IOException{
        Enumeration<JarEntry> en = jar.entries();
        while (en.hasMoreElements()){
            JarEntry je = en.nextElement();
            String name = je.getName();
            String clss = null;
            //�����ǰ�����ȡclass�ļ������жϵ�ǰ�ļ��Ƿ���class�ļ�������ǣ������map�У�������ǣ���continue
            if(resourceType.equalsIgnoreCase(RESOURCE_TYPE_CLASS)) {
            	if (name.endsWith(".class")){
                     clss = name.replace(".class", "").replaceAll("/", ".");
                     InputStream input = jar.getInputStream(je);
                     ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
                     int bufferSize = 4096; 
                     byte[] buffer = new byte[bufferSize]; 
                     int bytesNumRead = 0; 
                     while ((bytesNumRead = input.read(buffer)) != -1) { 
                         baos.write(buffer, 0, bytesNumRead); 
                     }
                     byte[] cc = baos.toByteArray();
                     input.close();
                     resultMap.put(clss, cc);//��ʱ��������
                }else {
                	continue;
                }
            }
            //�����ǰ�����ȡ������Դ�ļ������жϵ�ǰ�ļ��Ƿ� ����class�ļ�,������ǣ������map�У��������continue
            if(resourceType.equalsIgnoreCase(RESOURCE_TYPE_OTHER)) {
            	if (!name.endsWith(".class") && name.contains(".")){
                    clss = name;
                    String resourceUrl = RESOURCE_URL.replace("${path}", jar.getName()).replace("${name}", name);
                    URL url = new URL(resourceUrl);
                    resultMap.put(clss, url);
               }else {
               		continue;
               }
            }
           
        }
    }
}
