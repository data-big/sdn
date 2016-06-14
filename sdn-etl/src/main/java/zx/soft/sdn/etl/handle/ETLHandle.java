package zx.soft.sdn.etl.handle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.sdn.etl.component.Business;
import zx.soft.sdn.model.VPNCard;
import zx.soft.sdn.model.VPNPostion;
import zx.soft.sdn.model.VPNUser;
import zx.soft.sdn.util.ConfigUtil;
import zx.soft.sdn.util.DateUtil;
import zx.soft.sdn.util.FileUtil;
import zx.soft.sdn.util.HttpClientUtil;
import zx.soft.sdn.util.JsonUtil;

/**
 * 数据处理
 * 
 * @author xuran
 *
 */
public class ETLHandle {

	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(ETLHandle.class);

	/**
	 * 配置信息
	 */
	private static Properties config = ConfigUtil.getProps("config.properties");

	/**
	 * 文件处理
	 * 
	 * @param type 业务类型
	 */
	public void work(Business type) {
		//获取主目录
		File mainDirectory = new File(config.getProperty("file.dir"));
		//获取主目录下的子目录
		File[] directoryArray = mainDirectory.listFiles();
		//检查主目录是否存在
		if (null != directoryArray) {
			//获取和业务类型一致的子目录下所有的文件
			File[] fileArray = null;
			for (File file : directoryArray) {
				if (file.isDirectory() && type.value().equals(file.getName()))
					fileArray = file.listFiles();
			}
			//检查业务目录是否存在
			if (null != fileArray) {
				//业务分发
				switch (type) {
				//VPN卡业务
				case VPNCARD:
					vpnCardHandle(fileArray);
					break;
				//VPN用户业务
				case VPNUSER:
					vpnUserHandle(fileArray);
					break;
				//VPN用户的地理位置业务
				case VPNPOSTION:
					vpnPostionHandle(fileArray);
					break;
				}
			} else {
				logger.error("Exception : {}目录不存在", config.getProperty("file.dir") + type.value());
			}
		} else {
			logger.error("Exception : {}目录不存在", config.getProperty("file.dir"));
		}

	}

	/**
	 * VPN用户数据文件解析并存储
	 * @param fileArray VPN用户数据文件数组
	 */
	private void vpnCardHandle(File[] fileArray) {
		//存储接口
		String saveAPI = config.getProperty("mysql.vpncard.add.api");
		//遍历文件
		for (File file : fileArray) {
			//读取文件
			try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));) {
				//读取行
				String lineText = null;
				while ((lineText = bufferedReader.readLine()) != null) {
					//数据转换
					String[] data = lineText.split(",");
					VPNCard vpnCard = new VPNCard();
					vpnCard.setRealNumber(data[0]);
					vpnCard.setBizIP(data[1]);
					vpnCard.setStopIP(data[2]);
					vpnCard.setSpecialIP(data[3]);
					vpnCard.setOffsetBizIP(data[4]);
					vpnCard.setOffsetStopIP(data[5]);
					vpnCard.setOffsetSpecialIP(data[6]);
					vpnCard.setInsertDate(data[7]);
					String json = JsonUtil.parseString(vpnCard);
					//提交数据
					BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
					ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 10, TimeUnit.MINUTES, queue);
					executor.execute(new PostRunnable(saveAPI, json, "json"));
					logger.info("****RealNmuber={},InsertDate{}的VPN卡数据处理成功****", vpnCard.getRealNumber(),
							vpnCard.getInsertDate());
				}
				//文件转移
				FileUtil.move(file,
						FileUtil.getFileDirectory(file) + "finish/" + DateUtil.yyyyMMddFormat.format(new Date()) + "/");
				logger.info("****VPN卡数据文件{}处理成功****", file.getName());
			} catch (Exception e) {
				logger.error("Exception : {}VPN卡数据文件处理失败", file.getName());
			}

		}

	}

	/**
	 * VPN用户数据文件解析并存储
	 * @param fileArray VPN用户数据文件数组
	 */
	private void vpnUserHandle(File[] fileArray) {
		//存储接口
		String saveAPI = config.getProperty("mysql.vpnuser.add.api");
		//遍历文件
		for (File file : fileArray) {
			//读取文件
			try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));) {
				//读取行
				String lineText = null;
				while ((lineText = bufferedReader.readLine()) != null) {
					//数据转换
					String[] data = lineText.split(",");
					VPNUser vpnUser = new VPNUser();
					vpnUser.setRealNumber(data[0]);
					vpnUser.setSponsorNumber(data[1]);
					vpnUser.setSponsorName(data[2]);
					vpnUser.setSponsorIDType(Integer.valueOf(data[3]));
					vpnUser.setSponsorIDNumber(data[4]);
					vpnUser.setUserNumber(data[5]);
					vpnUser.setUserName(data[6]);
					vpnUser.setUserIDType(Integer.valueOf(data[7]));
					vpnUser.setUserIDNumber(data[8]);
					vpnUser.setRegisterDate(data[9]);
					vpnUser.setCancelDate(data[10]);
					vpnUser.setRegisterAgent(data[11]);
					vpnUser.setModifyType(Integer.valueOf(data[12]));
					vpnUser.setModifyDate(data[13]);
					String json = JsonUtil.parseString(vpnUser);
					//提交数据
					BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
					ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 10, TimeUnit.MINUTES, queue);
					executor.execute(new PostRunnable(saveAPI, json, "json"));
					logger.info("****RealNmuber={},RegisterDate={}的VPN用户数据处理成功****", vpnUser.getRealNumber(),
							vpnUser.getRegisterDate());
				}
				//文件转移
				FileUtil.move(file,
						FileUtil.getFileDirectory(file) + "finish/" + DateUtil.yyyyMMddFormat.format(new Date()) + "/");
				logger.info("****VPN用户数据文件{}处理成功****", file.getName());
			} catch (Exception e) {
				logger.error("Exception : {}VPN用户数据文件处理失败", file.getName());
			}

		}

	}

	/**
	 * VPN用户地理位置数据文件解析并存储
	 * @param fileArray VPN用户地理位置数据文件数组
	 */
	private void vpnPostionHandle(File[] fileArray) {
		//存储接口
		String saveAPI = config.getProperty("opentsdb.vpnpostion.add.api");
		//遍历文件
		for (File file : fileArray) {
			//读取文件
			try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));) {
				//读取行
				String lineText = null;
				while ((lineText = bufferedReader.readLine()) != null) {
					//数据转换
					String[] data = lineText.split(",");
					VPNPostion vpnPostion = new VPNPostion();
					vpnPostion.setRealNumber(data[0]);
					vpnPostion.setBizIP(data[1]);
					vpnPostion.setSac(data[2]);
					vpnPostion.setLac(data[3]);
					vpnPostion.setTime(data[4]);
					String json = JsonUtil.parseString(vpnPostion);
					//提交数据
					BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
					ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 10, TimeUnit.MINUTES, queue);
					executor.execute(new PostRunnable(saveAPI, json, "json"));
					logger.info("****RealNmuber={},Time={}的地理位置数据处理成功****", vpnPostion.getRealNumber(),
							vpnPostion.getTime());
				}
				//文件转移
				FileUtil.move(file,
						FileUtil.getFileDirectory(file) + "finish/" + DateUtil.yyyyMMddFormat.format(new Date()) + "/");
				logger.info("****VPN用户地理位置数据文件{}处理成功****", file.getName());
			} catch (Exception e) {
				logger.error("Exception : {} VPN用户地理位置数据文件处理失败", file.getName());
			}

		}

	}

	/**
	 * POST数据提交线程
	 * 
	 * @author xuran
	 *
	 */
	class PostRunnable implements Runnable {

		public PostRunnable(String api, String data, String dataType) {
			this.api = api;
			this.data = data;
			this.dataType = dataType;
		}

		/**接口**/
		private String api;
		/**数据**/
		private String data;
		/**数据类型**/
		private String dataType;

		@Override
		public void run() {
			HttpClientUtil.doPost(api, data, dataType);
		}

	}
}
