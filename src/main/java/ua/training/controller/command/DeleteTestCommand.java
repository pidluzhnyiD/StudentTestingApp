package ua.training.controller.command;

import static ua.training.constants.Constants.APP_NAME;

import java.util.Locale;
import java.util.ResourceBundle;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.training.model.service.TestService;
import ua.training.model.service.impl.TestServiceImpl;

public class DeleteTestCommand implements Command{
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String testId = request.getParameter("testId");
        TestService testService = new TestServiceImpl();
        testService.deleteTest(testId);     
        return new TestListingCommand().execute(request, response);
    }
}