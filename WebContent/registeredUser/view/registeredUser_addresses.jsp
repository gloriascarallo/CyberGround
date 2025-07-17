<%@page import="model.RegisteredUser_has_addressBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<% 
request.getAttribute("user_addresses");
 %>
 
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Personal Addresses Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/RegisteredUser_addresses.css?v=2">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Layout.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<%@ include file="/includes/header.jsp" %>

<h2>Qui sono mostrati tutti i tuoi indirizzi</h2>

<div id="container">
<c:forEach var="user_address" items="${user_addresses}">
	<label>
	${user_address.nameAddress}
	</label><br>
</c:forEach>
 <span>Vuoi aggiungere un nuovo indirizzo? <a href="${pageContext.request.contextPath}/registeredUser/view/add_address.jsp?redirectAfter=/registeredUser/view/user.jsp">Clicca qui!</a></span>
    <c:if test="${not empty sessionScope.message}">
    <div class="success-message">${sessionScope.message}</div>
    <c:remove var="message" scope="session"/>
    </c:if>
</div>
<%@ include file="/includes/footer.jsp" %>
</body>
</html>