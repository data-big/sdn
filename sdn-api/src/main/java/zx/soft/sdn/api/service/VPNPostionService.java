package zx.soft.sdn.api.service;

import java.util.List;

import zx.soft.sdn.api.domain.VPNPostion;

/**
 * VPN用户地理位置信息业务层接口
 * 
 * @author xuran
 *
 */
public interface VPNPostionService {

	/**
	 * 添加VPN用户地理位置信息
	 * @param vpnPostion VPN用户地理位置信息
	 * @return 成功或失败
	 */
	public boolean putVPNPostion(VPNPostion vpnPostion);

	/**
	 * 根据VPN用户真实号和时间区间查询VPN用户地理位置信息
	 * @param realNumber 用户真实号
	 * @param start 开始时间（yyyy-MM-dd HH:mm:ss）
	 * @param end 结束时间（yyyy-MM-dd HH:mm:ss）
	 * @return VPN用户地理位置信息集合
	 */
	public List<VPNPostion> queryVPNPostions(String realNumber, String start, String end);

}
