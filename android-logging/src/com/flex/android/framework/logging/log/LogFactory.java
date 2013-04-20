/**
 * 
 */
package com.flex.android.framework.logging.log;

import java.io.File;

import org.apache.commons.logging.Log;

import android.os.Environment;

import com.flex.android.framework.logging.log.helpers.LogLog;


/**
 * @author xu.jianpu
 *
 * 2013 2013-4-16 ����01:36:48
 */
public class LogFactory {
	
	private static final String LogCatLog = "android";
	
	private static volatile boolean isInit = false ;
	
	private static volatile boolean isLogCat = false ;

	/**
	 * ��ȡlog��ʵ���ࡣ
	 * 	<br/>	ͨ��log4j.rootLogger=DEBUG, F �������á�
	 * 	<br/>	Ĭ�ϻ�ȡ�ڶ���������Ϊʵ�����֡����ÿ�ѡΪandroid��F��
	 * <br/>	android��ʾʹ��logcatʵ�֡�
	 * 	<br/>	f��ʾʹ���ļ������
	 * @param clz
	 * @return
	 */
	public static Log getLog(Class<?>clz){
		if(!isInit)init();
		if(isLogCat){
			return new LogcatLogger(clz);
		}else{
			return new FileLogger(clz);
		}
	}

	private static void init() {
		isInit = true;
		LogConfigurator configurator = new LogConfigurator();
		if(!configurator.configure()){
			isLogCat = true ;
			return ;
		}
		isLogCat = LogCatLog.equals(LogConfigurator.getLogInstantName())||!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
		if(!isLogCat){
			if(!canWrite(Environment.getExternalStorageDirectory().getPath())){
				isLogCat=true ;
				LogLog.error(" the log file cannot writer , please check the permission ");
				return ;
			}
			LogConfigurator.setDEVICE_STORGE_PATH(Environment.getExternalStorageDirectory().getPath());
		}
	}
	
	private static boolean canWrite(String sdPath){
		File file = new File(sdPath);
		return file.canWrite();
	}
}

