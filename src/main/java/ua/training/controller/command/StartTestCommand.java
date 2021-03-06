package ua.training.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.training.model.entity.Test;
import ua.training.model.service.QuestionService;
import ua.training.model.service.ServiceFactory;
import ua.training.model.service.TestService;

import static ua.training.constants.Constants.*;

public class StartTestCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		TestService testService = ServiceFactory.getInstance().createTestService();
		QuestionService questionService = ServiceFactory.getInstance().createQuestionService();
		
		int id = Integer.parseInt(request.getParameter("testId"));
		String language = request.getSession().getAttribute(LANGUAGE)==null?
				DEFAULT_LANGUAGE:request.getSession().getAttribute(LANGUAGE).toString();
		
		Test test = testService.getTestById(id).get();		
		test.setQuestions(questionService.getQuestionsByTestId(id, language));
				
        request.getSession().setAttribute(SELECTED_TEST, test);
        request.getSession().setAttribute("startTime", System.currentTimeMillis());
    	testService.increaseRequestsNumber(test);
        return REDIRECT+TEST_PATH;
	}
}
