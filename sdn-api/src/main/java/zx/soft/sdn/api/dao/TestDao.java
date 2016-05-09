package zx.soft.sdn.api.dao;

import org.apache.ibatis.annotations.Select;

import zx.soft.sdn.api.model.Test;

public interface TestDao {

	@Select(value = "SELECT name FROM test WHERE id=#{id}")
	public String getNameById(int id);

	public String getIdByName(String name);

	@Select(value = "SELECT * FROM test WHERE id=#{id}")
	public Test getTestById(int id);

}
