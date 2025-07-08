<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Login.css"/>
</head>
<body>


<div id="container">
<%
    String errors = (String) request.getAttribute("errors");
    if (errors != null && !errors.isEmpty()) {
%>
    <div id="error" style="color: red;">
        <%= errors %>
    </div>
<%
    }
%>

<h1>Login</h1>

<form name="LoginForm" action="<%= request.getContextPath() %>/Login" method="post">

<div>
<label for="username">Inserisci username: </label>
<input required type="text" name="username" id="username" onchange="validateFormElement(this, usernamePattern, document.getElementById('errorUsername'), errorUsernameMessage)">
<span id="errorUsername"></span><br>
</div>

<div>
<label for="password">Inserisci password: </label>
<input required type="password" name="password" id="password" onchange="validateFormElement(this, passwordPattern, document.getElementById('errorPassword'), errorPasswordMessage)">
<span id="errorPassword"></span><br>
</div>

<div id="buttons">
<input type="submit" value="Accedi" onclick="return validateLoginForm()">
<input type="reset" value="Reset">
</div>
</form>
<span> Non sei ancora registrato? Clicca qui per registrarti: <a href="./registration.jsp">Registrati!</a></span>
</div>

</body>
<script src="<%=request.getContextPath()%>/scripts/validation.js" type="text/javascript"></script>
</html>