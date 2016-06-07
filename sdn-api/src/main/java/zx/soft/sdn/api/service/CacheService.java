package zx.soft.sdn.api.service;

import zx.soft.sdn.api.domain.Index;

/**
 * 信息缓存业务层接口
 * 
 * @author xuran
 *
 */
public interface CacheService {

	/**
	 * 缓存索引信息
	 * @param index 索引信息
	 * @return 成功或失败
	 */
	public boolean cacheIndex(Index index);

}
