package zx.soft.sdn.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Mybatis会话工厂
 * 
 * @author xuran
 *
 */
public class MybatisUtil {

	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(MybatisUtil.class);

	/**
	 * 私有构造方法
	 */
	private MybatisUtil() {
		//加载MyBatis配置文件创建会话工厂
		try (InputStream inputStream = MybatisUtil.class.getClassLoader().getResourceAsStream("mybatis-util.xml");) {
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			logger.error("Exception:{}", ExceptionUtil.exceptionToString(e));
			throw new RuntimeException(e);
		}
	}

	/**
	 * 私有实例
	 */
	private static MybatisUtil instance;

	/**
	 * 获取MybatisUtil单例对象
	 * @return MybatisUtil单例对象
	 */
	public static MybatisUtil getInstance() {
		if (null == instance) {
			synchronized (MybatisUtil.class) {
				if (null == instance)
					instance = new MybatisUtil();
			}
		}
		return instance;
	}

	/**
	 * 会话工厂
	 */
	private SqlSessionFactory sqlSessionFactory;

	/**
	 * 开启会话
	 * @return 会话
	 */
	public SqlSession openSession() {
		return sqlSessionFactory.openSession();
	}

}
