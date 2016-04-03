package com.flur.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 日期操作工具类
 * 
 * @author wx
 * 
 */
public class DateUtil {

	private static final DateFormat FORMATER_DATE = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static final DateFormat FORMATER_DATE_YMD = new SimpleDateFormat("yyyy-MM-dd");

	private static final DateFormat FORMATER_MINUTE = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	private static final DateFormat FORMATER_DAY = new SimpleDateFormat("MM.dd");

	/**
	 * 获取当前时间
	 */
	public static Date getCurruntDate() {
		Calendar ca = Calendar.getInstance();
		return ca.getTime();
	}

	/**
	 * 返回当前时间的"yyyy-MM-dd hh:mm:ss"格式字符串
	 * */
	public static String currentTime() {
		return FORMATER_DATE.format(new Date());
	}

	/**
	 * 解析日期
	 * 
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static Date parseTime(String time) throws ParseException {
		Date date = FORMATER_DATE.parse(time);
		return date;
	}
	
	/**
	 * 解析日期
	 * 
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static Date parseTimeYMD(String time) throws ParseException {
		FORMATER_DATE_YMD.setLenient(false);
		Date date = FORMATER_DATE_YMD.parse(time);
		return date;
	}
	
	/**
	 * 解析日期
	 * 
	 * @param time
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date parseTime(String time, String format) throws ParseException {
		DateFormat formater = new SimpleDateFormat(format);
		return formater.parse(time);
	}

	/**
	 * 解析日期
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDate(String date) throws ParseException {
		DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		return formater.parse(date);
	}

	/**
	 * 将Date转为字符串
	 * 
	 * @param dateStr
	 * @param formatStr
	 * @return
	 */
	public static String formatTime(Date date, String formatStr) {
		if (date == null) {
			return "";
		} else {
			SimpleDateFormat format = new SimpleDateFormat(formatStr, Locale.US);
			String d = format.format(date);
			return d;
		}
	}

	/**
	 * Date转为字符串，默认格式
	 * 
	 * @param date
	 * @return
	 */
	public static String formatTime(Date date) {
		return FORMATER_DATE.format(date);
	}

	/**
	 * 把一个日期字符串转换成指定日期格式
	 * 
	 * @param timeStr
	 *            日期字符串
	 * @param pattern
	 *            日期格式
	 * @return String
	 * @throws ParseException
	 */
	public static String formatTime(String timeStr, String pattern) throws ParseException {
		return formatTime(parseTime(timeStr), pattern);
	}

	/**
	 * 返回当前时间的"yyyy-MM-dd"格式字符串
	 * */
	public static String currentDay() {
		DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		return formater.format(new Date());
	}

	/**
	 * 比较两个日期
	 */
	public static boolean compareTwoDate(Date date1, Date date2) {
		Calendar ca1 = Calendar.getInstance();
		Calendar ca2 = Calendar.getInstance();
		ca1.setTime(date1);
		ca2.setTime(date2);
		return ca1.after(ca2);
	}

	/**
	 * 某个日期和当前时间做比较
	 */
	public static boolean compareToCurrent(Date date) {
		Calendar ca1 = Calendar.getInstance();
		Calendar ca2 = Calendar.getInstance();
		ca1.setTime(date);
		return ca1.after(ca2);
	}

	/**
	 * 两个时间是否相等
	 */
	public static boolean equalTwoDate(Date date1, Date date2) {
		Calendar ca1 = Calendar.getInstance();
		Calendar ca2 = Calendar.getInstance();
		ca1.setTime(date1);
		ca2.setTime(date2);
		return ca1.equals(ca2);
	}

	/**
	 * 获得下一星期的日期
	 */
	public static Date getNextWeek() {
		Calendar ca = Calendar.getInstance();
		ca.add(Calendar.DAY_OF_YEAR, 7);
		return ca.getTime();
	}

	/**
	 * 获得当前日期的移动间隔日期
	 */
	public static Date getTheDay(int days) {
		Calendar ca = Calendar.getInstance();
		ca.set(2010, 1, 1);
		ca.add(Calendar.DAY_OF_YEAR, days);
		return ca.getTime();
	}

	public static Date getTheMonth(int months) {
		Calendar ca = Calendar.getInstance();
		ca.add(Calendar.MONTH, months);
		return ca.getTime();
	}

	/**
	 * 获取过去某一时间点与当期相隔的分钟数
	 */
	public static long calendarDiffMinutes(String timeStr) {
		if (timeStr == null || timeStr.trim().equals("")) {
			return 0;
		}
		Date date;
		try {
			date = DateUtil.parseTime(timeStr);
			return calendarDiffMinutes(date);
		} catch (ParseException e) {
			return 0;
		}
	}

	/**
	 * 计算两个任意时间中间的间隔时间：XX 天X X小时XX 分钟
	 */
	public static List<Integer> getDaysBetween(Date date1, Date date2) {
		Calendar startday = Calendar.getInstance();
		Calendar endday = Calendar.getInstance();
		startday.setTime(date1);
		endday.setTime(date2);
		List<Integer> times = new ArrayList<Integer>();
		if (startday.after(endday)) {
			Calendar cal = startday;
			startday = endday;
			endday = cal;
		}
		long sl = startday.getTimeInMillis();
		long el = endday.getTimeInMillis();

		long ei = el - sl;
		int days = (int) (ei / (1000 * 60 * 60 * 24));
		int hours = (int) ((ei % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
		int minutes = (int) ((ei % (1000 * 60 * 60)) / (1000 * 60));
		times.add(days);
		times.add(hours);
		times.add(minutes);
		return times;
	}

	/**
	 * 获取过去某一时间点与当期相隔的分钟数
	 */
	public static long calendarDiffMinutes(Date date) {
		Calendar ca1 = Calendar.getInstance();
		ca1.setTime(date);
		long time = ca1.getTimeInMillis();
		Calendar ca2 = Calendar.getInstance();
		long now = ca2.getTimeInMillis();
		return (now - time) / 60000;
	}

	/**
	 * 得到几天前的时间
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static String getDateBefore(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(now.getTime());
	}

	/**
	 * 得到几分钟前的时间
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static String getMinutesBefore(Date d, int minutes) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.MINUTE, now.get(Calendar.MINUTE) - minutes);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(now.getTime());
	}
	
	/**
	 * 格式化成11.04格式
	 * @param time
	 * @return
	 */
	public static String formatDay(String time){
		Date d = null;
		try {
			d = DateUtil.parseTime(time);
		} catch (ParseException e) {
			return time;
		}
		return FORMATER_DAY.format(d);
	}

	/**
	 * 友好显示时间 几天前、几小时前等
	 * 
	 * @param sdate
	 * 
	 * @return
	 */
	public static String friendlyTime(String date) {
		if (date == null) {
			return null;
		}
		try {
			// 获取当前时间与date相差的毫秒数
			Date d = parseTime(date);
			Date now = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(now);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			Date today = cal.getTime();
			
			long diff = d.getTime() - today.getTime();
			if(diff > 0){
				//今天
				SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
				return "今天 " + formatter.format(d);
			}else{
				//今天以前的时间
				cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) - 1);
				Date yesterday = cal.getTime();
				diff = d.getTime() - yesterday.getTime();
				if(diff > 0){
					//昨天
					SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
					return "昨天 " + formatter.format(d);
				}else{
					return FORMATER_MINUTE.format(d);
				}
			}
		} catch (Exception e) {
			return date;
		}
	}

	/**
	 * 当前是周几
	 * @return
	 */
	public static String getCurrentWeek(){
		Calendar calendar = Calendar.getInstance();//获得一个日历
		Calendar c = Calendar.getInstance();
		c.setTime(new Date(System.currentTimeMillis()));
	    int number = calendar.get(Calendar.DAY_OF_WEEK);//星期表示1-7，是从星期日开始，   
	    String [] str = {"","星期日","星期一","星期二","星期三","星期四","星期五","星期六",};
		return str[number];
	}
	
	/**
	 * 和当前时间相比 有几周
	 * @param date
	 * @return
	 */
/*	public static Long getWeekCount(String date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		try {
			 Date d1 = sdf.parse(date);
			 Long daysBetween = (new Date().getTime()-d1.getTime()+1000000)/(3600*24*1000);
			 if(daysBetween<7){
				 return Long.parseLong("1");
			 }
			 return daysBetween % 7 == 0 ? daysBetween / 7 :(daysBetween/7+1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}*/
}
