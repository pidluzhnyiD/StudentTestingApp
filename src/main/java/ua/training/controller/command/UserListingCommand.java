package ua.training.controller.command;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;
import ua.training.model.service.impl.UserServiceImpl;

public class UserListingCommand implements Command{
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
		UserService userService = new UserServiceImpl();
		List<User> students = userService.getAllStudents();
		
        request.getSession().setAttribute("students", students);

        return "redirect:account/admin/userslist.jsp";
    }	
}