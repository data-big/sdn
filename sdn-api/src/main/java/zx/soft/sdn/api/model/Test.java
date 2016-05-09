package zx.soft.sdn.api.model;

import javax.xml.bind.annotation.XmlRootElement;

//支持XML请求，必须结合@RequestBody
@XmlRootElement
public class Test {

	public Test() {
		super();
	}

	public Test(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	private int id;

	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
