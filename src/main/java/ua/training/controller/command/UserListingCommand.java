package ua.training.controller.command;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.training.model.entity.User;
import ua.training.model.service.ServiceFactory;
import ua.training.model.service.UserService;

import static ua.training.constants.Constants.*;

public class UserListingCommand implements Command{
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
		UserService userService = ServiceFactory.getInstance().createUserService();
		List<User> students = userService.getAllStudents();
        request.getSession().setAttribute(STUDENTS, students);
        return REDIRECT+USER_LIST_PATH;
    }	
}
