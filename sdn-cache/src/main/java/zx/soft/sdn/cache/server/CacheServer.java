package zx.soft.sdn.cache.server;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.sdn.cache.handle.CountHandle;
import zx.soft.sdn.util.DateUtil;
import zx.soft.sdn.util.ExceptionUtil;

/**
 * 启动缓存
 * 
 * @author xuran
 *
 */
public class CacheServer {

	private static Logger logger = LoggerFactory.getLogger(CacheServer.class);

	/**
	 * 主函数
	 */
	public static void main(String[] args) throws Exception {
		//一小时统计一次用户分布情况
		Timer timer = new Timer();
		timer.schedule(new CacheTask(), 0, 3600 * 1000);
		logger.info("CacheServer is runing ...............");
	}

	/**
	 * 定时任务
	 * 
	 * @author xuran
	 *
	 */
	static class CacheTask extends TimerTask {

		//任务是否执行结束
		private static boolean taskOver = true;

		@Override
		public void run() {
			if (taskOver) {
				taskOver = false;
				try {
					String[] array = null;
					String start;
					String end;
					//统计一小时前的用户分布情况
					array = DateUtil.getLastHour().split(",");
					start = array[0];
					end = array[1];
					CountHandle.countPartByAnHui("hour", start, end);
					logger.info("****一小时前的用户分布情况统计成功****");
					//每天凌晨一点前执行
					if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) < 2) {
						//统计昨天的用户分布情况
						array = DateUtil.getYesterDay().split(",");
						start = array[0];
						end = array[1];
						CountHandle.countPartByAnHui("yesterday", start, end);
						logger.info("****昨天的用户分布情况统计成功****");
						//统计上周的用户分布情况
						array = DateUtil.getLastWeek().split(",");
						start = array[0];
						end = array[1];
						CountHandle.countPartByAnHui("week", start, end);
						logger.info("****上周的用户分布情况统计成功****");
						//统计上个月的用户分布情况
						array = DateUtil.getLastMonth().split(",");
						start = array[0];
						end = array[1];
						CountHandle.countPartByAnHui("month", start, end);
						logger.info("****上个月的用户分布情况统计成功****");
					}
					taskOver = true;
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("Exception : {}", ExceptionUtil.exceptionToString(e));
				}
				taskOver = true;
			}
		}

	}

}
