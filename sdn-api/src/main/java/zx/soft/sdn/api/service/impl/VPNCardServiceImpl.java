package zx.soft.sdn.api.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zx.soft.sdn.api.dao.VPNCardDao;
import zx.soft.sdn.api.model.VPNCard;
import zx.soft.sdn.api.service.VPNCardService;
import zx.soft.sdn.api.util.ExceptionUtil;

/**
 * VPN卡信息业务层接口实现
 * 
 * @author xuran
 *
 */
@Service
public class VPNCardServiceImpl implements VPNCardService {

	/**
	 * 日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(VPNCardServiceImpl.class);

	/**
	 * 注入VPN卡信息持久层接口实现
	 */
	@Autowired
	private VPNCardDao vpnCardDao;

	@Override
	public void updateInsert(VPNCard vpnCard) {
		try {
			vpnCardDao.updateVPNCardToInvalid(vpnCard.getRealNumber());
		} catch (Exception e) {
			logger.error("Exception : [realNumber={} 的VPN卡信息更新异常 : {}]", vpnCard.getRealNumber(),
					ExceptionUtil.exceptionToString(e));
			//抛出异常交给Spring事务管理。
			throw e;
		}
		try {
			vpnCardDao.insertVPNCard(vpnCard);
		} catch (Exception e) {
			logger.error("Exception : [realNumber={} 的VPN卡信息添加异常 : {}]", vpnCard.getRealNumber(),
					ExceptionUtil.exceptionToString(e));
			//抛出异常交给Spring事务管理。
			throw e;
		}
	}

	@Override
	public String getRealNumber(String bizIP) {
		return vpnCardDao.getRealNumber(bizIP);
	}

	@Override
	public VPNCard getByRealNumber(String realNumber) {
		return vpnCardDao.getByRealNumber(realNumber);
	}

	@Override
	public List<VPNCard> getList(Map<String, Object> param) {
		return vpnCardDao.getList(param);
	}

	@Override
	public List<VPNCard> getByRealNumbers(String realNumbers) {
		return vpnCardDao.getByRealNumbers(realNumbers);
	}

}
