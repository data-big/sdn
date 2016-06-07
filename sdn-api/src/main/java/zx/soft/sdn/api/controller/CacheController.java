package zx.soft.sdn.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import zx.soft.sdn.api.component.HandleResult;
import zx.soft.sdn.api.domain.Index;
import zx.soft.sdn.api.service.CacheService;

/**
 * 信息缓存控制器
 * 
 * @author xuran
 *
 */
@Controller
public class CacheController {

	/**
	 * 注入信息缓存业务层接口实现
	 */
	@Autowired
	private CacheService cacheService;

	/**
	 * 索引信息缓存
	 * @param index 索引数据
	 * @return 处理结果
	 */
	@RequestMapping(value = "/cache/index", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody HandleResult cacheIndex(@RequestBody Index index) {
		if (cacheService.cacheIndex(index)) {
			return new HandleResult(0, index.getId());
		} else {
			return new HandleResult(1, index.getId());
		}
	}

}
