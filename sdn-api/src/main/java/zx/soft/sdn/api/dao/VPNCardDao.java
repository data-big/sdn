package zx.soft.sdn.api.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import zx.soft.sdn.api.model.VPNCard;

/**
 * VPN卡信息持久层接口
 * 
 * @author xuran
 *
 */
public interface VPNCardDao {

	/**
	 * 根据用户真实号查询VPN卡信息
	 * @param realNumber 用户真实号
	 * @return VPNCard对象
	 */
	@Select(value = "SELECT * FROM vpn_card where realNumber=#{realNumber}")
	public VPNCard getByRealNumber(String realNumber);

	/**
	 * 根据一组用户真实号查询VPN卡信息
	 * @param realNumbers 用户真实号1,2,3,4,5
	 * @return VPNCard集合
	 */
	@Select(value = "SELECT * FROM vpn_card where realNumber in (#{realNumbers}) ORDER BY insertDate")
	public List<VPNCard> getListByRealNumbers(String realNumbers);

	/**
	 * 根据参数分页查询VPN卡信息集合
	 * @param param 查询参数
	 * @return VPNCard集合
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
						sql.append(" and ").append(key).append(" like ").append("'%")
								.append(param.get(key).toString().replace("'", "")).append("%'");
					}
				}
			}
			sql.append(" ORDER BY insertDate");
			return sql.toString();
		}

	}
}
