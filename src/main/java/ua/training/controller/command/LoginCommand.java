package ua.training.controller.command;

import ua.training.model.entity.Role;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;
import ua.training.model.service.impl.UserServiceImpl;

import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginCommand implements Command{
	
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("login");
        String password = request.getParameter("password");
        
        UserService userService = new UserServiceImpl(); 
        Optional<User>user = userService.getUserByLogin(userName);
        
        if(!user.isPresent()||!userService.checkIfInputDataIsAccurate(userName, password)) {
        	Locale locale;
        	if(request.getSession().getAttribute("language")==null||request.getSession().getAttribute("language")=="en") {
        		locale = new Locale("en");       		
        	}
        	else {
        		locale = new Locale("ru");  
        	}
        	ResourceBundle message = ResourceBundle.getBundle("resources", locale);
    		request.setAttribute("errorMessage", message.getString("error.unknown.login"));
        	return "/index.jsp";
        }

        if(CommandUtility.checkIfUserIsLogged(request, userName)){
            return "error.jsp";
        }
        
        String role = user.get().getRole().toString().toLowerCase();
        
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(30*60);
        session.setAttribute("User", user.get());
        session.setAttribute("userLoggedIn", true);
        
        if (role.equals("admin")){
            CommandUtility.setUserRole(request, Role.ADMIN, userName);
            return new TestListingCommand().execute(request, response);
        } else if(role.equals("student")) {
            CommandUtility.setUserRole(request, Role.STUDENT, userName);
            return new TestListingCommand().execute(request, response);
        } else {
            CommandUtility.setUserRole(request, Role.GUEST, userName);
            return "redirect:index.jsp";
        }
    }	
}
