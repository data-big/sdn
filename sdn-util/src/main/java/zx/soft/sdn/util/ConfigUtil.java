package zx.soft.sdn.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
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

	private static Logger logger = LoggerFactory.getLogger(ConfigUtil.class);

	/**
	 * classpath根目录下资源文件获取
	 * @param confFileName
	 * @return Properties
	 */
	public static Properties getProps(String confFileName) {
		Properties result = new Properties();
		logger.info("Load resource: " + confFileName);
		try (InputStream in = ConfigUtil.class.getClassLoader().getResourceAsStream(confFileName);) {
			result.load(in);
			return result;
		} catch (Exception e) {
			logger.error("Exception:{}", ExceptionUtil.exceptionToString(e));
			throw new RuntimeException(e);
		}
	}

	/**
	 * 自定义目录下资源文件获取
	 * @param confFileName
	 * @return Properties
	 */
	public static Properties getDictProps(String confFileName) {
		Properties result = new Properties();
		logger.info("Load resource: " + confFileName);
		ConfigUtil.class.getClassLoader();
		try (InputStream in = new FileInputStream(
				new File(new URI(ClassLoader.getSystemResource("") + confFileName)))) {
			result.load(in);
			return result;
		} catch (Exception e) {
			logger.error("Exception:{}", e);
			throw new RuntimeException(e);
		}
	}
}
