package zx.soft.sdn.api.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SpringMVC控制器切面，记录运行日志。
 * 
 * @author xuran
 *
 */
public class ControllerAspect {

	/**
	 * 日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		long begin = System.currentTimeMillis();
		if (logger.isInfoEnabled()) {
			logger.info("开始执行:{}", joinPoint.toShortString());
		}
		Object object = joinPoint.proceed();
		long end = System.currentTimeMillis();
		if (logger.isInfoEnabled()) {
			logger.info("{}[耗时:{}]", joinPoint.toShortString(), (end - begin));
		}
		return object;
	}

}