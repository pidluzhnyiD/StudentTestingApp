package ua.training.controller.command;

import ua.training.model.entity.Role;
import ua.training.model.entity.User;

import java.util.HashSet;
import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static ua.training.constants.Constants.APP_NAME;

public class LogOutCommand implements Command{
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        CommandUtility.setUserRole(request, Role.GUEST, "Guest");
       
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession()
        		.getServletContext().getAttribute("loggedUsers");

        User currentUser = (User) request.getSession().getAttribute("User");
        
        loggedUsers.remove(currentUser.getLogin());
        request.getSession().getServletContext()
                .setAttribute("loggedUsers", loggedUsers);
        
        request.getSession().invalidate();
        
        return "redirect:"+APP_NAME+"/index.jsp";
    }
}
