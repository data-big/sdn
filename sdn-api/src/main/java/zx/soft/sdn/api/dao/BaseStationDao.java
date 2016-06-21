package zx.soft.sdn.api.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import zx.soft.sdn.model.BaseStation;

/**
 * 基站位置信息持久层接口
 * 
 * @author xuran
 *
 */
public interface BaseStationDao {

	/**
	 * 根据基站SAC和LAC信息查询基站位置信息
	 * @param sac 基站SAC
	 * @param lac 基站LAC
	 * @return 基站位置信息
	 */
	@Select(value = "SELECT * FROM basestation WHERE sac=#{sac} AND lac=#{lac} ORDER BY id LIMIT 0,1")
	public BaseStation getBySacAndLAC(@Param("sac") String sac, @Param("lac") String lac);

	/**
	 * 根据基站CELL和LAC信息查询基站位置信息
	 * @param cell 基站号
	 * @param lac 基站LAC
	 * @return 基站位置信息
	 */
	@Select(value = "SELECT * FROM basestation WHERE cell=#{cell} AND lac=#{lac} ORDER BY id LIMIT 0,1")
	public BaseStation getByCellAndLAC(@Param("cell") String cell, @Param("lac") String lac);
}
