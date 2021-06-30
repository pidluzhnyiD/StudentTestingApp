package ua.training.controller.command;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.training.model.entity.TestResult;
import ua.training.model.entity.User;
import ua.training.model.service.ServiceFactory;
import ua.training.model.service.TestResultService;

import static ua.training.constants.Constants.*;

public class ResultsInfoCommand  implements Command{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		TestResultService testResultService = ServiceFactory.getInstance().createTestResultService();

		User user = (User) request.getSession().getAttribute(USER);

		List<TestResult> results = testResultService.getTestResultsByUserId(user.getId());		

        request.getSession().setAttribute(RESULTS, results);
        return REDIRECT+STUDENT_INFO_PATH;
	}
}

