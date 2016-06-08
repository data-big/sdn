package zx.soft.sdn.etl.handle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.sdn.model.VPNPostion;
import zx.soft.sdn.util.ConfigUtil;
import zx.soft.sdn.util.HttpClientUtil;
import zx.soft.sdn.util.JsonUtil;

/**
 * 文件处理类
 * 
 * @author xuran
 *
 */
public class FileHandle {

	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(FileHandle.class);

	/**
	 * 文件处理
	 * 
	 * @param type
	 */
	public void handle(String type) {
		//获取主目录
		File mainDirectory = new File(ConfigUtil.getProps("config.properties").getProperty("file.dir"));
		//获取主目录下的子目录
		File[] directoryArray = mainDirectory.listFiles();
		//获取和业务类型一致的子目录下所有的文件
		File[] fileArray = null;
		for (File file : directoryArray) {
			if (file.isDirectory() && type.equals(file.getName()))
				fileArray = file.listFiles();
		}
		//业务分发
		switch (type) {
		case "vpnuser":
			break;
		case "vpncard":
			break;
		case "vpnpostion":
			vpnPostionHandle(fileArray);
			break;
		}

	}

	/**
	 * VPN用户地理位置文件解析并存储
	 */
	private void vpnPostionHandle(File[] fileArray) {
		//存储接口
		String saveAPI = ConfigUtil.getProps("config.properties").getProperty("opentsdb.vpnpostion.add.api");
		//遍历文件
		for (File file : fileArray) {
			try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));) {
				while (bufferedReader.read() > 0) {
					//数据转换
					String[] data = bufferedReader.readLine().split(",");
					VPNPostion vpnPostion = new VPNPostion();
					vpnPostion.setRealNumber(data[0]);
					vpnPostion.setSac(data[1]);
					vpnPostion.setLac(data[2]);
					vpnPostion.setTime(data[3]);
					String json = JsonUtil.parseString(vpnPostion);
					//创建线程池
					BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
					ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 10, TimeUnit.MINUTES, queue);
					HttpClientUtil.doPost(saveAPI, json, "json");
				}
			} catch (Exception e) {
				logger.error("Exception : {} 文件解析存储失败", file.getName());
			}

		}

	}

}
