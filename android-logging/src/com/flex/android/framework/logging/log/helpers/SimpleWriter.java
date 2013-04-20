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
 * 		logĬ�����writer�������ļ��������
 * 
 * 
 * @author xu.jianpu
 *
 * 2013 2013-4-18 ����09:38:16
 */
public class SimpleWriter implements Writer{

	/***��ǰwriter�Ƿ���ã����ļ������ڻ��ߴ���writerʧ�ܵ�ʱ�����Ϊfalse����Ӧ��������з�������ִ�С�**/
	private boolean isValid = true ;
	
	private static SimpleWriter simpleWriter = null;
	
	private java.io.Writer writer = null;
	
	/***
	 * Ϊ��ʵ����Դ������ʵһ����־�ļ���Ӧ��ֻ��һ����Ӧ�����������������ļ�������
	 * 	���Ҳ��Ϊ�˶��̲߳�����ʱ���׼�������̷߳���ͬһ���ļ���
	 * @param fileName
	 */
	private SimpleWriter(String fileName){
		System.err.println(SimpleWriter.class);
		if(writer==null){
			try {
			initWriter(fileName);
		} catch (IOException e) {
			LogLog.error("��ʼ����log������쳣,�ļ�·����"+fileName, e);
			isValid = false ;
		}
		}
		
	}
	
	
	
	/****
	 * ������־�ļ���
	 * 		��ʵ���������
	 * @param name
	 * @throws IOException 
	 */
	public   void initWriter(String name) throws IOException{
		if(name!=null){
			File parent = new File(Util.getFilePath(name));
			if(!parent.exists())parent.mkdirs();
			//����ļ�����Ϊandroid.logging.log��ʱ�������file����Ϊ��·�����������ļ��������ļ���������"."����
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
				LogLog.error("��ʼ����log������쳣,�ļ�·����"+file.getName(), e);
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
				 * TODO ����ÿ�ζ�ˢ�»��������ᵼ��io�������ܵ��¡�������bufferedwriter��װЧ�����ã�
				 * BufferedOutputStream,��Ĭ�ϻ�������С��8192�ֽ�
				 * ���ʹ�û��������и�Σ�վ��ǣ���ϵͳ�ҵ��ˣ�����쳣��־���û���ܽ������������Ļ�����ô�ǲ����ӡ��ϵͳ�ҵ�����־�ġ�
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
