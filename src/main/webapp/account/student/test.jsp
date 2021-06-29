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
    	<form action="/StudentTestingApp/Servlet" method="post">
	  		<div class="header-right">

		    	<button type="submit" name="logout" value="logout">
		    		<fmt:message key = "logout" bundle ="${lang}"/>
		    	</button>
		    	<input type="hidden" name="command" value="logout"/>
		    </div>
		</form>	  
		<p id="timer" class="timer"></p>		
  		 		 
    </header>
    <section>
      <div class = "test-outer-form">
          <div class = "test-inner-form">
	    	<form name="testForm" action="/StudentTestingApp/Servlet" method="post">				
				 <c:forEach var="i" begin="0" end="${selectedTest.questionsCount-1}" step="1">	
				 	<div class="question">
						<h4>${selectedTest.questions[i].description}</h4>  
							<c:forEach var="j" begin="0" end="${selectedTest.questions[i].optionsCount-1}" step="1">	
									<c:if test="${selectedTest.questions[i].options[j] != null}">
										<p>								
											<input type="checkbox" name="question${i+1}" value="${j}">
											<label for="question${i+1}">${selectedTest.questions[i].options[j]}</label><br> 
										</p>    
									</c:if>						   						
					         </c:forEach>     
					</div>   
		         </c:forEach>
		         <fmt:message key="submit" var="buttonPlaceholder" bundle ="${lang}" />
				 <input type='submit' id="submit" value="${buttonPlaceholder}"/>
				 <input type="hidden" name="command" value="calculateResult"/>
	     	</form>
		 </div>
	 </div>
    </section>
    </body>
    <script>
    window.onbeforeunload = function() {
    	 document.getElementById("submit").click();
    }
    window.onload = function() {
    	var startTime = new Date().getTime();
	 // Set the date we're counting down to
		var countDownDate = new Date().getTime() + ${selectedTest.testDuration}*60000;
		
		// Update the count down every 1 second
		var x = setInterval(function() {
		
		  // Get today's date and time
		  var now = new Date().getTime();
		    
		  // Find the distance between now and the count down date
		  var distance = countDownDate - now;
		    
		  // Time calculations for days, hours, minutes and seconds
		  var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
		  var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
		  var seconds = Math.floor((distance % (1000 * 60)) / 1000);
		    
		  // Output the result in an element
		  document.getElementById("timer").innerHTML = hours + "h "
		  + minutes + "m " + seconds + "s ";
		    
		  // If the count down is over, submit result 
		  if (distance < 0) {
		    clearInterval(x);
		    document.getElementById("submit").click();
		  }
		}, 1000);
	}  
    </script>
</html>