<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="bean.ProductBean" %>
<%

ProductBean product=(ProductBean)request.getAttribute("product");

%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
Name: ${product.name}
</body>
</html>