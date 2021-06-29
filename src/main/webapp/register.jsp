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
        <title>Testing WebSite</title>
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
    	<a class="header-right" href="index.jsp"><fmt:message key = "login" bundle ="${lang}"/></a>			 		 
    </header>
    <section>
    	<div class="register-box">
    		 <div class="register-form">	 
    		 <h1><fmt:message key = "welcome" bundle ="${lang}"/></h1>
    		 	<h3><fmt:message key = "register" bundle ="${lang}"/></h3>
	    		 	<div class="register-inner-form">
				         <form action="Servlet" method="POST">
				         	 <h4 class="register-form-text-align-left"><fmt:message key = "enter.name" bundle ="${lang}"/></h4>
				        	 <fmt:message key="first.name" var="firstNamePlaceholder" bundle ="${lang}"/>
				             <input type="text" class="input-field" placeholder="${firstNamePlaceholder}" name="firstName" required/> <br>
				         
				  	         <h4 class="register-form-text-align-left"><fmt:message key = "enter.username" bundle ="${lang}"/></h4>
				         	 <fmt:message key="login" var="loginPlaceholder" bundle ="${lang}" />
				             <input type="text" class="input-field" placeholder="${loginPlaceholder}" name="login" required/> <br>
				     
				     		 <h4 class="register-form-text-align-left"><fmt:message key = "enter.password" bundle ="${lang}"/></h4>        
				             <fmt:message key="password" var="passwordPlaceholder" bundle ="${lang}"/>
				             <input type="password" class="input-field" placeholder="${passwordPlaceholder}" name="password" required/> <br>
		
							 <span class="error">${errorMessage}</span> <br>
									             
				             <fmt:message key="submit" var="submitText" bundle ="${lang}"/>
				             <input type="submit" class="input-submit" value="${submitText}"/>
				             
				             <input type="hidden" name="command" value="register"/>
				         </form>
			         </div>
		         <br/>
    		 </div>
    	</div>
    </section>
    </body>
</html>
