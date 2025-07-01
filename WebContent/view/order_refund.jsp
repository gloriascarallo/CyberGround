<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<%@page import="bean.OrderBean" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<% OrderBean order=(OrderBean)request.getAttribute("order");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:forEach var="product_inorder" items="${order}">
<a href="${pageContext.request.contextPath}/Product_refund">
    <img src="${product_inordert.product.imagePath}" alt="Product image" />
    </a>
    Nome: ${product_inorder.product.name}<br>
    Prezzo: ${product_inorder.SubTotal}<br>
    
   
    
   
</c:forEach>


</body>
</html>