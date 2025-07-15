<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
request.getAttribute("message");
%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Login</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Layout.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Login.css?v=2"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
<%@ include file="/includes/header.jsp" %>

<div id="container">


<h1>Login</h1>

<%
    String errors = (String) request.getAttribute("errors");
    if (errors != null && !errors.equals("")) {
%>
    <div id="error">
        <%= errors %>
    </div>
<%
    }
%>

${message}

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
<span> Non sei ancora registrato? Clicca qui per registrarti: <a href="${pageContext.request.contextPath}/guest/view/registration.jsp">Registrati!</a></span>
<span> Desideri continuare come guest? Clicca qui per andare alla home: <a href="${pageContext.request.contextPath}/guest/view/index.jsp">Home!</a></span>
</div>

<%@ include file="/includes/footer.jsp" %>
</body>
<script src="<%=request.getContextPath()%>/scripts/validation.js" type="text/javascript"></script>
</html>