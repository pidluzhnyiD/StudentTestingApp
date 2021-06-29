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

public class RegisterCommand  implements Command{
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("login");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        
        UserService userService = ServiceFactory.getInstance().createUserService();
        Optional<User>user = userService.getUserByLogin(userName);
        
        if(user.isPresent()) {
        	Locale locale;
        	if(request.getSession().getAttribute("language")==null||request.getSession().getAttribute("language")=="en") {
        		locale = new Locale("en");       		
        	}
        	else {
        		locale = new Locale("ru");  
        	}
        	ResourceBundle message = ResourceBundle.getBundle("resources", locale);
    		request.setAttribute("errorMessage", message.getString("username.taken"));
        	return "/register.jsp";
        }
        
        User newUser = new User(0, firstName, userName, password, Role.STUDENT);
        userService.addNewUser(newUser);
        
        HttpSession session = request.getSession(true);
        session.setAttribute("User", newUser);
        
        CommandUtility.setUserRole(request, Role.STUDENT, userName);
        return new TestListingCommand().execute(request, response);     
    }	
}
