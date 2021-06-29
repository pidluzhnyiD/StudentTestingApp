package ua.training.model.dao;

import java.util.List;

import ua.training.model.entity.TestResult;

public interface TestResultDao extends GenericDao<TestResult>{

	List<TestResult> findByUserId(int id);

}
