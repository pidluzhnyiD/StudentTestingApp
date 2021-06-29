package ua.training.controller.command;

import ua.training.model.entity.Role;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.HashSet;

class CommandUtility {
	static void setUserRole(HttpServletRequest request, Role role, String name) {
		HttpSession session = request.getSession();
		ServletContext context = request.getServletContext();
		context.setAttribute("userName", name);
		session.setAttribute("role", role);
	}
	
	static boolean checkIfUserIsLogged(HttpServletRequest request, String userName){		
		HashSet<String> loggedUsers = (HashSet<String>) request.getServletContext().getAttribute("loggedUsers");

        if(loggedUsers.stream().anyMatch(userName::equals)){
        	return true;
        }
        loggedUsers.add(userName);
        request.getServletContext()
                .setAttribute("loggedUsers", loggedUsers);
        return false;
    }
}
