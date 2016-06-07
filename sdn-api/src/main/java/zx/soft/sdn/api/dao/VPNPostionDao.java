package zx.soft.sdn.api.dao;

import java.util.List;

import zx.soft.sdn.api.domain.TSDBPutRequest;
import zx.soft.sdn.api.domain.TSDBPutResponse;
import zx.soft.sdn.api.domain.TSDBQueryRequest;
import zx.soft.sdn.api.domain.TSDBQueryResponse;

/**
 * VPN用户地理位置信息持久层(OpenTSDB)接口
 * 
 * @author xuran
 *
 */
public interface VPNPostionDao {

	/**
	 * 添加VPN地理位置信息到OpenTSDB
	 * @param tsdbPutRequest OpenTSDB PUT接口请求数据
	 * @return OpenTSDB Put接口响应数据模型
	 */
	public TSDBPutResponse putVPNPostion(TSDBPutRequest tsdbPutRequest);

	/**
	 * 根据用户真实号和时间戳区间从OpenTSDB查询VPN用户地理位置信息
	 * @param tsdbQueryRequest OpenTSDB PUT接口请求数据
	 * @return OpenTSDB Query接口响应数据模型
	 */
	public List<TSDBQueryResponse> queryVPNPostions(TSDBQueryRequest tsdbQueryRequest);


}
