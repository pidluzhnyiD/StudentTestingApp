package ua.training.controller.command;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.training.model.entity.Test;
import ua.training.model.entity.User;
import ua.training.model.service.ServiceFactory;
import ua.training.model.service.TestResultService;

import static ua.training.constants.Constants.*;

public class CalculateResultCommand implements Command{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		List<String[]>answers = new ArrayList<String[]>();
		Test test = (Test) request.getSession().getAttribute(SELECTED_TEST);
		for(int i=1;i<=test.getQuestionsCount();i++) {
			answers.add(request.getParameterValues("question"+i));
		}

		TestResultService testResultService = ServiceFactory.getInstance().createTestResultService();	
		
		double result = Math.round(testResultService.calculateResultPercentage(test, answers)*PERCENTAGE)/ PERCENTAGE;
		
		User user = (User) request.getSession().getAttribute(USER);

		long startTimeMillis = (long) request.getSession().getAttribute("startTime");
		
		testResultService.createTestResult(test, user, startTimeMillis, result);
		
        return new ResultsInfoCommand().execute(request, response);
	}
}
