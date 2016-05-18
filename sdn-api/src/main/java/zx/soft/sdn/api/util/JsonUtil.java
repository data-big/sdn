package zx.soft.sdn.api.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON工具类
 * 
 * @author xuran
 *
 */
public class JsonUtil {

	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

	/**
	 * 对象转换JSON字符串
	 * @param object 数据对象
	 * @return 成功返回json字符串，失败返回null
	 */
	public static String parseString(Object object) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(object);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Exception : {}", ExceptionUtil.exceptionToString(e));
			return null;
		}
	}

	/**
	 * Json字符串转换成JavaBean
	 * @param json json字符串
	 * @param cla 类
	 * @return JavaBean
	 */
	public static <T> T parseBean(String json, Class<T> cla) {
		ObjectMapper objectMapper = new ObjectMapper();
		T t = null;
		try {
			t = objectMapper.readValue(json, cla);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception : {}", ExceptionUtil.exceptionToString(e));
		}
		return t;
	}
}
