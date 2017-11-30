<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
	<h1>Add new user:</h1>
	<form:form action="./add-user" method="post" modelAttribute="user">	
		<fieldset class="form-group">
			<form:label path="name">Name</form:label>
			<form:input path="name" type="text" class="form-control" required="required"/>
			<form:errors path="name" cssClass="text-warning"/>
		</fieldset>
			
		<fieldset class="form-group">
			<form:label path="password">Password</form:label>
			<form:input path="password" type="password" class="form-control" required="required"/>
			<form:errors path="password" cssClass="text-warning"/>
		</fieldset>
			
		<input class="btn btn-success" type="submit" value="Add">
	</form:form>
</div>

<%@ include file="common/footer.jspf" %>