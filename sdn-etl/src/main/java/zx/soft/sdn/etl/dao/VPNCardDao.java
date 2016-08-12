package zx.soft.sdn.etl.dao;

import zx.soft.sdn.model.VPNCard;

/**
 * VPN卡信息持久层接口
 * 
 * @author xuran
 *
 */
public interface VPNCardDao {

	/**
	 * 添加VPN卡信息
	 * VPN卡信息过期机制
	 * 先更新数据库中与当前用户真实号一致的所有VPN卡信息为过期
	 * 然后再添加当前VPN卡信息
	 * @param vpnCard VPN卡信息
	 * @return 成功或失败
	 */
	public boolean batchUpdateInsert(VPNCard vpnCard);

}
