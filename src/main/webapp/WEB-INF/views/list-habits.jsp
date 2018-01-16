<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	
<div class="container col-md-5">
	<br/>
	<table class="table table-striped">		
		<thead>
			<tr>
				<th>Description</th>
				<th>Target Date</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<jstl:forEach items="${habits}" var="habit">
				<tr>
					<td><a href="./habit-details?id=${habit.habitID}" style="color:black">${habit.description}</a></td>
					<td><fmt:formatDate pattern="dd/MM/yyyy" value="${habit.targetDate}"/></td>
					<td>
						<a class="btn btn-info" href="./habit-details?id=${habit.habitID}">Show</a>
						<a class="btn btn-danger" href="./delete-habit?id=${habit.habitID}">Delete</a>
					</td>
				</tr>
			</jstl:forEach>
		</tbody>
	</table>
	<div>
		<a class="btn btn-success" href="./add-habit">Add</a>
	</div>
</div>

<%@ include file="common/footer.jspf" %>