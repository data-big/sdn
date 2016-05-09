package zx.soft.sdn.api.service;

import zx.soft.sdn.api.model.Test;

public interface TestService {

	public String getNameById(int id);

	public String getIdByName(String name);

	public Test getTestById(int id);

}
