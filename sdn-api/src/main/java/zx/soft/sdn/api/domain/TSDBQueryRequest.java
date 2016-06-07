package zx.soft.sdn.api.domain;

/**
 * OpenTSDB Query接口请求数据模型
 * 
 * @author xuran
 *
 */
public class TSDBQueryRequest {

	/**时间戳 起始时间**/
	private Long start;
	/**时间戳 结束时间**/
	private Long end;
	/**查询参数**/
	private TSDBQueries[] queries;

	public TSDBQueryRequest() {
		super();
	}

	public TSDBQueryRequest(Long start, Long end, TSDBQueries[] queries) {
		super();
		this.start = start;
		this.end = end;
		this.queries = queries;
	}

	public Long getStart() {
		return start;
	}

	public void setStart(Long start) {
		this.start = start;
	}

	public Long getEnd() {
		return end;
	}

	public void setEnd(Long end) {
		this.end = end;
	}

	public TSDBQueries[] getQueries() {
		return queries;
	}

	public void setQueries(TSDBQueries[] queries) {
		this.queries = queries;
	}

}
