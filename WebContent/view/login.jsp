<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Login</title>
</head>
<body>

<%
String errors=(String)request.getAttribute("errors");

if(errors!=null && !errors.equals("")) { %>

<div class="error"><%=errors%></div>

<%  }%>

<form name="LoginForm" action="<%= request.getContextPath() %>/Login" method="post">

<label for="username">Inserisci username: </label>
<input required type="text" name="username" onchange="validateFormElement(this, usernamePattern, document.getElementById('errorUsername'), errorUsernameMessage)"><span id="errorUsername"></span><br>

<label for="password">Inserisci password: </label>
<input required type="password" name="password" onchange="validateFormElement(this, passwordPattern, document.getElementById('errorPassword'), errorPasswordMessage)"><span id="errorPassword"></span><br>

<span> Non sei ancora registrato? Clicca qui per registrarti: <a href="./registration.jsp">Registrati!</a></span>

<input type="submit" value="Submit" onclick="return validateLoginForm()">
<input type="reset" value="Reset">
</form>


</body>
<script src="<%=request.getContextPath()%>/scripts/validation.js" type="text/javascript"></script>
</html>