const namePattern=/^[A-z]{1,20}$/;
const usernamePattern=/^\w{1,45}$/;
const passwordPattern=/^\w{5,20}$/;
const telephonePattern=/^[0-9]{10}$/;
const emailPattern=/^\S+@\S+\.\S+$/;
const addressPattern=/^\w+(\s\w+)*$/g;
const PANPattern=/^[0-9]{4}-[0-9]{4}-[0-9]{4}$/;
const ScadenzaPattern=/^(0[1-9]|1[0-2])\/\d{2}$/;
const CVCPattern=/^[0-9]{3,4}$/;
const errorNameMessage="Devi inserire almeno una lettera e non devi superare 20 lettere";
const errorUsernameMessage="Un username valido deve contenere almeno un carattere e non deve superarne 45";
const errorPasswordMessage="Una password valida deve contenere minimo 5 caratteri e al massimo 20";
const errorTelephoneMessage="Un numero di telefono valido deve avere formato ##########";
const errorEmailMessage="Un'email valida deve avere formato username@domain.ext";
const errorAddressMessage="Un indirizzo valido deve essere formato da sequenze di lettere o numeri separati da spazi"
const errorPANMessage="Un metodo di pagamento valido deve avere formato ####-####-####-####";
const errorScadenzaMessage="Una data di scadenza valida deve avere formato ##/##";
const errorCVCMessage="Un CVC valido deve avere formato ### o ####";


function validateFormElement(formElement, pattern, span, error) {
	
	
	if(formElement.value.match(pattern)) {
		
		span.style.color="black";
		span.innerHTML="";
		return true;
		
	}
	
	span.style.color="red";
	span.innerHTML=error;
	return false;
	
}

let countAddress=2;

function addAddress() {
	
	
	let container=document.getElementById("addresses");
	let div=document.createElement("div");
	div.id="addressNum" + countAddress;
	
	let label=document.createElement("label");
	label.htmlFor="address" + countAddress;
	label.appendChild(document.createTextNode("Inserisci un indirizzo: "));
	div.appendChild(label);
	
	let input=document.createElement("input");
	input.type="text";
	input.name="address";
	input.id="address" + countAddress;
	input.placeholder="Inserisci il tuo indirizzo";
	div.appendChild(input);
	
	let button=document.createElement("input");
	button.type="button";
	button.value="-";
	button.addEventListener("click", function() {div.parentNode.removeChild(div); countAddress--;});
	div.appendChild(button);
	
	let span=document.createElement("span");
	span.id="errorAddress" + countAddress;
	div.appendChild(span);
	
	
input.addEventListener("change", function() {validateFormElement(input, addressPattern, span, errorAddressMessage);});
countAddress++;
container.appendChild(div);
	
	
	
	
}

let countMethodPayment=2;

function addMethodPayment() {
	
	
	let container=document.getElementById("methodsPayment");
	let div=document.createElement("div");
	div.id="methodPaymentNum" + countMethodPayment;
	
	let label=document.createElement("label");
	label.htmlFor="methodPaymentPAN" + countMethodPayment;
	label.appendChild(document.createTextNode("Inserisci un metodo di pagamento: "));
	div.appendChild(label);
	div.appendChild(document.createElement("br"));
	
	
	let inputPAN=document.createElement("input");
	inputPAN.type="text";
	inputPAN.name="methodPaymentPAN";
	inputPAN.id="methodPaymentPAN" + countMethodPayment;
	inputPAN.placeholder="####-####-####-####";
	div.appendChild(inputPAN);
	
	let spanPAN=document.createElement("span");
	spanPAN.id="errorPAN" + countMethodPayment;
	div.appendChild(spanPAN);
	div.appendChild(document.createElement("br"));
		
	let inputScadenza=document.createElement("input");
    inputScadenza.type="text";
	inputScadenza.name="methodPaymentScadenza";
	inputScadenza.id="methodPaymentScadenza" + countMethodPayment;
	inputScadenza.placeholder="##/##";
	div.appendChild(inputScadenza);
		
	let spanScadenza=document.createElement("span");
	spanScadenza.id="errorScadenza" + countMethodPayment;
	div.appendChild(spanScadenza);
	div.appendChild(document.createElement("br"));
		
	let inputCVC=document.createElement("input");
	inputCVC.type="text";
	inputCVC.name="methodPaymentCVC";
	inputCVC.id="methodPaymentCVC" + countMethodPayment;
	inputCVC.placeholder="### or ####";
	div.appendChild(inputCVC);
	
	let spanCVC=document.createElement("span");
	spanCVC.id="errorCVC" + countMethodPayment;
	div.appendChild(spanCVC);
	
				
	let button=document.createElement("input");
	button.type="button";
	button.value="-";
	button.addEventListener("click", function() {div.parentNode.removeChild(div); countMethodPayment--;});
	div.appendChild(button);
	
	
	
inputPAN.addEventListener("change", function() {validateFormElement(inputPAN, PANPattern, spanPAN, errorPANMessage);});
inputScadenza.addEventListener("change", function() {validateFormElement(inputScadenza, ScadenzaPattern, spanScadenza, errorScadenzaMessage);});
inputCVC.addEventListener("change", function() {validateFormElement(inputCVC, CVCPattern, spanCVC, errorCVCMessage);});
countMethodPayment++;
container.appendChild(div);
	

	
}

function validateLoginForm(){
	
	
	const usernameEl = document.getElementsByName("username")[0];
	  const passwordEl = document.getElementsByName("password")[0];
	  const validUsername = validateFormElement(usernameEl, usernamePattern, document.getElementById('errorUsername'), errorUsernameMessage);
	  const validPassword = validateFormElement(passwordEl, passwordPattern, document.getElementById('errorPassword'), errorPasswordMessage);
	  return validUsername && validPassword;
	
}
	
function validateRegistrationForm() {
	
	const usernameEl=document.getElementsByName("username")[0];
	const nameEl=document.getElementsByName("name")[0];
	const lastNameEl=document.getElementsByName("lastName")[0];
	const telephoneEl=document.getElementsByName("username")[0];
	const emailEl=document.getElementsByName("email")[0];
	const passwordEl=document.getElementsByName("password")[0];
	let addressesEl=document.getElementsByName("address");
	let panEl=document.getElementsByName("methodPaymentPAN");
	let expirationDateEl=document.getElementById("methodPaymentScadenza");
	let cvcEl=document.getElementById("methodPaymentCVC");
	
	for(i=0; i<addressesEl.lenght; i++) {
	
		if(!validateFormElement(addressesEl[i], addressPattern, document.getElementById("errorAddress" + (i+1)), errorAddressMessage));
		return false;
		
	}
	
	for(i=0; i<panEl.lenght; i++) {
		
			if(!validateFormElement(panEl[i], PANPattern, document.getElementById("errorPAN" + (i+1)), errorPANMessage));
			return false;
			
		}
		
		for(i=0; i<expirationDateEl.lenght; i++) {
				
					if(!validateFormElement(expirationDateEl[i], ScadenzaPattern, document.getElementById("errorScadenza" + (i+1)), errorScadenzaMessage));
					return false;
					
				}
				
				for(i=0; i<cvcEl.lenght; i++) {
						
							if(!validateFormElement(cvcEl[i], CVCPattern, document.getElementById("errorCVC" + (i+1)), errorCVCMessage));
							return false;
							
						}	
						
				return (validateFormElement(nameEl, namePattern, document.getElementById('errorName'), errorNameMessage) 
				&& validateFormElement(lastNameEl, namePattern, document.getElementById('errorLastName'))
				&& validateFormElement(telephoneEl, telephonePattern, document.getElementById('errorTelephone'), errorTelephoneMessage)
				&& validateFormElement(emailEl, emailPattern, document.getElementById('errorEmail'), errorEmailMessage)
				&& validateFormElement(usernameEl, usernamePattern, document.getElementById('errorUsername'), errorUsernameMessage)
				&& validateFormElement(passwordEl, passwordPattern, document.getElementById('errorPassword'), errorPasswordMessage))
				
	
}
	
	
	





