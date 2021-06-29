
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
 <fmt:setLocale value = "${language}"/>
 <fmt:setBundle basename = "resources" var = "lang"/>
<!DOCTYPE html>

<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
		<a class="header-right" href="register.jsp"><fmt:message key = "register" bundle ="${lang}"/></a>		
    </header>
    <section>
    	<div class="sign-in-box">
    		 <div class="sign-in-form">	 
    		 <h1><fmt:message key = "welcome" bundle ="${lang}"/></h1>
    		 	<h3><fmt:message key = "sign.in" bundle ="${lang}"/></h3>
    		 	
		         <form action="Servlet" method="POST">
		         
		         	 <fmt:message key="login" var="loginPlaceholder" bundle ="${lang}" />
		             <input type="text" class="input-field" placeholder="${loginPlaceholder}" name="login" required > <br>
		             
		             <fmt:message key="password" var="passwordPlaceholder" bundle ="${lang}"/>
		             <input type="password" class="input-field" placeholder="${passwordPlaceholder}" name="password" required> <br>

					 <span class="error">${errorMessage}</span> <br>
							             
		             <fmt:message key="submit" var="submitText" bundle ="${lang}"/>
		             <input type="submit" class="input-submit" value="${submitText}"/>
		             
		             <input type="hidden" name="command" value="login"/>
		         </form>
		         
		         <br/>
    		 </div>
    	</div>
    </section>
    </body>
    
</html>
