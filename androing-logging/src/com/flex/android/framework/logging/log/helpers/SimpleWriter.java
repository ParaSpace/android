/**
 * 
 */
package com.flex.android.framework.logging.log.helpers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.flex.android.framework.logging.log.Writer;
import com.flex.android.framework.logging.log.constant.Constant;



/**
 * 		log默认输出writer，进行文件内容输出
 * 
 * 
 * @author xu.jianpu
 *
 * 2013 2013-4-18 上午09:38:16
 */
public class SimpleWriter implements Writer{

	/***当前writer是否可用，但文件不存在或者创建writer失败的时候，这个为false。对应着类的所有方法都不执行。**/
	private boolean isValid = true ;
	
	private static SimpleWriter simpleWriter = null;
	
	private java.io.Writer writer = null;
	
	/***
	 * 为了实现资源管理，其实一个日志文件，应该只有一个对应的输出流，进行输出文件操作。
	 * 	这个也是为了多线程并发的时候的准备。对线程访问同一个文件。
	 * @param fileName
	 */
	private SimpleWriter(String fileName){
		System.err.println(SimpleWriter.class);
		if(writer==null){
			try {
			initWriter(fileName);
		} catch (IOException e) {
			LogLog.error("初始化话log输出流异常,文件路径："+fileName, e);
			isValid = false ;
		}
		}
		
	}
	
	
	
	/****
	 * 创建日志文件。
	 * 		和实例化输出流
	 * @param name
	 * @throws IOException 
	 */
	public   void initWriter(String name) throws IOException{
		if(name!=null){
			File parent = new File(Util.getFilePath(name));
			if(!parent.exists())parent.mkdirs();
			//如果文件名称为android.logging.log的时候，下面的file会认为是路径，而不是文件。所以文件名不能有"."符合
			File file = new File(parent,Util.getLogFileName(name));
			if(!file.exists()){
				file.createNewFile();
				LogLog.debug("log file "+name+" create  success...");
			}
			writer =new LockWriter(file,true);
		}
	}
	
	public synchronized static SimpleWriter getInstance(String fileName){
		if(simpleWriter!=null)return simpleWriter;
		return simpleWriter = new SimpleWriter(fileName);
	}
	
	public synchronized static SimpleWriter getInstance(File file){
		if(simpleWriter!=null)return simpleWriter;
		return simpleWriter = new SimpleWriter(file);
	}
	
	private  SimpleWriter(File file){
		if(!file.exists())isValid=false;
		else{
			try {
				writer = new FileWriter(file);
			} catch (IOException e) {
				LogLog.error("初始化话log输出流异常,文件路径："+file.getName(), e);
				isValid = false ;
			}
		}
		
	}

	@Override
	public void close() throws IOException {
		if(writer!=null)writer.close();
	}

	@Override
	public void flush() throws IOException {
		if(isValid)writer.flush();
		
	}

	@Override
	public java.io.Writer append(char c)  {
		try {
			return writer.append(c);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public java.io.Writer append(CharSequence csq, int start, int end) {
		return append(csq, start, end);
	}

	@Override
	public java.io.Writer  append(CharSequence csq)  {
		return append(csq);
	}

	@Override
	public void write(char[] buf, int offset, int count) {
		if(isValid)
			try {
				/*
				 * TODO 这里每次都刷新缓冲区，会导致io操作性能低下。但是用bufferedwriter封装效果不好，
				 * BufferedOutputStream,其默认缓冲区大小是8192字节
				 * 如果使用缓冲区，有个危险就是，等系统挂掉了，这个异常日志如果没有能将缓冲区填满的话，那么是不会打印出系统挂掉的日志的。
				 */
				writer.write(buf, offset, count);
				writer.write(Constant.Separator);
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@Override
	public void write(char[] buf)  {
		write(buf,0,buf.length);
	}

	@Override
	public void write(int oneChar)  {
		write(new char[]{(char)oneChar});
	}

	@Override
	public void write(String str, int offset, int count) {
		write(str.toCharArray(),offset,count);
	}

	@Override
	public void write(String str)  {
		write(str,0,str.length());
	}

}
