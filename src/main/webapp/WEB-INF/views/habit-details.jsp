<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
	<br/>
	
	<h1>${habit.description}</h1>
	
	<br/>
	
	<p id="isTodaySuccessful"><a class="btn btn-danger" href="./succesful-day?id=${habit.habitID}">Mark this day as successful</a></p>
	
	<br/>
	
	<p id="daysLeft" style="font-size:32px;"></p>
	
	<br/>
	<br/>
	
	<p style="font-size:26px;">Current streak: <span style="font-size:30px; font-weight: bold;">${habit.currentStreak}</span></p>
	<p style="font-size:26px;">Longest streak: <span style="font-size:30px; font-weight: bold;">${habit.longestStreak}</span></p>
	<p style="font-size:26px;">Success Rate: <span style="font-size:30px; font-weight: bold;">${habit.successRate}%</span></p>
	<p style="font-size:26px;">Completion Rate: <span style="font-size:30px; font-weight: bold;">${habit.completionRate}%</span></p>
	
	<br/>
	<br/>
	
	<a class="btn btn-info" href="./update-habit?id=${habit.habitID}">Update</a>
	<a class="btn btn-danger" href="./list-habits">Return</a>
</div>

<script>
	$(document).ready(function() {
		var daysLeft =<jstl:out value="${habit.daysLeft}"/>
		
		if (daysLeft > 0)
			document.getElementById('daysLeft').innerHTML = "<span style='color:red; font-weight: bold;'>${habit.daysLeft}</span> days left to build the habit!";
		else if(daysLeft == 0)
			document.getElementById('daysLeft').innerHTML = "<span style='color:green; font-weight: bold;'>The last day to build the habit!</span>";
		else document.getElementById('daysLeft').innerHTML = "<span style='color:grey;'>Habit completed!</span>";
		
		var hasStarted =<jstl:out value="${habit.hasStarted}"/>
		
		if(Boolean(hasStarted)){
			var isTodaySuccessful =<jstl:out value="${habit.today}"/>
			if (Boolean(isTodaySuccessful))
				document.getElementById('isTodaySuccessful').innerHTML = "<a class='btn btn-success' href='./succesful-day?id=${habit.habitID}'>Today is successful</a>";
		} else {
			document.getElementById('isTodaySuccessful').innerHTML = "<a class='btn btn-success disabled' href='./succesful-day?id=${habit.habitID}'>Mark this day as successful</a>";
		}
		
	});
	
		
</script>

<%@ include file="common/footer.jspf" %>