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
			 <div class="test-create-form">	 
    		 	<h3><fmt:message key = "test" bundle ="${lang}"/></h3>
    		 	
		         <form action="/StudentTestingApp/Servlet" method="POST">		         		 
		         	<label>  
		         		<h4>
		         			<fmt:message key = "subject" bundle ="${lang}"/>
		         		</h4>      	
		         	 	<c:choose>
							    <c:when test="${language == null||language == 'en'}">
							    	<select name="testSubject" id="testSubject">
		         						<c:forEach items="${subjects}" var="subject">
		         							 <c:choose>  
										         <c:when test = "${selectedTest.subjectId==subject.id}">
										           <option value="${subject.id}" selected>${subject.englishName}</option>
										         </c:when>
										         <c:otherwise>
										            <option value="${subject.id}">${subject.englishName}</option>
										         </c:otherwise>
										      </c:choose>							       				
										</c:forEach>
									</select>
							    </c:when>
							    <c:when test="${language == 'ru'}">
								    <select name="testSubject" id="testSubject">
			         					<c:forEach items="${subjects}" var="subject">
			         						 <c:choose>  
			         						 <c:when test = "${selectedTest.subjectId==subject.id}">
										           <option value="${subject.id}" selected>${subject.russianName}</option>	
										         </c:when>
										         <c:otherwise>
										            <option value="${subject.id}">${subject.russianName}</option>	
										         </c:otherwise>
										      </c:choose>							    			
							    		</c:forEach>
									</select>						       
							    </c:when>
							</c:choose>
					 </label> 
					 <h4>
		         		<fmt:message key = "test.name.english" bundle ="${lang}"/>
		         	 </h4>	         	 	         	 		         	
		         	 <fmt:message key="test.name.english" var="testNameEnglishPlaceholder" bundle ="${lang}" />
		             <input type="text" class="input-field" value = "${selectedTest.englishName}" placeholder="${testNameEnglishPlaceholder}" name="englishName" required> <br>
		             
		              <h4>
		         		<fmt:message key = "test.name.russian" bundle ="${lang}"/>
		         	 </h4>	         	 	         	 		         	
		         	 <fmt:message key="test.name.russian" var="testNameRussianPlaceholder" bundle ="${lang}" />
		             <input type="text" class="input-field" value = "${selectedTest.russianName}" placeholder="${testNameRussianPlaceholder}" name="russianName" required> <br>
		             
		             <h4>
		         		<fmt:message key = "test.duration" bundle ="${lang}"/>
		         	 </h4>	 
		         	 <fmt:message key = "test.duration" var="durationPlaceholder"  bundle ="${lang}"/>
		             <input type="number" class="input-field" value = "${selectedTest.testDuration}"  placeholder="${durationPlaceholder}" name="duration" min="0" max="180" required> <br>	       
		             <label>  
			             <h4>
			         		<fmt:message key = "test.difficulty" bundle ="${lang}"/>
			         	 </h4>	 
			             <select name="difficulty" id="difficulty">		             				  
							<fmt:message key = "test.difficulty.easy" var="easyDifficultyPlaceholder"  bundle ="${lang}"/>
			         		<option value="0">${easyDifficultyPlaceholder}</option>
			         		<fmt:message key = "test.difficulty.medium" var="mediumDifficultyPlaceholder"  bundle ="${lang}"/>
			         		<option value="1">${mediumDifficultyPlaceholder}</option>
			         		<fmt:message key = "test.difficulty.hard" var="hardDifficultyPlaceholder"  bundle ="${lang}"/>
			         		<option value="2">${hardDifficultyPlaceholder}</option>
						 </select>
		         	 </label>
					 <span class="error">${errorMessage}</span> <br>
							             
		             <fmt:message key="submit" var="submitText" bundle ="${lang}"/>
		             <input type="submit" class="input-submit" value="${submitText}"/>
		             
		             <input type="hidden" name="command" value="updateTest"/>
		         </form>
		         
		         <br/>
    		 </div>
	 </div>
    </section>
    </body>
</html>