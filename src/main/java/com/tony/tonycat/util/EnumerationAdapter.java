package com.tony.tonycat.util;

import java.util.Enumeration;
import java.util.Iterator;
/**
 * Enumeration������������Iteratorת��ΪEnumeration
* <p>Title: EnumerationAdapter</p>  
* <p>Description: </p>  
* @author tony.tian  
* @date 2018��11��7��
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
