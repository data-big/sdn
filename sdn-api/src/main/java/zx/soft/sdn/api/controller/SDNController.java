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

import zx.soft.sdn.api.common.Page;
import zx.soft.sdn.api.common.SystemConstant;
import zx.soft.sdn.api.model.VPNCard;
import zx.soft.sdn.api.model.VPNPostion;
import zx.soft.sdn.api.model.VPNUser;
import zx.soft.sdn.api.service.VPNCardService;
import zx.soft.sdn.api.util.ConvertUtil;
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
	 * 根据真实号查询VPN卡信息
	 * @param realNumber
	 * @return VPNCard Json数据
	 */
	@RequestMapping(value = "/vpncard/{realNumber}", method = RequestMethod.GET)
	public @ResponseBody VPNCard getVPNCard(@PathVariable(value = "realNumber") String realNumber) {
		return vpnCardService.getByRealNumber(realNumber);
	}

	/**
	 * 根据一组真实号查询VPN卡信息
	 * @param realNumbers 用户真实号连接字符串 1&2&3&4&5
	 * @return VPNCard Json数据
	 */
	@RequestMapping(value = "/vpncards/{realNumbers}", method = RequestMethod.GET)
	public @ResponseBody List<VPNCard> getVPNCards(@PathVariable(value = "realNumbers") String realNumbers) {
		return vpnCardService.getByRealNumbers(ConvertUtil.replaceSplit(realNumbers));
	}

	/**
	 * 分页查询VPN卡列表信息
	 * @param vpnCard
	 * @return JSON数据
	 */
	@RequestMapping(value = "/vpncards/{pageNo}/{pageSize}", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getVPNCards(@PathVariable(value = "pageNo") int pageNo,
			@PathVariable(value = "pageSize") int pageSize, VPNCard vpnCard) {
		Map<String, Object> param = ConvertUtil.parseMap(vpnCard);
		Page page = Page.newBuilder(pageNo, pageSize, "/sdn/vpncards");
		param.put(SystemConstant.PAGE, page);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(SystemConstant.DATA, vpnCardService.getList(param));
		result.put(SystemConstant.PAGE, page);
		return result;
	}

	/**
	 * 分页查询VPN卡列表信息
	 * @param vpnCard
	 * @return JSON数据
	 */
	@RequestMapping(value = "/vpncards/{pageNo}/{pageSize}/{vpnCardJson}", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getVPNCards(@PathVariable(value = "pageNo") int pageNo,
			@PathVariable(value = "pageSize") int pageSize, @PathVariable(value = "vpnCardJson") String vpnCardJson) {
		Map<String, Object> param = ConvertUtil.parseMap(JsonUtil.parseBean(vpnCardJson, VPNCard.class));
		Page page = Page.newBuilder(pageNo, pageSize, "/sdn/vpncards");
		param.put(SystemConstant.PAGE, page);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(SystemConstant.DATA, vpnCardService.getList(param));
		result.put(SystemConstant.PAGE, page);
		return result;
	}

	/**
	 * 根据真实号查询VPN用户信息
	 * @param vpnCard
	 * @return VPNUser Json数据
	 */
	@RequestMapping(value = "/vpnuser/{realNumber}", method = RequestMethod.GET)
	public @ResponseBody VPNUser getVPNUser(@PathVariable(value = "realNumber") String realNumber) {
		return new VPNUser("vpnuser", realNumber, "13900000000", "张三", 1, "342622XXXXXXXX8888", "13800000000", "李四", 1,
				"342622XXXXXXXX8888", new Date(), new Date(), "李刚", 1, new Date());
	}

	/**
	 * 分页查询VPN用户信息
	 * @param vpnCard
	 * @return JSON数据
	 */
	@RequestMapping(value = "/vpnusers/{pageNo}/{pageSize}", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getVPNUsers(@PathVariable(value = "pageNo") int pageNo,
			@PathVariable(value = "pageSize") int pageSize) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		VPNUser vpnUser = new VPNUser("vpnuser", "123456789", "13900000000", "张三", 1, "342622XXXXXXXX8888",
				"13800000000", "李四", 1, "342622XXXXXXXX8888", new Date(), new Date(), "李刚", 1, new Date());
		List<VPNUser> list = new ArrayList<VPNUser>();
		list.add(vpnUser);
		jsonMap.put("data", list);
		jsonMap.put("page", Page.newBuilder(pageNo, pageSize, ""));
		return jsonMap;
	}

	/**
	 * 根据真实号和时间区间查询用户地理位置信息
	 * @param vpnCard
	 * @return VPNCard Json数据
	 */
	@RequestMapping(value = "/vpnpostion/{realNumber}/{start}/{end}", method = RequestMethod.GET)
	public @ResponseBody List<VPNPostion> getVPNPostion(@PathVariable(value = "realNumber") String realNumber,
			@PathVariable(value = "start") String start, @PathVariable(value = "end") String end) {
		List<VPNPostion> list = new ArrayList<VPNPostion>();
		list.add(new VPNPostion("vpnpostion", realNumber, "34567", "56789", new Date()));
		return list;
	}
}
