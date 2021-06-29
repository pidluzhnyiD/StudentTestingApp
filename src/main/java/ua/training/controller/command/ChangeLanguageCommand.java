package ua.training.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static ua.training.constants.Constants.APP_NAME;

public class ChangeLanguageCommand implements Command{
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String language = request.getParameter("language");
        request.getSession().setAttribute("language", language);
        String page = request.getHeader("Referer");
        page = page.substring(page.indexOf(APP_NAME));
        return "redirect:"+page;
    }
}
