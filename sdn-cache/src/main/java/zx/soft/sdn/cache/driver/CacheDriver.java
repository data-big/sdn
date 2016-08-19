package zx.soft.sdn.cache.driver;

import org.apache.hadoop.util.ProgramDriver;

import zx.soft.sdn.cache.server.CacheServer;

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

		ProgramDriver pgd = new ProgramDriver();
		try {
			pgd.addClass("cacheServer", CacheServer.class, "sdn-cache");
			pgd.driver(args);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
}
