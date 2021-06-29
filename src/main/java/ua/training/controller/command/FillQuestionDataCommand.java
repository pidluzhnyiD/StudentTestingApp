package ua.training.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.training.model.entity.Test;
import ua.training.model.service.TestService;
import ua.training.model.service.impl.TestServiceImpl;

public class FillQuestionDataCommand implements Command{
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int testId = Integer.parseInt(request.getParameter("testId"));
        TestService testService = new TestServiceImpl();
        Test test = (Test)testService.getTestById(testId).get();
        request.getSession().setAttribute("selectedTest", test);    
        return "redirect:account/admin/addquestion.jsp";
    }
}