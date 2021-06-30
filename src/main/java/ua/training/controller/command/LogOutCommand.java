package ua.training.controller.command;

import ua.training.model.entity.User;

import java.util.HashSet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static ua.training.constants.Constants.*;

public class LogOutCommand implements Command{
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {       
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession()
        		.getServletContext().getAttribute(LOGGED_USERS);

        User currentUser = (User) request.getSession().getAttribute(USER);
        
        loggedUsers.remove(currentUser.getLogin());
        request.getSession().getServletContext()
                .setAttribute(LOGGED_USERS, loggedUsers);
        
        request.getSession().invalidate();
        
        return REDIRECT+APP_NAME+"/index.jsp";
    }
}
