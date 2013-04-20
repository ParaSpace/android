/**
 * 
 */
package com.flex.android.framework.logging.log.constant;

/**
 * @author xu.jianpu
 *
 * 2013 2013-4-17 ����04:05:22
 */
public enum LoggerElement {

	/**�ļ���������·��*/
	File("File"),
	
	/**�ļ������ڸ�ʽ*/
	DatePattern("DatePattern"),
	
	/****
	 * ��־��ӡ��ʾģʽ����ǰ��֧�̶ֹ�ģʽ����鿴����
	 */
	LayoutPattern("LayoutPattern"),
	
	Conversion("ConversionPattern");
	
	public final  String key ;
	
	private LoggerElement(String key){
		this.key= key ;
	}
	
	public static String getElement(String name){
		LoggerElement[] elements = LoggerElement.values();
		for(LoggerElement e : elements){
			if(e.key.equals(name)){
				return e.key;
			}
		}
		return null;
	}
	
}
