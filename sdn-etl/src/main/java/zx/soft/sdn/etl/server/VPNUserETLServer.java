package zx.soft.sdn.etl.server;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.sdn.etl.handle.VPNUserETLHandle;

/**
 * VPN用户数据采集转换程序
 * 
 * @author xuran
 *
 */
public class VPNUserETLServer {

	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(VPNUserETLServer.class);

	/**
	 * 时间间隔 一天 
	 */
	private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;

	/**
	 * 主函数
	 */
	public static void main(String[] args) throws Exception {
		//设置执行时间为每天的凌晨三点
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 3);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date date = calendar.getTime();
		//如果第一次执行定时任务的时间小于当前的时间  
		//此时要在第一次执行定时任务的时间加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。  
		if (date.before(new Date())) {
			//			date = DateUtil.addDay(date, 1);
		}
		Timer timer = new Timer();
		//安排指定的任务在指定的时间开始进行重复的固定延迟执行。  
		timer.schedule(new ETLTask(), date, PERIOD_DAY);
		logger.info("VPNUserETLServer is runing ...............");
	}

	/**
	 * 定时任务
	 * 
	 * @author xuran
	 *
	 */
	static class ETLTask extends TimerTask {
		@Override
		public void run() {
			new VPNUserETLHandle().main();
		}
	}

}
