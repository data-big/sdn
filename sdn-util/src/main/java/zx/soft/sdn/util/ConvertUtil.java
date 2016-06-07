package zx.soft.sdn.util;

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
							if (!"class".equals(key)) {
								map.put(key, value);
							}
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
	 * 替换SQL IN字符串中的分隔符
	 * @param string SQL IN字符串
	 * @param split 分隔符
	 * @return 标准SQL IN 字符串 '1','2','3','4','5'  如果是非法SQL IN字符串则返回 '-1'
	 */
	public static String replaceSQLINSplit(String string, String split) {
		//如果满足正则表达式，格式为(1split2或1)的合法字符串。
		if (string.matches("^[0-9A-Za-z][0-9A-Za-z" + split + "]+[0-9A-Za-z]|[0-9A-Za-z]+$")) {
			string = string.replace(split, ",");
			String[] strings = string.split(",");
			StringBuilder stringBuilder = new StringBuilder();
			for (String str : strings) {
				stringBuilder.append("'").append(str).append("',");
			}
			string = stringBuilder.toString().substring(0, stringBuilder.length() - 1);
		} else {
			//如果是非法字符串设置非法默认值
			string = "'-1'";
		}
		return string;
	}
}
