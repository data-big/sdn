package zx.soft.sdn.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import zx.soft.sdn.api.component.HandleResult;
import zx.soft.sdn.api.domain.Index;
import zx.soft.sdn.util.ConfigUtil;
import zx.soft.sdn.util.ExceptionUtil;
import zx.soft.sdn.util.JsonUtil;
import zx.soft.sdn.util.RedisUtil;

/**
 * 信息缓存控制器
 * 
 * @author xuran
 *
 */
@Controller
public class CacheController {

	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(CacheController.class);

	/**
	 * 索引信息缓存
	 * @param index 索引数据
	 * @return HandleResult 处理结果
	 */
	@RequestMapping(value = "/cache/index", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody HandleResult cacheIndex(@RequestBody Index index) {
		//写入Redis
		boolean handleResult = false;
		try {
			handleResult = RedisUtil.getInstance().sadd(
					ConfigUtil.getProps("redis.properties").getProperty("sdn.cache.internetinfo"),
					JsonUtil.parseString(index)) == 1 ? true : false;
			if (handleResult) {
				logger.info("****ID : {} 的上网信息缓存成功****", index.getId());
				return new HandleResult(0, index.getId());
			} else {
				logger.error("Exception : [ ID : {} 的上网信息缓存失败 ]", index.getId());
				return new HandleResult(1, index.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception : [ ID : {} 的上网信息缓存失败 ] {}", index.getId(), ExceptionUtil.exceptionToString(e));
			return new HandleResult(1, index.getId());
		}

	}

}
