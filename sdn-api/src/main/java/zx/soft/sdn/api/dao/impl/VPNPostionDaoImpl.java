package zx.soft.sdn.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import zx.soft.sdn.api.dao.VPNPostionDao;
import zx.soft.sdn.api.domain.TSDBPutRequest;
import zx.soft.sdn.api.domain.TSDBPutResponse;
import zx.soft.sdn.api.domain.TSDBQueryRequest;
import zx.soft.sdn.api.domain.TSDBQueryResponse;
import zx.soft.sdn.util.ConfigUtil;
import zx.soft.sdn.util.HttpClientUtil;
import zx.soft.sdn.util.JsonUtil;

/**
 * VPN用户地理位置信息持久层(OpenTSDB)接口实现
 *  
 * @author xuran
 *
 */
@Repository(value = "vpnPostionDao") //设置为Spring扫描创建，(value = "vpnPostionDao") 用于区分MyBatis中创建的Bean。
public class VPNPostionDaoImpl implements VPNPostionDao {

	@Override
	public TSDBPutResponse putVPNPostion(TSDBPutRequest tsdbPutRequest) {
		String handleResult = HttpClientUtil.doPost(ConfigUtil.getProps("opentsdb.properties").getProperty("put.api"),
				JsonUtil.parseString(tsdbPutRequest), "json");
		return JsonUtil.parseBean(handleResult, TSDBPutResponse.class);
	}

	@Override
	public List<TSDBQueryResponse> queryVPNPostions(TSDBQueryRequest tsdbQueryRequest) {
		String handleResult = HttpClientUtil.doPost(ConfigUtil.getProps("opentsdb.properties").getProperty("query.api"),
				JsonUtil.parseString(tsdbQueryRequest), "json");
		return JsonUtil.parseList(handleResult, TSDBQueryResponse.class);
	}

}
