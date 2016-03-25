package org.jb.common.biz;

import org.jb.common.dao.CommonDAO;


public class BaseBiz {
	protected CommonDAO commonDAO = null;

	public CommonDAO getCommonDAO() {
		return commonDAO;
	}

	public void setCommonDAO(CommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}
	
	public static boolean isNotNullOrEmpty(String s){
		if (null==s){
			return false;
		}
		if (s.trim().equals("")){
			return false;
		}
		return true;
	} 
	
	public static String BuildDateClause(String dateString,String fieldName){
		String ret = "";
		if (null==dateString){
			return ret;
		}
		String[] arr = dateString.split("-");
		for(String s : arr){
			int i = parseInt(s);
			if (-1!=i){
				ret += "and " + fieldName + " like '%" + i + "%' ";
			}
		}
		return ret;
	}
	public static int parseInt(String s){
		int i = -1;
		try{
			i = Integer.parseInt(s);
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		return i;
	}
}
