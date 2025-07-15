<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>

<%
     request.getAttribute("orders");
%>

<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Admin User Orders Page</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/User_orders.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Admin_Layout.css"/>
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<%@ include file="/includes/admin_header.jsp" %>

  <h2>Filtra Ordini per Intervallo di Date</h2>
  
  <div class="orders-container">
  
  <%
    String errors = (String) request.getAttribute("errors");
    if (errors != null && !errors.trim().isEmpty()) {
  %>
    <div class="error"><%= errors %></div>
  <% } %>

  <form action="${pageContext.request.contextPath}/Filter_orders_byDates" method="get" class="filter-form" id="orderDateForm">
    <label for="dateX">Da data:</label>
    <input type="date" id="dateX" name="dateX" />
    <span id="errorDateX" class="error-message"></span>

    <label for="dateY">A data:</label>
    <input type="date" id="dateY" name="dateY" />
    <span id="errorDateY" class="error-message"></span>

    <button type="submit">
      <i class="fas fa-filter"></i> Filtra
    </button>
  </form>

  <hr>

  <div id="orders-list">
    <c:if test="${not empty orders}">
      <h3>Ordini trovati:</h3>
      <table class="orders-table">
        <thead>
          <tr>
            <th>ID Ordine</th>
            <th>Data Acquisto</th>
            <th>Data Spedizione</th>
            <th>Data Consegna</th>
            <th>Totale</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="order" items="${orders}">
            <tr>
              <td>${order.idOrder}</td>
              <td>${order.datePurchase}</td>
              <td>${order.dateShipping}</td>
              <td>${order.dateDelivery}</td>
              <td>${order.totalOrder} €</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </c:if>
  </div>
</div>
<%@ include file="/includes/admin_footer.jsp" %>
</body>
<script src="${pageContext.request.contextPath}/scripts/admin_user_orders_validation.js" type="text/javascript"></script>
</html>