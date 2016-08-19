package zx.soft.sdn.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hive工具类
 * 
 * @author xuran
 *
 */
public class HiveUtil {

	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(HiveUtil.class);

	/**
	 * 私有实例
	 */
	private static HiveUtil instance;

	/**
	 * 私有构造方法
	 */
	private HiveUtil() {
	}

	/**
	 * 获取HiveUtil单例对象
	 * @return Hive工具类单例对象
	 */
	public static HiveUtil getInstance() {
		if (null == instance) {
			synchronized (HiveUtil.class) {
				if (null == instance)
					instance = new HiveUtil();
			}
		}
		return instance;
	}

	/**
	 * 获取Hive连接
	 * @return Hive连接
	 */
	public Connection getConnection() {
		Connection connection = null;
		try {
			Properties config = ConfigUtil.getProps("hive.properties");
			Class.forName(config.getProperty("hive.jdbc.driver"));
			connection = DriverManager.getConnection(config.getProperty("hive.jdbc.url"),
					config.getProperty("hive.jdbc.username"), config.getProperty("hive.jdbc.password"));
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Exception : {}", ExceptionUtil.exceptionToString(e));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.error("Exception : {}", ExceptionUtil.exceptionToString(e));
		}
		return connection;
	}

	/**
	 * 关闭资源
	 * @param resultSet 结果集
	 * @param preparedStatement SQL声明
	 * @param connection 连接
	 */
	public void close(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {

		try {
			if (null != resultSet) {
				resultSet.close();
			}
			if (null != preparedStatement && !preparedStatement.isClosed()) {
				preparedStatement.close();
			}
			if (null != connection && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Exception : {}", ExceptionUtil.exceptionToString(e));
		}

	}

}
