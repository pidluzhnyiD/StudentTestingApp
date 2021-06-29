<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
	document.getElementById("englishButton").addEventListener("load", setDefaultLanguage);
	function setDefaultLanguage() {
		document.getElementById("englishButton").click();
	}
	window.onload = function() {
		var enButton = document.getElementById("englishButton");
		    var ruButton = document.getElementById("russianButton");
	    var selectedLanguage = sessionStorage.getItem("selectedLanguage"); 
	    
	    if(selectedLanguage=="en"){
	    	enButton.style.display = "block";
	    	ruButton.style.display = "none";
	    }
	    else{
	    	enButton.style.display = "none";
	      	ruButton.style.display = "block";
	    }
	}
	
	function toggleButtons() {   	
	    var enButton = document.getElementById("englishButton");
	    var ruButton = document.getElementById("russianButton");
	    
	    if (enButton.style.display === "none") {
	    	sessionStorage.setItem("selectedLanguage", enButton.value);
	    	enButton.style.display = "block";
	    	ruButton.style.display = "none";
	   	} else {
	   		sessionStorage.setItem("selectedLanguage", ruButton.value);
	    	enButton.style.display = "none";
	      	ruButton.style.display = "block";
	   	}
	}
</script>
</body>
</html>