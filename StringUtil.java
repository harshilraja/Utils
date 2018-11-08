/*
 * StringUtil.java
 *
 * Created on September 29, 2004, 12:50 PM
 */

package ez.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author 500997990
 */
public class StringUtil {

	public static String getCsvValue(String str) {
		String updatedStr = str.replaceAll("\"", "\"\"");
		StringBuffer temp = new StringBuffer("\"");
		temp.append(updatedStr);
		temp.append("\"");
		return temp.toString();
	}

	public static String rtrim(String str) {
		if (str.trim().length() > 0) {
			int index = str.length() - 1;
			while (str.charAt(index) == ' ') {
				index--;
			}
			return str.substring(0, index + 1);
		} else {
			return "";
		}
	}

	/**
	 * 
	 * @param p_Array
	 * @param optimizationFactor
	 * @return
	 */
	public static List<String[]> getOptimizedStringArray(String[] p_Array, int optimizationFactor) {
		final int MAX_BUCKETS = optimizationFactor;
		String[] o_Array;
		int j = 0;
		List<String[]> list = new ArrayList<String[]>();
		int arryLength = p_Array.length;
		while (j < p_Array.length) {
			if (arryLength < MAX_BUCKETS)
				o_Array = new String[arryLength];
			else
				o_Array = new String[MAX_BUCKETS];
			int k = 0;
			while (k < MAX_BUCKETS) {
				o_Array[k] = p_Array[j];
				k = k + 1;
				j = j + 1;
				if (j == p_Array.length)
					break;
			}
			arryLength = arryLength - MAX_BUCKETS;
			list.add(o_Array);
		}
		return list;
	}

	public static String getExceptionStackTraceAsString(Exception e) {
		StringWriter strWriter = new StringWriter();
		e.printStackTrace(new PrintWriter(strWriter));
		String stackTrace = strWriter.toString();
		return stackTrace;
	}
}
