package ua.training.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.training.model.service.ServiceFactory;
import ua.training.model.service.UserService;

import static ua.training.constants.Constants.*;

public class StudentAuthorizationCommand implements Command{
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {       
        UserService userService = ServiceFactory.getInstance().createUserService();
        userService.changeUserRights(request.getParameter(LOGIN));        
        return new UserListingCommand().execute(request, response);
    }
}