package ez.eau.util;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import oracle.sql.TIMESTAMP;

/**
 * This DateUtil utility class will provide various methods for date
 * manipulations
 * 
 * @author Author: 120004943
 * @version Revision: 1.0
 */
public class DateUtil {

	public static final String offlineTimeKeeperFormat = "MMM dd, yyyy HH:mm:ss";

	public static Calendar getCalendar(TIMESTAMP timestamp) throws SQLException {
		return (timestamp != null ? getCalendar(timestamp.timestampValue()) : null);
	}

	/**
	 * Converts the given date into the specified format
	 * 
	 * @param Date
	 *            String to be converted
	 * @param format
	 *            String into which the date string has to be converted
	 * @throws ParseException
	 * @return Calendar object
	 */
	public static Calendar getCalendar(String p_date, String p_format) throws ParseException {

		Calendar p_cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat();
		formatter.applyPattern(p_format);
		p_cal.setTime(formatter.parse(p_date));
		return p_cal;
	}

	/**
	 * returns the Calendar with the given Timestamp.
	 * 
	 * @param Timestamp
	 *            object to be set to calendar
	 * @return Calendar object
	 */
	public static Calendar getCalendar(Timestamp p_time) {
		Calendar p_cal = Calendar.getInstance();
		p_cal.setTimeInMillis(p_time.getTime());
		return p_cal;
	}

	/**
	 * returns the Calendar by interpreting the format of the date string passed
	 * Current supported formats are: <br>
	 * yyyyMMdd, yyyyMMddHHmmss, <br>
	 * MM/dd/yyyy, MM/dd/yyyyHH:mm, MM/dd/yyyyHHmmss, MM/dd/yyyy HHmmss, <br>
	 * MM/dd/yyyyHH:mm:ss, MM/dd/yyyy HH:mm:ss Interpretation is carried out
	 * based on length of parameter
	 * 
	 * @param Date
	 *            String to be set to calendar
	 * @return Calendar object
	 */
	public static Calendar getCalendar(String p_date) throws ParseException {
		Calendar time = null;
		switch (p_date.length()) {
		case 8:
			time = getCalendar(p_date, "yyyyMMdd");
			break;
		case 10:
			time = getCalendar(p_date, "MM/dd/yyyy");
			break;
		case 14:
			time = getCalendar(p_date, "yyyyMMddHHmmss");
			break;
		case 15:
			time = getCalendar(p_date, "MM/dd/yyyyHH:mm");
			break;
		case 16:
			time = getCalendar(p_date, "MM/dd/yyyyHHmmss");
			break;
		case 17:
			time = getCalendar(p_date, "MM/dd/yyyy HHmmss");
			break;
		case 18:
			time = getCalendar(p_date, "MM/dd/yyyyHH:mm:ss");
			break;
		case 19:
			time = getCalendar(p_date, "MM/dd/yyyy HH:mm:ss");
			break;
		default:
//			time = null;
			break;
		}
		return time;
	}

	/**
	 * returns the Timestamp with the given date string and format.
	 * 
	 * @param Date
	 *            String to be set to Timestamp
	 * @param fomrat
	 *            String to be set to Timestamp
	 * @return Timestamp object
	 */
	public static Timestamp getTimestamp(String p_date, String p_format) {
		try {
			Calendar p_cal = Calendar.getInstance();
			SimpleDateFormat formatter = new SimpleDateFormat();
			formatter.applyPattern(p_format);
			p_cal.setTime(formatter.parse(p_date));
			return new Timestamp(p_cal.getTimeInMillis());
		} catch (ParseException pe) {
			return null;
		}
	}

	public static Calendar truncTime(Calendar p_cal) throws ParseException {
		return getCalendar(format(p_cal, "yyyyMMdd"));
	}

	/**
	 * returns the current Timestamp
	 * 
	 * @return Timestamp object
	 */
	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(Calendar.getInstance().getTimeInMillis());
	}

	/**
	 * returns the Timestamp with the given date string
	 * 
	 * @param Date
	 *            String to be set to Timestamp
	 * @return Timestamp object
	 */

	public static Timestamp getTimeStamp(String p_date) {
		Timestamp time = null;
		try {
			switch (p_date.length()) {
			case 8:
				time = getTimestamp(p_date, "yyyyMMdd");
				break;
			case 10:
				time = getTimestamp(p_date, "MM/dd/yyyy");
				break;
			case 14:
				time = getTimestamp(p_date, "yyyyMMddHHmmss");
				break;
			case 15:
				time = getTimestamp(p_date, "MM/dd/yyyyHH:mm");
				break;
			case 16:
				time = getTimestamp(p_date, "MM/dd/yyyyHHmmss");
				break;
			case 17:
				time = getTimestamp(p_date, "MM/dd/yyyy HHmmss");
				break;
			case 18:
				time = getTimestamp(p_date, "MM/dd/yyyyHH:mm:ss");
				break;
			case 19:
				time = getTimestamp(p_date, "MM/dd/yyyy HH:mm:ss");
				break;
			default:
//				time = null;
				break;
			}
			return time;
		} catch (NullPointerException pe) {
			return null;
		}
	}

	/**
	 * returns the 24hr time string for a given int type Returns a number e.g.
	 * 2300 in a format of 23:00
	 * 
	 * @param int
	 *            type
	 * @return string format of the time.
	 */
	public static String getTimeDisplay(int p_time) {
		int p_hour = (int) (p_time / 100);
		int p_min = (int) ((((double) p_time / 100) - p_hour) * 100);
		return Integer.toString(p_hour) + ':' + Integer.toString(p_min);
	}

	/**
	 * returns the max date eg: returns 2099/12/31 for number 4102376400000
	 * 
	 * @return Timestamp.
	 */

	public static Timestamp getMaxDate() {
		final Timestamp termdate = getTimeStamp("20991231");
		return termdate;
	}

	/**
	 * returns the min date eg: returns 1900/01/01 for number 4102376400000
	 * 
	 * @return Timestamp.
	 */
	public static Timestamp getMinDate() {
		final Timestamp mindate = getTimeStamp("19000101");
		return mindate;
	}

	/**
	 * Checks if the date is minimum date {@link #getMinDate()}. Typically min
	 * date means no value is set
	 */
	public static boolean isMinDate(Timestamp time) {
		return getMinDate().equals(time);
	}

	/**
	 * Checks if the date is minimum date {@link #getMinDate()}. Typically min
	 * date means no value is set
	 */
	public static boolean isMinDate(Calendar time) {
		return getMinDate().equals(new Timestamp(time.getTimeInMillis()));
	}

	/**
	 * returns the formatted timestamp object with the given format
	 * 
	 * @param Timestamp
	 *            to be formated, format string
	 * @return string format of the timestamp.
	 */
	public static String format(Timestamp p_stamp, String p_format) {
		String datestr = "";
		if (p_stamp != null) {
			SimpleDateFormat formatter = new SimpleDateFormat(p_format);
			datestr = formatter.format(p_stamp);
		}
		return datestr;
	}

	/**
	 * returns the formatted Calendar object with the given format
	 * 
	 * @param Calaendar
	 *            to be formated, format string
	 * @return string format of the Calendar.
	 */
	public static String format(Calendar p_cal, String p_format) {
		String datestr = "";
		if (p_cal != null) {
			SimpleDateFormat formatter = new SimpleDateFormat(p_format);
			datestr = formatter.format(p_cal.getTime());
		}
		return datestr;
	}

	/**
	 * returns the formatted Date object with the given format
	 * 
	 * @param Date
	 *            to be formated, format string
	 * @return string format of the Date.
	 */
	public static String format(Date p_date, String p_format) {
		String datestr = "";
		if (p_date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat(p_format);
			datestr = formatter.format(p_date);
		}
		return datestr;
	}

	/**
	 * returns the formatted Calendar object with the given format
	 * 
	 * @param Calendar
	 *            to be formated, format string
	 * @return string format of the Calendar.
	 */
	public static String format(String p_date, String p_format) throws ParseException {
		String datestr = "";
		Calendar cal = getCalendar(p_date);
		if (cal != null) {
			datestr = format(cal, p_format);
		}
		return datestr;
	}

	/**
	 * returns a calendar representing the last millsecond of the given date.
	 * Most useful is specifying "to" values in a date comparison e.g. usage
	 * DateUtil.getEODTime(Calendar.getInstance()) gives the last millisecond
	 * for today
	 */
	public static Calendar getEODTime(Calendar p_cal) {
		Calendar retCal = null;
		if (p_cal != null) {
			retCal = Calendar.getInstance();
			retCal.setTimeInMillis(p_cal.getTimeInMillis());
			retCal.set(Calendar.AM_PM, 0);
			retCal.set(Calendar.HOUR, 04);
			retCal.set(Calendar.MINUTE, 00);
			retCal.set(Calendar.SECOND, 00);
			retCal.set(Calendar.MILLISECOND, 000);
		}
		return retCal;
	}

	/**
	 * returns a calendar representing the first millsecond of the given date.
	 * Most useful is specifying "from" values in a date comparison e.g. usage
	 * DateUtil.getBODTime(Calendar) gives the first millisecond for today
	 */
	public static Calendar getBODTime(Calendar p_cal) {
		Calendar retCal = null;
		if (p_cal != null) {

			retCal = Calendar.getInstance();
			retCal.setTimeInMillis(p_cal.getTimeInMillis());
			retCal.set(Calendar.AM_PM, 0);
			retCal.set(Calendar.HOUR, 04);
			retCal.set(Calendar.MINUTE, 00);
			retCal.set(Calendar.SECOND, 00);
			retCal.set(Calendar.MILLISECOND, 000);
		}
		return retCal;
	}

	/**
	 * returns the SQL formatted date object
	 * 
	 * @return string format of the sql date.
	 */
	public static String getSqlDateString() {
		return getSqlDateString(Calendar.getInstance());
	}

	/**
	 * returns the SQL format for a given Calendar object
	 * 
	 * @param Calendar
	 *            to be formated
	 * @return string format of the sql date.
	 */
	public static String getSqlDateString(Calendar p_cal) {
		return getSqlString(p_cal, "yyyyMMdd");
	}

	/**
	 * returns the SQL formatted for a given date object
	 * 
	 * @param String
	 *            date to be formated
	 * @return string format of the sql date.
	 */
	public static String getSqlDateString(String p_date) throws ParseException {
		return getSqlDateString(getCalendar(p_date));
	}

	/**
	 * returns the SQL format of current time
	 * 
	 * @return string format of the sql date.
	 */
	public static String getSqlTimeString() {
		return getSqlTimeString(Calendar.getInstance());
	}

	/**
	 * returns the SQL format of current time for a given Calendar
	 * 
	 * @param Calendar
	 *            to be formated
	 * @return string format of the sql time.
	 */
	public static String getSqlTimeString(Calendar p_cal) {
		return getSqlString(p_cal, "yyyyMMddHHmmss");
	}

	/**
	 * returns the SQL format of current time for a given Date
	 * 
	 * @param Stirng
	 *            Date to be formated
	 * @return string format of the sql time.
	 */
	public static String getSqlTimeString(String p_date) throws ParseException {
		return getSqlTimeString(getCalendar(p_date));

	}

	/**
	 * returns the SQL format of current time for a given timestamp
	 * 
	 * @param Tiemstamp
	 *            to be formated
	 * @return string format of the sql time.
	 */
	public static String getSqlTimeString(Timestamp p_time) {
		return getSqlString(p_time, "yyyyMMddHHmmss");
	}

	/**
	 * returns the SQL format for a given Calendar and format
	 * 
	 * @param Calendar
	 *            to be formated, format string
	 * @return string format of the sql date.
	 */
	public static String getSqlString(Calendar p_cal, String p_format) {
		SimpleDateFormat formatter = new SimpleDateFormat();
		formatter.applyPattern(p_format);
		String oracleFormat = p_format.replaceAll("mm", "mi");
		oracleFormat = oracleFormat.replaceAll("HH", "hh24");
		oracleFormat = oracleFormat.replaceAll("MM", "mm");
		String p_fmtStr = "to_date('" + formatter.format(p_cal.getTime()) + "','" + oracleFormat + "')";
		return p_fmtStr;
	}

	/**
	 * returns the SQL format for a given Calendar and format
	 * 
	 * @param Timestamp
	 *            to be formated, format string
	 * @return string format of the sql date.
	 */
	public static String getSqlString(Timestamp p_cal, String p_format) {
		SimpleDateFormat formatter = new SimpleDateFormat();
		formatter.applyPattern(p_format);
		String oracleFormat = p_format.replaceAll("mm", "mi");
		oracleFormat = oracleFormat.replaceAll("HH", "hh24");
		oracleFormat = oracleFormat.replaceAll("MM", "mm");
		String p_fmtStr = "to_date('" + formatter.format(p_cal.getTime()) + "','" + oracleFormat + "')";
		return p_fmtStr;
	}

	/**
	 * Gives the difference between two Calendar [date&time] objects in minutes
	 * 
	 * @param p_assignStart -
	 *            Start date & time
	 * @param p_assignEnd -
	 *            End date and & time
	 * @return int Difference in minutes [ End Date - Start Date ]
	 */
	public static int getDiffernceInMin(Calendar p_StartTime, Calendar p_EndTime) {
		final int MILIS_PER_MIN = 60 * 1000;
		int differenceInMin = 0;
		differenceInMin = (int) ((p_EndTime.getTimeInMillis() - p_StartTime.getTimeInMillis()) / MILIS_PER_MIN);
		return differenceInMin;
	}

	public static String stringFromDouble(String value) {
		Double dblvalue = Double.parseDouble(value);
		if (dblvalue == 0.0){
			return " ";
		}
		else if (dblvalue == -1.0){
			return " ";
		}
		else{
			return (new Double(value)).toString();
		}
	}

	public static long getDate(String date1) {

		StringTokenizer st = new StringTokenizer(date1);
		String firstPart = st.nextToken();

		StringTokenizer strDate = new StringTokenizer(firstPart, "-");
		StringTokenizer stHour = new StringTokenizer(st.nextToken(), ":");
		int year = 0;
		int month = 0;
		int date = 0;
		int hour = 0;
		int min = 0;

		month = Integer.parseInt(strDate.nextToken());
		date = Integer.parseInt(strDate.nextToken());
		year = Integer.parseInt(strDate.nextToken());

		hour = Integer.parseInt(stHour.nextToken());
		min = Integer.parseInt(stHour.nextToken());

		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(Calendar.YEAR, year);
		calendar1.set(Calendar.MONTH, month - 1);
		calendar1.set(Calendar.DAY_OF_MONTH, date);
		calendar1.set(Calendar.HOUR_OF_DAY, hour);
		calendar1.set(Calendar.MINUTE, min);
		return calendar1.getTimeInMillis();
	}

	public static String getDateDiff(String date1, String date2) {
		long milliseconds1 = getDate(date1);
		long milliseconds2 = getDate(date2);
		long tempValue = 0;
		if (milliseconds1 > milliseconds2) {
			tempValue = milliseconds1;
			milliseconds1 = milliseconds2;
			milliseconds2 = tempValue;
		}
		long diff = milliseconds2 - milliseconds1;
		//long diffSeconds = diff / 1000;
		long diffMinutes = diff / (60 * 1000);
		long diffHours = diff / (60 * 60 * 1000);
		//long diffDays = diff / (24 * 60 * 60 * 1000);
		long finalValue = 0;
		String returnValue = "";
		finalValue = diffMinutes % 60;
		String finalStringValue = new Long(finalValue).toString();
		long firstDigit = (new Long((new Long(finalValue).toString()).substring(0, 1))).longValue();

		if (finalStringValue.length() == 1) {
			finalStringValue = "";
			finalStringValue = "0" + new Long(firstDigit).toString();
		} else {
			finalStringValue = "";
			finalStringValue = new Long(finalValue).toString();
		}
		returnValue = new Long(diffHours).toString() + "." + finalStringValue;
		return (new Double(returnValue)).toString();
	}

	public static String getTargetElapseTime(Calendar target_time, Calendar start_time, String time) throws ParseException {
		String targetTime = "";
		String startTime = "";
		targetTime = format(target_time, "MM-dd-yyyy HH:mm");
		startTime = format(start_time, "MM-dd-yyyy HH:mm");
		// GEALogger.dbg("","targetTime:"+targetTime);
		// GEALogger.dbg("","startTime:"+startTime);
		// GEALogger.dbg("","isTarget:"+"TARGET".equals(time));
		// GEALogger.dbg("","isElapse:"+"ELAPSE".equals(time));
		StringTokenizer tokenTargetElapseTime = null;
		if ("TARGET".equals(time)) {
			// GEALogger.dbg("","In Target");
			tokenTargetElapseTime = new StringTokenizer(stringFromDouble(getDateDiff(targetTime, startTime)), ".");
		}
		String timeLogic = "";
		if ("ELAPSE".equals(time)) {
			// GEALogger.dbg("","In Elapse");
			timeLogic = getCurrentTime("MM-dd-yyyy HH:mm");
			// GEALogger.dbg("","timeLogic:"+timeLogic);
			tokenTargetElapseTime = new StringTokenizer(stringFromDouble(getDateDiff(targetTime, timeLogic)), ".");
		}
		// GEALogger.dbg("","tokenTargetElapseTime:"+tokenTargetElapseTime);
		String targetElapseTime = "";
		if (tokenTargetElapseTime.hasMoreTokens()) {
			// GEALogger.dbg("","In tokeniser");
			String tokenHour = tokenTargetElapseTime.nextToken();
			// GEALogger.dbg("","tokenHour.length():"+tokenHour.length());
			if (tokenHour.trim().length() == 1) {
				targetElapseTime = "0" + tokenHour + ":";
			} else if (tokenHour.trim().length() == 0) {
				targetElapseTime = "00" + ":";
			} else {
				targetElapseTime = tokenHour + ":";
			}
			// GEALogger.dbg("","After padding tokenHour:"+tokenHour);
			if (tokenTargetElapseTime.hasMoreTokens()) {
				String tokenMin = tokenTargetElapseTime.nextToken();
				// GEALogger.dbg("","tokenMin:"+tokenMin);
				if (tokenMin.trim().length() == 1) {
					targetElapseTime += tokenMin + "0";
				} else {
					targetElapseTime += tokenMin;
				}
				// GEALogger.dbg("","After padding tokenHour:"+tokenHour);
			} else {
				targetElapseTime += "00";
			}
		}
		// GEALogger.dbg("","targetElapseTime:"+targetElapseTime);
		// GEALogger.dbg("","---------------------------");
		return targetElapseTime;
	}

	public static String getCurrentTime(String format) {
		SimpleDateFormat sdf = null;
		Date date = null;
		String currentTime = null;

		date = new Date();
		sdf = new SimpleDateFormat(format);
		currentTime = sdf.format(date);

		return currentTime;
	}

	public static long getCurrentDifferenceInHours(Calendar cal) {
		long diff = Calendar.getInstance().getTimeInMillis() - cal.getTimeInMillis();
		return (diff / (1000 * 60 * 60));
	}

	public static long getCurrentDifferenceInMins(Calendar cal) {
		long diff = Calendar.getInstance().getTimeInMillis() - cal.getTimeInMillis();
		return (diff / (1000 * 60));
	}

	public static int getDateDifferenceInMins(Calendar start, Calendar end) {
		long diff = end.getTimeInMillis() - start.getTimeInMillis();
		return ((int) diff / (1000 * 60));
	}

	public static long getCurrentDifferenceInSeconds(Calendar cal) {
		long diff = Calendar.getInstance().getTimeInMillis() - cal.getTimeInMillis();
		return (diff / (1000));
	}


	/**
	 * Parses a Date String , given in Format YYYY-MM-DD
	 * 
	 * @param dateString
	 * @return
	 * @throws Exception
	 */
	public static Date parseDate(String dateString) throws Exception {
		return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
	}

}
