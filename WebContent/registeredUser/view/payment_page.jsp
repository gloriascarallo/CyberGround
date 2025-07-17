<%@page import="model.RegisteredUser_has_method_paymentBean"%>
<%@page import="model.RegisteredUser_has_addressBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="model.CartBean" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Payment Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Payment_page.css?v=4"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Layout.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<%@ include file="/includes/header.jsp" %>

<h2>Riepilogo prodotti</h2>
<div class="product-grid">
  <c:forEach var="product_incart" items="${cart.products}">
    <div class="product-card">
      <a href="${pageContext.request.contextPath}/Product?idProduct=${product_incart.product.idProduct}">
        <img src="${product_incart.product.imagePath}" alt="Product image" />
      </a>
      <div class="product-details">
        <p><strong>Nome:</strong> ${product_incart.product.name}</p>
         <c:choose>
  <c:when test="${product_incart.product.discountPercentage != null && product_incart.product.discountPercentage > 0}">
    <p>
      <strong>Prezzo:</strong>
      <span class="prezzo_pieno">€ ${product_incart.product.price}</span>
      <span class="prezzo_scontato">
        € <fmt:formatNumber value="${product_incart.product.price - (product_incart.product.price * product_incart.product.discountPercentage / 100)}" type="number" maxFractionDigits="2"/>
      </span>
    </p>
  </c:when>
  <c:otherwise>
    <p><strong>Prezzo:</strong> € ${product_incart.product.price}</p>
  </c:otherwise>
</c:choose>
        <p><strong>Quantità:</strong> ${product_incart.quantity}</p>
      </div>
    </div>
  </c:forEach>
</div>

<h2>Seleziona indirizzo e metodo di pagamento</h2>
<div class="form-container">
  <form action="${pageContext.request.contextPath}/Payment" method="post">
    <div>
      <label for="indirizzo">Indirizzo</label> <br>
      <select name="indirizzo" id="indirizzo">
        <c:forEach var="user_address" items="${user_addresses}">
          <option value="${user_address.id_has_address}">${user_address.nameAddress}</option>
        </c:forEach>
      </select>
    </div>

    <span>Vuoi aggiungere un nuovo indirizzo? <a href="${pageContext.request.contextPath}/registeredUser/view/add_address.jsp?redirectAfter=/registeredUser/view/payment_page.jsp">Clicca qui!</a></span>
    <c:if test="${not empty sessionScope.message}">
    <div class="success-message">${sessionScope.message}</div>
    <c:remove var="message" scope="session"/>
    </c:if>
    <br><br>

    <div>
      <label for="metodo_pagamento">Metodo di pagamento</label> <br>
      <select name="metodo_pagamento" id="metodo_pagamento">
        <c:forEach var="user_methods" items="${user_methods_payment}">
          <option value="${user_methods.id_has_method_payment}">${user_methods.pan} - ${user_methods.expirationDate} - ${user_methods.cvc}</option>
        </c:forEach>
      </select>
    </div>
<span>Vuoi aggiungere un nuovo metodo di pagamento? <a href="${pageContext.request.contextPath}/registeredUser/view/add_payment_method.jsp?redirectAfter=/registeredUser/view/payment_page.jsp">Clicca qui!</a></span>
    <c:if test="${not empty sessionScope.message}">
    <div class="success-message">${sessionScope.message}</div>
    <c:remove var="message" scope="session"/>

    </c:if>

    <br><br>


    <input type="submit" value="Conferma pagamento">
  </form>
</div>

<%@ include file="/includes/footer.jsp" %>
</body>
</html>