package zx.soft.sdn.api.domain;

/**
 * OpenTSDB PUT接口请求数据模型
 * 
 * @author xuran
 *
 */
public class TSDBPutRequest {

	/**指标名称**/
	private String metric;
	/**时间戳**/
	private Long timestamp;
	/**指标值**/
	private String value;
	/**标签**/
	private TSDBTags tags;

	public TSDBPutRequest() {
		super();
	}

	public TSDBPutRequest(String metric, Long timestamp, String value, TSDBTags tags) {
		super();
		this.metric = metric;
		this.timestamp = timestamp;
		this.value = value;
		this.tags = tags;
	}

	public String getMetric() {
		return metric;
	}

	public void setMetric(String metric) {
		this.metric = metric;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public TSDBTags getTags() {
		return tags;
	}

	public void setTags(TSDBTags tags) {
		this.tags = tags;
	}

}
