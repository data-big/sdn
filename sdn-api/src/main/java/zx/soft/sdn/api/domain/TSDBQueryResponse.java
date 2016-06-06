package zx.soft.sdn.api.domain;

import java.util.Map;

/**
 * OpenTSDB Query接口响应数据模型
 * 
 * @author xuran
 *
 */
public class TSDBQueryResponse {

	/**指标名称**/
	private String metric;
	/**聚合字段**/
	private String[] aggregateTags;
	/**标签**/
	private TSDBTags tags;
	/**dps**/
	private Map<Long, Integer> dps;

	public TSDBQueryResponse() {
		super();
	}

	public TSDBQueryResponse(String metric, String[] aggregateTags, TSDBTags tags, Map<Long, Integer> dps) {
		super();
		this.metric = metric;
		this.aggregateTags = aggregateTags;
		this.tags = tags;
		this.dps = dps;
	}

	public String getMetric() {
		return metric;
	}

	public void setMetric(String metric) {
		this.metric = metric;
	}

	public String[] getAggregateTags() {
		return aggregateTags;
	}

	public void setAggregateTags(String[] aggregateTags) {
		this.aggregateTags = aggregateTags;
	}

	public TSDBTags getTags() {
		return tags;
	}

	public void setTags(TSDBTags tags) {
		this.tags = tags;
	}

	public Map<Long, Integer> getDps() {
		return dps;
	}

	public void setDps(Map<Long, Integer> dps) {
		this.dps = dps;
	}
}
