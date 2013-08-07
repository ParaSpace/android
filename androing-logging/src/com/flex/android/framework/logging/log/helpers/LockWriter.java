/**
 * 
 */
package com.flex.android.framework.logging.log.helpers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author xu.jianpu
 *
 * 2013 2013-4-18 ÏÂÎç02:14:02
 */
public class LockWriter extends FileWriter{
	
	private byte[] lock = new byte[0];
	
	/**
	 * @param file
	 * @param append
	 * @throws IOException
	 */
	public LockWriter(File file, boolean append) throws IOException {
		super(file, append);
		super.lock = lock;
	}


	/**
	 * @param fileName
	 * @throws IOException
	 */
	public LockWriter(String fileName) throws IOException {
		super(fileName);
		super.lock = lock;
	}
	


}
