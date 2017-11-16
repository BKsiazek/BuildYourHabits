<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<!DOCTYPE html>
<html>
<head>
	<title>List Of Habits</title>
	<link href="webjars/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	Hi ${name}<br>
	
	<table>
		<caption>Your habits</caption>
		<thead>
			<tr>
				<th>Description</th>
				<th>Target Date</th>
				<th>Is Completed</th>			
			</tr>
		</thead>
		<tbody>
			<jstl:forEach items="${habits}" var="habit">
				<tr>
					<td>${habit.description}</td>
					<td>${habit.targetDate}</td>
					<td>${habit.done}</td>
				</tr>
			</jstl:forEach>
		</tbody>
	</table>
	 
	<br>
	
	
	
	<br>
	<a class="button" href="/add-habit">Add</a>
	
	<script src="webjars/jquery/3.2.1/jquery.min.js"></script>
	<script src="webjars/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
	    
</body>
</html>