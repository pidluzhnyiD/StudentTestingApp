package ua.training.controller.command;

import static ua.training.constants.Constants.APP_NAME;

import java.util.Locale;
import java.util.ResourceBundle;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.training.model.service.ServiceFactory;
import ua.training.model.service.TestService;

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