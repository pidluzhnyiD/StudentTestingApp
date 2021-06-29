package ua.training.controller.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.training.model.entity.User;

import static ua.training.constants.Constants.*;

import java.io.IOException;

public class AuthFilter implements Filter  {
	@Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
	
	@Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        User user = (User)req.getSession().getAttribute(USER);
        
        if(hasAccess(user, req.getRequestURI())) {
        	filterChain.doFilter(request, response);
            return;
        }
        res.sendRedirect(request.getServletContext().getContextPath()+ACCESS_RESTRICTED_PATH);
     
        filterChain.doFilter(request, response);
    }
	
	private boolean hasAccess(User user, String page) {
        return user != null && page.contains(user.getRole().toString().toLowerCase());
    }
	
	@Override
    public void destroy() {

    }
}
