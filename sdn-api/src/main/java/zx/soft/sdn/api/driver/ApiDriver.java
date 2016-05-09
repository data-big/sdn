package zx.soft.sdn.api.driver;

import zx.soft.sdn.api.server.JettyServer;

/**
 * 接口驱动
 * 
 * @author xuran
 *
 */
public class ApiDriver {

	/**
	 * 主函数
	 */
	public static void main(String[] args) {

		int exitCode = -1;
		ProgramDriver pgd = new ProgramDriver();
		try {
			pgd.addClass("jettyServer", JettyServer.class, "sdn-api");
			pgd.driver(args);
			// Success
			exitCode = 0;
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
		System.exit(exitCode);
	}
}
