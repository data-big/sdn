package zx.soft.sdn.api.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zx.soft.sdn.api.dao.VPNUserDao;
import zx.soft.sdn.api.service.VPNUserService;
import zx.soft.sdn.model.VPNUser;
import zx.soft.sdn.util.ExceptionUtil;
import zx.soft.sdn.util.JsonUtil;

/**
 * VPN用户信息业务层接口实现
 * 
 * @author xuran
 *
 */
@Service
public class VPNUserServiceImpl implements VPNUserService {

	/**
	 * 日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(VPNUserServiceImpl.class);

	/**
	 * 注入VPN用户信息持久层接口实现
	 */
	@Autowired
	private VPNUserDao vpnUserDao;

	@Override
	public void batchUpdateInsert(VPNUser vpnUser) {
		try {
			vpnUserDao.updateVPNUserToInvalid(vpnUser.getRealNumber());
		} catch (Exception e) {
			logger.error("Exception : [realNumber={} 的VPN用户信息更新异常 : {}]", vpnUser.getRealNumber(),
					ExceptionUtil.exceptionToString(e));
			//抛出异常交给Spring事务管理。
			throw e;
		}
		try {
			vpnUserDao.insertVPNUser(vpnUser);
		} catch (Exception e) {
			logger.error("Exception : [realNumber={} 的VPN用户信息添加异常 : {}]", vpnUser.getRealNumber(),
					ExceptionUtil.exceptionToString(e));
			//抛出异常交给Spring事务管理。
			throw e;
		}
		logger.info("****VPNUser信息 : {} 添加成功****", JsonUtil.parseString(vpnUser));
	}

	@Override
	public VPNUser getByRealNumber(String realNumber) {
		return vpnUserDao.getByRealNumber(realNumber);
	}

	@Override
	public List<VPNUser> getByRealNumbers(String realNumbers) {
		return vpnUserDao.getListByRealNumbers(realNumbers);
	}

	@Override
	public List<VPNUser> getList(Map<String, Object> param) {
		return vpnUserDao.getList(param);
	}

}
