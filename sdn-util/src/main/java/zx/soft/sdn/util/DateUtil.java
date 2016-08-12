package zx.soft.sdn.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期工具类
 * 
 * @author xuran
 *
 */
public class DateUtil {

	/**日期格式2016-01-01 00:00:00**/
	public static DateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

	/**日期格式2016-01-01**/
	public static DateFormat yyyyMMddFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

	/**日期格式2016-01-01 00:**/
	public static DateFormat yyyyMMddHHFormat = new SimpleDateFormat("yyyy-MM-dd HH", Locale.ENGLISH);

	/**
	 * 日期后推
	 * @param date 待处理日期
	 * @param num 天数
	 * @return 新日期
	 */
	public static Date addDay(Date date, int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return startDT.getTime();
	}

	/**
	 * 获取上个小时的时间区间
	 * @return yyyy-MM-dd HH:mm:ss
	 * @throws ParseException 
	 */
	public static String getLastHour() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String date = yyyyMMddHHFormat.format(calendar.getTime());
		return date + ":00:00," + date + ":59:59";
	}

	/**
	 * 获取昨天的时间区间
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getYesterDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 1);
		String date = yyyyMMddFormat.format(calendar.getTime());
		return date + " 00:00:00," + date + " 23:59:59";
	}

	/**
	 * 获取上周的时间区间
	 * @return yyyy-MM-dd HH:mm:ss,yyyy-MM-dd HH:mm:ss
	 * @throws ParseException 
	 */
	@SuppressWarnings("static-access")
	public static String getLastWeek() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(calendar.DATE, -7);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		String dateStart = yyyyMMddFormat.format(calendar.getTime());
		String dateEnd = yyyyMMddFormat.format(addDay(calendar.getTime(), 6));
		return dateStart + " 00:00:00," + dateEnd + " 23:59:59";
	}

	/**
	 * 获取上月的时间区间
	 * @return yyyy-MM-dd HH:mm:ss,yyyy-MM-dd HH:mm:ss
	 */
	public static String getLastMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		String dateStart = yyyyMMddFormat.format(calendar.getTime());
		calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		String dateEnd = yyyyMMddFormat.format(calendar.getTime());
		return dateStart + " 00:00:00," + dateEnd + " 23:59:59";
	}

	public static void main(String[] args) {
		System.out.println(getLastHour());
		System.out.println(getYesterDay());
		System.out.println(getLastWeek());
		System.out.println(getLastMonth());
	}

}
