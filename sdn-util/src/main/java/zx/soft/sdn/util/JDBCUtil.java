package zx.soft.sdn.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JDBC工具类
 * 
 * @author xuran
 *
 */
public class JDBCUtil {

	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(JDBCUtil.class);

	/**
	 * 私有实例
	 */
	private static JDBCUtil instance;

	/**
	 * 配置
	 */
	private Properties config;

	/**
	 * SDN库数据源
	 */
	private DataSource sdnDataSource;

	/**
	 * BS库数据源
	 */
	private DataSource bsDataSource;

	/**
	 * 构造方法
	 */
	private JDBCUtil() {
		//读取数据库配置文件
		config = ConfigUtil.getProps("db.properties");
		//创建SDN库数据源
		BasicDataSource sdnBasicDataSource = new BasicDataSource();
		sdnBasicDataSource.setDriverClassName(config.getProperty("jdbc.driver"));
		sdnBasicDataSource.setUrl(config.getProperty("jdbc.url"));
		sdnBasicDataSource.setUsername(config.getProperty("jdbc.username"));
		sdnBasicDataSource.setPassword(config.getProperty("jdbc.password"));
		sdnBasicDataSource.setInitialSize(60);
		sdnBasicDataSource.setMaxActive(100);
		sdnBasicDataSource.setMaxIdle(50);
		sdnBasicDataSource.setMinIdle(10);
		sdnBasicDataSource.setTestWhileIdle(true);
		sdnBasicDataSource.setTestOnBorrow(false);
		sdnBasicDataSource.setTestOnReturn(false);
		sdnBasicDataSource.setValidationQuery("select 1");
		sdnBasicDataSource.setTimeBetweenEvictionRunsMillis(20000);
		sdnBasicDataSource.setNumTestsPerEvictionRun(100);
		sdnDataSource = sdnBasicDataSource;
		//创建BS库数据源
		BasicDataSource bsBasicDataSource = new BasicDataSource();
		bsBasicDataSource.setDriverClassName(config.getProperty("bs.jdbc.driver"));
		bsBasicDataSource.setUrl(config.getProperty("bs.jdbc.url"));
		bsBasicDataSource.setUsername(config.getProperty("bs.jdbc.username"));
		bsBasicDataSource.setPassword(config.getProperty("bs.jdbc.password"));
		bsBasicDataSource.setInitialSize(60);
		bsBasicDataSource.setMaxActive(100);
		bsBasicDataSource.setMaxIdle(50);
		bsBasicDataSource.setMinIdle(10);
		bsBasicDataSource.setTestWhileIdle(true);
		bsBasicDataSource.setTestOnBorrow(false);
		bsBasicDataSource.setTestOnReturn(false);
		bsBasicDataSource.setValidationQuery("select 1");
		bsBasicDataSource.setTimeBetweenEvictionRunsMillis(20000);
		bsBasicDataSource.setNumTestsPerEvictionRun(100);
		bsDataSource = bsBasicDataSource;
	}

	/**
	 * 获取JDBCUtil单例对象
	 * @return JDBC工具类单例对象
	 */
	public static JDBCUtil getInstance() {
		if (null == instance) {
			synchronized (JDBCUtil.class) {
				if (null == instance)
					instance = new JDBCUtil();
			}
		}
		return instance;
	}

	/**
	 * 获得SDN数据库连接
	 * @return SDN数据库连接
	 */
	public Connection getSDNConnection() {
		Connection connection = null;
		try {
			connection = sdnDataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception : getSDNConnection error {}", ExceptionUtil.exceptionToString(e));
		}
		return connection;
	}

	/**
	 * 获得BS数据库连接
	 * @return return BS数据库连接
	 */
	public Connection getBSConnection() {
		Connection connection = null;
		try {
			connection = bsDataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception : getSDNConnection error {}", ExceptionUtil.exceptionToString(e));
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
			if (null != resultSet && !resultSet.isClosed()) {
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
