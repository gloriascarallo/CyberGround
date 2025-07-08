<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form id="filterForm">
  <input type="hidden" name="idCart" value="<% %>" /> <!-- Sostituisci con valore dinamico -->

  <label>Prezzo Min:</label>
  <input type="number" id="priceMin" step="0.01" />

  <label>Prezzo Max:</label>
  <input type="number" id="priceMax" step="0.01" />

  <button type="button" onclick="filterByPrice()">Filtra per Prezzo</button>

  <br><br>

  <label>Data Aggiunta:</label>
  <input type="date" id="dateAdded" />

  <button type="button" onclick="filterByDate()">Filtra per Data</button>
</form>

<div id="results" style="margin-top: 20px;"></div>
</body>
</html>