package zx.soft.sdn.api.service;

import zx.soft.sdn.api.model.Location;

/**
 * 基站位置信息业务层接口
 * 
 * @author xuran
 *
 */
public interface LocationService {

	/**
	 * 根据基站SAC和LAC信息查询基站位置信息
	 * @param sac 基站SAC
	 * @param lac 基站LAC
	 * @return 基站位置信息
	 */
	public Location getLocation(String sac, String lac);

}
