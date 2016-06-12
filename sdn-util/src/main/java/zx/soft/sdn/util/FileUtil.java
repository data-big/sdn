package zx.soft.sdn.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

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
		return file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf("/")) + "/";
	}

}
