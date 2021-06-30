package ua.training.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.training.model.entity.Test;
import ua.training.model.service.ServiceFactory;
import ua.training.model.service.TestService;

import static ua.training.constants.Constants.*;

public class FillTestDataCommand implements Command{
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int testId = Integer.parseInt(request.getParameter("testId"));
        TestService testService = ServiceFactory.getInstance().createTestService();
        Test test = (Test)testService.getTestById(testId).get();
        request.getSession().setAttribute(SELECTED_TEST, test);    
        return REDIRECT+UPDATE_TEST_PATH;
    }
}