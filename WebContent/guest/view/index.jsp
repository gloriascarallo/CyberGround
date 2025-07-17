<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%
    request.setAttribute("nowTimestamp", System.currentTimeMillis());
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Index Page</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/Index.css?v=5" />
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Layout.css?v=2"/>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
<%@ include file="/includes/header.jsp" %>

  <!-- CATEGORIE -->
  <div class="category-bar">
    <form action="${pageContext.request.contextPath}/Category" method="post">
    <input type="hidden" name="category" value="Telefonia" />
    <button type="submit">
      <i class="fas fa-mobile-alt"></i> Telefonia
    </button>
  </form>

  <form action="${pageContext.request.contextPath}/Category" method="post">
    <input type="hidden" name="category" value="Computer" />
    <button type="submit">
      <i class="fas fa-laptop"></i> Computer
    </button>
  </form>

  <form action="${pageContext.request.contextPath}/Category" method="post">
    <input type="hidden" name="category" value="Console" />
    <button type="submit">
      <i class="fas fa-gamepad"></i> Console
    </button>
  </form>
  </div>

  <% String errors = (String)request.getAttribute("errors");
     if (errors != null && !errors.isEmpty()) { %>
    <div class="error"><%= errors %></div>
  <% } %>

<!-- Layout separato a due colonne -->
<div class="page-layout">

  <!-- Colonna sinistra: Filtri -->
  <section class="filters">
    <h2>Filtra prodotti</h2>
    
    <div style="position: relative;">
      <input type="text" id="nameInput" placeholder="Nome prodotto" autocomplete="off">
    </div>

<form action="${pageContext.request.contextPath}/Filter_by" method="get">
<input type="hidden" name="action" value="supplier">
    <input type="text" name="supplierInput" id="supplierInput" placeholder="Fornitore">
    <button type="submit">
        Cerca per fornitore
    </button>
    </form>

<form action="${pageContext.request.contextPath}/Filter_by" method="get">
<input type="hidden" name="action" value="price">
    <input type="text" name="priceMin" id="priceMin" placeholder="Prezzo minimo">
    <input type="text" name="priceMax" id="priceMax" placeholder="Prezzo massimo">
    <button type="submit">
        Cerca per prezzo
    </button>
    </form>

<form action="${pageContext.request.contextPath}/Filter_by" method="get">
<input type="hidden" name="action" value="yearUpload">
    <input type="text" name="yearUpload" id="yearUpload" placeholder="Anno di pubblicazione">
    <button type="submit">
        Cerca per anno
    </button>
</form>

    <form action="${pageContext.request.contextPath}/Discounts" method="get">
      <input type="text" name="discountPercentage" placeholder="Sconto in % (es. 20)">
      <button type="submit">Cerca prodotti scontati</button>
    </form>
  </section>

  <!-- Colonna destra: Risultati -->
  <section class="results">
  <div id="result">
    <c:choose>
      <c:when test="${not empty products}">
        <c:forEach var="product" items="${products}">
          <div class="product-card">
          <a href="${pageContext.request.contextPath}/Product?idProduct=${product.idProduct}">
          <img src="${product.imagePath}" alt="Immagine ${product.name}" />
        </a>
            <h3>${product.name}</h3>
            <c:choose>
  <c:when test="${product.discountPercentage != null && product.discountPercentage > 0 && (product.dateExpirationDiscount == null || product.dateExpirationDiscount.time>nowTimestamp)}">
    <p>
      <strong>Prezzo:</strong>
      <span class="prezzo_pieno">€ ${product.price}</span>
      <span class="prezzo_scontato">
        € <fmt:formatNumber value="${product.price - (product.price * product.discountPercentage / 100)}" type="number" maxFractionDigits="2"/>
      </span>
    </p>
  </c:when>
  <c:otherwise>
    <p><strong>Prezzo:</strong> € ${product.price}</p>
  </c:otherwise>
</c:choose>
            <p>Descrizione: ${product.description}</p>
            <p>Fornitore: ${product.supplier}</p>
            <p>Data di pubblicazione: ${product.dateUpload}</p>
          </div>
        </c:forEach>
      </c:when>
      <c:otherwise>
        <p>Nessun prodotto da mostrare.</p>
      </c:otherwise>
    </c:choose>
  </div>
</section>

</div>

<%@ include file="/includes/footer.jsp" %>
</body>
<script src="${pageContext.request.contextPath}/scripts/search_bar.js"></script>
</html>