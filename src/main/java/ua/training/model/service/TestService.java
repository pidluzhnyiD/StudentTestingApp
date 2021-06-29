package ua.training.model.service;

import java.util.List;
import java.util.Optional;

import ua.training.model.entity.Test;
import ua.training.model.entity.Subject;

public interface TestService {
	public List<Test> getAllTests();
	 
	public Optional<Test> getTestById(int id);
	 
	List<Test> getAllTestsBySubject(Subject subject);

	Boolean createTest(String subject, String englishName, String russianName, String duration, String difficulty);

	Boolean deleteTest(String testId);

	Boolean updateTest(int id, String subject, String englishName, String russianName, String durationString,
			String difficulty);

	void increaseRequestsNumber(Test test);

	int getTestsCount();

	int getTestsCountBySubjectId(int subjectId);
	
	List<Test> findTestsByPageAndSubjectId(int subjectId, int offset, int numberOfTests);

	List<Test> findTestsByPageSorted(int offset, int numberOfTests, Boolean descSort, String sortMethod);
}
