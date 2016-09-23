package zx.soft.sdn.util;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FastDFS工具类
 * 
 * @author xuran
 *
 */
public class FastDFSUtil {

	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(FastDFSUtil.class);

	/**
	 * 私有实例
	 */
	private static FastDFSUtil instance;

	/**
	 * FastDFS Tracker客户端
	 */
	TrackerClient trackerClient;

	/**
	 * 私有构造方法
	 */
	private FastDFSUtil() {
		try {
			//初始化客户端配置
			ClientGlobal.init("fastdfs.properties");
			//创建客户端对象
			trackerClient = new TrackerClient();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception : FastDFS工具类创建失败 {}", ExceptionUtil.exceptionToString(e));
		}
	}

	/**
	 * 获取FastDFSUtil单例对象
	 * @return FastDFS工具类单例对象
	 */
	public static FastDFSUtil getInstance() {
		if (null == instance) {
			synchronized (FastDFSUtil.class) {
				if (null == instance)
					instance = new FastDFSUtil();
			}
		}
		return instance;
	}

	/**
	 * 上传图片
	 * @param bytes 字节码
	 * @param fileName 文件名，包含扩展名
	 * @return 上传后的文件路径
	 */
	public String upload(byte[] bytes, String fileName) {
		String result = null;
		try {
			//建立会话
			TrackerServer trackerServer = trackerClient.getConnection();
			StorageServer storageServer = null;
			//创建存储节客户端
			StorageClient1 storageClient = new StorageClient1(trackerServer, storageServer);
			//设置元信息  
			NameValuePair[] metaList = new NameValuePair[3];
			metaList[0] = new NameValuePair("fileName", fileName.substring(0, fileName.indexOf(".")));
			metaList[1] = new NameValuePair("fileExtName", fileName.substring(fileName.indexOf(".") + 1));
			metaList[2] = new NameValuePair("fileLength", String.valueOf(bytes.length));
			//上传并返回文件路径
			result = ConfigUtil.getProps("fastdfs.properties").getProperty("http.server")
					+ storageClient.upload_file1(bytes, fileName.substring(fileName.indexOf(".") + 1), metaList);
			//关闭会话
			trackerServer.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception : FastDFS工具类上传失败 {}", ExceptionUtil.exceptionToString(e));
		}
		return result;
	}

}
