package zx.soft.sdn.util;

/**
 * 算数工具类
 * 
 * @author xuran
 *
 */
public class MathUtil {

	/**
	 * 获取Long数组中的最大值
	 * @param array long数组
	 * @return 最大值
	 */
	public static Long getMAX(Long[] array) {
		Long result = 0L;
		for (Long value : array) {
			if (value > result)
				result = value;
		}
		return result;
	}

}
