package zx.soft.sdn.gather.dao;

import zx.soft.sdn.model.VPNPostion;

/**
 * VPN用户地理信息持久层接口
 * 
 * @author xuran
 *
 */
public interface VPNPostionDao {

	/**
	 * 根据SAC和LAC在基站库中查询详细地址
	 * @param sac 基站SAC
	 * @param lac 基站LAC
	 * @return 详细地址
	 */
	public String getAddressInBS(String sac, String lac);

	/**
	 * 添加VPN用户地理位置信息到HBase
	 * @param vpnPostion VPN用户地理位置信息
	 * @return 成功或失败
	 */
	public boolean insertVPNPostion(VPNPostion vpnPostion);

}
