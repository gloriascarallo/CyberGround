<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<%@page import="bean.Product_situatedin_cartBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.CartBean" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

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

<a href="${pageContext.request.contextPath}/Product?id=${product_incart.idSituatedIn}">
    <img src="${product_incart.product.imagePath}" alt="Product image" />
    </a>
    <div id="product-${product_incart.idSituatedIn}">
    
    Nome: ${product_incart.product.name}<br>
    Prezzo: <span id="total-${product_incart.idSituatedIn}">${product_incart.totalPrice}</span><br>
    
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


