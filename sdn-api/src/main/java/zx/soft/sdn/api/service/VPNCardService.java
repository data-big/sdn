package zx.soft.sdn.api.service;

import java.util.List;
import java.util.Map;

import zx.soft.sdn.api.model.VPNCard;

/**
 * VPN卡信息业务层接口
 * 
 * @author xuran
 *
 */
public interface VPNCardService {

	/**
	 * 根据用户真实号查询VPN卡信息
	 * @param realNumber 用户真实号
	 * @return VPNCard对象
	 */
	public VPNCard getByRealNumber(String realNumber);

	/**
	 * 根据一组用户真实号查询VPN卡信息
	 * @param realNumber 用户真实号连接字符串 1,2,3,4,5
	 * @return VPNCard集合
	 */
	public List<VPNCard> getByRealNumbers(String realNumber);

	/**
	 * 根据参数分页查询VPN卡信息集合
	 * @param param 查询参数
	 * @return VPNCard集合
	 */
	public List<VPNCard> getList(Map<String, Object> param);

}
