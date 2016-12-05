package zx.soft.sdn.util;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 多线程工具类
 *
 * @author xuran
 *
 */
public class ThreadPoolUtil {

	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(ThreadPoolUtil.class);

	/**
	 * 私有构造方法
	 */
	private ThreadPoolUtil(int size) {
		//创建有界队列线程池
		createThreadPoolExecutor(size);
	}

	/**
	 * 私有实例
	 */
	private static ThreadPoolUtil instance;

	/**
	 * 线程池
	 */
	private ThreadPoolExecutor threadPoolExecutor;

	/**
	 * 创建有界队列线程池
	 * @return ThreadPoolExecutor
	 */
	private void createThreadPoolExecutor(int size) {

		threadPoolExecutor = new ThreadPoolExecutor(size, size, 0L, TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<Runnable>(size * 2), new ThreadPoolExecutor.CallerRunsPolicy());
		//设置线程工厂
		threadPoolExecutor.setThreadFactory(new ThreadFactory() {
			//实现线程创建方法
			@Override
			public Thread newThread(Runnable r) {
				Thread thread = new Thread(r);
				//设置异常捕获处理方法
				thread.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
					//记录信息，并平滑关闭线程池。
					@Override
					public void uncaughtException(Thread thread, Throwable e) {
						e.printStackTrace();
						logger.error("Thread exception: " + thread.getName(), e);
						threadPoolExecutor.shutdown();
					}

				});
				return thread;
			}
		});

	}

	/**
	 * 获取有界队列线程池
	 * @return 有界队列线程池
	 */
	public ThreadPoolExecutor getThreadPoolExecutor() {
		return threadPoolExecutor;
	}

	/**
	 * 获取ThreadPoolUtil单例对象
	 * @return ThreadPool工具类单例对象
	 */
	public static ThreadPoolUtil getInstance(int size) {
		if (null == instance) {
			synchronized (ThreadPoolUtil.class) {
				if (null == instance)
					instance = new ThreadPoolUtil(size);
			}
		}
		return instance;
	}

}