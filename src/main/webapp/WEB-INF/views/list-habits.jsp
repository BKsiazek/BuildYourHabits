<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	
<div class="container">
	<table class="table table-striped">		
		<thead>
			<tr>
				<th>Description</th>
				<th>Target Date</th>
				<th>Is Completed</th>	
				<th></th>
			</tr>
		</thead>
		<tbody>
			<jstl:forEach items="${habits}" var="habit">
				<tr>
					<td>${habit.description}</td>
					<td><fmt:formatDate pattern="dd/MM/yyyy" value="${habit.targetDate}"/></td>
					<td>${habit.done}</td>
					<td>
						<a class="btn btn-info" href="./update-habit?id=${habit.id}">Update</a>
						<a class="btn btn-danger" href="./delete-habit?id=${habit.id}">Delete</a>
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