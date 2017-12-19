<head>
	<title>Build Your Habits</title>
	<link href="webjars/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<nav role="navigation" class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="navbar-header">
			<a class="navbar-brand text-white">BuildYourHabits</a>
		</div>
		<ul class="navbar-nav ml-auto">
			<li><a class="nav-link" href="./sign-up">Create new account</a></li>
		</ul>
	</nav>
	<br>
	<br>
	<div class="container">
		<h2>Log in to track your habits</h2>
		<font color="red">
	        ${SPRING_SECURITY_LAST_EXCEPTION.message}
        </font>
		<form action="<%=request.getContextPath()%>/login" method="post">
		    <div><label> Username  <input class="form-control" type="text" name="username" />
		    </label></div>
		    <div><label> Password <input class="form-control" type="password" name="password" />
		    </label></div>
		    <div><input type="submit" value="Sign In"/></div>
		    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>			
		</form>
	</div>
</body>
</html>