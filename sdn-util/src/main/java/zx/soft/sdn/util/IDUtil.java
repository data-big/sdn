package zx.soft.sdn.util;

/**
 * ID工具类
 * 
 * @author xuran
 *
 */
public class IDUtil {

	/**
	 * 根据时间戳和随机数生成唯一字符串并采用MD5加密
	 * @return MD5值
	 */
	public static String generateUniqueID() {
		String timestamp = String.valueOf(System.currentTimeMillis());
		String redom = String.valueOf(Math.random()).substring(2);
		return MD5.MD5Encode(timestamp + redom);
	}
}
