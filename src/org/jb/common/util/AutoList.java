package org.jb.common.util;

import java.util.ArrayList;

public class AutoList<T extends Object> extends ArrayList<T> {

	private static final long serialVersionUID = 259626355275049562L;
	private Class entityClass = null;
	
	public AutoList(Class clz){
		this.entityClass = clz;
	}

	@SuppressWarnings("unchecked")
	public T get(int index) {
		try {
			while (index >= size()) {
				super.add((T)this.entityClass.newInstance());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.get(index);
	}
	public static void main(String[] args){
		
	} 
}
