/**
 * 
 */
package com.flex.android.framework.logging.log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.flex.android.framework.logging.log.constant.AppenderElement;
import com.flex.android.framework.logging.log.constant.Level;
import com.flex.android.framework.logging.log.constant.LoggerElement;
import com.flex.android.framework.logging.log.helpers.LogLog;

/**
 * 		日志配置中心
 * 
 * 
 * @author xu.jianpu
 * 
 *         2013 2013-4-17 下午01:13:46
 */
public class LogConfigurator {
	private static Level rootLevel = Level.DEBUG;
	private String filePattern = "%d - [%p::%c::%C] - %m%n";
	private String logCatPattern = "%m%n";
	private static String fileName = "android-log4j.log";
	private int maxBackupSize = 5;
	private long maxFileSize = 524288L;
	private boolean immediateFlush = true;
	private boolean useLogCatAppender = true;
	private boolean useFileAppender = true;
	private boolean resetConfiguration = true;
	private boolean internalDebugging = false;

	/**添加设备的访问路径**/
	private static String DEVICE_STORGE_PATH = null;

	public static final String APPENDER = "log4j.appender.F.";
	public static final String LOGGER = "log4j.logger.";
	public static final String ROOT_LOGGER = "log4j.rootLogger";

	private static final List<String> rootLogger = new ArrayList<String>();
	private static final Map<String,String>appenderMap = new HashMap<String,String>();
	private static final Map<String,String>loggerMap = new HashMap<String,String>();
	
	public LogConfigurator() {
	}

	/***
	 * 进行日志输出的配置获取
	 * 
	 * **/
	public boolean configure() {
		System.err.println("start load config...");
		if(!loadProperties()){
			LogLog.error(" log4j properties is not found , please /assets/log4j.properties file! loggoing config exit with exception ");
			return false;
			
		}
		System.err.println("loadProperties success...");
		fileName = appenderMap.get(AppenderElement.File.key);
//		createFile(fileName);
		System.err.println("load success...");
		return true ;
	}
	
	/***
	 * 当有外部设备的路径
	 * @param devicePath
	 */
	public boolean configure(String devicePath) {
		if(!configure()) return false;
		setDEVICE_STORGE_PATH(devicePath);
		System.err.println("load with devicePath success...");
		return true ;
	}
//	
//	/****
//	 * 创建日志文件。
//	 * 		当在初始化配置中创建失败后，依然可以再log writer中进行创建，有具体的调用类觉得
//	 * @param name
//	 */
//	public static  void createFile(String name){
//		if(name!=null){
//			File parent = new File(Util.getFilePath(fileName));
//			if(!parent.exists())parent.mkdirs();
//			//如果文件名称为android.logging.log的时候，下面的file会认为是路径，而不是文件。所以文件名不能有"."符合
//			File file = new File(parent,Util.getLogFileName(fileName));
//			if(!file.exists())
//				try {
//					file.createNewFile();
//					System.err.println("lig file "+name+" create  success...");
//				} catch (IOException e) {
//					LogLog.error("穿件日志文件file异常", e);
//				}	
//		}
//	}
	
	

	/**
 * 
 */
	private boolean loadProperties() {
		Properties props = LogProperty.initProperty();
		if(props.isEmpty()) {
			LogLog.error("log4j propertier is empty ...");
			return false;
		}
		rootLogger.addAll(Arrays.asList(props.getProperty(ROOT_LOGGER).split(
				",")));
		setRootLevel();
		for (Object o : props.keySet()) {
			String s = (String) o;
			if (s.startsWith(APPENDER)) {
				loadAppender(s, props.getProperty(s));
			} else if (s.startsWith(LOGGER))
				loadLogger(s, props.getProperty(s));
		}
		return true;
	}

	/**
	 * 	如果没有指定，默认为DEBUG
	 */
	private void setRootLevel() {
		Level level = Level.getLevel(rootLogger.get(0));
		if(level!=null) rootLevel = level;
	}

	/**
	 * @param property
	 */
	private void loadLogger(String key, String value) {
		if(!Level.isValid(value)) return ;
		String clz = key.substring(LOGGER.length());
		loggerMap.put(clz,value);
	}

	/**
	 * @param s
	 * @param props
	 */
	private void loadAppender(String key, String value) {
		String element = LoggerElement.getElement(key.substring(APPENDER.length()));
		if(element==null) return ;
		appenderMap.put(element, value);
	}

	public void setLevel(String loggerName, Level level) {

	}
	
	
	/***
	 * 获取对应类的level
	 * @param clzName	全限类名
	 * @return
	 */
	public static Level getClassLevel(String clzName){
		if(!loggerMap.containsKey(clzName))return rootLevel;
		Level level = Level.getLevel(clzName);
		return level==null?rootLevel:level;
	}
	
	public static String getConversion(){
		return appenderMap.get(AppenderElement.ConversionPattern.key);
	}

	public Level getRootLevel() {
		return rootLevel;
	}
	
	public static String getLogInstantName(){
		return rootLogger.get(1);
	}

	public void setRootLevel(Level level) {
		rootLevel = level;
	}

	public String getFilePattern() {
		return this.filePattern;
	}

	public void setFilePattern(String filePattern) {
		this.filePattern = filePattern;
	}

	public String getLogCatPattern() {
		return this.logCatPattern;
	}

	public void setLogCatPattern(String logCatPattern) {
		this.logCatPattern = logCatPattern;
	}

	public static String getFileName() {
		return DEVICE_STORGE_PATH==null?fileName:DEVICE_STORGE_PATH+fileName;
	}

	public static  void setFileName(String name) {
		LogConfigurator.fileName = name;
	}

	public int getMaxBackupSize() {
		return this.maxBackupSize;
	}

	public void setMaxBackupSize(int maxBackupSize) {
		this.maxBackupSize = maxBackupSize;
	}

	public long getMaxFileSize() {
		return this.maxFileSize;
	}

	public void setMaxFileSize(long maxFileSize) {
		this.maxFileSize = maxFileSize;
	}

	public boolean isImmediateFlush() {
		return this.immediateFlush;
	}

	public void setImmediateFlush(boolean immediateFlush) {
		this.immediateFlush = immediateFlush;
	}

	public boolean isUseFileAppender() {
		return this.useFileAppender;
	}

	public void setUseFileAppender(boolean useFileAppender) {
		this.useFileAppender = useFileAppender;
	}

	public boolean isUseLogCatAppender() {
		return this.useLogCatAppender;
	}

	public void setUseLogCatAppender(boolean useLogCatAppender) {
		this.useLogCatAppender = useLogCatAppender;
	}

	public void setResetConfiguration(boolean resetConfiguration) {
		this.resetConfiguration = resetConfiguration;
	}

	public boolean isResetConfiguration() {
		return this.resetConfiguration;
	}

	public void setInternalDebugging(boolean internalDebugging) {
		this.internalDebugging = internalDebugging;
	}

	public boolean isInternalDebugging() {
		return this.internalDebugging;
	}

	/**
	 * @param dEVICE_STORGE_PATH the dEVICE_STORGE_PATH to set
	 */
	public static void setDEVICE_STORGE_PATH(String dEVICE_STORGE_PATH) {
		DEVICE_STORGE_PATH = dEVICE_STORGE_PATH;
	}

	/**
	 * @return the dEVICE_STORGE_PATH
	 */
	public static String getDEVICE_STORGE_PATH() {
		return DEVICE_STORGE_PATH;
	}

	public static void main(String[] args) {
		
	}
	

}