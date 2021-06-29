package ua.training.controller.command;

import static ua.training.constants.Constants.APP_NAME;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.training.model.entity.Role;
import ua.training.model.entity.Test;
import ua.training.model.entity.TestResult;
import ua.training.model.entity.User;
import ua.training.model.service.TestResultService;
import ua.training.model.service.TestService;
import ua.training.model.service.impl.TestResultServiceImpl;
import ua.training.model.service.impl.TestServiceImpl;

public class UpdateTestCommand implements Command{
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
		Test selectedTest = (Test) request.getSession().getAttribute("selectedTest");

        String subject = request.getParameter("testSubject");
        String englishName = request.getParameter("englishName");
        String russianName = request.getParameter("russianName");
        String duration = request.getParameter("duration");
        String difficulty = request.getParameter("difficulty");
        TestService testService = new TestServiceImpl();
        
        if(!testService.updateTest(selectedTest.getId(),subject, englishName, russianName, duration, difficulty)){
        	Locale locale;
        	if(request.getSession().getAttribute("language")==null||request.getSession().getAttribute("language")=="en") {
        		locale = new Locale("en");       		
        	}
        	else {
        		locale = new Locale("ru");  
        	}
        	ResourceBundle message = ResourceBundle.getBundle("resources", locale);
    		request.setAttribute("errorMessage", message.getString("error.test.not.created"));
    		String page = request.getHeader("Referer");
            return page.substring(page.indexOf(APP_NAME));
        }   
        return new TestListingCommand().execute(request, response);
    }
}