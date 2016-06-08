package zx.soft.sdn.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import zx.soft.sdn.model.DateCount;
import zx.soft.sdn.model.TypeCount;

/**
 * 数据统计持久层接口
 * 
 * @author xuran
 *
 */
public interface StatisticsDao {

	/**
	 * 根据时间分组并按照时间区间统计每日VPN用户总量和增量
	 * @param start 开始时间（2016-01-01）
	 * @param end 结束时间（2016-01-01）
	 * @return 统计结果
	 */
	@Select(value = "SELECT DATE_FORMAT(registerDate, '%Y-%m-%d') AS date,COUNT(1) AS total,IFNULL(COUNT(1) - (SELECT COUNT(1) FROM vpn_user WHERE invalid = 0 AND DATE_FORMAT(registerDate, '%Y-%m-%d') BETWEEN #{start} AND #{end} AND DATE_FORMAT(registerDate, '%Y-%m-%d') = DATE_SUB(date, INTERVAL 1 DAY) GROUP BY DATE_FORMAT(registerDate, '%Y-%m-%d')),0) AS increment FROM vpn_user WHERE invalid = 0 AND DATE_FORMAT(registerDate, '%Y-%m-%d') BETWEEN #{start} AND #{end} GROUP BY DATE_FORMAT(registerDate, '%Y-%m-%d') ORDER BY DATE_FORMAT(registerDate, '%Y-%m-%d') DESC")
	public List<DateCount> countUser(@Param("start") String start, @Param("end") String end);

	/**
	 * 根据用户证件类型分组并按照时间区间统计VPN用户总量
	 * @param start 开始时间（2016-01-01）
	 * @param end 结束时间（2016-01-01）
	 * @return 统计结果
	 */
	@Select(value = "SELECT userIDType AS 'name', COUNT(1) AS 'value' FROM vpn_user WHERE invalid = 0 AND DATE_FORMAT(registerDate, '%Y-%m-%d') BETWEEN #{start} AND #{end} GROUP BY userIDType")
	public List<TypeCount> countUserByIDType(@Param("start") String start, @Param("end") String end);

	/**
	 * 根据时间分组并按照时间区间统计每日VPN卡总量和增量
	 * @param start 开始时间（2016-01-01）
	 * @param end 结束时间（2016-01-01）
	 * @return 统计结果
	 */
	@Select(value = "SELECT DATE_FORMAT(insertDate, '%Y-%m-%d') AS date,COUNT(1) AS total,IFNULL(COUNT(1) - (SELECT COUNT(1) FROM vpn_card WHERE invalid = 0 AND DATE_FORMAT(insertDate, '%Y-%m-%d') BETWEEN #{start} AND #{end} AND DATE_FORMAT(insertDate, '%Y-%m-%d') = DATE_SUB(date, INTERVAL 1 DAY) GROUP BY DATE_FORMAT(insertDate, '%Y-%m-%d')),0) AS increment FROM vpn_card WHERE invalid = 0 AND DATE_FORMAT(insertDate, '%Y-%m-%d') BETWEEN #{start} AND #{end} GROUP BY DATE_FORMAT(insertDate, '%Y-%m-%d') ORDER BY DATE_FORMAT(insertDate, '%Y-%m-%d') DESC")
	public List<DateCount> countCard(@Param("start") String start, @Param("end") String end);

	/**
	 * 根据VPN卡状态分组统计VPN卡总量
	 * @return 统计结果
	 */
	@Select(value = "SELECT invalid AS `name`, COUNT(1) AS `value` FROM vpn_card GROUP BY invalid")
	public List<TypeCount> countCardByStatus();

}
