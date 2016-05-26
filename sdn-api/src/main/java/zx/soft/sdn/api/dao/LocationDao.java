package zx.soft.sdn.api.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import zx.soft.sdn.api.model.Address;
import zx.soft.sdn.api.model.Location;

/**
 * 基站位置信息持久层接口
 * 
 * @author xuran
 *
 */
public interface LocationDao {

	/**
	 * 根据基站SAC和LAC信息查询基站位置信息
	 * @param sac 基站SAC
	 * @param lac 基站LAC
	 * @return 基站位置信息
	 */
	public Location getLocation(@Param("sac") String sac, @Param("lac") String lac);

	/**
	 * 根据地址信息主键查询地址信息
	 * @param id 地址信息主键
	 * @return 地址信息
	 */
	@Select(value = "SELECT * FROM address WHERE id=#{id}")
	public Address getAddress(@Param("id") String id);

}
