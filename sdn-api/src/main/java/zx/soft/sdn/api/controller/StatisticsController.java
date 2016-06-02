package zx.soft.sdn.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import zx.soft.sdn.api.model.DateCount;
import zx.soft.sdn.api.model.TypeCount;
import zx.soft.sdn.api.service.StatisticsService;

/**
 * 数据统计控制器
 * 
 * @author xuran
 *
 */
@Controller
public class StatisticsController {

	/**
	 * 注入数据统计业务层接口实现
	 */
	@Autowired
	private StatisticsService statisticsService;

	/**
	 * 根据时间分组并按照时间区间统计每日VPN用户总量和增量
	 * @param start 开始时间（2016-01-01）
	 * @param end 结束时间（2016-01-01）
	 * @return 统计结果
	 */
	@RequestMapping(value = "/statistics/vpnuser/{start}/{end}", method = RequestMethod.GET)
	public @ResponseBody List<DateCount> countUser(@PathVariable(value = "start") String start,
			@PathVariable(value = "end") String end) {
		return statisticsService.countUser(start, end);
	}

	/**
	 * 根据用户证件类型分组并按照时间区间统计VPN用户总量
	 * @param start 开始时间（2016-01-01）
	 * @param end 结束时间（2016-01-01）
	 * @return 统计结果
	 */
	@RequestMapping(value = "/statistics/vpnuser/idtype/{start}/{end}", method = RequestMethod.GET)
	public @ResponseBody List<TypeCount> countUserByIDType(@PathVariable(value = "start") String start,
			@PathVariable(value = "end") String end) {
		return statisticsService.countUserByIDType(start, end);
	}

	/**
	 * 根据时间分组并按照时间区间统计每日VPN卡总量和增量
	 * @param start 开始时间（2016-01-01）
	 * @param end 结束时间（2016-01-01）
	 * @return 统计结果
	 */
	@RequestMapping(value = "/statistics/vpncard/{start}/{end}", method = RequestMethod.GET)
	public @ResponseBody List<DateCount> countCard(@PathVariable(value = "start") String start,
			@PathVariable(value = "end") String end) {
		return statisticsService.countCard(start, end);
	}

	/**
	 * 根据VPN卡状态分组统计VPN卡总量
	 * @return 统计结果
	 */
	@RequestMapping(value = "/statistics/vpncard/status", method = RequestMethod.GET)
	public @ResponseBody List<TypeCount> countCardByStatus() {
		return statisticsService.countCardByStatus();
	}

}
