/**
 * 
 */
package com.flex.android.framework.logging.log.constant;


/**
 * 
 * 	��־����
 * 
 * @author xu.jianpu
 *
 * 2013 2013-4-17 ����03:39:34
 */
public enum Level {

	DEBUG(2,"DEBUG"),
	INFO(4,"INFO"),
	WARN(6,"WARN"),
	ERROR(8,"ERROR");
	
	public final int level ;
	
	public final String name ;
	
	private Level(int level,String name){
		this.level = level;
		this.name = name ;
	}
	
	/**�Ƿ���Ч��level**/
	public static boolean isValid(String lever){
		Level[] ls = Level.values();
		for(Level l : ls){
			if(l.name.equals(lever.toUpperCase())){
				return true ;
			}
		}
		return false ;
	}
	
	/**�Ƿ���Ч��level**/
	public static Level getLevel(String lever){
		Level[] ls = Level.values();
		for(Level l : ls){
			if(l.name.equals(lever.toUpperCase())){
				return l ;
			}
		}
		return null ;
	}
	
	/***
	 * self �ļ����Ƿ�С��comTo
	 * @param self
	 * @param comTo
	 * @return
	 */
	public static boolean isLessThan(Level self, Level comTo){
		if(self.level<comTo.level) return true ;
		return false;
	}
	
	/***
	 * self �ļ����Ƿ����comTo
	 * @param self
	 * @param comTo
	 * @return
	 */
	public static boolean isGreatThan(Level self, Level comTo){
		if(self.level>comTo.level) return true ;
		return false;
	}
}
