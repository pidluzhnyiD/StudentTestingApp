package ua.training.controller.command;

import java.util.List;
import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.training.model.entity.Test;
import ua.training.model.entity.TestResult;
import ua.training.model.entity.User;
import ua.training.model.service.QuestionService;
import ua.training.model.service.TestResultService;
import ua.training.model.service.TestService;
import ua.training.model.service.impl.QuestionServiceImpl;
import ua.training.model.service.impl.TestResultServiceImpl;
import ua.training.model.service.impl.TestServiceImpl;

public class ResultsInfoCommand  implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		TestResultService testResultService = new TestResultServiceImpl();

		User user = (User) request.getSession().getAttribute("User");

		List<TestResult> results = testResultService.getTestResultsByUserId(user.getId());		

        request.getSession().setAttribute("results", results);
        return "redirect:account/student/info.jsp";
	}
}

