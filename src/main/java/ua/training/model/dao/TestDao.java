package ua.training.model.dao;

import java.util.List;

import ua.training.model.entity.Subject;
import ua.training.model.entity.Test;

public interface TestDao extends GenericDao<Test>{

	List<Test> findAllTestsBySubject(Subject subject);

	int countNumberOfTests();

	List<Test> fintNTestsBySubjectId(int subjectId, int offset, int numberOfTests);

	List<Test> getNTestsSorted(String sortMethod);

	int countTestsBySubjectId(int id);
}
