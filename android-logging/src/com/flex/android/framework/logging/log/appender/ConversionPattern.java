/**
 * 
 */
package com.flex.android.framework.logging.log.appender;

import com.flex.android.framework.logging.log.LogConfigurator;


/**
 * @author xu.jianpu
 *
 * 2013 2013-4-18 下午02:42:52
 */
//TODO Unfinish
public class ConversionPattern {
	
	enum Pattern{
		
		/**输出日志信息所属的类的全名 */
		ClassName("%c"),
		/***%f 输出日志信息所属的类的类名 **/
		FName("%f"),
		/** 输出日志时间点的日期或时间**/
		Date("%d"),
		/*** 输出优先级，**/
		Levle("%p"),
		/**输出产生该日志事件的线程名*/
		Thread("t"),
		UnDefine("");
		
		public final  String format;
		/**
		 * 
		 */
		private Pattern(String format) {
			this.format = format;
		}
		
		public static Pattern getPattern(String key){
			Pattern[] ps = Pattern.values();
			for(Pattern p: ps){
				if(p.format.equals(key))
					return p;
			}
			return UnDefine;
		}
		
	}

	/**
	 * 
	 */
	public ConversionPattern() {
		System.err.println("类构造函数 执行");
	}
	
	public static String getContent(String clz , String outMsg){
		String[] ps = LogConfigurator.getConversion().split(" ");
		String content ;
		for(String s: ps){
//			content = content + matchFormat(s);
		}
		return null;
	}
	
	/**
	 * @param s
	 * @return
	 */
	private static String matchFormat(String s) {
//		Pattern p = Pattern.getPattern(s);
		switch(Pattern.getPattern(s)){
			case ClassName : 
		}
		return null;
	}

	public static void main(String[] args) {
		ConversionPattern c = new ConversionPattern();
	}
}
