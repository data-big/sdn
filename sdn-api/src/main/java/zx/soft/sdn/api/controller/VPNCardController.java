package zx.soft.sdn.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import zx.soft.sdn.api.component.Page;
import zx.soft.sdn.api.component.SystemConstant;
import zx.soft.sdn.api.domain.VPNCardVO;
import zx.soft.sdn.api.service.VPNCardService;
import zx.soft.sdn.model.VPNCard;
import zx.soft.sdn.util.ConvertUtil;
import zx.soft.sdn.util.JsonUtil;

/**
 * VPN卡信息控制器
 * 
 * @author xuran
 *
 */
@Controller
public class VPNCardController {

	/**
	 * 注入VPN卡信息业务层接口实现
	 */
	@Autowired
	private VPNCardService vpnCardService;

	/**
	 * 根据业务IP查询用户真实号
	 * @param bizIP 业务IP地址
	 * @return {"realNumber":value} 用户真实号 
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
	 * @return VPN卡信息
	 */
	@RequestMapping(value = "/vpncard/{realNumber}", method = RequestMethod.GET)
	public @ResponseBody VPNCard getVPNCard(@PathVariable(value = "realNumber") String realNumber) {
		return vpnCardService.getByRealNumber(realNumber);
	}

	/**
	 * 根据一组真实号查询VPN卡信息
	 * @param realNumbers 用户真实号连接字符串 1&2&3&4&5
	 * @return VPN卡信息集合
	 */
	@RequestMapping(value = "/vpncards/{realNumbers}", method = RequestMethod.GET)
	public @ResponseBody List<VPNCard> getVPNCards(@PathVariable(value = "realNumbers") String realNumbers) {
		return vpnCardService.getByRealNumbers(ConvertUtil.replaceSQLINSplit(realNumbers, "&"));
	}

	/**
	 * 分页并支持模糊查询，时间区间查询VPN卡信息列表
	 * @param pageNo 页码
	 * @param pageSize 页行
	 * @param VPNCardVO 查询条件
	 * @return VPN卡信息集合和分页信息
	 */
	@RequestMapping(value = "/vpncards/{pageNo}/{pageSize}", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getVPNCards(@PathVariable(value = "pageNo") int pageNo,
			@PathVariable(value = "pageSize") int pageSize, VPNCardVO vpnCardVO) {
		Map<String, Object> param = ConvertUtil.parseMap(vpnCardVO);
		Page page = Page.newBuilder(pageNo, pageSize, "/sdn/vpncards", ConvertUtil.parseMap(vpnCardVO));
		param.put(SystemConstant.PAGE, page);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(SystemConstant.DATA, vpnCardService.getList(param));
		result.put(SystemConstant.PAGE, page);
		return result;
	}

	/**
	 * 分页并支持模糊查询，时间区间查询VPN卡信息列表
	 * @param pageNo 页码
	 * @param pageSize 页行
	 * @param vpnCardVOJson 查询条件
	 * @return VPN卡信息集合和分页信息
	 */
	@RequestMapping(value = "/vpncards/{pageNo}/{pageSize}/{vpnCardVOJson:.+}", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getVPNCards(@PathVariable(value = "pageNo") int pageNo,
			@PathVariable(value = "pageSize") int pageSize,
			@PathVariable(value = "vpnCardVOJson") String vpnCardVOJson) {
		Map<String, Object> param = ConvertUtil.parseMap(JsonUtil.parseBean(vpnCardVOJson, VPNCardVO.class));
		Page page = Page.newBuilder(pageNo, pageSize, "/sdn/vpncards",
				ConvertUtil.parseMap(JsonUtil.parseBean(vpnCardVOJson, VPNCardVO.class)));
		param.put(SystemConstant.PAGE, page);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(SystemConstant.DATA, vpnCardService.getList(param));
		result.put(SystemConstant.PAGE, page);
		return result;
	}

}
