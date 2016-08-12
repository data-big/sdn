package zx.soft.sdn.etl.dao;

import zx.soft.sdn.model.VPNUser;

/**
 * VPN用户信息持久层接口
 * 
 * @author xuran
 *
 */
public interface VPNUserDao {

	/**
	 * 添加VPN用户信息
	 * VPN用户信息过期机制
	 * 先更新数据库中与当前用户真实号一致的所有VPN用户信息为过期
	 * 然后再添加当前VPN用户信息
	 * @param vpnUser VPN用户信息
	 * @return 成功或失败
	 */
	public boolean batchUpdateInsert(VPNUser vpnUser);

}
