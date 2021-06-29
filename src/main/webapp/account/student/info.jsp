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
  		 		 
    </header>
    <section>
    	<table class="userInfoTable">
    		<tr>
    			<td class="leftTable">
    				<table class = "results">
			          	<tr>
			          	  	 <th><fmt:message key = "user.name" bundle ="${lang}"/></th>
			              	 <th><fmt:message key = "user.username" bundle ="${lang}"/></th>
			          	  	 <th><fmt:message key = "user.status" bundle ="${lang}"/></th>	               
			          	</tr>
			         	 <tr> 
			          		<td>${User.login}</td>   
			          		<td>${User.name}</td>   
			          		<td>${User.role}</td>        	
			          	</tr>
			    	</table>
    			</td>
    			<td>
    				<table class = "results">
	          			<tr>
			               <th><fmt:message key = "test.name" bundle ="${lang}"/></th>
			               <th><fmt:message key = "test.difficulty" bundle ="${lang}"/></th>
			               <th><fmt:message key = "test.completion.time" bundle ="${lang}"/></th>
			               <th><fmt:message key = "test.completion.date" bundle ="${lang}"/></th>
			               <th><fmt:message key = "test.result" bundle ="${lang}"/></th>
	         		 	</tr>
							<c:forEach items="${results}" var="result">
		               	      
		               			 <tr>         	
				               		<td>       	
									<c:choose>
									    <c:when test="${language == null}">
									       ${result.englishName}
									    </c:when>
									    <c:when test="${language == 'en'}">
									        ${result.englishName}
									    </c:when>
									    <c:when test="${language == 'ru'}">
									        ${result.russianName}
									    </c:when>
									</c:choose>
				               		</td>
				               		<td>       	
									<c:choose>
									    <c:when test="${language == null}">
									       ${result.englishDifficulty}
									    </c:when>
									    <c:when test="${language == 'en'}">
									        ${result.englishDifficulty}
									    </c:when>
									    <c:when test="${language == 'ru'}">
									        ${result.russianDifficulty}
									    </c:when>
									</c:choose>
				               		</td>
				               		<td>${result.completionTime}</td>  
				               		<td>${result.completionDate}</td>  
				               		<td>${result.result}</td>               	
				        		</tr>
		               		</c:forEach>
	     			</table>
    			</td>
    		</tr>  	
    	</table>
    </section>
    </body>
</html>