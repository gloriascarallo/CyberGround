<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Consult us Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Consult_us.css?v=3"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Layout.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<%@ include file="/includes/header.jsp" %>

<h2>Pagina di Consulenza</h2>

<div id="container">
<p>Per qualsiasi problema puoi contattare il nostro operatore a questi recapiti:</p>

<h3>Alfredo Goffredo</h3>

<p id="recapiti">Telefono: 333-8475394 <br>
   Email: agoffredo@gmail.com</p>
</div>
<%@ include file="/includes/footer.jsp" %>
</body>
</html>