<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Вхід</title>
</head>
<body>
<form action="login" method="POST">
        Login: <input name="login" />
        <br><br>
        Password: <input name="pass" />
        <br><br>
        <input type="submit" value="Submit" />     
    </form>
    <p>${msg}</p>
	<%session.removeAttribute("msg"); %>
</body>
</html>