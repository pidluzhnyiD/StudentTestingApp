package ua.training.controller.command;

import static ua.training.constants.Constants.APP_NAME;
import static ua.training.constants.Constants.DEFAULT_NUMBER_OF_OPTIONS;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.training.model.entity.Test;
import ua.training.model.service.QuestionService;
import ua.training.model.service.TestService;
import ua.training.model.service.impl.QuestionServiceImpl;
import ua.training.model.service.impl.TestServiceImpl;

public class AddQuestionCommand implements Command{
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
		Test test = (Test) request.getSession().getAttribute("selectedTest");		
        String englishDescription = request.getParameter("englishDescription");
        
        List<Boolean>answers = new ArrayList<Boolean>();
		for(int i=1;i<=DEFAULT_NUMBER_OF_OPTIONS;i++) {
			if(request.getParameter("answer"+i) != null) {
				answers.add(true);
			}
			else {
				answers.add(false);
			}
		}
		
		List<String>options = new ArrayList<String>();
		for(int i=1;i<=DEFAULT_NUMBER_OF_OPTIONS;i++) {		
			if(request.getParameter("englishOption"+i) != null) {
				options.add(request.getParameter("englishOption"+i));
			}
			else {
				options.add(null);
			}
			
		}
        
        QuestionService questionService = new QuestionServiceImpl();
        try {
			questionService.createQuestion(test.getId(), englishDescription, options, answers);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return new TestListingCommand().execute(request, response);
    }
}