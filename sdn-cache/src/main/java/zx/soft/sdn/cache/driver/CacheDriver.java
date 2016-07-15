package zx.soft.sdn.cache.driver;

import zx.soft.sdn.cache.server.CacheServer;
import zx.soft.sdn.common.ProgramDriver;

/**
 * 缓存驱动
 * 
 * @author xuran
 *
 */
public class CacheDriver {

	/**
	 * 主函数
	 */
	public static void main(String[] args) {

		int exitCode = -1;
		ProgramDriver pgd = new ProgramDriver();
		try {
			pgd.addClass("cacheServer", CacheServer.class, "sdn-cache");
			pgd.driver(args);
			// Success
			exitCode = 0;
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
		System.exit(exitCode);
	}
}
