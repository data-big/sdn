package zx.soft.sdn.api.domain;

/**
 * OpenTSDB 查询参数数据模型
 * 
 * @author xuran
 *
 */
public class TSDBQueries {

	/**聚合条件：min,sum,max,avg**/
	private String aggregator;
	/**指标名称**/
	private String metric;
	/**标签**/
	private TSDBTags tags;

	public TSDBQueries() {
		super();
	}

	public TSDBQueries(String aggregator, String metric, TSDBTags tags) {
		super();
		this.aggregator = aggregator;
		this.metric = metric;
		this.tags = tags;
	}

	public String getAggregator() {
		return aggregator;
	}

	public void setAggregator(String aggregator) {
		this.aggregator = aggregator;
	}

	public String getMetric() {
		return metric;
	}

	public void setMetric(String metric) {
		this.metric = metric;
	}

	public TSDBTags getTags() {
		return tags;
	}

	public void setTags(TSDBTags tags) {
		this.tags = tags;
	}

}
