package zx.soft.sdn.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件工具类
 * 
 * @author xuran
 *
 */
public class FileUtil {

	/**
	 * 文件读写
	 * @param file 文件
	 * @param fileDirectory 目标文件目录
	 * @param fileName 目标文件名
	 * @return  文件字节数
	 * @throws IOException
	 */
	public static int readAndWirte(File file, String fileDirectory, String fileName) throws IOException {
		int result = 0;
		//缓冲10K
		byte[] buffer = new byte[1024 * 10];
		File outDirectory = new File(fileDirectory);
		File outFile = new File(fileDirectory + fileName);
		//如果目录不存在则创建
		if (!outDirectory.isDirectory()) {
			outDirectory.mkdirs();
		}
		//读写
		try (FileInputStream in = new FileInputStream(file); FileOutputStream out = new FileOutputStream(outFile);) {
			//获取文件字节数
			result = in.available();
			//读
			while (in.read(buffer) > 0) {
				//写
				out.write(buffer);
			}
		}
		return (result > 0 && outFile.exists()) ? result : 0;
	}

	/**
	 * 文件删除
	 * @param fileDirectory 文件目录
	 * @return fileName 文件全名
	 */
	public static boolean remove(String fileDirectory, String fileName) {
		boolean isSuccess = false;
		File directory = new File(fileDirectory);
		File file = new File(fileName);
		//删除文件
		if (file.exists()) {
			isSuccess = file.delete();
		}
		//如果目录下没有文件和文件夹则删除该目录
		if (directory.isDirectory() && directory.listFiles().length == 0) {
			directory.delete();
		}
		return isSuccess;
	}

	/**
	 * 文件移动
	 * @param file 文件
	 * @param targetDirectory 目标目录
	 * @return 成功或失败
	 */
	public static boolean move(File file, String targetDirectory) {
		//如果目录不存在则创建
		File directory = new File(targetDirectory);
		if (!directory.isDirectory()) {
			directory.mkdirs();
		}
		return file.renameTo(new File(targetDirectory + file.getName()));
	}

	/**
	 * 获取文件所在目录
	 * @param file 文件
	 * @return 文件目录
	 */
	public static String getFileDirectory(File file) {
		return file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf(File.separator)) + File.separator;
	}

	/**
	 * 获取远程文件字节码
	 * @param url 远程文件URL
	 * @return 字节码数组
	 */

	public static byte[] getRemoteFileBytes(String url) {
		try {
			// 构造URL  
			URL targetURL = new URL(url);
			// 打开连接  
			HttpURLConnection connection = (HttpURLConnection) targetURL.openConnection();
			//设置请求超时为10s  
			connection.setConnectTimeout(10 * 1000);
			//数据缓冲
			byte[] buffer = new byte[1];
			//数据临时存储
			List<Byte> temp = new ArrayList<Byte>();
			byte[] imageByte = null;
			//判断状态
			if (200 == connection.getResponseCode()) {
				//读写
				try (InputStream in = connection.getInputStream();) {
					//处理输入流
					while (in.read(buffer) != -1)
						temp.add(buffer[0]);
					//转换字节数组
					imageByte = new byte[temp.size()];
					for (int i = 0; i < temp.size(); i++) {
						imageByte[i] = temp.get(i);
					}
					return imageByte;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
