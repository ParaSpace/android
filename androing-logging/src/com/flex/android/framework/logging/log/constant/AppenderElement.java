/**
 * 
 */
package com.flex.android.framework.logging.log.constant;

/**
 * @author xu.jianpu
 *
 * 2013 2013-4-17 下午04:05:22
 */
public enum AppenderElement {

	/**文件名，包括路径*/
	File("File"),
	
	/**文件名日期格式*/
	DatePattern("DatePattern"),
	
	/****
	 * 日志打印显示模式，当前仅支持固定模式，请查看样例
	 */
	LayoutPattern("LayoutPattern"),
	ConversionPattern("ConversionPattern");
	
	public final  String key ;
	
	private AppenderElement(String key){
		this.key= key ;
	}
	
	public static String getElement(String name){
		AppenderElement[] elements = AppenderElement.values();
		for(AppenderElement e : elements){
			if(e.key.equals(name)){
				return e.key;
			}
		}
		return null;
	}
	
}
