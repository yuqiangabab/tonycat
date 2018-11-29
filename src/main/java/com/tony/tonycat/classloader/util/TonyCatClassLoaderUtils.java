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
 * ClassLoader工具类
* Title: TonyCatClassLoaderUtils 
* Description: 
* @author tony.tian  
* @date 2018年11月17日
 */
public class TonyCatClassLoaderUtils {
	private static final String RESOURCE_TYPE_CLASS ="class";
	private static final String RESOURCE_TYPE_OTHER = "other";
	/**
	 *资源url
	 */
	private static final String RESOURCE_URL = "jar:file:${path}!/${name}";
	/**
	 * 
	 * Title: getClassBytesByJarPath 
	 * Description:   预读取jar下面的所有class
	 * @param jarPath
	 * @return
	 */
	public static Map<String,byte[]> getClassBytesByJarPath(String jarPath){
		return (Map<String, byte[]>) preReadJarFile(jarPath,RESOURCE_TYPE_CLASS);
		
	}
	 /**
     * 根据jar路径遍历获取其他资源
     * Title: getResourcesByPath 
     * Description:   
     * @return
     */
    public static Map<String,URL>  getResourcesByJarPath(String jarPath){
		return (Map<String, URL>) preReadJarFile(jarPath,RESOURCE_TYPE_OTHER);
    	
    }
	/**
	 * 读取class返回byte数组
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
     * 预读lib下面的包
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
     * 扫描lib下面的所有jar包
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
     * 根据resourceType读取一个jar包内的class文件或者resource，并存在当前加载器的map中
     * @param jar
     * @throws IOException
     */
    private static void readJAR(JarFile jar,Map resultMap,String resourceType) throws IOException{
        Enumeration<JarEntry> en = jar.entries();
        while (en.hasMoreElements()){
            JarEntry je = en.nextElement();
            String name = je.getName();
            String clss = null;
            //如果当前是想读取class文件，则判断当前文件是否是class文件吗，如果是，则放入map中，如果不是，则continue
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
                     resultMap.put(clss, cc);//暂时保存下来
                }else {
                	continue;
                }
            }
            //如果当前是想读取其他资源文件，则判断当前文件是否 不是class文件,如果不是，则放入map中，如果是则continue
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
