/**
 * 
 */
package com.flex.android.framework.logging.log.constant;

/**
 * @author xu.jianpu
 *
 * 2013 2013-4-17 ����04:05:22
 */
public enum AppenderElement {

	/**�ļ���������·��*/
	File("File"),
	
	/**�ļ������ڸ�ʽ*/
	DatePattern("DatePattern"),
	
	/****
	 * ��־��ӡ��ʾģʽ����ǰ��֧�̶ֹ�ģʽ����鿴����
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
