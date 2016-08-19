package zx.soft.sdn.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import zx.soft.sdn.api.service.StatisticsService;
import zx.soft.sdn.model.DateCount;
import zx.soft.sdn.model.ScatterCount;
import zx.soft.sdn.model.TypeCount;
import zx.soft.sdn.util.JsonUtil;
import zx.soft.sdn.util.RedisUtil;

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
	 * @param start 开始时间 yyyy-MM-dd
	 * @param end 结束时间 yyyy-MM-dd
	 * @return 统计结果
	 */
	@RequestMapping(value = "/statistics/vpnuser/{start}/{end}", method = RequestMethod.GET)
	public @ResponseBody List<DateCount> countUser(@PathVariable(value = "start") String start,
			@PathVariable(value = "end") String end) {
		return statisticsService.countUser(start, end);
	}

	/**
	 * 根据用户证件类型分组并按照时间区间统计VPN用户总量
	 * @param start 开始时间 yyyy-MM-dd
	 * @param end 结束时间 yyyy-MM-dd
	 * @return 统计结果
	 */
	@RequestMapping(value = "/statistics/vpnuser/idtype/{start}/{end}", method = RequestMethod.GET)
	public @ResponseBody List<TypeCount> countUserByIDType(@PathVariable(value = "start") String start,
			@PathVariable(value = "end") String end) {
		return statisticsService.countUserByIDType(start, end);
	}

	/**
	 * 根据时间分组并按照时间区间统计每日VPN卡总量和增量
	 * @param start 开始时间 yyyy-MM-dd
	 * @param end 结束时间 yyyy-MM-dd
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

	/**
	 * 
	 * 根据省份和时间区间查询上网用户分布
	 * @param region 省份名称
	 * @param start yyyy-MM-dd
	 * @param end yyyy-MM-dd
	 * @return 统计结果
	 */
	@RequestMapping(value = "/statistics/vpnpostion/{region}/{timeType}", method = RequestMethod.GET)
	public @ResponseBody ScatterCount partCountVPNPostion(@PathVariable(value = "region") String region,
			@PathVariable(value = "timeType") String timeType) {
		ScatterCount result = null;
		switch (timeType) {
		case "hour":
			result = JsonUtil.parseBean(RedisUtil.getInstance().get("sdn.user.part.hour"), ScatterCount.class);
			break;
		case "yesterday":
			result = JsonUtil.parseBean(RedisUtil.getInstance().get("sdn.user.part.yesterday"), ScatterCount.class);
			break;
		case "week":
			result = JsonUtil.parseBean(RedisUtil.getInstance().get("sdn.user.part.week"), ScatterCount.class);
			break;
		case "month":
			result = JsonUtil.parseBean(RedisUtil.getInstance().get("sdn.user.part.month"), ScatterCount.class);
			break;
		}
		return null == result ? new ScatterCount() : result;
	}
}
