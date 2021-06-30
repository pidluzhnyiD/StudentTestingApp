package ua.training.controller.command;

import ua.training.model.entity.Role;
import ua.training.model.entity.User;
import ua.training.model.service.ServiceFactory;
import ua.training.model.service.UserService;

import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import static ua.training.constants.Constants.*;

public class LoginCommand implements Command{
	
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        
        UserService userService = ServiceFactory.getInstance().createUserService();
        Optional<User>user = userService.getUserByLogin(userName);
        
        if(!user.isPresent()||!userService.checkIfInputDataIsAccurate(userName, password)) {     	
        	String message = CommandUtility.getErrorMessage(request.getSession().getAttribute(LANGUAGE).toString(), "error.unknown.login");
    		request.setAttribute(ERROR_MESSAGE, message);
        	return "/index.jsp";
        }

        if(CommandUtility.checkIfUserIsLogged(request, userName)){
            return "error.jsp";
        }
        
        String role = user.get().getRole().toString().toLowerCase();
        
        HttpSession session = request.getSession(true);
        session.setAttribute(USER, user.get());
        
        if (role.equals(ADMIN)){
            CommandUtility.setUserRole(request, Role.ADMIN, userName);
            return new TestListingCommand().execute(request, response);
        } else if(role.equals(STUDENT)) {
            CommandUtility.setUserRole(request, Role.STUDENT, userName);
            return new TestListingCommand().execute(request, response);
        } else {
            CommandUtility.setUserRole(request, Role.GUEST, userName);
            return REDIRECT+"index.jsp";
        }
    }	
	
}
