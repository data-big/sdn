package zx.soft.sdn.api.model;

/**
 * 日期统计模型
 * 
 * @author xuran
 *
 */
public class DateCount {
	/**日期 yyyy-MM-dd**/
	private String Date;
	/**总量**/
	private Integer total;
	/**增量**/
	private Integer increment;
	/**转换JSON总量增量封装**/
	private Count count = new Count();

	public DateCount() {
		super();
	}

	class Count {

		private Integer total;
		private Integer increment;

		public Integer getTotal() {
			return total;
		}

		public void setTotal(Integer total) {
			this.total = total;
		}

		public Integer getIncrement() {
			return increment;
		}

		public void setIncrement(Integer increment) {
			this.increment = increment;
		}

	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public void setTotal(Integer total) {
		this.total = total;
		this.count.setTotal(total);
	}

	public void setIncrement(Integer increment) {
		this.increment = increment;
		this.count.setIncrement(increment);
	}

	public Count getCount() {
		return count;
	}

	public void setCount(Count count) {
		this.count = count;
	}

}
