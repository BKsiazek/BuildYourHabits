<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
	<h1>Add/Update a habit:</h1>
	<form:form method="post" modelAttribute="habit">
		<form:hidden path="habitID"/> 
		<fieldset class="form-group">
			<form:label path="description">Description</form:label>
			<form:input path="description" type="text" class="form-control" required="required" /> 
			<form:errors path="description" cssClass="text-warning"/>
		</fieldset>
			
		<fieldset class="form-group">
			<form:label path="targetDate">Target Date</form:label>
			<form:input path="targetDate" type="text" class="form-control" required="required"/>
			<form:errors path="targetDate" cssClass="text-warning"/>
		</fieldset>
			
		<input class="btn btn-success" type="submit" value="Submit">
	</form:form>
</div>

<%@ include file="common/footer.jspf" %>