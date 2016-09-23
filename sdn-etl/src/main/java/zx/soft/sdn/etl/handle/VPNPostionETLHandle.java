package zx.soft.sdn.etl.handle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.sdn.etl.component.Business;
import zx.soft.sdn.etl.dao.VPNPostionDao;
import zx.soft.sdn.etl.dao.impl.VPNPostionDaoImpl;
import zx.soft.sdn.model.VPNPostion;
import zx.soft.sdn.util.ConfigUtil;
import zx.soft.sdn.util.DateUtil;
import zx.soft.sdn.util.FileUtil;

/**
 * VPN用户地理位置数据处理
 * 
 * @author xuran
 *
 */
public class VPNPostionETLHandle {

	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(VPNPostionETLHandle.class);

	/**
	 * 配置信息
	 */
	private static Properties config = ConfigUtil.getProps("ftp.properties");

	/**
	 * 主方法
	 * 文件校验
	 */
	public void main() {
		//获取主数据目录
		File mainDirectory = new File(config.getProperty("dir"));
		//获取主数据目录下的子目录
		File[] directoryArray = mainDirectory.listFiles();
		//检查主数据目录是否存在
		if (null != directoryArray) {
			//获取VPN用户地理位置数据目录下的所有文件
			File[] fileArray = null;
			for (File file : directoryArray) {
				if (file.isDirectory() && Business.VPNPOSTION.value().equals(file.getName()))
					fileArray = file.listFiles();
			}
			//检查VPN用户地理位置数据目录是否存在
			if (null != fileArray) {
				//处理数据
				handle(fileArray);
			} else {
				logger.error("****VPN用户地理位置数据目录{}不存在****", config.getProperty("dir") + Business.VPNPOSTION.value());
			}
		} else {
			logger.error("****主数据目录{}不存在****", config.getProperty("dir"));
		}

	}

	/**
	 * 数据处理
	 * @param fileArray VPN用户地理位置数据文件数组
	 */
	private void handle(File[] fileArray) {
		//创建持久层交互对象
		VPNPostionDao vpnPostionDao = new VPNPostionDaoImpl();
		//遍历文件
		for (File file : fileArray) {
			//是否是文本文件
			if (file.isFile()) {
				//读取行
				String lineText = null;
				//行号
				Integer lineNumber = 0;
				//读取文件
				try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));) {
					while ((lineText = bufferedReader.readLine()) != null) {
						//记录行号
						lineNumber++;
						String[] data = null;
						VPNPostion vpnPostion = null;
						try {
							//数据转换
							data = lineText.split(",");
							vpnPostion = new VPNPostion();
							vpnPostion.setRealNumber(data[0]);
							vpnPostion.setBizIP(data[1]);
							vpnPostion.setSac(data[2]);
							vpnPostion.setLac(data[3]);
							vpnPostion.setFlow(data[4]);
							vpnPostion.setTime(data[5]);
							//写入HBase
							if (vpnPostionDao.insertVPNPostion(vpnPostion)) {
								logger.info("****添加数据文件{}第{}行VPN用户地理位置数据到HBase成功****", file.getName(), lineNumber);
							} else {
								logger.error("****添加数据文件{}第{}行VPN用户地理位置数据[{}]到HBase失败****", file.getName(), lineNumber,
										lineText);
							}
						} catch (Exception e) {
							e.printStackTrace();
							logger.error("****数据文件{}第{}行数据[{}]不符合VPN用户地理位置数据格式约定****", file.getName(), lineNumber,
									lineText);
						} finally {
							//销毁对象
							vpnPostion = null;
							data = null;
						}
					}
					//如果handle.over.delete配置为true则删除处理后的文件，否则转移文件。
					if ("true".equals(config.getProperty("handle.over.delete"))) {
						//删除
						file.delete();
					} else {
						//转移
						FileUtil.move(file, FileUtil.getFileDirectory(file) + "finish/"
								+ DateUtil.yyyyMMddFormat.format(new Date()) + "/");
					}
					logger.info("****VPN用户地理位置数据文件{}处理成功****", file.getName());
				} catch (Exception e) {
					logger.error("****VPN用户地理位置数据文件处理失败****", file.getName());
				} finally {
					//销毁对象
					lineText = null;
					lineNumber = null;
				}
			}
		}
	}
}
