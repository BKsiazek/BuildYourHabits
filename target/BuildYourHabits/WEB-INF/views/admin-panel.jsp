<%@ include file="common/header.jspf" %>

<nav role="navigation" class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="navbar-header">
		<a class="navbar-brand text-white">BuildYourHabits</a>
	</div>
	
	<ul class="navbar-nav">
		<li class="active"><a class="nav-link">Welcome admin!</a></li>
	</ul>
	<ul class="navbar-nav ml-auto">
		<li><a class="nav-link" href="./logout">Logout</a></li>
	</ul>
</nav>

<div class="container">
	<table class="table table-striped">		
		<thead>
			<tr>
				<th>User</th>
				<th>Password</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<jstl:forEach items="${users}" var="user">
				<tr>
					<td>${user.name}</td>
					<td>${user.password}</td>
					<td>
						<!-- <a class="btn btn-info" href="./update-habit?id=${habit.habitID}">Update</a> -->
						<a class="btn btn-danger" href="./admin/delete-user?id=${user.userID}">Delete</a>
					</td>
				</tr>
			</jstl:forEach>
		</tbody>
	</table>
</div>

<%@ include file="common/footer.jspf" %>