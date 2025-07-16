<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    request.setAttribute("nowTimestamp", System.currentTimeMillis());
	%>
    
<!DOCTYPE html>
<%@page import="bean.Product_situatedin_cartBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.CartBean" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
 <%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Cart Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Cart.css?v=4"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Layout.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>


<%@ include file="/includes/header.jsp" %>

<div id="container">

<form action="${pageContext.request.contextPath}/Payment_page" method="post">

<c:forEach var="product_incart" items="${sessionScope.cart.products}">

<a href="${pageContext.request.contextPath}/Product?idProduct=${product_incart.product.idProduct}">
    <img src="${product_incart.product.imagePath}" alt="Product image" />
    </a>

    <div id="product-${product_incart.idSituatedIn}">
      
    <strong>Nome:</strong> ${product_incart.product.name}<br>
    <c:choose>
 <c:when test="${product_incart.product.discountPercentage != null && product_incart.product.discountPercentage > 0 && (product_incart.product.dateExpirationDiscount == null || product_incart.product.dateExpirationDiscount.time>nowTimestamp)}">    <p>
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
   
    <div>
    <a href="${pageContext.request.contextPath}/UpdateCart?id=${product_incart.idSituatedIn}&action=decrease">
  <button type="button">-</button>
</a>

 <span id="quantity-${product_incart.idSituatedIn}">${product_incart.quantity}</span>
    <a href="${pageContext.request.contextPath}/UpdateCart?id=${product_incart.idSituatedIn}&action=increase">
  <button type="button">+</button>
</a>
        
       
    </div>
    

   <a href="${pageContext.request.contextPath}/UpdateCart?id=${product_incart.idSituatedIn}&action=remove">
  <button type="button">Rimuovi</button>
</a>
    </div>
</c:forEach>
<h3>Totale carrello: €<span id="cart-total">${cart.total}</span></h3>

    <input type="submit" value="Payment">
    </form>
    </div>
    <%@ include file="/includes/footer.jsp" %>
</body>
<script src="${pageContext.request.contextPath}/scripts/update_cart.js"></script>
</html>


