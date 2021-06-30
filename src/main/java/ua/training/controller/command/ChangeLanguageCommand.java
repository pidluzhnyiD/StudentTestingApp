package ua.training.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static ua.training.constants.Constants.*;

public class ChangeLanguageCommand implements Command{
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String language = request.getParameter(LANGUAGE);
        request.getSession().setAttribute(LANGUAGE, language);
        String page = request.getHeader(REFERER);
        page = page.substring(page.indexOf(APP_NAME));
        return REDIRECT+page;
    }
}
