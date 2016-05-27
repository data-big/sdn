package zx.soft.sdn.api.controller;

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

import zx.soft.sdn.api.component.HandResult;
import zx.soft.sdn.api.component.Page;
import zx.soft.sdn.api.component.SystemConstant;
import zx.soft.sdn.api.model.VPNCard;
import zx.soft.sdn.api.service.VPNCardService;
import zx.soft.sdn.util.ConvertUtil;
import zx.soft.sdn.util.IDUtil;
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

}
