/*
 * Created on Nov 9th,2009
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ez.eau.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 320004019
 * 
 *         To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class StringSplit {
	private static List<String> list;

	/*public StringSplit() {
	}*/

	public static String[] split(String str, char deLimiter) {
		if (str == null || str.length() == 0){
			return null;
		}	
		list = new ArrayList<String>();
		boolean readLast = true;
		int start = 0;
		int i = 0;

		for (; i < str.length(); i++) {
			if (str.charAt(i) == deLimiter) {
				list.add(str.substring(start, i));
				start = i + 1;
			}
			if (start >= str.length()){
				readLast = false;
			}	
		}
		if (readLast) {
			list.add(str.substring(start, i));
		}

		return (String[]) list.toArray(new String[list.size()]);
	}

	public static int getCount() {
		return list.size();
	}

	
	 /*public static void main(String args[]) { String[] arrVal =
	  StringSplit.split("10054811P1,,22000,0,0,0,0,0,0,0,0,0,0,end", ','); 
	 	for(int i = 0; i < arrVal.length; i++) { 
	 		GEALogger.dbg("","arrStr[" + i +"]:" + arrVal[i]+""); 
	 	} 
	 	GEALogger.dbg("","Count:" +StringSplit.getCount()); 
	 }*/
	 
}
