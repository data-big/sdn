package zx.soft.sdn.util;

/**
 * 异常工具类
 * 
 * @author xuran
 *
 */
public class ExceptionUtil {

	/**
	 * 异常对象转换为字符串信息
	 * @param e 异常对象
	 * @return 异常信息
	 */
	public static String exceptionToString(Exception e) {
		String result = e.getMessage() + ";";
		for (StackTraceElement stack : e.getStackTrace()) {
			result += stack.toString() + ";";
		}
		return result.substring(0, result.length() - 1).replaceAll("\n", ",");
	}

}
