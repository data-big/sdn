package zx.soft.sdn.etl.driver;

import zx.soft.sdn.common.ProgramDriver;
import zx.soft.sdn.etl.server.VPNCardETLServer;
import zx.soft.sdn.etl.server.VPNPostionETLServer;
import zx.soft.sdn.etl.server.VPNUserETLServer;

/**
 * 数据采集转换驱动
 * 
 * @author xuran
 *
 */
public class ETLDriver {

	/**
	 * 主函数
	 */
	public static void main(String[] args) {

		ProgramDriver pgd = new ProgramDriver();
		try {
			//每天凌晨两点执行
			pgd.addClass("vpnCardETLServer", VPNCardETLServer.class, "VPN卡数据采集转换程序");
			//每天凌晨三点执行
			pgd.addClass("vpnUserETLServer", VPNUserETLServer.class, "VPN用户数据采集转换程序");
			//一小时执行一次
			pgd.addClass("vpnPostionETLServer", VPNPostionETLServer.class, "VPN地理位置数据采集转换程序");
			pgd.driver(args);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
}
