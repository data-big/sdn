package zx.soft.sdn.cache.util;

import java.io.IOException;
import java.io.StringWriter;

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
		StringWriter stringWrite = new StringWriter();
		try {
			objectMapper.writeValue(objectMapper.getFactory().createGenerator(stringWrite), object);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Exception : {}", ExceptionUtil.exceptionToString(e));
			return null;
		}
		return stringWrite.toString();
	}
}
