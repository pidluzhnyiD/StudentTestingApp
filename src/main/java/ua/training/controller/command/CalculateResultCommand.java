package ua.training.controller.command;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.training.model.entity.Test;
import ua.training.model.entity.TestResult;
import ua.training.model.entity.User;
import ua.training.model.service.QuestionService;
import ua.training.model.service.TestResultService;
import ua.training.model.service.TestService;
import ua.training.model.service.impl.QuestionServiceImpl;
import ua.training.model.service.impl.TestResultServiceImpl;
import ua.training.model.service.impl.TestServiceImpl;

public class CalculateResultCommand implements Command{
	private final double PERCENTAGE = 100.0;
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		List<String[]>answers = new ArrayList<String[]>();
		Test test = (Test) request.getSession().getAttribute("selectedTest");
		for(int i=1;i<=test.getQuestionsCount();i++) {
			answers.add(request.getParameterValues("question"+i));
		}

		TestResultService testResultService = new TestResultServiceImpl();	
		
		double result = Math.round(testResultService.calculateResultPercentage(test, answers)*PERCENTAGE)/ PERCENTAGE;
		
		User user = (User) request.getSession().getAttribute("User");

		long startTimeMillis = (long) request.getSession().getAttribute("startTime");
		
		testResultService.createTestResult(test, user, startTimeMillis, result);
		
        return new ResultsInfoCommand().execute(request, response);
	}
}
