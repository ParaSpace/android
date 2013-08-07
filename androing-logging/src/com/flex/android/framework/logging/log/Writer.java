/**
 * 
 */
package com.flex.android.framework.logging.log;

import java.io.IOException;

/**
 * @author xu.jianpu
 *
 * 2013 2013-4-18 ÉÏÎç10:46:07
 */
public interface Writer {


	public void close() throws IOException ;
	
	public void flush() throws IOException ;

	
	public void write(char[] buf, int offset, int count)  ;

	
	public java.io.Writer append(char c)  ;

	
	public java.io.Writer append(CharSequence csq, int start, int end);
	
	public java.io.Writer append(CharSequence csq) ;

	
	public void write(char[] buf) ;
	
	public void write(int oneChar) ;
	
	public void write(String str, int offset, int count);

	public void write(String str) ;
}
