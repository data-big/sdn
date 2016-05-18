package zx.soft.sdn.api.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zx.soft.sdn.api.dao.VPNCardDao;
import zx.soft.sdn.api.model.VPNCard;
import zx.soft.sdn.api.service.VPNCardService;

/**
 * VPN卡信息业务层接口实现
 * 
 * @author xuran
 *
 */
@Service
public class VPNCardServiceImpl implements VPNCardService {

	/**
	 * 注入VPN卡信息持久层接口实现
	 */
	@Autowired
	private VPNCardDao vpnCardDao;

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
		return vpnCardDao.getListByRealNumbers(realNumbers);
	}

}
