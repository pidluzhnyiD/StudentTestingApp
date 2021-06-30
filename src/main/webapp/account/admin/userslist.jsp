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
        <title>Student page</title>
        <jsp:include page="../../header.jsp"/>
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

		    	<button type="submit" name="logout" value="logout">
		    		<fmt:message key = "logout" bundle ="${lang}"/>
		    	</button>
		    	<input type="hidden" name="command" value="logout"/>
		    </div>
		</form>	 
				<form action="/StudentTestingApp/Servlet" method="post">
	  		<div class="header-right">

		    	<button type="submit" name="testListing" value="testListing">
		    		<fmt:message key = "tests" bundle ="${lang}"/>
		    	</button>
		    	<input type="hidden" name="command" value="testListing"/>
		    </div>
		</form>				
		<form action="/StudentTestingApp/Servlet" method="post">
	  		<div class="header-right">
				<a class="headerButton" href="createtest.jsp"><fmt:message key = "test.create" bundle ="${lang}"/></a>
		    </div>
		</form>		
  		 		 
    </header>
    <section>
      <div style="width:100%;text-align:center">   	
			<table class = "tests">
	          <tr>
	               <th>#</th>
	               <th><fmt:message key = "user.name" bundle ="${lang}"/></th>
	               <th><fmt:message key = "user.username" bundle ="${lang}"/></th>
	               <th><fmt:message key = "user.status" bundle ="${lang}"/></th>
	               <th><fmt:message key = "user.authorization" bundle ="${lang}"/></th>
	               <th><fmt:message key = "user.delete" bundle ="${lang}"/></th>
	          </tr>
	         <c:forEach items="${students}" var="student">
	               	      
	                <tr>         		   	
	               		 <td>${student.id}</td>
	               		 <td>${student.name}</td>
	               		 <td>${student.login}</td>	
	               		 <td>${student.role}</td>		                         	
			               	<td>
			               		<form action="/StudentTestingApp/Servlet" method="post">
			               			<c:choose>
									    <c:when test="${student.role == 'STUDENT'}">
									       <fmt:message key="user.block" var="buttonPlaceholder" bundle ="${lang}" />
									    </c:when>
									    <c:when test="${student.role == 'BLOCKED'}">
									        <fmt:message key="user.unblock" var="buttonPlaceholder" bundle ="${lang}" />
									    </c:when>
									</c:choose>
				               		<input class='tableButtons' type='submit' value="${buttonPlaceholder}"/>
				               		<input type="hidden" name="login" value="${student.login}"/>
				               		<input type="hidden" name="command" value="studentAuthorization"/>
			               		</form>
			               	</td>
			               		<td>
			               		<form action="/StudentTestingApp/Servlet" method="post">
				               		<fmt:message key="test.delete" var="buttonPlaceholder" bundle ="${lang}" />
				               		<input class='tableButtons' type='submit' value="${buttonPlaceholder}"/>
				               		<input type="hidden" name="login" value="${student.login}"/>
				               		<input type="hidden" name="command" value="deleteUser"/>
			               		</form>
			               	</td>
			        </tr>
			</c:forEach>
	     </table>
	 </div>
    </section>
    </body>
</html>
