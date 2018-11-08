package ez.eau.util;

public class JdbcUtil {

	public static final String TRUE = "1";
	public static final String FALSE = "0";

	/**
	 * Converts Boolean object to a string representation of TRUE or FALSE if
	 * the object is not null @ return TRUE (1) if p_bool is true FALSE (0) if
	 * p_bool is false null if p_bool is null
	 */
	public static String getBooleanString(Boolean p_bool) {
		String res = null;
		if (p_bool != null) {
			res = p_bool ? TRUE : FALSE;
		}
		return res;
	}

	/**
	 * Converts Integer object to a string representation if the object is not
	 * null @ return String representation of p_int (same as Integer.toString()
	 * if p_int is not null null if p_int is null
	 */
	public static String getIntegerString(Integer p_int) {
		return p_int == null ? null : p_int.toString();
	}

	/**
	 * Converts Double object to a string representation if the object is not
	 * null @ return String representation of p_dbl (same as Double.toString()
	 * if p_dbl is not null null if p_dbl is null
	 */
	public static String getDoubleString(Double p_dbl) {
		return p_dbl == null ? null : p_dbl.toString();
	}

	/**
	 * Converts an array into a string capable of being used in the IN Clause of
	 * SQL Specially takes care to format the string to meet GenericDAO needs
	 * 
	 * @param p_array
	 *            - Array to be converted
	 * @param quoteFlds
	 *            - whether quotes should be placed around each field. Specify
	 *            true for quotes to be placed for String fields. For numeric
	 *            fields, specify false
	 * @return
	 */
	public static String getInClause(String[] p_array, boolean quoteFlds) {
		final char QUOTE = '\'';
		final char COMMA = ',';
		String inList = null;
		if (p_array != null && p_array.length > 0) {
			StringBuilder sb = new StringBuilder(p_array.length * 3);
			sb.append('(');
			for (String elem : p_array) {
				if (quoteFlds) {
					sb.append(QUOTE);
				}
				sb.append(elem);
				if (quoteFlds) {
					sb.append(QUOTE);
				}
				sb.append(COMMA);
			}
			sb.deleteCharAt(sb.length() - 1); // Delete the last comma
			sb.append(')');
			inList = sb.toString();
		}
		return inList;

	}

	/**
	 * Converts an array into a string capable of being used in the ANY Clause
	 * of SQL Specially takes care to format the string to meet GenericDAO needs
	 * 
	 * @param p_array
	 *            - Array to be converted
	 * @param quoteFlds
	 *            - whether quotes should be placed around each field. Specify
	 *            true for quotes to be placed for String fields. For numeric
	 *            fields, specify false
	 * @return
	 */
	public static String getAnyClause(String[] p_array, boolean quoteFlds) {
		final char QUOTE = '\'';
		final char COMMA = ',';
		String inList = null;
		if (p_array != null && p_array.length > 0) {
			StringBuilder sb = new StringBuilder(p_array.length * 3);
			sb.append('(');
			for (String elem : p_array) {
				sb.append('(');
				String[] data = elem.split(",");
				int i = 0;
				while (i < data.length) {
					sb.append(QUOTE);
					sb.append(data[i]);
					sb.append(QUOTE);
					sb.append(",");
					i = i + 1;
				}
				sb.deleteCharAt(sb.length() - 1);
				sb.append(')');
				sb.append(COMMA);
			}
			sb.deleteCharAt(sb.length() - 1); // Delete the last comma
			sb.append(')');
			inList = sb.toString();
		}
		return inList;

	}

	public static String getBetweenClause(String from, String to) {
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(from);
		buffer.append(" AND ");
		buffer.append(to);
		return buffer.toString();

	}

	/*
	 * public static void printBeanValues(Object bean) { if (bean == null)
	 * return; String className = bean.getClass().getName(); try { Class
	 * thisClass = Class.forName(bean.getClass().getName());
	 * com.sun.org.apache.bcel.internal.classfile.Method[] methods =
	 * thisClass.getDeclaredMethods(); String methodName = null; Class retType =
	 * null; for (Method method : methods) { methodName = method.getName();
	 * retType = method.getReturnType(); if (methodName.indexOf("get") == 0) {
	 * methodName = methodName.substring(3, methodName.length()); if
	 * (retType.toString().indexOf(" eau") > -1 ||
	 * retType.toString().indexOf(" ez") > -1) {
	 * printBeanValues(method.invoke(bean)); } else { GEALogger.info(className,
	 * methodName + " - :" + method.invoke(bean) + ":"); } } } } catch
	 * (Exception e) { GEALogger.error(className,
	 * "Error occured while printing bean values."); GEALogger.error(className,
	 * "Bean being printed - " + className); GEALogger.printStackTrace(e); } }
	 */

}
