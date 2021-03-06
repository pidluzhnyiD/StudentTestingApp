package ua.training.model.service.impl;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.TestDao;
import ua.training.model.entity.Subject;
import ua.training.model.entity.Test;
import ua.training.model.entity.TestDifficulty;
import ua.training.model.service.TestService;

import static ua.training.constants.SqlConstants.*;
import static ua.training.constants.Constants.*;

public class TestServiceImpl implements TestService{
	private static final Logger logger = LogManager.getLogger(TestServiceImpl.class);
	DaoFactory daoFactory = DaoFactory.getInstance();
	
	@Override
	public List<Test> getAllTests(){
		try(TestDao dao = daoFactory.createTestDao()){
			return dao.findAll();
		}	
	}
	
	@Override
	public Optional<Test> getTestById(int id){
		try(TestDao dao = daoFactory.createTestDao()){
			return dao.findById(id);
		}	
	}
	
	@Override
	public List<Test> getAllTestsBySubject(Subject subject){
		try(TestDao dao = daoFactory.createTestDao()){
			return dao.findAllTestsBySubject(subject);
		}
	}
	
	private Test newTest(int id, String subject,String englishName, String russianName, String durationString, String difficulty) {
		int subjectId = Integer.parseInt(subject);
		int durationTime = Integer.parseInt(durationString);
		int difficultyIdEnglish = Integer.parseInt(difficulty);
		int difficultyIdRussian = difficultyIdEnglish+DIFFICULTIES_COUNT;
		TestDifficulty englishDifficulty = TestDifficulty.values()[difficultyIdEnglish];
		TestDifficulty russianDifficulty = TestDifficulty.values()[difficultyIdRussian];
		
		return new Test(id, subjectId, englishName, russianName, durationTime, 
				englishDifficulty, russianDifficulty, 0, Collections.emptyList());
	}
	
	@Override
	public Boolean createTest(String subject,String englishName, String russianName, String durationString, String difficulty){		
		try(TestDao dao = daoFactory.createTestDao()){
			return dao.create(newTest(0, subject, englishName, russianName, durationString, difficulty));
		}
	}
	
	@Override
	public Boolean updateTest(int id, String subject,String englishName, String russianName, String durationString, String difficulty){		
		try (TestDao dao = daoFactory.createTestDao()){
			return dao.update(newTest(id, subject, englishName, russianName, durationString, difficulty));
		} catch (SQLException e) {
			logger.log(Level.ERROR, e);
			return false;
		}
	}
	
	@Override
	public void increaseRequestsNumber(Test test) {
		test.setNumberOfRequests(test.getNumberOfRequests()+1);
		try (TestDao dao = daoFactory.createTestDao()) {
			dao.update(test);
		} catch (SQLException e) {
			logger.log(Level.ERROR, e);
		}
	}
	
	@Override
	public Boolean deleteTest(String testId){
		try(TestDao dao = daoFactory.createTestDao()){
			return dao.delete(Integer.parseInt(testId));	
		}
	}
	
	@Override
	public List<Test> findTestsByPageAndSubjectId(int subjectId, int offset, int numberOfTests) {
		try(TestDao dao = daoFactory.createTestDao()){
			return dao.fintNTestsBySubjectId(subjectId, offset, numberOfTests);    	
		}
	}
	
	@Override
	public List<Test> findTestsByPageSorted(int offset, int numberOfTests, Boolean descSort, String sortMethod) {
		String query = getQuery(sortMethod);
		if(descSort) {
			query+=" DESC";
		}
		query+=" Limit " + offset +", "+ numberOfTests;
		try(TestDao dao = daoFactory.createTestDao()){
			return dao.getNTestsSorted(query);   
		}	   
	}
	
	private String getQuery(String sortMethod) {
		switch(sortMethod) {
			case NAME_EN:
				return SELECT_N_TESTS_BY_NAME_EN;
			case NAME_RU:
				return SELECT_N_TESTS_BY_NAME_RU;
			case DIFFICULTY_EN:
				return SELECT_N_TESTS_BY_DIFFICULTY_EN;
			case DIFFICULTY_RU:
				return SELECT_N_TESTS_BY_DIFFICULTY_RU;
			case NUMBER_OF_REQUESTS:
				return SELECT_N_TESTS_BY_REQUESTS;
			default:
				return  SELECT_ALL_TESTS_BY_SUBJECT;
		}
	}
	
	@Override
	public int getTestsCount() {
		try(TestDao dao = daoFactory.createTestDao()){
			return dao.countNumberOfTests(); 
		}	
	}

	@Override
	public int getTestsCountBySubjectId(int subjectId) {
		try(TestDao dao = daoFactory.createTestDao()){
			return dao.countTestsBySubjectId(subjectId);    
		}	  
	}
}
