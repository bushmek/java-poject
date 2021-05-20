<%@ page language="java" contentType="text/html; charset=utf-8"%>
    <!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <title>Реєстрація</title>
    </head>
    <body>
    <form action="signup" method="POST">
        Login: <input name="login" />
        <br><br>
        Password: <input name="pass" />
        <br><br>
         Confirm password: <input name="repeatPass" />
        <br><br>
         Full name: <input name="name" />
        <br><br>
         E-mail: <input name="email" />
        <br><br>
        <input type="submit" value="Submit" />     
    </form>
	<p>${msg}</p>
	<%session.removeAttribute("msg"); %>
    </body>
    </html>