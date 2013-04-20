/**
 * 
 */
package com.flex.android.framework.logging.log;

import android.util.Log;


/**
 * 
 * 	使用android的log实现
 * 
 * 
 * @author xu.jianpu
 *
 * 2013 2013-4-16 下午12:41:44
 */
public class LogcatLogger implements org.apache.commons.logging.Log{
	
	private String clz ;
	
	public LogcatLogger(Class<?>clz){
		this.clz=clz.toString();
	}

	public  void i(String str){
		Log.i(clz, str);
	}
	
	public  void i(String str , Throwable e){
		Log.i(clz, str,e);
	}

	
	@Override
	public void debug(Object arg0) {
		Log.d(clz, arg0.toString() );
		
	}

	
	@Override
	public void debug(Object arg0, Throwable arg1) {
		Log.d(clz, arg0.toString() , arg1);
		
	}

	
	@Override
	public void error(Object msg) {
		Log.e(clz, msg.toString());
		
	}

	
	@Override
	public void error(Object msg, Throwable arg1) {
		Log.e(clz, msg.toString(),arg1);
	}

	
	@Override
	public void fatal(Object arg0) {
		Log.i(clz, arg0.toString());
		
	}

	
	@Override
	public void fatal(Object arg0, Throwable arg1) {
		Log.i(clz, arg0.toString() , arg1);
		
	}

	
	@Override
	public void info(Object arg0) {
		Log.i(clz, arg0.toString() );
		
	}


	@Override
	public void info(Object arg0, Throwable arg1) {
		Log.i(clz, arg0.toString() , arg1);
		
	}


	@Override
	public boolean isDebugEnabled() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean isErrorEnabled() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean isFatalEnabled() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean isInfoEnabled() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean isTraceEnabled() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean isWarnEnabled() {
		// TODO Auto-generated method stub
		return false;
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
	public void warn(Object msg) {
		Log.w(clz, msg.toString());
	}


	@Override
	public void warn(Object arg0, Throwable arg1) {
		Log.w(clz, arg0.toString(),arg1);
		
	}
}
