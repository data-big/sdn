package zx.soft.sdn.etl.server;

import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.sdn.etl.handle.VPNPostionETLHandle;
import zx.soft.sdn.util.ExceptionUtil;

/**
 * VPN地理位置数据采集转换程序
 * 
 * @author xuran
 *
 */
public class VPNPostionETLServer {

	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(VPNPostionETLServer.class);

	/**
	 * 主函数
	 */
	public static void main(String[] args) throws Exception {
		//一小时处理一次
		Timer timer = new Timer();
		timer.schedule(new ETLTask(), 0, 180 * 1000);
		logger.info("VPNPostionETLServer is runing ...............");
	}

	/**
	 * 定时任务
	 * 
	 * @author xuran
	 *
	 */
	static class ETLTask extends TimerTask {

		//任务是否执行结束
		private static boolean taskOver = true;

		@Override
		public void run() {
			if (taskOver) {
				taskOver = false;
				try {
					new VPNPostionETLHandle().main();
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("Exception : {}", ExceptionUtil.exceptionToString(e));
				}
				taskOver = true;
			}
		}

	}
}
