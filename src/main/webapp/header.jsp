<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
			body{
			  margin:0;
			  padding:0;
			  background-color:#eaeaea;
			}
        	
			header {
			  overflow: hidden;
			  background-color: #00BFB2;
			  margin:0;
			  padding:0;
			}
			
			header button, a {
			  border:none;
			  text-decoration:none;
			  background-color: #00BFB2;
			  float: left;
			  color: black;
			  text-align: center;
			  padding: 14px;
			  text-decoration: none;
			  font-size: 20px;
			  line-height: 25px;
			  border-radius: 4px;
			  transition: background 0.5s linear 0.1s , color 0.5s linear 0s;
			}
			
			header button.logo {
			  font-size: 24px;
			}
	
			header button:hover, a:hover, 
			.pagination-button-active .tableButtons:hover{
			  background-color: #05a398;
			  color: black;
			}
			
			.header-right {
			  float: right;
			}
			
			.sign-in-box{
				display: flex;
				justify-content: center;
				align-items: center;
	    		margin: 0 auto;
	    		margin-top:100px;
				width:800px;
				height:500px;
				background-color:white;	
				border-radius: 50px;
			}
			
			.register-box{
				display: flex;
				justify-content: center;
				align-items: center;
	    		margin: 0 auto;
	    		margin-top:100px;
				width:800px;
				height:700px;
				background-color:white;	
				border-radius: 50px;
			}
			
			.register-form{
				width:100%;
				padding: 0;
				text-align: center;
	    		vertical-align: middle;
			}
			
			.register-inner-form{
				margin:auto;
				width:60%;
			}
			
			.register-form-text-align-left{
				font-size: 25px;
				text-align: left;
				margin: 5px;
			}
			
			.message-box-centered{
				display: flex;
				justify-content: center;
				align-items: center;
	    		margin: 0 auto;
	    		margin-top:100px;
				width:800px;
				height:500px;
				border-radius: 50px;
			}
			
			.sign-in-form{
				width:100%;
				padding: 0;
				text-align: center;
	    		vertical-align: middle;
			}
			
			#russianButton{
				display:none;
			}
			
			.error{
				padding: 12px 20px;
			}
			
			.tests, .results, .questions {
			  font-family: Arial, Helvetica, sans-serif;
			  border-collapse: collapse;
			  width: 90%;
			  margin: auto;
			}
						
			.questions {
			  text-align: center;
			}
			
			.tests td, .results td,
			.questions td, th {
			  border: 1px solid #ddd;
			  padding: 8px;
			}
			
			
			.tests tr:nth-child(even),
			.results tr:nth-child(even),
			.questions tr:nth-child(even){
			 	background-color: #f2f2f2;
			 }
								
			.tests th, .results th,
			.questions th {
			  padding-top: 12px;
			  padding-bottom: 12px;
			  text-align: center;
			  background-color: black;
			  color: white;
			}
						
			.timer{
				font-size: 22px;
    			margin: 0;
    			padding: 12px;
    			float:right;
			}
			
			.leftTable{
				vertical-align:top;
			}
			
			.userInfoTable{
				width:100%;
			}
			
			.userInfoTable td{
				text-align: center;
				padding:1%;
			}
			
			.question{
				text-align: left;
			}
			
			.question p{
				font-size: 20px;
			}
			
			.test-outer-form{
				width:100%;
				display: flex;
		        flex-direction: row;
		        flex-wrap: wrap;
		        justify-content: center;
		        align-items: center;
			}
			
			.test-inner-form{
				width:40%;
			}
			
			h1{
				font-size: 50px;
			}
			
			h3{
				font-size: 30px;
			}
			
			h4{
				font-size: 25px;
			}
			
			input{
				margin:10px;
			}
			
			input[type=text], input[type=password], select, 
			input[type=number]{
			  width: 100%;
			  padding: 12px 20px;
			  margin: 8px 0;
			  display: inline-block;
			  border: 1px solid #ccc;
			  border-radius: 4px;
			  box-sizing: border-box;
			}
			
			input[type=submit] {
			  width: 100%;
			  background-color: #00BFB2;
			  color: white;
			  padding: 14px 20px;
			  margin: 8px 0;
			  border: none;
			  border-radius: 4px;
			  cursor: pointer;
			  font-size:16px;
			}
			
			.sign-in-box input:invalid,
			.register-box input:invalid {
			  border: 2px dashed red;
			}
			
			.sign-in-box input:valid,
			.register-box input:valid {
			  border: 2px solid green;
			}
			
			.tableButtons{
			    display:inline-block;
			    width: 100%;
			    height: 100%;
			    margin:0;
			    border:none;
			}
			
			.sign-in-box input[type=text], 
			.sign-in-box  input[type=password],
			.sign-in-box  select,
			.sign-in-box  input[type=submit]{
				width: 50%;
			}
			
			.pagination{
				margin: 0 auto;
			}
			
			.sort-buttons{
				margin: 0 auto;
			}
			
			.sort-buttons td{
				padding:5px;
			}
			
			.pagination input[type=submit] {
				width: 100%;
			}
			
			.pagination-button-inactive input[type=submit] {
				background-color: lightgrey;
				color: white;
			}
			
						
			.test-create-form{
				width:40%;
				padding: 0;
				text-align: center;
	    		vertical-align: middle;
	    		padding-top: 2%;
    			padding-left: 5%;
			}
			
			.test-create-form h4, .questions h4{
				font-size: 20px;
				text-align: left;
				margin:5px;
			}
			
			.questions input[type=text],
			.questions input[type=number],
			.questions select{
				width: 80%;
			}
			
			.answersForm{
				width:10%;
				padding: 0;
				text-align: center;
	    		vertical-align: middle;
	    		padding-top: 2%;
    			padding-left: 5%;
    			float:left;
			}
			.leftQuestionForm, .rightQuestionForm{
				width:35%;
				padding: 0;
				text-align: center;
	    		vertical-align: middle;
	    		padding-top: 2%;
    			padding-left: 5%;
    			float:left;
			}
			
			.leftQuestionForm input[type=text], .leftQuestionForm input[type=number], 
				.leftQuestionForm select, .leftQuestionForm input[type=submit]{
				width:100%;
			}
			.rightQuestionForm input[type=text], .rightQuestionForm input[type=number], 
				.rightQuestionForm select, .rightQuestionForm input[type=submit]{
				width:100%;
			}
						
			.header-right select{
			  width: 100%;
			  background-color: #00BFB2;
			  padding: 14px 20px;
			  margin: 0;
			  border: none;
			  border-radius: 4px;
			  cursor: pointer;
			  font-size:20px;
			  transition: background 0.5s linear 0.1s , color 0.5s linear 0s;
			  outline: none !important;
  			  box-shadow: none !important;
			}
			.header-right select option {
			  background: white;
			}
			.header-right select:hover{
		  	  background-color: #05a398;
			  color: black;
			}
						
			
			@media screen and (max-width: 500px) {
			  header a {
			    float: none;
			    display: block;
			    text-align: left;
			  }
			  .header-right {
			    float: none;
			  }
			}
</style>
</head>
<body>

</body>
</html>