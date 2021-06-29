<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <fmt:setLocale value = "${language}"/>
 <fmt:setBundle basename = "resources" var = "lang"/>
<!DOCTYPE html>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Error</title>
        <jsp:include page="header.jsp"/>
    </head>
    <body>
    <header>    
    	<button type="submit" name="logo" class="logo" value="logo">Student Testing System</button>
    	<div class="header-right">
				<form action="/StudentTestingApp/Servlet"  method="POST">
				    <select  name="language" id ="language" onchange="this.form.submit()">				    	 	
				    	<c:choose>
							<c:when test="${language == 'en'||language==null}">
								<option value="en" selected>EN</option>
								<option value="ru">RU</option>
							</c:when>
							<c:when test="${language == 'ru'}">
								<option value="en">EN</option>
								<option value="ru" selected>RU</option>
							</c:when>
						</c:choose>		        
				    </select>
				    <input type="hidden" name="command" value="changeLanguage"/>
				</form>
		 </div>
    	<form action="/StudentTestingApp/Servlet" method="post">
	  		<div class="header-right">

		    	<button type="submit" name="register" value="register">
		    		<fmt:message key = "register" bundle ="${lang}"/>
		    	</button>
		    	<input type="hidden" name="command" value="register"/>
		    </div>
		</form>	 		
  		 		 
    </header>
    <section>
    	<div class="message-box-centered">
    		<h3><fmt:message key = "already.logged" bundle ="${lang}"/></h3>
    	</div>
    </section>
    </body>
</html>
