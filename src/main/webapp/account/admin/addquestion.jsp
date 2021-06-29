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

		    	<button type="submit" name="users" value="users">
		    		<fmt:message key = "users" bundle ="${lang}"/>
		    	</button>
		    	<input type="hidden" name="command" value="userListing"/>
		    </div>
		</form>			
  		 		 		
  		 		 
    </header>
    <section>
      <div>  
      	<form action="/StudentTestingApp/Servlet" method="post"> 
      		<table class = "questions">
	          <tr>
	          	   <th></th>
				   <th><fmt:message key = "answers" bundle ="${lang}"/></th>
	               <th><fmt:message key = "question.english" bundle ="${lang}"/></th>
	          </tr>
	          <tr>
	           	   <td><fmt:message key = "question.description" bundle ="${lang}"/></td>
				   <td></td>
	               <td>
		             	<input type="text" class="input-field" name="englishDescription" required> <br>
	               	</td>
	          </tr>
	           <tr>
	           	   <td><fmt:message key = "question.option1" bundle ="${lang}"/></td>
				   <td>
				   		<input type="checkbox" name="answer1" value=0> 	
				   	</td>
	                <td>
		             	<input type="text" class="input-field" name="englishOption1" required> <br>
	               	</td>
	          </tr>
	         
	          <tr>
	           	   <td><fmt:message key = "question.option2" bundle ="${lang}"/></td>
				   <td>
				   		<input type="checkbox" name="answer2" value=1> 	
				   	</td>
	                <td>
		             	<input type="text" class="input-field" name="englishOption2" required> <br>
	               	</td>
	          </tr>
	          
	           <tr>
	           	   <td><fmt:message key = "question.option3" bundle ="${lang}"/></td>
				   <td>
				   		<input type="checkbox" name="answer3" value=2> 	
				   	</td>
	                <td>
		             	<input type="text" class="input-field" name="englishOption3"> <br>
	               	</td>
	          </tr>
	          
	           <tr>
	           	   <td><fmt:message key = "question.option4" bundle ="${lang}"/></td>
				   <td>
				   		<input type="checkbox" name="answer4" value=3> 	
				   	</td>
	                <td>
		             	<input type="text" class="input-field" name="englishOption4"> <br>
	               	</td>
	          </tr>	          
	     </table>
	     
	      <span class="error">${errorMessage}</span> <br>				             
		 
		  <fmt:message key="submit" var="submitText" bundle ="${lang}"/>
		  <input type="submit" class="input-submit" value="${submitText}"/>
		  <input type="hidden" name="command" value="addQuestion"/>
		</form>
	 </div>
    </section>
    </body>
</html>