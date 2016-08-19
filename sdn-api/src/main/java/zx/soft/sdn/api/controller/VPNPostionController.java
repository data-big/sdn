package zx.soft.sdn.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
