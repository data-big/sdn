package zx.soft.sdn.api.service;

import java.util.List;

import zx.soft.sdn.api.model.DateCount;
import zx.soft.sdn.api.model.TypeCount;

/**
 * 数据统计业务层接口
 * 
 * @author xuran
 *
 */
public interface StatisticsService {

	/**
	 * 根据时间分组并按照时间区间统计每日VPN用户总量和增量
	 * @param start 开始时间（2016-01-01）
	 * @param end 结束时间（2016-01-01）
	 * @return 统计结果
	 */
	public List<DateCount> countUser(String start, String end);

	/**
	 * 根据用户证件类型分组并按照时间区间统计VPN用户总量
	 * @param start 开始时间（2016-01-01）
	 * @param end 结束时间（2016-01-01）
	 * @return 统计结果
	 */
	public List<TypeCount> countUserByIDType(String start, String end);

	/**
	 * 根据时间分组并按照时间区间统计每日VPN卡总量和增量
	 * @param start 开始时间（2016-01-01）
	 * @param end 结束时间（2016-01-01）
	 * @return 统计结果
	 */
	public List<DateCount> countCard(String start, String end);

	/**
	 * 根据VPN卡状态分组统计VPN卡总量
	 * @return 统计结果
	 */
	public List<TypeCount> countCardByStatus();

}
