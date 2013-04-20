/**
 * 
 */
package com.flex.android.framework.logging.log;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;

import com.flex.android.framework.logging.log.constant.Constant;
import com.flex.android.framework.logging.log.constant.Level;
import com.flex.android.framework.logging.log.helpers.SimpleWriter;

/**
 * 	文件log实现，输出到文件
 * 
 * 
 * @author xu.jianpu
 *
 * 2013 2013-4-17 下午02:12:29
 */
public class FileLogger implements Log{
	
	private Class<?> clz ;
	
	private Writer writer;
	
	private Level effectiveLevel ;
	
	private String fileName ;
	
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	public FileLogger(Class<?>clz)
	{
		this.clz= clz;
		this.effectiveLevel = LogConfigurator.getClassLevel(clz.toString());
		fileName = LogConfigurator.getFileName();
		writer =  SimpleWriter.getInstance(fileName);
	}
	
	/**时间紧急，就先写死输出格式，日后优化框架的时候再修改**/
	private String getPattern(Level level ,String msg){
		return df.format(new Date()) + " "+ level.name+" "+ "["+Thread.currentThread().getName()+"]-" + "["+clz.getSimpleName()+"]: "+ msg ;
	}
	
	@Override
	public void debug(Object arg0) {
		if(Level.isGreatThan(this.effectiveLevel,Level.DEBUG)) return ;
		writer.write(getPattern(Level.DEBUG,arg0.toString()));
	}

	@Override
	public void debug(Object arg0, Throwable arg1) {
		if(Level.isGreatThan(this.effectiveLevel,Level.DEBUG)) return ;
		writer.write(getPattern(Level.DEBUG,arg0.toString())+getThrowableMsg(arg1));
	}

	@Override
	public void error(Object arg0) {
		if(Level.isGreatThan(this.effectiveLevel,Level.ERROR)) return ;
		writer.write(getPattern(Level.DEBUG,arg0.toString()));
	}

	@Override
	public void error(Object arg0, Throwable arg1) {
		if(Level.isGreatThan(this.effectiveLevel,Level.ERROR)) return ;
		writer.write(getPattern(Level.ERROR,arg0.toString())+getThrowableMsg(arg1));
	}
	
	/***
	 * 打印异常栈信息
	 * @param e
	 * @return
	 * 
	 */
	private String getThrowableMsg(Throwable e){
		StringBuffer sb = new StringBuffer(Constant.Separator+""+e.toString()+": "+getErrorMsg(e.getMessage())+Constant.Separator);
		for( StackTraceElement s :e.getStackTrace()){
			sb.append("     at "+s.toString());
			sb.append(Constant.Separator);
		}
		if(e.getCause()!=null){
			sb.append(" Cause by : ");
			for(StackTraceElement s : e.getCause().getStackTrace()){
				sb.append("     at "+s.toString());
				sb.append(Constant.Separator);
			}
		}
		return sb.toString();
	}
	
	private String getErrorMsg(String msg){
		return msg==null?"":msg;
	}

	@Override
	public void fatal(Object arg0) {
		if(Level.isGreatThan(this.effectiveLevel,Level.INFO)) return ;
		writer.write(getPattern(Level.INFO,arg0.toString()));
		
	}

	@Override
	public void fatal(Object arg0, Throwable arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void info(Object arg0) {
		if(Level.isGreatThan(this.effectiveLevel,Level.INFO)) return ;
		writer.write(getPattern(Level.INFO,arg0.toString()));
	}

	@Override
	public void info(Object arg0, Throwable arg1) {
		if(Level.isGreatThan(this.effectiveLevel,Level.INFO)) return ;
		writer.write(getPattern(Level.INFO,arg0.toString())+getThrowableMsg(arg1));
	}

	@Override
	public boolean isDebugEnabled() {
		return Level.isGreatThan(this.effectiveLevel,Level.DEBUG);
	}

	@Override
	public boolean isErrorEnabled() {
		return Level.isGreatThan(this.effectiveLevel,Level.ERROR);
	}

	@Override
	public boolean isFatalEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInfoEnabled() {
		return Level.isGreatThan(this.effectiveLevel,Level.INFO);
	}

	@Override
	public boolean isTraceEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isWarnEnabled() {
		return Level.isGreatThan(this.effectiveLevel,Level.WARN);
	}

	@Override
	public void trace(Object arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void trace(Object arg0, Throwable arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void warn(Object arg0) {
		if(Level.isGreatThan(this.effectiveLevel,Level.WARN)) return ;
		writer.write(getPattern(Level.WARN,arg0.toString()));
		
	}

	@Override
	public void warn(Object arg0, Throwable arg1) {
		if(Level.isGreatThan(this.effectiveLevel,Level.WARN)) return ;
		writer.write(getPattern(Level.WARN,arg0.toString())+getThrowableMsg(arg1));
	}


}
