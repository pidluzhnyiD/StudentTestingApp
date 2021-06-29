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

		    	<button type="submit" name="resultsInfo" value="resultsInfo">
		    		${User.login}
		    	</button>
		    	<input type="hidden" name="command" value="resultsInfo"/>
		    </div>
		</form>			
  		 		 
    </header>
    <section>
      	<div>
		    <table class="sort-buttons">
		        <tr>		        	
		        	<td>
		        		<form action="/StudentTestingApp/Servlet"  method="POST">
						    <select  name="selectedSubject" onchange="this.form.submit()">	
						    	<c:choose>
								    <c:when test="${selectedSubject==null||selectedSubject==0}">
								       <option value="0" selected>ALL</option>
								    </c:when>
								    <c:otherwise>
								       <option value="0" selected>ALL</option>
								    </c:otherwise>
								</c:choose>	    	 	
						    	 <c:forEach items="${subjects}" var="subject">   
						    	 	<c:choose>
						    	 		<c:when test="${selectedSubject == subject.id}">
						    	 			<c:choose>
											    <c:when test="${language == null||language == 'en'}">
											       <option value="${subject.id}" selected>${subject.englishName}</option>
											    </c:when> 
											    <c:when test="${language == 'ru'}">
											        <option value="${subject.id}" selected>${subject.russianName}</option>
											    </c:when>
											</c:choose>											
										</c:when>
										<c:otherwise>
											<c:choose>
											    <c:when test="${language == null||language == 'en'}">
											       <option value="${subject.id}">${subject.englishName}</option>
											    </c:when>
											    <c:when test="${language == 'ru'}">
											        <option value="${subject.id}">${subject.russianName}</option>
											    </c:when>
											</c:choose>												
										</c:otherwise>
						    	 	</c:choose>
						    	 </c:forEach>   					        
						    </select>
						    <input type="hidden" name="command" value="testListing"/>
						</form>	
		        	</td>
		        	<td></td>
		        	<td>
		        		<h4>
		        			<fmt:message key="sort.by" bundle ="${lang}" />
		        		</h4>
		        	</td>
		        	<td></td>
		        	<td>
		        		<form action="/StudentTestingApp/Servlet"  method="POST">
			        		<select name="orderBy" onchange="this.form.submit()">
			        				<option value="name" ${orderBy == 'name' ? 'selected' : ''}>
				        				<fmt:message key="sort.by.name.asc" bundle ="${lang}" />
				        			</option>
				        			<option value="name_desc" ${orderBy == 'name_desc'  ? 'selected' : ''}>
				        				<fmt:message key="sort.by.name.desc" bundle ="${lang}" />
				        			</option>
				        			<option value="difficulty" ${orderBy == 'difficulty' ? 'selected' : ''}>
				        				<fmt:message key="sort.by.difficulty.asc" bundle ="${lang}" />
				        			</option>
				        			<option value="difficulty_desc" ${orderBy == 'difficulty_desc' ? 'selected' : ''}>
				        				<fmt:message key="sort.by.difficulty.desc" bundle ="${lang}" />
				        			</option>
				        			<option value="number_of_requests" ${orderBy == 'number_of_requests' ? 'selected' : ''}>
				        				<fmt:message key="sort.by.requests.asc" bundle ="${lang}" />
				        			</option>
				        			<option value="number_of_requests_desc" ${orderBy == 'number_of_requests_desc' ? 'selected' : ''}>
				        				<fmt:message key="sort.by.requests.desc" bundle ="${lang}" />
				        			</option>
			        		</select>	
			        		<input type="hidden" name="command" value="testListing"/>				        		
			        	</form>	
		        	</td>
		        </tr>
		    </table>		    
     	</div>
      <div style="width:100%;text-align:center">   	
			 <table class = "tests">
	          <tr>
				   <th><fmt:message key = "subject" bundle ="${lang}"/></th>
	               <th>#</th>
	               <th><fmt:message key = "test.name" bundle ="${lang}"/></th>
	               <th><fmt:message key = "test.duration" bundle ="${lang}"/></th>
	               <th><fmt:message key = "test.difficulty" bundle ="${lang}"/></th>
	               <th><fmt:message key = "test.requests" bundle ="${lang}"/></th>
	               <th><fmt:message key = "test.start" bundle ="${lang}"/></th>
	          </tr>
	
	          <c:forEach items="${tests}" var="test">          
	                	<tr>         	
	               		<td>      
							<c:choose>
							    <c:when test="${language == null||language == 'en'}">
							       ${subjects[test.subjectId-1].englishName}
							    </c:when>
							    <c:when test="${language == 'ru'}">
							        ${subjects[test.subjectId-1].russianName}
							    </c:when>
							</c:choose>
		               	</td>
	               		 <td>${test.id}</td>
			               <td>       	
								<c:choose>
								    <c:when test="${language == null}">
								       ${test.englishName}
								    </c:when>
								    <c:when test="${language == 'en'}">
								        ${test.englishName}
								    </c:when>
								    <c:when test="${language == 'ru'}">
								        ${test.russianName}
								    </c:when>
								</c:choose>
			               	</td>
			               <td>
			               		${test.testDuration} <fmt:message key = "minutes" bundle ="${lang}"/>
			               </td>
			               <td>       	
								<c:choose>
								    <c:when test="${language == null}">
								       ${test.englishDifficulty}
								    </c:when>
								    <c:when test="${language == 'en'}">
								        ${test.englishDifficulty}
								    </c:when>
								    <c:when test="${language == 'ru'}">
								        ${test.russianDifficulty}
								    </c:when>
								</c:choose>
			               	</td>
			               	<td>${test.numberOfRequests}</td>               	
			               	<td>
			               		<form action="/StudentTestingApp/Servlet" method="post">
				               		<fmt:message key="start" var="buttonPlaceholder" bundle ="${lang}" />
				               		<input class='tableButtons' type='submit' value="${buttonPlaceholder}"/>
				               		<input type="hidden" name="testId" value="${test.id}"/>
				               		<input type="hidden" name="command" value="startTest"/>
			               		</form>
			               	</td>
			        </tr>	          	         
	          </c:forEach>
	     </table>
	 	<div>
		    <table class="pagination">
		        <tr>
		        	<c:if test="${currentPage != 1}">
					 	<td  class="pagination-button-active">
							<form action="/StudentTestingApp/Servlet" method="get">
								<input class='tableButtons' type='submit' value="Previous"/>
								<input type="hidden" name="command" value="testListing"/>
								<input type="hidden" name="page" value="${currentPage -1}"/>
							</form>
						</td>
				    </c:if>
		            <c:forEach begin="1" end="${noOfPages}" var="i">
		                <c:choose>
		                    <c:when test="${currentPage eq i}">
		                     	<td class="pagination-button-inactive">
		                        	<input class='tableButtons' type='submit' value="${i}"/>
		                        </td>                   
		                    </c:when>
		                    <c:otherwise>
		                        <td class="pagination-button-active">
		                        	<form action="/StudentTestingApp/Servlet" method="get">
						               	<input class='tableButtons' type='submit' value="${i}"/>
						               	<input type="hidden" name="command" value="testListing"/>
						               	<input type="hidden" name="page" value="${i}"/>
					               	</form>
		                        </td>
		                    </c:otherwise>
		                </c:choose>
		            </c:forEach>
		           	<c:if test="${currentPage lt noOfPages}">
				     	<td  class="pagination-button-active">
							<form action="/StudentTestingApp/Servlet" method="get">
								<input class='tableButtons' type='submit' value="Next"/>
								<input type="hidden" name="command" value="testListing"/>
								<input type="hidden" name="page" value="${currentPage + 1}"/>
							</form>
						</td>
		    		</c:if>
		        </tr>
		    </table>		    
     	</div>
	 </div>
    </section>
    </body>
</html>