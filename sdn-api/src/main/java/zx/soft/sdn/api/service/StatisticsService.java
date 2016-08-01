package zx.soft.sdn.api.service;

import java.util.List;

import zx.soft.sdn.model.DateCount;
import zx.soft.sdn.model.ScatterCount;
import zx.soft.sdn.model.TypeCount;

/**
 * 数据统计业务层接口
 * 
 * @author xuran
 *
 */
public interface StatisticsService {

	/**
	 * 根据时间分组并按照时间区间统计每日VPN用户总量和增量
	 * @param start 开始时间 yyyy-MM-dd
	 * @param end 结束时间 yyyy-MM-dd
	 * @return 统计结果
	 */
	public List<DateCount> countUser(String start, String end);

	/**
	 * 根据用户证件类型分组并按照时间区间统计VPN用户总量
	 * @param start 开始时间 yyyy-MM-dd
	 * @param end 结束时间 yyyy-MM-dd
	 * @return 统计结果
	 */
	public List<TypeCount> countUserByIDType(String start, String end);

	/**
	 * 根据时间分组并按照时间区间统计每日VPN卡总量和增量
	 * @param start 开始时间 yyyy-MM-dd
	 * @param end 结束时间 yyyy-MM-dd
	 * @return 统计结果
	 */
	public List<DateCount> countCard(String start, String end);

	/**
	 * 根据VPN卡状态分组统计VPN卡总量
	 * @return 统计结果
	 */
	public List<TypeCount> countCardByStatus();

	/**
	 * 根据省份名称和时间区间统计各地市VPN用户分布情况
	 * @param region 省份名称
	 * @param start 开始时间
	 * @param end 结束时间
	 * @return 分布统计结果
	 */
	public ScatterCount countPart(String region, String start, String end);

}
