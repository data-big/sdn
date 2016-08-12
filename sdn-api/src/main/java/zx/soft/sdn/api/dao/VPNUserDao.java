package zx.soft.sdn.api.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

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
	 * @param VPNUser VPN用户信息
	 * @return 成功或失败
	 */
	@Insert(value = "INSERT INTO vpn_user(id,realNumber,sponsorNumber,sponsorName,sponsorIDType,sponsorIDNumber,userNumber,userName,userIDType,userIDNumber,registerDate,cancelDate,registerAgent,modifyDate,invalid) VALUES(#{id},#{realNumber},#{sponsorNumber},#{sponsorName},#{sponsorIDType},#{sponsorIDNumber},#{userNumber},#{userName},#{userIDType},#{userIDNumber},#{registerDate},#{cancelDate},#{registerAgent},#{modifyDate},#{invalid})")
	public boolean insertVPNUser(VPNUser vpnUser);

	/**
	 * 根据用户真实号更新VPN用户信息为失效
	 * @param realNumber 用户真实号
	 * @return 成功或失败
	 */
	@Update(value = "UPDATE vpn_user SET invalid=1 WHERE id IN (SELECT id FROM (SELECT id FROM vpn_user WHERE realNumber=#{realNumber} AND invalid=0) AS T)")
	public boolean updateVPNUserToInvalid(@Param("realNumber") String realNumber);

	/**
	 * 根据用户真实号查询VPN用户信息
	 * @param realNumber 用户真实号
	 * @return VPNUser信息
	 */
	@Select(value = "SELECT * FROM vpn_user where realNumber=#{realNumber} AND invalid=0")
	public VPNUser getByRealNumber(@Param("realNumber") String realNumber);

	/**
	 * 根据一组用户真实号查询VPN用户信息
	 * @param realNumbers 用户真实号1,2,3,4,5
	 * @return VPNUser信息集合
	 */
	@Select(value = "SELECT * FROM vpn_user where realNumber in (${_parameter}) AND invalid=0 ORDER BY registerDate")
	public List<VPNUser> getListByRealNumbers(String realNumbers);

	/**
	 * 根据参数分页查询VPN用户信息集合
	 * @param param 查询参数
	 * @return VPNUser信息集合
	 */
	@SelectProvider(type = VPNUserDaoProvider.class, method = "getListSQL")
	public List<VPNUser> getList(Map<String, Object> param);

	/**
	 * 动态SQL处理内部类
	 * 
	 * @author xuran
	 *
	 */
	class VPNUserDaoProvider {

		/**
		 * 根据参数分页查询VPN用户信息集合动态SQL处理
		 * @param param 查询参数
		 * @return 动态SQL语句
		 */
		public static String getListSQL(Map<String, Object> param) {
			//查询SQL
			StringBuffer sql = new StringBuffer("SELECT * FROM vpn_user where 1=1");
			//如果有参数
			if (null != param) {
				//遍历参数
				for (String key : param.keySet()) {
					//排除分页参数
					if (!("page".equals(key))) {
						//如果是整型字段，作精确查询。
						if ("sponsorIDType".equals(key) || "userIDType".equals(key) || "invalid".equals(key)) {
							sql.append(" and ").append(key).append("=").append("#{").append(key).append("}");
						} else if ("startTime".equals(key)) {//开始时间
							sql.append(" and ").append("registerDate").append(">=").append("#{").append(key)
									.append("}");
						} else if ("endTime".equals(key)) {//结束时间
							sql.append(" and ").append("registerDate").append("<=").append("#{").append(key)
									.append("}");
						} else {
							sql.append(" and ").append(key).append(" like ").append("'%")
									.append(param.get(key).toString().replace("'", "")).append("%'");
						}
					}
				}
			}
			sql.append(" ORDER BY registerDate DESC");
			return sql.toString();
		}

	}

}
