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

	/***
	 * 添加VPN卡信息
	 * VPN卡信息过期机制
	 * 先更新数据库中与当前用户真实号一致的所有VPN卡信息为过期
	 * 然后再添加当前VPN卡信息
	 */
	public void updateInsert(VPNCard vpnCard);

	/**
	 * 根据业务IP查询VPN卡用户真实号
	 * @param bizIP 业务IP地址
	 * @return 用户真实号
	 */
	public String getRealNumber(String bizIP);

	/**
	 * 根据用户真实号查询VPN卡信息
	 * @param realNumber 用户真实号
	 * @return VPNCard VPN卡信息
	 */
	public VPNCard getByRealNumber(String realNumber);

	/**
	 * 根据一组用户真实号查询VPN卡信息
	 * @param realNumbers 用户真实号连接字符串 1,2,3,4,5
	 * @return  VPN卡信息集合
	 */
	public List<VPNCard> getByRealNumbers(String realNumbers);

	/**
	 * 根据参数分页查询VPN卡信息集合
	 * @param param 查询参数
	 * @return  VPN卡信息集合
	 */
	public List<VPNCard> getList(Map<String, Object> param);

}
