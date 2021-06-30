package ua.training.controller.command;

import ua.training.model.entity.Role;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Locale;
import java.util.ResourceBundle;

import static ua.training.constants.Constants.*;

class CommandUtility {
	static void setUserRole(HttpServletRequest request, Role role, String name) {
		HttpSession session = request.getSession();
		ServletContext context = request.getServletContext();
		context.setAttribute(USERNAME, name);
		session.setAttribute(ROLE, role);
	}
	
	static boolean checkIfUserIsLogged(HttpServletRequest request, String userName){		
		HashSet<String> loggedUsers = (HashSet<String>) request.getServletContext().getAttribute(LOGGED_USERS);

        if(loggedUsers.stream().anyMatch(userName::equals)){
        	return true;
        }
        loggedUsers.add(userName);
        request.getServletContext()
                .setAttribute(LOGGED_USERS, loggedUsers);
        return false;
    }
	
	static String getErrorMessage(String language, String message) {
		Locale locale;
    	if(language==null||language==ENGLISH_LANGUAGE) {
    		locale = new Locale(DEFAULT_LANGUAGE);       		
    	}
    	else {
    		locale = new Locale(RUSSIAN_LANGUAGE);  
    	}
    	return ResourceBundle.getBundle(RESOURCES, locale).getString(message);
	}
}
