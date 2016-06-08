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

import zx.soft.sdn.api.component.HandleResult;
import zx.soft.sdn.api.component.Page;
import zx.soft.sdn.api.component.SystemConstant;
import zx.soft.sdn.api.service.VPNUserService;
import zx.soft.sdn.model.VPNUser;
import zx.soft.sdn.util.ConvertUtil;
import zx.soft.sdn.util.IDUtil;
import zx.soft.sdn.util.JsonUtil;

/**
 * VPN用户信息控制器
 * 
 * @author xuran
 *
 */
@Controller
public class VPNUserController {

	/**
	 * 注入VPN用户信息业务层接口实现
	 */
	@Autowired
	private VPNUserService vpnUserService;

	/**
	 * 添加VPN用户信息
	 * @param vpnUser VPN用户信息
	 * @return 处理结果
	 */
	@RequestMapping(value = "/vpnuser", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody HandleResult postVPNUser(@RequestBody VPNUser vpnUser) {
		vpnUser.setId(IDUtil.generateUniqueID());
		vpnUser.setInvalid(0);
		boolean serviceHandleResult = false;
		try {
			vpnUserService.updateInsert(vpnUser);
			serviceHandleResult = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serviceHandleResult ? new HandleResult(0, vpnUser.getId())
				: new HandleResult(1, "add realNumber=" + vpnUser.getRealNumber() + " 's VPNUser is fail");
	}

	/**
	 * 根据真实号查询VPN用户信息
	 * @param realNumber
	 * @return VPN用户信息
	 */
	@RequestMapping(value = "/vpnuser/{realNumber}", method = RequestMethod.GET)
	public @ResponseBody VPNUser getVPNUser(@PathVariable(value = "realNumber") String realNumber) {
		return vpnUserService.getByRealNumber(realNumber);
	}

	/**
	 * 根据一组真实号查询VPN用户信息
	 * @param realNumbers 用户真实号连接字符串 1&2&3&4&5
	 * @return VPN用户信息集合
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
	 * @return VPN用户信息集合和分页信息
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
	 * @return  VPN用户信息集合和分页信息
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

}
