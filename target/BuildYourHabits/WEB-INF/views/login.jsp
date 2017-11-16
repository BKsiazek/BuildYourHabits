<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
</head>
<body>
	<p><font color="red">${errorMessage}</font></p>
	<form action="login" method="post">
		Name: <input type="text" name="name">
		Password: <input type="password" name="password">
		<input type="submit">
	</form>
</body>
</html>