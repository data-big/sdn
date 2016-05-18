package zx.soft.sdn.api.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 对象转换工具类
 * 
 * @author xuran
 *
 */
public class ConvertUtil {

	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(ConvertUtil.class);

	/**
	 * 将对象转换成Map
	 * @param object 对象
	 * @return Map
	 */
	public static Map<String, Object> parseMap(Object object) {
		Map<String, Object> map = new HashMap<String, Object>();
		Class<?> clas = null;
		try {
			clas = Class.forName(object.getClass().getName());
			Method[] m = clas.getMethods();
			for (int i = 0; i < m.length; i++) {
				String method = m[i].getName();
				if (method.startsWith("get")) {
					try {
						Object value = m[i].invoke(object);
						if (value != null) {
							String key = method.substring(3);
							key = key.substring(0, 1).toLowerCase() + key.substring(1);
							if (!"class".equals(key))
								map.put(key, value);
						}
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("Exception : {}", ExceptionUtil.exceptionToString(e));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception : {}", ExceptionUtil.exceptionToString(e));
		}
		return map;
	}

	/**
	 * 将字符串中&替换成,
	 * @param string
	 * @return
	 */
	public static String replaceSplit(String string) {
		String[] array = string.split("&");
		StringBuilder stringBuilder = new StringBuilder();
		for (String str : array) {
			stringBuilder.append(str).append(",");
		}
		string = stringBuilder.toString();
		return string.substring(0, string.length() - 1);
	}

}
