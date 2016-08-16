package zx.soft.sdn.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import zx.soft.sdn.api.dao.BaseStationDao;
import zx.soft.sdn.api.service.VPNPostionService;
import zx.soft.sdn.model.BaseStation;
import zx.soft.sdn.model.VPNPostion;
import zx.soft.sdn.util.DateUtil;
import zx.soft.sdn.util.ExceptionUtil;
import zx.soft.sdn.util.HBaseUtil;
import zx.soft.sdn.util.IDUtil;
import zx.soft.sdn.util.JsonUtil;
import zx.soft.sdn.util.MybatisUtil;

/**
 * VPN用户地理位置信息业务层接口实现
 * 
 * @author xuran
 *
 */
@Service
public class VPNPostionServiceImpl implements VPNPostionService {

	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(VPNPostionServiceImpl.class);

	@Override
	public boolean putVPNPostion(VPNPostion vpnPostion) {
		try {
			String rowKey = IDUtil.generateUniqueID();
			HBaseUtil hbase = HBaseUtil.getInstance();
			hbase.put("sdn", rowKey, "vpnpostion", "realNumber", vpnPostion.getRealNumber());
			hbase.put("sdn", rowKey, "vpnpostion", "bizIP", vpnPostion.getBizIP());
			hbase.put("sdn", rowKey, "vpnpostion", "sac", vpnPostion.getSac());
			hbase.put("sdn", rowKey, "vpnpostion", "lac", vpnPostion.getLac());
			//获取基站精确位置信息
			BaseStation baseStation = null;
			try (SqlSession sqlSession = MybatisUtil.getInstance().openSession();) {
				baseStation = sqlSession.getMapper(BaseStationDao.class).getBySacAndLAC(vpnPostion.getSac(),
						vpnPostion.getLac());
				//如果SAC+LAC组合查询无数据，则选择CELL+LAC组合查询。****特宽业务要求****
				if (null == baseStation) {
					baseStation = sqlSession.getMapper(BaseStationDao.class).getByCellAndLAC(vpnPostion.getSac(),
							vpnPostion.getLac());
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Exception : [ sac : {} lac : {} ]获取详细地址失败 {}", vpnPostion.getSac(), vpnPostion.getLac(),
						ExceptionUtil.exceptionToString(e));
			}
			hbase.put("sdn", rowKey, "vpnpostion", "address", baseStation != null ? baseStation.getAddress() : null);
			hbase.put("sdn", rowKey, "vpnpostion", "time", vpnPostion.getTime());
			logger.info("****添加VPN用户[ realNumber={} ]地理位置信息成功****", vpnPostion.getRealNumber());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception : 添加VPN用户[ realNumber={} ]地理位置信息失败 {}", vpnPostion.getRealNumber(),
					ExceptionUtil.exceptionToString(e));
			return false;
		}
	}

	@Override
	public List<VPNPostion> queryVPNPostions(String realNumber, String start, String end) {
		List<VPNPostion> list = new ArrayList<VPNPostion>();
		//时间补齐，实现天级和秒级双重查询。
		if (start.length() == 10)
			start += " 00:00:00";
		if (end.length() == 10)
			end += " 23:59:59";
		//转换HbaseRowKey
		try {
			start = realNumber + DateUtil.yyyyMMddHHmmss.format(DateUtil.simpleFormat.parse(start));
			end = realNumber + DateUtil.yyyyMMddHHmmss.format(DateUtil.simpleFormat.parse(end));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception : 根据真实号和时间区间查询用户地理位置信息时间参数有误[ start={},end={}]", start, end);
			return list;
		}
		//查询HBase
		HBaseUtil hbase = HBaseUtil.getInstance();
		List<Map<String, Object>> mapList = hbase.scan("sdn", "vpnpostion",
				new String[] { "realNumber", "bizIP", "sac", "lac", "time" }, start, end);
		//遍历查询结果
		for (Map<String, Object> map : mapList) {
			//转换对象
			VPNPostion vpnPostion = JsonUtil.parseBean(JsonUtil.parseString(map), VPNPostion.class);
			//获取基站精确位置信息
			BaseStation baseStation = null;
			SqlSession sqlSession = null;
			try {
				sqlSession = MybatisUtil.getInstance().openSession();
				baseStation = sqlSession.getMapper(BaseStationDao.class).getBySacAndLAC(vpnPostion.getSac(),
						vpnPostion.getLac());
				//如果SAC+LAC组合查询无数据，则选择CELL+LAC组合查询。****特宽业务要求****
				if (null == baseStation) {
					baseStation = sqlSession.getMapper(BaseStationDao.class).getByCellAndLAC(vpnPostion.getSac(),
							vpnPostion.getLac());
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Exception : VPN用户[ realNumber={} ]的精确位置信息获取失败 {}", realNumber);
			} finally {
				if (null != sqlSession) {
					sqlSession.close();
				}
			}
			//设置基站信息
			vpnPostion.setBaseStation(baseStation);
			//加入集合
			list.add(vpnPostion);
		}
		return list;
	}

}
