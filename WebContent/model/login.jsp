<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>

<form action="Login" method="post">

<label for="username">Inserisci username: </label>
<input type="text" name="username" onchange="validateFormElement(this, namePattern, document.getElementById('errorUsername'), errorNameMessage)"><span id="errorUsername"></span>

<label for="password">Inserisci password: </label>
<input type="text" name="password" onchange="validateFormElement(this, passwordPattern, document.getElementById('errorUsername'), errorPasswordMessage)"><span id="errorPassword"></span>

<span> Non sei ancora registrato? Clicca qui per registrarti: <a href="registration.jsp">Registrati!</a></span>

<input type="submit" value="Submit" onclick="return validate()">
<input type="reset" value="Reset">
</form>


</body>
</html>