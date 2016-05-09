package zx.soft.sdn.cache.util;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 配置文件读取类
 * 
 * @author xuran
 *
 */
public class ConfigUtil {

	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(ConfigUtil.class);

	/**
	 * 获取配置对象
	 * @param confFileName
	 * @return 配置对象
	 */
	public static Properties getProps(String confFileName) {
		Properties result = new Properties();
		logger.info("load resource : " + confFileName);
		try (InputStream in = ConfigUtil.class.getClassLoader().getResourceAsStream(confFileName);) {
			result.load(in);
			return result;
		} catch (Exception e) {
			logger.error("Exception : {}", ExceptionUtil.exceptionToString(e));
			throw new RuntimeException(e);
		}
	}

}
