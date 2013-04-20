package com.flex.android.framework.logging.log.helpers;

import java.util.Date;

import android.text.format.DateFormat;


public class Util {

	public static String getFileName(String name){
		return name.substring(name.lastIndexOf("/")+1,name.length());
	}
	
	public static String getFilePath(String name){
		return name.substring(0,name.lastIndexOf("/"));
	}
	
	public static String getLogFileName(String name){
		String temp = getFileName(name);
		int index = temp.lastIndexOf(".");
		String prefix = temp.substring(0, index)+getSimpleDate();
		String sufix = temp.substring(index,temp.length());
		return prefix + sufix;
	}
	
	/***
	 * 	获取简单的日期
	 * 		格式：yyyy-MM-dd
	 * @return
	 */
	public static String getSimpleDate(){
		return DateFormat.format("yyyy-MM-dd", new Date()).toString();
	}
	
//	public static Date getFormateDate(String strDate){
//		java.text.DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		try {
//			return df.parse(strDate);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	
	public static void main(String[] args) {
//		String fi = "/opt/logs/android.logging.log";
//		System.err.println(getFileName("/opt/logs/android.logging.log"));
//		System.err.println(getFilePath("/opt/logs/android.logging.log"));
//		File parent = new File(getFilePath(fi));
//		if(!parent.exists())parent.mkdirs();
//		File file = new File(parent,getFileName(fi));
//		try {
//			file.createNewFile();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.err.println(getSimpleDate());
	}
}
