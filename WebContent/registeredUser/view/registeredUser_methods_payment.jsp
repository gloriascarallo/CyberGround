<%@page import="bean.RegisteredUser_has_method_paymentBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<% 
request.getAttribute("user_methods_payment");
 %>

<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Personal Methods Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/RegisteredUser_methods_payment.css?v=3">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Layout.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<%@ include file="/includes/header.jsp" %>

<h2>Qui sono mostrati tutti i tuoi metodi di pagamento</h2>

<div id="container">
<c:forEach var="user_methods" items="${user_methods_payment}">
	<label>
	PAN: ${user_methods.pan} - Data di Scadenza: ${user_methods.expirationDate} - CVC: ${user_methods.cvc}
	</label><br>
</c:forEach>
<span>Vuoi aggiungere un nuovo metodo di pagamento? <a href="${pageContext.request.contextPath}/registeredUser/view/add_payment_method.jsp?redirectAfter=/registeredUser/view/user.jsp">Clicca qui!</a></span>
    <c:if test="${not empty sessionScope.message}">
    <div class="success-message">${sessionScope.message}</div>
    <c:remove var="message" scope="session"/>

    </c:if>
</div>
<%@ include file="/includes/footer.jsp" %>
</body>
</html>