<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<!DOCTYPE html>
<html>
<head>
	<title>Habits Of ${name}</title>
	<link href="webjars/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		Hi ${name}<br>

		<table class="table table-striped">		
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
		<div>
			<a class="btn btn-success" href="/add-habit">Add</a>
		</div>
	</div>
	
	<script src="webjars/jquery/3.2.1/jquery.min.js"></script>
	<script src="webjars/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
	    
</body>
</html>