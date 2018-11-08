package com.tony.tonycat.util;

import java.util.Enumeration;
import java.util.Iterator;
/**
 * Enumeration适配器，用于Iterator转换为Enumeration
* <p>Title: EnumerationAdapter</p>  
* <p>Description: </p>  
* @author tony.tian  
* @date 2018年11月7日
 */
public class EnumerationAdapter<E> implements Enumeration{
	private Iterator<E> it;
	public EnumerationAdapter(Iterator<E> it){
		this.it = it;
	}
	public boolean hasMoreElements() {
		return it.hasNext();
	}
	public Object nextElement() {
		return it.next();
	}
}
