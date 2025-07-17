<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
 <%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
    <%
    request.setAttribute("nowTimestamp", System.currentTimeMillis());
	%>


<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Filter User Cart Page</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/User_cart.css?v=5" />
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Admin_Layout.css"/>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<%@ include file="/includes/admin_header.jsp" %>

  <div class="cart-container">
    <h2>Console di Filtraggio Carrello Utente</h2>
 <c:if test="${not empty errors}">
  <div class="error">${errors}</div>
</c:if>
    <div id="results"></div>

    <form id="filterFormPrice" action="${pageContext.request.contextPath}/Filter_cart_by">
      <input type="hidden" name="idCart" value="${idCart}" /> 
      <input type="hidden" name="action" value="price">

      <div class="filter-group">
        <label for="priceMin">Prezzo Min:</label>
        <input type="number" name="priceMin" id="priceMin" step="0.01" />
      </div>

      <div class="filter-group">
        <label for="priceMax">Prezzo Max:</label>
        <input type="number" name="priceMax" id="priceMax" step="0.01" />
      </div>

      <button type="submit" class="filter-btn">
        <i class="fas fa-euro-sign"></i> Filtra per Prezzo
      </button>
      </form>
      

      <hr />

<form id="filterFormDate" action="${pageContext.request.contextPath}/Filter_cart_by">
      <input type="hidden" name="idCart" value="${idCart}" /> 
      <input type="hidden" name="action" value="date">
 
      <div class="filter-group">
        <label for="dateAdded">Data Aggiunta:</label>
        <input type="date" id="dateAdded" name="dateAdded">
      </div>

      <button type="submit" class="filter-btn">
        <i class="fas fa-calendar-day"></i> Filtra per Data
      </button>
    </form>
    
    <hr />
    
    <c:forEach var="product_incart" items="${products_situatedin_cart}">
    <img src="${product_incart.product.imagePath}" alt="Product image" />
    <div id="product-${product_incart.product.idProduct}">    
    Nome: ${product_incart.product.name}<br>
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
    </div>
    </c:forEach>

  </div>
<%@ include file="/includes/admin_footer.jsp" %>
</body>

</html>
