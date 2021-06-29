package ua.training.controller.command;

import static ua.training.constants.Constants.APP_NAME;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import com.mysql.fabric.xmlrpc.base.Data;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.training.model.entity.Question;
import ua.training.model.entity.Subject;
import ua.training.model.entity.Test;
import ua.training.model.service.QuestionService;
import ua.training.model.service.SubjectService;
import ua.training.model.service.TestService;
import ua.training.model.service.impl.QuestionServiceImpl;
import ua.training.model.service.impl.SubjectServiceImpl;
import ua.training.model.service.impl.TestServiceImpl;

public class StartTestCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		TestService testService = new TestServiceImpl();
		QuestionService questionService = new QuestionServiceImpl();
		
		int id = Integer.parseInt(request.getParameter("testId"));
		String language = request.getSession().getAttribute("language")==null?
				"en":request.getSession().getAttribute("language").toString();
		
		Test test = testService.getTestById(id).get();		
		test.setQuestions(questionService.getQuestionsByTestId(id, language));
				
        request.getSession().setAttribute("selectedTest", test);
        request.getSession().setAttribute("startTime", System.currentTimeMillis());
    	testService.increaseRequestsNumber(test);
        return "redirect:account/student/test.jsp";
	}
}
