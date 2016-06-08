package zx.soft.sdn.api.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

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
	 * @param vpnCard VPN卡信息
	 * @return 成功或失败
	 */
	@Insert(value = "INSERT INTO vpn_card(id,realNumber,bizIP,stopIP,specialIP,offsetBizIP,offsetStopIP,offsetSpecialIP,invalid,insertDate) VALUES(#{id},#{realNumber},#{bizIP},#{stopIP},#{specialIP},#{offsetBizIP},#{offsetStopIP},#{offsetSpecialIP},#{invalid},#{insertDate})")
	public boolean insertVPNCard(VPNCard vpnCard);

	/**
	 * 根据用户真实号更新VPN卡信息为失效
	 * @param realNumber 用户真实号
	 * @return 成功或失败
	 */
	@Update(value = "UPDATE vpn_card SET invalid=1 WHERE realNumber=#{realNumber} AND invalid=0")
	public boolean updateVPNCardToInvalid(@Param("realNumber") String realNumber);

	/**
	 * 根据业务IP查询VPN卡用户真实号
	 * @param bizIP 业务IP地址
	 * @return 用户真实号
	 */
	@Select(value = "SELECT realNumber FROM vpn_card where bizIP=#{bizIP} AND invalid=0")
	public String getRealNumber(@Param("bizIP") String bizIP);

	/**
	 * 根据用户真实号查询VPN卡信息
	 * @param realNumber 用户真实号
	 * @return VPNCard信息
	 */
	@Select(value = "SELECT * FROM vpn_card where realNumber=#{realNumber} AND invalid=0")
	public VPNCard getByRealNumber(@Param("realNumber") String realNumber);

	/**
	 * 根据一组用户真实号查询VPN卡信息
	 * @param realNumbers 用户真实号1,2,3,4,5
	 * @return VPNCard信息集合
	 */
	@Select(value = "SELECT * FROM vpn_card where realNumber in (${_parameter}) AND invalid=0 ORDER BY insertDate")
	public List<VPNCard> getByRealNumbers(String realNumbers);

	/**
	 * 根据参数分页查询VPN卡信息集合
	 * @param param 查询参数
	 * @return VPNCard信息集合
	 */
	@SelectProvider(type = VPNCardDaoProvider.class, method = "getListSQL")
	public List<VPNCard> getList(Map<String, Object> param);

	/**
	 * 动态SQL处理内部类
	 * 
	 * @author xuran
	 *
	 */
	class VPNCardDaoProvider {

		/**
		 * 根据参数分页查询VPN卡信息集合动态SQL处理
		 * @param param 查询参数
		 * @return 动态SQL语句
		 */
		public static String getListSQL(Map<String, Object> param) {
			//查询SQL
			StringBuffer sql = new StringBuffer("SELECT * FROM vpn_card where 1=1");
			//如果有参数
			if (null != param) {
				//遍历参数
				for (String key : param.keySet()) {
					//排除分页参数
					if (!("page".equals(key))) {
						//如果是整型字段
						if ("invalid".equals(key)) {
							sql.append(" and ").append(key).append("=").append("#{").append(key).append("}");
						} else {
							sql.append(" and ").append(key).append(" like ").append("'%")
									.append(param.get(key).toString().replace("'", "")).append("%'");
						}

					}
				}
			}
			sql.append(" ORDER BY insertDate");
			return sql.toString();
		}

	}
}
