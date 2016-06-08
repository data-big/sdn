package zx.soft.sdn.api.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zx.soft.sdn.api.component.MybatisSessionFactory;
import zx.soft.sdn.api.component.SystemConstant;
import zx.soft.sdn.api.dao.LocationDao;
import zx.soft.sdn.api.dao.VPNPostionDao;
import zx.soft.sdn.api.domain.TSDBPutRequest;
import zx.soft.sdn.api.domain.TSDBPutResponse;
import zx.soft.sdn.api.domain.TSDBQueries;
import zx.soft.sdn.api.domain.TSDBQueryRequest;
import zx.soft.sdn.api.domain.TSDBQueryResponse;
import zx.soft.sdn.api.domain.TSDBTags;
import zx.soft.sdn.api.service.VPNPostionService;
import zx.soft.sdn.model.Location;
import zx.soft.sdn.model.VPNPostion;
import zx.soft.sdn.util.DateUtil;
import zx.soft.sdn.util.ExceptionUtil;

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

	/**
	 * 注入VPN用户地理位置信息持久层(OpenTSDB)接口实现
	 */
	@Autowired
	private VPNPostionDao vpnPostionDao;

	@Override
	public boolean putVPNPostion(VPNPostion vpnPostion) {
		//创建OpenTSDB Put接口请求参数
		TSDBPutRequest tsdbPutRequest = new TSDBPutRequest();
		//1.设置指标名称
		tsdbPutRequest.setMetric(SystemConstant.TSDB_METRIC);
		//2.设置时间
		try {
			tsdbPutRequest.setTimestamp(DateUtil.simpleFormat.parse(vpnPostion.getTime()).getTime());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception : 日期转换时间戳错误 {}", ExceptionUtil.exceptionToString(e));
			return false;
		}
		//3.设置指标值，暂设0。
		tsdbPutRequest.setValue("0");
		//4.设置标签
		TSDBTags tags = new TSDBTags();
		tags.setRealNumber(vpnPostion.getRealNumber());
		tags.setSac(vpnPostion.getSac());
		tags.setLac(vpnPostion.getLac());
		tsdbPutRequest.setTags(tags);
		//5.调用持久层接口
		TSDBPutResponse daoHandleResult = null;
		try {
			daoHandleResult = vpnPostionDao.putVPNPostion(tsdbPutRequest);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception : VPN用户[ realNumber={} ]的地理位置信息添加错误 {}", vpnPostion.getRealNumber(),
					ExceptionUtil.exceptionToString(e));
		}
		//6.解析持久层处理结果
		boolean handleResult = null != daoHandleResult && daoHandleResult.getSuccess() > 0
				&& daoHandleResult.getFailed() == 0 ? true : false;
		//7.记录日志
		if (handleResult) {
			logger.info("****添加VPN用户[ realNumber={} ]地理位置信息成功****", vpnPostion.getRealNumber());
		} else {
			logger.info("****添加VPN用户[ realNumber={} ]地理位置信息失败****", vpnPostion.getRealNumber());
		}
		return handleResult;
	}

	@Override
	public List<VPNPostion> queryVPNPostions(String realNumber, String start, String end) {
		//时间补齐，实现天级和秒级双重查询。
		if (start.length() == 10)
			start += " 00:00:00";
		if (end.length() == 10)
			end += " 23:59:59";
		//创建OpenTSDB Query接口请求参数
		TSDBQueryRequest tsdbQueryRequest = new TSDBQueryRequest();
		//1.设置时间区间
		try {
			tsdbQueryRequest.setStart(DateUtil.simpleFormat.parse(start).getTime());
			tsdbQueryRequest.setEnd(DateUtil.simpleFormat.parse(end).getTime());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception : 日期转换时间戳错误 {}", ExceptionUtil.exceptionToString(e));
			return new ArrayList<VPNPostion>();
		}
		//2.设置查询条件
		TSDBQueries tsdbQueries = new TSDBQueries();
		//设置指标名称
		tsdbQueries.setMetric(SystemConstant.TSDB_METRIC);
		//设置聚合条件
		tsdbQueries.setAggregator("sum");
		//设置标签
		TSDBTags tags = new TSDBTags();
		tags.setRealNumber(realNumber);
		tags.setSac("*");
		tags.setLac("*");
		tsdbQueries.setTags(tags);
		TSDBQueries[] queries = new TSDBQueries[] { tsdbQueries };
		tsdbQueryRequest.setQueries(queries);
		//3.调用持久层接口
		List<TSDBQueryResponse> daoHandleResult = null;
		try {
			daoHandleResult = vpnPostionDao.queryVPNPostions(tsdbQueryRequest);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception : VPN用户[ realNumber={} ]的地理位置信息查询失败 {}", realNumber,
					ExceptionUtil.exceptionToString(e));
			return new ArrayList<VPNPostion>();
		}
		//4.解析持久层处理结果
		//如果持久层查询成功，则进行数据解析。
		if (null != daoHandleResult) {
			//最终处理结果
			List<VPNPostion> handleResult = new ArrayList<VPNPostion>();
			//遍历处理结果
			for (TSDBQueryResponse tsdbQueryResponse : daoHandleResult) {
				//获取时间戳集合
				Map<Long, Integer> dps = tsdbQueryResponse.getDps();
				//获取查询结果
				TSDBTags tsdbTags = tsdbQueryResponse.getTags();
				//如果时间戳集合和查询结果不为空，则继续解析数据。
				if (null != dps && null != tsdbTags) {
					//解析数据
					for (Long timestamp : dps.keySet()) {
						//获取VPN用户地理位置信息
						VPNPostion vpnPostion = new VPNPostion();
						vpnPostion.setRealNumber(tsdbTags.getRealNumber());
						vpnPostion.setSac(tsdbTags.getSac());
						vpnPostion.setLac(tsdbTags.getLac());
						//时间戳转换为yyyy-MM-dd HH:mm:ss日期格式
						timestamp = Long.valueOf(timestamp.toString() + "000");
						vpnPostion.setTime(DateUtil.simpleFormat.format(new Date(timestamp)));
						//获取基站精确位置信息
						Location location = null;
						try (SqlSession sqlSession = MybatisSessionFactory.openSession();) {
							location = sqlSession.getMapper(LocationDao.class).getLocation(vpnPostion.getSac(),
									vpnPostion.getLac());
						} catch (Exception e) {
							e.printStackTrace();
							logger.error("Exception : VPN用户[ realNumber={} ]的精确位置信息获取失败 {}", realNumber);
						}
						vpnPostion.setLocation(location);
						//写入处理结果
						handleResult.add(vpnPostion);
					}
				}
			}
			return handleResult;
		} else {
			logger.error("Exception : VPN用户[ realNumber={} ]的地理位置信息查询失败 {}", realNumber);
			return new ArrayList<VPNPostion>();
		}
	}

}
