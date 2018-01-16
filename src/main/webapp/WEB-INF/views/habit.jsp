<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
	<h1>Add/Update a habit:</h1>
	<form:form method="post" modelAttribute="habit">
		<form:hidden path="habitID"/> 
		<fieldset class="row col-md-6 form-group">
			<form:label path="description">Description</form:label>
			<form:input path="description" type="text" class="form-control" required="required" /> 
			<form:errors path="description" cssClass="text-warning"/>
		</fieldset>
			
		<fieldset class="row col-md-2 form-group">
			<form:label path="startDate">Start Date</form:label>
			<div class="input-group date" id="dateRangePicker">
				<form:input path="startDate" type="text" class="form-control" required="required"/>
				<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
			</div>
			<form:errors path="startDate" cssClass="text-warning"/>			
		</fieldset>
			
		<fieldset class="row col-md-2 form-group">
			<form:label path="targetDate">Target Date</form:label>
			<div class="input-group date" id="dateRangePicker2">
				<form:input path="targetDate" type="text" class="form-control" required="required"/>
				<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
			</div>
			<form:errors path="targetDate" cssClass="text-warning"/>			
		</fieldset>
			
		<input class="btn btn-success" type="submit" value="Submit">
	</form:form>
</div>


<script>
	$(document).ready(function() {
		$('#dateRangePicker')
		.datepicker({
			format: 'dd/mm/yyyy'
		});
	});
	
	$(document).ready(function() {
		$('#dateRangePicker2')
		.datepicker({
			format: 'dd/mm/yyyy'
		});
	});
</script>

</body>
</html>