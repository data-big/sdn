package zx.soft.sdn.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import zx.soft.sdn.api.service.StatisticsService;
import zx.soft.sdn.model.DateCount;
import zx.soft.sdn.model.TypeCount;

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
	 * DEMO
	 * 根据时间区间查询全国上网用户分布
	 * @param start yyyy-MM-dd
	 * @param end yyyy-MM-dd
	 * @return 统计结果
	 */
	@RequestMapping(value = "/statistics/vpnpostion/{start}/{end}", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> totalCountVPNPostion(@PathVariable(value = "start") String start,
			@PathVariable(value = "end") String end) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> regionList = new ArrayList<Map<String, Object>>();
		Map<String, Object> region1 = new HashMap<String, Object>();
		region1.put("name", "北京");
		region1.put("value", 3600);
		Map<String, Object> region2 = new HashMap<String, Object>();
		region2.put("name", "安徽");
		region2.put("value", 4000);
		Map<String, Object> region3 = new HashMap<String, Object>();
		region3.put("name", "四川");
		region3.put("value", 200);
		Map<String, Object> region4 = new HashMap<String, Object>();
		region4.put("name", "香港");
		region4.put("value", 24000);
		regionList.add(region1);
		regionList.add(region2);
		regionList.add(region3);
		regionList.add(region4);
		result.put("maxCount", 24000);
		result.put("minCount", 200);
		result.put("region", regionList);
		return result;
	}

	/**
	 * DEMO
	 * 根据省份和时间区间查询上网用户分布
	 * @param region 省份代码
	 * @param start yyyy-MM-dd
	 * @param end yyyy-MM-dd
	 * @return 统计结果
	 */
	@RequestMapping(value = "/statistics/vpnpostion/{region}/{start}/{end}", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> partCountVPNPostion(@PathVariable(value = "region") String region,
			@PathVariable(value = "start") String start, @PathVariable(value = "end") String end) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> regionList = new ArrayList<Map<String, Object>>();
		Map<String, Object> region1 = new HashMap<String, Object>();
		region1.put("name", "合肥");
		region1.put("value", 458);
		Map<String, Object> region2 = new HashMap<String, Object>();
		region2.put("name", "芜湖");
		region2.put("value", 273);
		Map<String, Object> region3 = new HashMap<String, Object>();
		region3.put("name", "黄山");
		region3.put("value", 29);
		Map<String, Object> region4 = new HashMap<String, Object>();
		region4.put("name", "蚌埠");
		region4.put("value", 568);
		Map<String, Object> region5 = new HashMap<String, Object>();
		region5.put("name", "滁州");
		region5.put("value", 4);
		Map<String, Object> region6 = new HashMap<String, Object>();
		region6.put("name", "淮北");
		region6.put("value", 149);
		regionList.add(region1);
		regionList.add(region2);
		regionList.add(region3);
		regionList.add(region4);
		regionList.add(region5);
		regionList.add(region6);
		result.put("max", 568);
		result.put("min", 4);
		result.put("data", regionList);
		return result;
	}
}
