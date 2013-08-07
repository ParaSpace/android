/**
 * 
 */
package com.flex.android.framework.logging.log;

import java.io.InputStream;
import java.util.Properties;

import com.flex.android.framework.logging.log.helpers.LogLog;

/**
 * 
 * 日志配置文件读取类
 * @author xu.jianpu
 *
 * 2013 2013-4-17 下午03:47:20
 */
public class LogProperty {

	private static Properties prop = null;
	
	
	/***
	 * 加载日志配置文件
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
//				LogLog.debug("载入配置文件[log4j.properties]");
//			} catch (Exception e) {
//				LogLog.error("载入配置文件[log4j.properties]出现异常！", e);
//			}
//			try {
//				is.close();
//			} catch (IOException e) {
//				LogLog.error("关闭配置文件流异常", e);
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
				LogLog.debug("载入配置文件[log4j.properties]");
			} catch (Exception e) {
				LogLog.error("载入配置文件[log4j.properties]出现异常！", e);
			}
		}
		return prop;
	}


}
