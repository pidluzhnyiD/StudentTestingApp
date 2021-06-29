package ua.training.controller.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;

import static ua.training.constants.Constants.*;

public class EncodingFilter implements Filter{
	 @Override
	 public void init(FilterConfig filterConfig) throws ServletException {
		 
	 }
	 
	 @Override
	 public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		 servletResponse.setContentType(TEXT_HTML);
	     servletResponse.setCharacterEncoding(ENCODING);
	     servletRequest.setCharacterEncoding(ENCODING);
	     filterChain.doFilter(servletRequest,servletResponse);
	 }  
	 
	 @Override
	 public void destroy() {
	 
	 }    

	  
}
