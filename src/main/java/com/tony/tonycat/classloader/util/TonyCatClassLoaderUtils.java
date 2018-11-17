package com.tony.tonycat.classloader.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
	/**
	 * 
	 * Title: getClassBytesByJarPath 
	 * Description:   Ԥ��ȡjar���������class
	 * @param jarPath
	 * @return
	 */
	public static Map<String,byte[]> getClassBytesByJarPath(String jarPath){
		return preReadJarFile(jarPath);
		
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
	private static Map<String,byte[]> preReadJarFile(String jarPath){
        List<File> list = scanDir(jarPath);
        Map<String,byte[]> resultMap = new  HashMap<String,byte[]>();
        for(File f : list){
            JarFile jar;
            try {
                jar = new JarFile(f);
                readJAR(jar,resultMap);
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
        System.out.println("classLoaderJarPath:" + jarPath);
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
     * ��ȡһ��jar���ڵ�class�ļ��������ڵ�ǰ��������map��
     * @param jar
     * @throws IOException
     */
    private static void readJAR(JarFile jar,Map<String,byte[]> resultMap) throws IOException{
        Enumeration<JarEntry> en = jar.entries();
        while (en.hasMoreElements()){
            JarEntry je = en.nextElement();
            String name = je.getName();
            if (name.endsWith(".class")){
                String clss = name.replace(".class", "").replaceAll("/", ".");
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
            }
        }
    }
}
