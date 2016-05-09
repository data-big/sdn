package zx.soft.sdn.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zx.soft.sdn.api.dao.TestDao;
import zx.soft.sdn.api.model.Test;
import zx.soft.sdn.api.service.TestService;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestDao testDao;

	@Override
	public String getNameById(int id) {
		return testDao.getNameById(id);
	}

	@Override
	public String getIdByName(String name) {
		return testDao.getIdByName(name);
	}

	@Override
	public Test getTestById(int id) {
		return testDao.getTestById(id);
	}

}
