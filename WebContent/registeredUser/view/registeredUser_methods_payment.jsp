<%@page import="bean.RegisteredUser_has_method_paymentBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<% 
RegisteredUser_has_method_paymentBean methods=(RegisteredUser_has_method_paymentBean)request.getAttribute("user_methods_payment");
 %>

<html>
<head>
<meta charset="UTF-8">
<title>Personal Methods Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/RegisteredUser_methods_payment.css">
</head>
<body>

<h2>Qui sono mostrati tutti i tuoi metodi di pagamento</h2>

<div id="container">
<c:forEach var="user_methods" items="${methods}">
	<label>
	${user_methods.pan} - ${user_methods.expirationDate} - ${user_methods.cvc}
	</label><br>
</c:forEach>
</div>

</body>
</html>