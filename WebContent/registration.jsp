<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Page</title>
</head>
<body>

<%
String error=(String)request.getAttribute("error");
if(error!=null) {%> <div id="error"><%=error %></div>

<% } %>

<form name="loginForm" action="Login" method="post">

<div>
<label for="name">Insert name: </label>
<input type="text" name="name" onchange="validateFormElement(this, namePattern, document.getElementById('errorName'), errorNameMessage)"><span id="errorName"></span>
</div>

<div>
<label for="lastName">Insert last name: </label>
<input type="text" name="lastName" onchange="validateFormElement(this, namePattern, document.getElementById('errorLastName'), errorNameMessage)"><span id="errorLastName"></span>
</div>

<div>
<label for="telephone">Insert telephone number: </label>
<input type="text" name="telephone" placeholder="###-#######" onchange="validateFormElement(this, telephonePattern, document.getElementById('errorTelephone'), errorTelephoneMessage"><span id="errorTelephone"></span>
</div>

<div>
<label for="email">Insert email: </label>
<input type="text" name="email" onchange="validateFormElement(this, emailPattern, document.getElementById('errorEmail'), errorEmailMessage"><span id="errorEmail"></span>
</div>

<div id="addresses">
<div id="addressNum1">
<label for="address1"> Insert address (or more than one address): </label>
<input type="text" name="address1" onchange="validateFormElement(this, addressPattern, document.getElementById('errorAddress1'), errorAddressMessage" placeholder=""><input type="button" value="more addresses" onclick="addAddress()"></input>
<span id="errorAddress1"></span>
</div>
</div>

<div id="methodsPayment">
<div id="methodPaymentNum1">
<label for="methodPayment1"> Insert method of payment (or more than one): </label>
<input type="text" name="methodPayment1" onchange="validateFormElement(this, methodPaymentPattern, document.getElementById('errorMethodPayment1'), errorMethodPaymentMessage" placeholder=""><input type="button" value="more methods of payment" onclick="addMethodPayment()"></input>
<span id="errorMethodPayment1"></span>
</div>
</div>

<div>
<label for="username">Insert username: </label>
<input type="text" name="username" onchange="validateFormElement(this, namePattern, document.getElementById('errorUsername'), errorNameMessage)"><span id="errorUsername"></span>
</div>

<div>
<label for="password">Insert password: </label>
<input type="password" name="password" onchange="validateFormElement(this, passwordPattern, document.getElementById('errorPassword'), errorPasswordMessage)"><span id="errorPassword"></span>
</div>

<input type="submit" value="Submit" onclick="return validate()">
<input type="reset" value="Reset">

</form>
</body>
</html>