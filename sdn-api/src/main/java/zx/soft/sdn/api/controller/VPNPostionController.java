package zx.soft.sdn.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import zx.soft.sdn.api.model.VPNPostion;
import zx.soft.sdn.api.service.LocationService;
import zx.soft.sdn.util.DateUtil;

/**
 * VPN用户地理位置信息控制器
 * 
 * @author xuran
 *
 */
@Controller
public class VPNPostionController {

	/**
	 * 注入基站位置信息业务层接口实现
	 */
	@Autowired
	private LocationService locationService;

	/**################测试接口
	 * 根据真实号和时间区间查询用户地理位置信息
	 * @param vpnCard
	 * @return VPNCard Json数据
	 */
	@RequestMapping(value = "/vpnpostion/{realNumber}/{start}/{end}", method = RequestMethod.GET)
	public @ResponseBody List<VPNPostion> getVPNPostion(@PathVariable(value = "realNumber") String realNumber,
			@PathVariable(value = "start") String start, @PathVariable(value = "end") String end) {
		List<VPNPostion> list = new ArrayList<VPNPostion>();
		//		String[] a = new String[] { "117.274978637695", "117.274726867676", "117.262344360352", "117.249740600586",
		//				"117.265182495117", "117.240791320801", "117.275520324707", "117.270179748535", "117.275619506836",
		//				"117.288383483887" };
		//		String[] b = new String[] { "31.7736549377441", "31.8399982452393", "31.8351783752441", "31.8633556365967",
		//				"31.7807579040527", "31.8382968902588", "31.7728710174561", "31.8847351074219", "31.7760105133057",
		//				"31.8542327880859" };
		//		for (int i = 0; i < 10; i++) {
		//			Location location = new Location();
		//			location.setAddressDescription("江苏省苏州市吴中区波特兰小街维亭镇荷花广场波特兰小街东");
		//			location.setLongitude(a[i]);
		//			location.setLatitude(b[i]);
		//			location.setAccuracy("1000");
		//			Address address = new Address();
		//			address.setRegion("江苏省");
		//			address.setCounty("吴中区");
		//			address.setStreet("维亭镇");
		//			address.setStreet_number("荷花广场");
		//			address.setCity("苏州市");
		//			address.setCountry("中国");
		//			location.setAddress(address);
		//			list.add(new VPNPostion("vpnpostion", realNumber, "34567", "56789",
		//					DateUtil.simpleFormat.format(new Date()), location));
		//		}
		list.add(new VPNPostion("vpnpostion", realNumber, "34567", "56789", DateUtil.simpleFormat.format(new Date()),
				locationService.getLocation("34567", "56789")));
		return list;
	}

	/**################测试接口
	 * 根据时间区间查询活跃用户分布信息
	 * @param vpnCard
	 * @return VPNCard Json数据
	 */
	@RequestMapping(value = "/vpnpostion/{start}/{end}", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getVPNPostion(@PathVariable(value = "start") String start,
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

}
