package ua.training.controller.command;

import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;
import ua.training.model.service.ServiceFactory;
import ua.training.model.service.UserService;

import static ua.training.constants.Constants.*;

public class RegisterCommand  implements Command{
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String firstName = request.getParameter(FIRSTNAME);
        
        UserService userService = ServiceFactory.getInstance().createUserService();
        Optional<User>user = userService.getUserByLogin(userName);
        
        if(user.isPresent()) {
        	String message = CommandUtility.getErrorMessage
        			(request.getSession().getAttribute(LANGUAGE).toString(), "username.taken");
    		request.setAttribute(ERROR_MESSAGE, message);
        	return "/register.jsp";
        }
        
        User newUser = new User(0, firstName, userName, password, Role.STUDENT);
        userService.addNewUser(newUser);
        
        HttpSession session = request.getSession(true);
        session.setAttribute(USER, newUser);
        
        CommandUtility.setUserRole(request, Role.STUDENT, userName);
        return new TestListingCommand().execute(request, response);     
    }	
}
