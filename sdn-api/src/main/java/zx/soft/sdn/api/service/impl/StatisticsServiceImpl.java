package zx.soft.sdn.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zx.soft.sdn.api.dao.StatisticsDao;
import zx.soft.sdn.api.model.DateCount;
import zx.soft.sdn.api.model.TypeCount;
import zx.soft.sdn.api.service.StatisticsService;

/**
 * 数据统计业务层接口实现
 * 
 * @author xuran
 *
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

	/**
	 * 注入数据统计持久层接口实现
	 */
	@Autowired
	private StatisticsDao statisticsDao;

	@Override
	public List<DateCount> countUser(String start, String end) {
		return statisticsDao.countUser(start, end);
	}

	@Override
	public List<TypeCount> countUserByIDType(String start, String end) {
		return statisticsDao.countUserByIDType(start, end);
	}

	@Override
	public List<DateCount> countCard(String start, String end) {
		return statisticsDao.countCard(start, end);
	}

	@Override
	public List<TypeCount> countCardByStatus() {
		return statisticsDao.countCardByStatus();
	}

}
