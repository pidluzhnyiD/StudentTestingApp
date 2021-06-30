package ua.training.controller.command;

import java.util.Locale;
import java.util.ResourceBundle;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.training.model.service.ServiceFactory;
import ua.training.model.service.TestService;

import static ua.training.constants.Constants.*;

public class CreateTestCommand implements Command{
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String subject = request.getParameter("testSubject");
        String englishName = request.getParameter("englishName");
        String russianName = request.getParameter("russianName");
        String duration = request.getParameter("duration");
        String difficulty = request.getParameter("difficulty");
        
        TestService testService = ServiceFactory.getInstance().createTestService();
        
        if(!testService.createTest(subject, englishName, russianName, duration, difficulty)){
        	Locale locale;
        	if(request.getSession().getAttribute(LANGUAGE)==null||
        			request.getSession().getAttribute(LANGUAGE)==ENGLISH_LANGUAGE) {
        		locale = new Locale(DEFAULT_LANGUAGE);       		
        	}
        	else {
        		locale = new Locale(RUSSIAN_LANGUAGE);  
        	}
        	ResourceBundle message = ResourceBundle.getBundle(RESOURCES, locale);
    		request.setAttribute("errorMessage", message.getString("error.test.not.created"));
    		String page = request.getHeader(REFERER);
            return page.substring(page.indexOf(APP_NAME));
        }   
        return new TestListingCommand().execute(request, response);
    }
}