<%@ include file="common/header.jspf" %>

<nav role="navigation" class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="navbar-header">
			<a class="navbar-brand text-white">BuildYourHabits</a>
		</div>
		<ul class="navbar-nav ml-auto">
			<li><a class="nav-link" href="./login">Log in</a></li>
		</ul>
	</nav>

<div class="container">
	<h1>Add new user:</h1>
	<form:form action="./sign-up" method="post" modelAttribute="user">	
		<fieldset class="row col-md-6 form-group">
			<form:label path="name">Name</form:label>
			<form:input path="name" type="text" class="form-control" required="required"/>
			<form:errors path="name" cssClass="text-warning"/>
		</fieldset>
			
		<fieldset class="row col-md-6 form-group">
			<form:label path="password">Password</form:label>
			<form:input path="password" type="password" class="form-control" required="required"/>
			<form:errors path="password" cssClass="text-warning"/>
		</fieldset>
			
		<input class="btn btn-success" type="submit" value="Add">
	</form:form>
</div>



<%@ include file="common/footer.jspf" %>