<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pagina di registrazione</title>
<script src="validation.js" type="text/javascript"></script>
</head>
<body>

<%
String errors=(String)request.getAttribute("errors");
if(!errors.equals("")) {%> <div id="error"><%=errors %></div>

<% } %>

<form name="registrationForm" id="registrationForm" action="Registration" method="post">

<div>
<label for="name">Inserisci nome: </label>
<input type="text" name="name" id="name" onchange="validateFormElement(this, namePattern, document.getElementById('errorName'), errorNameMessage)" placeholder="Inserisci il tuo nome"><span id="errorName"></span>
</div>

<div>
<label for="lastName">Inserisci cognome: </label>
<input type="text" name="lastName" id="lastName" onchange="validateFormElement(this, namePattern, document.getElementById('errorLastName'), errorNameMessage)" placeholder="Inserisci il tuo cognome"><span id="errorLastName"></span>
</div>

<div>
<label for="telephone">Inserisci numero di telefono: </label>
<input type="text" name="telephone" id="telephone" placeholder="##########" onchange="validateFormElement(this, telephonePattern, document.getElementById('errorTelephone'), errorTelephoneMessage"><span id="errorTelephone"></span>
</div>

<div>s
<label for="email">Inserisci email: </label>
<input type="text" name="email" id="email" onchange="validateFormElement(this, emailPattern, document.getElementById('errorEmail'), errorEmailMessage" placeholder="username@domain.ext"><span id="errorEmail"></span>
</div>

<div id="addresses">
<div id="addressNum1">
<label for="address1"> Inserisci un indirizzo (o più indirizzi): </label>
<input type="text" name="address" id="address1" onchange="validateFormElement(this, addressPattern, document.getElementById('errorAddress1'), errorAddressMessage" placeholder="Inserisci il tuo indirizzo"><input type="button" value="+" onclick="addAddress()"></input>
<span id="errorAddress1"></span>
</div>
</div>

<div id="methodsPayment">
<div id="methodPaymentNum1">
<label for="methodPaymentPAN1"> Inserisci un metodo di pagamento (o più metodi di pagamento): </label>
<input type="text" name="methodPaymentPAN" id="methodPaymentPAN1" onchange="validateFormElement(this, PANPattern, document.getElementById('errorPAN1'), errorPANMessage" placeholder="####-####-####-####">
<span id="errorPAN1"></span>
<input type="text" name="methodPaymentScadenza" id="methodPaymentScadenza1" onchange="validateFormElement(this, ScadenzaPattern, document.getElementById('errorScadenza1'), errorScadenzaMessage" placeholder="##/##">
<span id="errorScadenza1"></span>
<input type="text" name="methodPaymentCVC" id="methodPaymentCVC1" onchange="validateFormElement(this, CVCPattern, document.getElementById('errorCVC1'), errorCVCMessage" placeholder="### or ####"><input type="button" value="+" onclick="addMethodPayment()"></input>
<span id="errorCVC1"></span>

</div>
</div>

<div>
<label for="username">Inserisci username: </label>
<input type="text" name="username" id="username" onchange="validateFormElement(this, namePattern, document.getElementById('errorUsername'), errorNameMessage)" placeholder="Inserisci il tuo username"><span id="errorUsername"></span>
</div>

<div>
<label for="password">Inserisci password: </label>
<input type="password" name="password" id="password" onchange="validateFormElement(this, passwordPattern, document.getElementById('errorPassword'), errorPasswordMessage)" placeholder="Inserisci la tua password"><span id="errorPassword"></span>
</div>

<input type="submit" value="Submit" onclick="return validateRegistrationForm()">
<input type="reset" value="Reset">

</form>
</body>
</html>