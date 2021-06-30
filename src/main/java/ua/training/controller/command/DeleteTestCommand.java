package ua.training.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.training.model.service.ServiceFactory;
import ua.training.model.service.TestService;

import static ua.training.constants.Constants.*;

public class DeleteTestCommand implements Command{
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String testId = request.getParameter("testId");
        TestService testService = ServiceFactory.getInstance().createTestService();
        testService.deleteTest(testId);     
        return new TestListingCommand().execute(request, response);
    }
}