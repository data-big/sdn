package zx.soft.sdn.api.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import zx.soft.sdn.api.component.SystemConstant;
import zx.soft.sdn.api.domain.Index;
import zx.soft.sdn.api.service.CacheService;
import zx.soft.sdn.util.ExceptionUtil;
import zx.soft.sdn.util.JsonUtil;
import zx.soft.sdn.util.RedisUtil;

/**
 * 信息缓存业务层接口实现
 * 
 * @author xuran
 *
 */
@Service
public class CacheServiceImpl implements CacheService {

	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(CacheServiceImpl.class);

	@Override
	public boolean cacheIndex(Index index) {
		boolean handleResult = false;
		try {
			handleResult = RedisUtil.getInstance().sadd(SystemConstant.CACHE_KEY, JsonUtil.parseString(index)) == 1
					? true : false;
			if (handleResult) {
				logger.info("****ID : {} 的上网信息缓存成功****", index.getId());
				handleResult = true;
			} else {
				logger.error("Exception : [ ID : {} 的上网信息缓存失败 ]", index.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception : [ ID : {} 的上网信息缓存失败 ] {}", index.getId(), ExceptionUtil.exceptionToString(e));
		}
		return handleResult;
	}

}
