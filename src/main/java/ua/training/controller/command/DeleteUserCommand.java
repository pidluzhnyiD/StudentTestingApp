package ua.training.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.training.model.service.UserService;
import ua.training.model.service.impl.UserServiceImpl;

public class DeleteUserCommand implements Command{
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {       
        UserService userService = new UserServiceImpl();
        userService.deleteUser(request.getParameter("login"));        

        return new UserListingCommand().execute(request, response);
    }
}