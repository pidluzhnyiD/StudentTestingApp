package ua.training.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.training.model.entity.Test;
import ua.training.model.service.ServiceFactory;
import ua.training.model.service.TestService;

import static ua.training.constants.Constants.*;

public class UpdateTestCommand implements Command{
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
		Test selectedTest = (Test) request.getSession().getAttribute(SELECTED_TEST);

        String subject = request.getParameter("testSubject");
        String englishName = request.getParameter("englishName");
        String russianName = request.getParameter("russianName");
        String duration = request.getParameter("duration");
        String difficulty = request.getParameter("difficulty");
        TestService testService = ServiceFactory.getInstance().createTestService();
        
        if(!testService.updateTest(selectedTest.getId(),subject, englishName, russianName, duration, difficulty)){
        	String message = CommandUtility.getErrorMessage
        			(request.getSession().getAttribute(LANGUAGE).toString(), "error.test.not.created");
    		request.setAttribute(ERROR_MESSAGE, message);
    		String page = request.getHeader(REFERER);
            return page.substring(page.indexOf(APP_NAME));
        }   
        return new TestListingCommand().execute(request, response);
    }
}