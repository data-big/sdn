package zx.soft.sdn.util;

import java.text.DateFormat;
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

}
