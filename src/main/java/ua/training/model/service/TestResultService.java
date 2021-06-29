package ua.training.model.service;

import java.util.List;

import ua.training.model.entity.Test;
import ua.training.model.entity.TestResult;
import ua.training.model.entity.User;

public interface TestResultService {	 
	 public List<TestResult> getTestResultsByUserId(int id);
	 
	 public void createTestResult(Test test, User user, long millis, double result);
	 
	 public double calculateResultPercentage(Test test, List<String[]>userAnswers);
}
