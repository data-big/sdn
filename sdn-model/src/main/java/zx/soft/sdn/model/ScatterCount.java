package zx.soft.sdn.model;

import java.util.List;

/**
 * 分布统计模型
 * 
 * @author xuran
 *
 */
public class ScatterCount {

	/**统计数据**/
	private List<TypeCount> data;
	/**最小值**/
	private Integer min;
	/**最大值**/
	private Integer max;

	public ScatterCount() {
		super();
	}

	public ScatterCount(List<TypeCount> data, Integer min, Integer max) {
		super();
		this.data = data;
		this.min = min;
		this.max = max;
	}

	public List<TypeCount> getData() {
		return data;
	}

	public void setData(List<TypeCount> data) {
		this.data = data;
	}

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

}
