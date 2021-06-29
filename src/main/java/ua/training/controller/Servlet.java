package ua.training.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import ua.training.controller.command.*;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static ua.training.constants.Constants.*;

public class Servlet extends HttpServlet {
	private Map<String, Command> commands = new HashMap<>();
	private static final long serialVersionUID = 100L;
	
	 public void init(ServletConfig servletConfig) throws ServletException {
		 servletConfig.getServletContext()
	                .setAttribute(LOGGED_USERS, new HashSet<String>());
		 
		 commands.put(LOGOUT, new LogOutCommand());
	     commands.put(LOGIN, new LoginCommand());
	     commands.put(REGISTER, new RegisterCommand());
	     commands.put(TEST_LISTING, new TestListingCommand());
	     commands.put(CHANGE_LANGUAGE, new ChangeLanguageCommand());
	     commands.put(START_TEST, new StartTestCommand());
	     commands.put(CALCULATE_RESULT, new CalculateResultCommand());
	     commands.put(RESULTS_INFO, new ResultsInfoCommand());
	     commands.put(USER_LISTING, new UserListingCommand());
	     commands.put(CREATE_TEST, new CreateTestCommand());
	     commands.put(STUDENT_AUTHORIZATION, new StudentAuthorizationCommand());
	     commands.put(DELETE_USER, new DeleteUserCommand());
	     commands.put(DELETE_TEST, new DeleteTestCommand());
	     commands.put(FILL_TEST_DATA, new FillTestDataCommand());
	     commands.put(UPDATE_TEST, new UpdateTestCommand());
	     commands.put(ADD_QUESTION, new AddQuestionCommand());
	     commands.put(FILL_QUESTION_DATA, new FillQuestionDataCommand());
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		Command command = commands.get(request.getParameter(COMMAND));
		
        String page = command.execute(request, response).replaceAll(SERVLET_PATH , "");

        if(page.contains(REDIRECT)){
            response.sendRedirect(page.replace(REDIRECT, ""));
        }else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }

}
