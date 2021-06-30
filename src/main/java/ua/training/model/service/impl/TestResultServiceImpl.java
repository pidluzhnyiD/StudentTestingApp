package ua.training.model.service.impl;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.sql.Date;
import java.util.List;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.TestResultDao;
import ua.training.model.entity.Question;
import ua.training.model.entity.Test;
import ua.training.model.entity.TestResult;
import ua.training.model.entity.User;
import ua.training.model.service.TestResultService;

public class TestResultServiceImpl implements TestResultService{
	private final int percents = 100;
	DaoFactory daoFactory = DaoFactory.getInstance();
	
	public List<TestResult> getTestResultsByUserId(int id){
		try(TestResultDao dao = daoFactory.createTestResultDao()){
			 return dao.findByUserId(id);
		}	
	}
	
	public double calculateResultPercentage(Test test, List<String[]>userAnswers){
		int score = 0;
		List<Question>questions = test.getQuestions();
		for(int i=0;i<questions.size();i++) {
			List<Boolean>correctAnswers = questions.get(i).getCorrectAnswers();
			int correctAnswersCount=0;
			int userAnswersCount=0;
			for(int j=0; j<correctAnswers.size();j++) {		
				if(userAnswers.get(i)!=null  && Arrays.asList(userAnswers.get(i)).contains(String.valueOf(j))){
					userAnswersCount++;
					if(correctAnswers.get(j)) {		
						correctAnswersCount++;						
					}
				}
				
			}
			if(correctAnswersCount==userAnswersCount) {					
				score++;
			}
		}
		
		if(test.getQuestionsCount()>0) {
			return (double)score*percents/test.getQuestionsCount();
		}
		return 0;
		
	}
	
	public void createTestResult(Test test, User user, long startTime, double result){
		java.sql.Time sqlTime = new java.sql.Time(System.currentTimeMillis()-startTime);
		int offset = sqlTime.getTimezoneOffset();
		LocalTime localTime = sqlTime.toLocalTime();
		localTime = localTime.plusMinutes(offset);
		
		TestResult newResult = new TestResult(0, user.getId(),test.getId(),test.getEnglishName(),
				test.getRussianName(),test.getEnglishDifficulty(),test.getRussianDifficulty(), 
				localTime, LocalDateTime.now(), result);
		
		try(TestResultDao dao = daoFactory.createTestResultDao()){
			dao.create(newResult);
		}	
	}
	
}
