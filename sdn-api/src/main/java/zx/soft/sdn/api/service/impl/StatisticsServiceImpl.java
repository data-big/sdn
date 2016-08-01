package zx.soft.sdn.api.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zx.soft.sdn.api.component.SystemConstant;
import zx.soft.sdn.api.dao.StatisticsDao;
import zx.soft.sdn.api.service.StatisticsService;
import zx.soft.sdn.model.DateCount;
import zx.soft.sdn.model.ScatterCount;
import zx.soft.sdn.model.TypeCount;
import zx.soft.sdn.util.ExceptionUtil;
import zx.soft.sdn.util.HiveUtil;

/**
 * 数据统计业务层接口实现
 * 
 * @author xuran
 *
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(StatisticsServiceImpl.class);

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

	@Override
	public ScatterCount countPart(String region, String start, String end) {
		//时间补齐，实现天级和秒级双重查询。
		if (start.length() == 10)
			start += " 00:00:00";
		if (end.length() == 10)
			end += " 23:59:59";
		HiveUtil hiveUtil = HiveUtil.getInstance();
		Integer max = null;
		Integer min = null;
		List<TypeCount> data = new ArrayList<TypeCount>();
		if ("安徽".equals(region)) {
			for (String city : SystemConstant.ANHUI_CITY) {
				Connection connection = hiveUtil.getConnection();
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				if (null != connection) {
					try {
						preparedStatement = connection.prepareStatement(
								"select count(id) from vpnpostion where address like ? and time>? and time < ?");
						preparedStatement.setString(1, "%" + city + "%");
						preparedStatement.setString(2, start);
						preparedStatement.setString(3, end);
						resultSet = preparedStatement.executeQuery();
						while (resultSet.next()) {
							TypeCount typeCount = new TypeCount();
							typeCount.setName(city);
							int value = resultSet.getInt(1);
							typeCount.setValue(value);
							data.add(typeCount);
							//求最大值和最小值
							if (null == max)
								max = value;
							if (null == min)
								min = value;
							if (value > max)
								max = value;
							if (value < min)
								min = value;
						}
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("Exception : {}", ExceptionUtil.exceptionToString(e));
					} finally {
						hiveUtil.close(resultSet, preparedStatement, connection);
					}
				}
			}
			ScatterCount scatterCount = new ScatterCount();
			scatterCount.setMax(max);
			scatterCount.setMin(min);
			scatterCount.setData(data);
			return scatterCount;
		} else {
			return null;
		}

	}

}
