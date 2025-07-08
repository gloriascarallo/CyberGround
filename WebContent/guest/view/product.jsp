<%@page import="bean.ProductBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
ProductBean pr = (ProductBean)request.getAttribute("product");
%>

<html>
<head>
<meta charset="UTF-8">
<title>Product Page</title>
</head>
<body>

<a href="${pageContext.request.contextPath}/Product">
    <img src="${pr.imagePath}" alt="Product image" />
    </a>
    <p>Nome: ${pr.name}</p>
    <p>Descrizione: ${pr.description}</p>
    <p>Prezzo: ${pr.price}</p>
    <p>Fornitore: ${pr.supplier}</p>
    <p>Quantità disponibili: ${pr.quantityAvailable}</p>
    

</body>
</html>