package zx.soft.sdn.api.model;

/**
 * 类型统计模型
 * 
 * @author xuran
 *
 */
public class TypeCount {

	/**类型名称**/
	private String name;
	/**统计结果**/
	private Integer value;

	public TypeCount() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

}
