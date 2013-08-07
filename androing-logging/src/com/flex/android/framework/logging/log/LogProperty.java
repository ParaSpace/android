/**
 * 
 */
package com.flex.android.framework.logging.log;

import java.io.InputStream;
import java.util.Properties;

import com.flex.android.framework.logging.log.helpers.LogLog;

/**
 * 
 * ��־�����ļ���ȡ��
 * @author xu.jianpu
 *
 * 2013 2013-4-17 ����03:47:20
 */
public class LogProperty {

	private static Properties prop = null;
	
	
	/***
	 * ������־�����ļ�
	 * @return
	 */
//	public static Properties initProperty() {
//		if (prop == null) {
//			InputStream is = null;
//			try {
//				is = LogProperty.class.getClassLoader()
//						.getResourceAsStream("log4j.properties");
//				prop = new Properties();
//				prop.load(is);
//				LogLog.debug("���������ļ�[log4j.properties]");
//			} catch (Exception e) {
//				LogLog.error("���������ļ�[log4j.properties]�����쳣��", e);
//			}
//			try {
//				is.close();
//			} catch (IOException e) {
//				LogLog.error("�ر������ļ����쳣", e);
//				is = null;
//			}
//		}
//		return prop;
//	}

	
	public static Properties initProperty() {
		if (prop == null) {
			try {
				prop = new Properties();
				InputStream in = LogProperty.class.getResourceAsStream("/assets/log4j.properties");
				if(in==null){
					LogLog.warn("log4j.properties is not found ,please check !");
					return prop;
				}
				prop.load(in); 
				LogLog.debug("���������ļ�[log4j.properties]");
			} catch (Exception e) {
				LogLog.error("���������ļ�[log4j.properties]�����쳣��", e);
			}
		}
		return prop;
	}


}
