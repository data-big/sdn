package zx.soft.sdn.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import zx.soft.sdn.api.common.HandResult;
import zx.soft.sdn.api.common.Page;
import zx.soft.sdn.api.common.SystemConstant;
import zx.soft.sdn.api.model.VPNCard;
import zx.soft.sdn.api.model.VPNPostion;
import zx.soft.sdn.api.model.VPNUser;
import zx.soft.sdn.api.service.LocationService;
import zx.soft.sdn.api.service.VPNCardService;
import zx.soft.sdn.api.service.VPNUserService;
import zx.soft.sdn.api.util.ConvertUtil;
import zx.soft.sdn.api.util.DateUtil;
import zx.soft.sdn.api.util.IDUtil;
import zx.soft.sdn.api.util.JsonUtil;

/**
 * SDN基础信息控制器
 * 
 * @author xuran
 *
 */
@Controller
@RequestMapping(value = "/sdn")
public class SDNController {

	/**
	 * 注入VPN卡信息业务层接口实现
	 */
	@Autowired
	private VPNCardService vpnCardService;
	/**
	 * 注入VPN用户信息业务层接口实现
	 */
	@Autowired
	private VPNUserService vpnUserService;
	/**
	 * 注入基站位置信息业务层接口实现
	 */
	@Autowired
	private LocationService locationService;

	/**
	 * 添加VPN卡信息
	 * @param vpnCard VPN卡信息
	 * @return 处理结果
	 */
	@RequestMapping(value = "/vpncard", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody HandResult postVPNCard(@RequestBody VPNCard vpnCard) {
		vpnCard.setId(IDUtil.generateUniqueID());
		vpnCard.setInvalid(0);
		boolean serviceHandResult = false;
		try {
			vpnCardService.updateInsert(vpnCard);
			serviceHandResult = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serviceHandResult ? new HandResult(0, vpnCard.getId())
				: new HandResult(1, "add realNumber=" + vpnCard.getRealNumber() + " 's VPNCard is fail");
	}

	/**
	 * 根据业务IP查询用户真实号
	 * @param bizIP 业务IP地址
	 * @return 用户真实号
	 */
	@RequestMapping(value = "/vpncard/realnumber/{bizIP:.+}", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getRealNumber(@PathVariable(value = "bizIP") String bizIP) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("realNumber", vpnCardService.getRealNumber(bizIP));
		return result;
	}

	/**
	 * 根据真实号查询VPN卡信息
	 * @param realNumber
	 * @return VPN卡信息Json数据
	 */
	@RequestMapping(value = "/vpncard/{realNumber}", method = RequestMethod.GET)
	public @ResponseBody VPNCard getVPNCard(@PathVariable(value = "realNumber") String realNumber) {
		return vpnCardService.getByRealNumber(realNumber);
	}

	/**
	 * 根据一组真实号查询VPN卡信息
	 * @param realNumbers 用户真实号连接字符串 1&2&3&4&5
	 * @return VPN卡信息Json数据集合
	 */
	@RequestMapping(value = "/vpncards/{realNumbers}", method = RequestMethod.GET)
	public @ResponseBody List<VPNCard> getVPNCards(@PathVariable(value = "realNumbers") String realNumbers) {
		return vpnCardService.getByRealNumbers(ConvertUtil.replaceSQLINSplit(realNumbers, "&"));
	}

	/**
	 * 分页并支持模糊查询VPN卡信息列表
	 * @param pageNo 页码
	 * @param pageSize 页行
	 * @param vpnCard 查询条件
	 * @return VPN卡信息集合和分页信息Json数据
	 */
	@RequestMapping(value = "/vpncards/{pageNo}/{pageSize}", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getVPNCards(@PathVariable(value = "pageNo") int pageNo,
			@PathVariable(value = "pageSize") int pageSize, VPNCard vpnCard) {
		Map<String, Object> param = ConvertUtil.parseMap(vpnCard);
		Page page = Page.newBuilder(pageNo, pageSize, "/sdn/vpncards", ConvertUtil.parseMap(vpnCard));
		param.put(SystemConstant.PAGE, page);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(SystemConstant.DATA, vpnCardService.getList(param));
		result.put(SystemConstant.PAGE, page);
		return result;
	}

	/**
	 * 分页并支持模糊查询VPN卡信息列表
	 * @param pageNo 页码
	 * @param pageSize 页行
	 * @param vpnCardJson 查询条件
	 * @return VPN卡信息集合和分页信息Json数据
	 */
	@RequestMapping(value = "/vpncards/{pageNo}/{pageSize}/{vpnCardJson:.+}", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getVPNCards(@PathVariable(value = "pageNo") int pageNo,
			@PathVariable(value = "pageSize") int pageSize, @PathVariable(value = "vpnCardJson") String vpnCardJson) {
		Map<String, Object> param = ConvertUtil.parseMap(JsonUtil.parseBean(vpnCardJson, VPNCard.class));
		Page page = Page.newBuilder(pageNo, pageSize, "/sdn/vpncards",
				ConvertUtil.parseMap(JsonUtil.parseBean(vpnCardJson, VPNCard.class)));
		param.put(SystemConstant.PAGE, page);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(SystemConstant.DATA, vpnCardService.getList(param));
		result.put(SystemConstant.PAGE, page);
		return result;
	}

	/**
	 * 添加VPN用户信息
	 * @param vpnUser VPN用户信息
	 * @return 处理结果
	 */
	@RequestMapping(value = "/vpnuser", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody HandResult postVPNUser(@RequestBody VPNUser vpnUser) {
		vpnUser.setId(IDUtil.generateUniqueID());
		vpnUser.setInvalid(0);
		boolean serviceHandResult = false;
		try {
			vpnUserService.updateInsert(vpnUser);
			serviceHandResult = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serviceHandResult ? new HandResult(0, vpnUser.getId())
				: new HandResult(1, "add realNumber=" + vpnUser.getRealNumber() + " 's VPNUser is fail");
	}

	/**
	 * 根据真实号查询VPN用户信息
	 * @param realNumber
	 * @return VPN用户信息Json数据
	 */
	@RequestMapping(value = "/vpnuser/{realNumber}", method = RequestMethod.GET)
	public @ResponseBody VPNUser getVPNUser(@PathVariable(value = "realNumber") String realNumber) {
		return vpnUserService.getByRealNumber(realNumber);
	}

	/**
	 * 根据一组真实号查询VPN用户信息
	 * @param realNumbers 用户真实号连接字符串 1&2&3&4&5
	 * @return VPN用户信息Json数据集合
	 */
	@RequestMapping(value = "/vpnusers/{realNumbers}", method = RequestMethod.GET)
	public @ResponseBody List<VPNUser> getVPNUsers(@PathVariable(value = "realNumbers") String realNumbers) {
		return vpnUserService.getByRealNumbers(ConvertUtil.replaceSQLINSplit(realNumbers, "&"));
	}

	/**
	 * 分页并支持模糊查询VPN用户信息列表
	 * @param pageNo 页码
	 * @param pageSize 页行
	 * @param vpnUser 查询条件
	 * @return VPN用户信息集合和分页信息Json数据
	 */
	@RequestMapping(value = "/vpnusers/{pageNo}/{pageSize}", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getVPNUsers(@PathVariable(value = "pageNo") int pageNo,
			@PathVariable(value = "pageSize") int pageSize, VPNUser vpnUser) {
		Map<String, Object> param = ConvertUtil.parseMap(vpnUser);
		Page page = Page.newBuilder(pageNo, pageSize, "/sdn/vpnusers", ConvertUtil.parseMap(vpnUser));
		param.put(SystemConstant.PAGE, page);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(SystemConstant.DATA, vpnUserService.getList(param));
		result.put(SystemConstant.PAGE, page);
		return result;
	}

	/**
	 * 分页并支持模糊查询VPN用户信息列表
	 * @param pageNo 页码
	 * @param pageSize 页行
	 * @param vpnUserJson 查询条件
	 * @return VPN用户信息集合和分页信息Json数据
	 */
	@RequestMapping(value = "/vpnusers/{pageNo}/{pageSize}/{vpnUserJson:.+}", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getVPNUsers(@PathVariable(value = "pageNo") int pageNo,
			@PathVariable(value = "pageSize") int pageSize, @PathVariable(value = "vpnUserJson") String vpnUSerJson) {
		Map<String, Object> param = ConvertUtil.parseMap(JsonUtil.parseBean(vpnUSerJson, VPNUser.class));
		Page page = Page.newBuilder(pageNo, pageSize, "/sdn/vpnusers",
				ConvertUtil.parseMap(JsonUtil.parseBean(vpnUSerJson, VPNUser.class)));
		param.put(SystemConstant.PAGE, page);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(SystemConstant.DATA, vpnUserService.getList(param));
		result.put(SystemConstant.PAGE, page);
		return result;
	}

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
