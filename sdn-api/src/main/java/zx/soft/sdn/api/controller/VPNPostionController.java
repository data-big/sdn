package zx.soft.sdn.api.controller;

import java.util.List;

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
import zx.soft.sdn.api.service.VPNPostionService;
import zx.soft.sdn.model.VPNPostion;

/**
 * VPN用户地理位置信息控制器
 * 
 * @author xuran
 *
 */
@Controller
public class VPNPostionController {

	/**
	 * 注入VPN用户地理位置信息业务层接口实现
	 */
	@Autowired
	private VPNPostionService vpnPostionService;

	/**
	 * 根据SAC和LAC值在基站库查询详细地址
	 * @param sac SAC值
	 * @param lac LAC值
	 * @return 详细地址
	 */
	@RequestMapping(value = "/vpnpostion/address/{sac}/{lac}", method = RequestMethod.GET)
	public @ResponseBody String getAddress(@PathVariable(value = "sac") String sac,
			@PathVariable(value = "lac") String lac) {
		return vpnPostionService.queryAddress(sac, lac);
	}

	/**
	 * OpenTSDB旧方案 弃用
	 * 添加VPN用户地理位置信息
	 * @param vpnPostion VPN用户地理位置信息
	 * @return 处理结果
	 */
	@RequestMapping(value = "/vpnpostion", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody HandleResult postVPNPostion(@RequestBody VPNPostion vpnPostion) {
		return vpnPostionService.putVPNPostion(vpnPostion) ? new HandleResult(0, vpnPostion.getRealNumber())
				: new HandleResult(1, vpnPostion.getRealNumber());
	}

	/**
	 * 根据真实号和时间区间查询用户地理位置信息
	 * @param realNumber 用户真实号
	 * @param start 开始时间 yyyy-MM-dd HH:mm:ss
	 * @param end 结束时间
	 * @return VPN用户地理位置信息集合
	 */
	@RequestMapping(value = "/vpnpostion/{realNumber}/{start}/{end}", method = RequestMethod.GET)
	public @ResponseBody List<VPNPostion> getVPNPostion(@PathVariable(value = "realNumber") String realNumber,
			@PathVariable(value = "start") String start, @PathVariable(value = "end") String end) {
		return vpnPostionService.queryVPNPostions(realNumber, start, end);
	}

}
