<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bean.ProductBean" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%
request.getAttribute("products");
request.getAttribute("category"); 
%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Category: ${category}</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Category.css?v=2"/>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Layout.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<jsp:include page="/includes/header.jsp" />

<main class="category-container">

  <h2>Categoria: ${category}</h2>

  <!-- Messaggio di errore -->
 <%
String errors=(String)request.getAttribute("errors");  
if(errors!=null && !errors.equals("")) {%>
	
	<div class="error"><%=errors%></div> <% 
}
%>

  <!-- Lista prodotti -->
  <div class="products-grid">
  <c:forEach var="product" items="${products}">
    <div class="product-card">
      
    <a href="${pageContext.request.contextPath}/Product?idProduct=${product.idProduct}&toRedirect=/guest/view/category.jsp">
<img src="${product.imagePath}" alt="${product.name}" class="product-image"/>
</a>
      <div class="product-info">
        <h3>${product.name}</h3>
        <p>${product.description}</p>
        <p><strong>Prezzo:</strong> € ${product.price}</p>
      </div>
    </div>
  </c:forEach>
 </div>
</main>

<jsp:include page="/includes/footer.jsp" />

</body>
</html>
