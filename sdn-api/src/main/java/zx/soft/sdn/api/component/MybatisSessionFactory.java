package zx.soft.sdn.api.component;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.sdn.util.ExceptionUtil;

/**
 * Mybatis会话工厂
 * 
 * @author xuran
 *
 */
public class MybatisSessionFactory {

	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(MybatisSessionFactory.class);

	/**
	 * 会话工厂
	 */
	private static SqlSessionFactory sqlSessionFactory;

	/**
	 * 启动加载MyBatis配置文件创建会话工厂
	 */
	static {
		try (InputStream inputStream = MybatisSessionFactory.class.getClassLoader()
				.getResourceAsStream("basestation-mybatis-config.xml");) {
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			logger.error("Exception:{}", ExceptionUtil.exceptionToString(e));
			throw new RuntimeException(e);
		}
	}

	/**
	 * 开启会话
	 * @return 会话
	 */
	public static SqlSession openSession() {
		return sqlSessionFactory.openSession();
	}

}
