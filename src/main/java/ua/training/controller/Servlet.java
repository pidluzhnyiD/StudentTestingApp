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

public class Servlet extends HttpServlet {
	private Map<String, Command> commands = new HashMap<>();
	private static final long serialVersionUID = 100L;
	
	 public void init(ServletConfig servletConfig) throws ServletException {
		 servletConfig.getServletContext()
	                .setAttribute("loggedUsers", new HashSet<String>());
		 
		 commands.put("logout", new LogOutCommand());
	     commands.put("login", new LoginCommand());
	     commands.put("register", new RegisterCommand());
	     commands.put("testListing", new TestListingCommand());
	     commands.put("changeLanguage", new ChangeLanguageCommand());
	     commands.put("startTest", new StartTestCommand());
	     commands.put("calculateResult", new CalculateResultCommand());
	     commands.put("resultsInfo", new ResultsInfoCommand());
	     commands.put("userListing", new UserListingCommand());
	     commands.put("createTest", new CreateTestCommand());
	     commands.put("studentAuthorization", new StudentAuthorizationCommand());
	     commands.put("deleteUser", new DeleteUserCommand());
	     commands.put("deleteTest", new DeleteTestCommand());
	     commands.put("fillTestData", new FillTestDataCommand());
	     commands.put("updateTest", new UpdateTestCommand());
	     commands.put("addQuestion", new AddQuestionCommand());
	     commands.put("fillQuestionData", new FillQuestionDataCommand());
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		Command command = commands.get(request.getParameter("command"));
		
        String page = command.execute(request, response).replaceAll("/Servlet" , "");

        if(page.contains("redirect:")){
            response.sendRedirect(page.replace("redirect:", ""));
        }else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }

}
