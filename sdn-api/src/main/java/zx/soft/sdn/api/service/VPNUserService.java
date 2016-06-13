package zx.soft.sdn.api.service;

import java.util.List;
import java.util.Map;

import zx.soft.sdn.model.VPNUser;

/**
 * VPN用户信息业务层接口
 * 
 * @author xuran
 *
 */
public interface VPNUserService {

	/***
	 * 添加VPN用户信息
	 * VPN用户信息过期机制
	 * 先更新数据库中与当前用户真实号一致的所有VPN用户信息为过期
	 * 然后再添加当前VPN用户信息
	 */
	public void batchUpdateInsert(VPNUser vpnUser);

	/**
	 * 根据用户真实号查询VPN用户信息
	 * @param realNumber 用户真实号
	 * @return VPN用户信息
	 */
	public VPNUser getByRealNumber(String realNumber);

	/**
	 * 根据一组用户真实号查询VPN用户信息
	 * @param realNumbers 用户真实号连接字符串 1,2,3,4,5
	 * @return VPN用户信息集合
	 */
	public List<VPNUser> getByRealNumbers(String realNumbers);

	/**
	 * 根据参数分页查询VPN用户信息集合
	 * @param param 查询参数
	 * @return VPN用户信息集合
	 */
	public List<VPNUser> getList(Map<String, Object> param);
}
