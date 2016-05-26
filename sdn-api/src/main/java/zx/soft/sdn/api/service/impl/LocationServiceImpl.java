package zx.soft.sdn.api.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import zx.soft.sdn.api.common.MybatisSessionFactory;
import zx.soft.sdn.api.dao.LocationDao;
import zx.soft.sdn.api.model.Location;
import zx.soft.sdn.api.service.LocationService;

/**
 * 基站位置信息业务层接口实现
 * 
 * @author xuran
 *
 */
@Service
public class LocationServiceImpl implements LocationService {

	@Override
	public Location getLocation(String sac, String lac) {
		try (SqlSession sqlSession = MybatisSessionFactory.openSession();) {
			return sqlSession.getMapper(LocationDao.class).getLocation(sac, lac);
		}
	}

}
