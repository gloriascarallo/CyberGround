const namePattern=/^[A-z]{1, 20}+$/g;
const usernamePattern=/^\w{1, 45}+$/g;
const passwordPattern=/^\w{5, 20}+$/g;
const telephonePattern=/^([0-9]{10})$/g;
const emailPattern=/^\S+@\S+\.\S+$/g;
const addressPattern=/^\w+(\s\w+)+$/g;
const PANPattern=/^[0-9]{4}-[0-9]{4}-{0-9}{4}$/g;
const ScadenzaPattern=/^[0-9]{2}\/\[0-9]{2}$/g;
const CVCPattern=/^[0-9]{3-4}$/g;
const errorNameMessage="Devi inserire almeno una lettera e non devi superare 20 lettere";
const errorUsernameMessage="Un username valido deve contenere almeno un carattere e non deve superarne 45";
const errorPasswordMessage="Una password valida deve contenere minimo 5 caratteri e al massimo 20";
const errorTelephoneMessage="Un numero di telefono valido deve avere formato ##########";
const errorEmailMessage="Un'email valida deve avere formato username@domain.ext";
const errorAddressMessage="Un indirizzo valido deve essere formato da sequenze di lettere o numeri separati da spazi"
const errorPANMessage="Un metodo di pagamento valido deve avere formato ####-####-####-####";
const errorScadenzaMessage="Una data di scadenza valida deve avere formato ##/##";
const errorCVCMessage="Un CVC valido deve avere formato ### o ####";


function validateFormElement(formElement, pattern, error, span) {
	
	
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
	input.name="address" + countAddress;
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
	
	
input.addEventListener("change", validateFormElement(input, addressPattern, span, errorAddressMessage));
count++;
container.appendChild(div);
	
	
	
	
}

let countMethodPayment=2;

function addMethodPayment() {
	
	
	let container=document.getElementById("methodsPayment");
	let div=document.createElement("div");
	div.id="methodPaymentNum" + countMethodPayment;
	
	let label=document.createElement("label");
	label.htmlFor="methodPaymentPAN" + countMethodPayment;
	label.appendChild(document.createTextNode("Inserisci un indirizzo: "));
	div.appendChild(label);
	
	let inputPAN=document.createElement("input");
	inputPAN.type="text";
	inputPAN.name="methodPaymentPAN" + countMethodPayment;
	inputPAN.id="methodPaymentPAN" + countMethodPayment;
	inputPAN.placeholder="####-####-####-####";
	div.appendChild(inputPAN);
	
	let spanPAN=document.createElement("span");
	span.id="errorPAN" + countMethodPayment;
	div.appendChild(spanPAN);
		
	let inputScadenza=document.createElement("input");
    inputScadenza.type="text";
	inputScadenza.name="methodPaymentScadenza" + countMethodPayment;
	inputScadenza.id="methodPaymentScadenza" + countMethodPayment;
	inputScadenza.placeholder="##/##";
	div.appendChild(inputScadenza);
		
	let spanScadenza=document.createElement("span");
	span.id="errorScadenza" + countMethodPayment;
	div.appendChild(spanScadenza);
		
	let inputCVC=document.createElement("input");
	inputCVC.type="text";
	inputCVC.name="methodPaymentCVC" + countMethodPayment;
	inputCVC.id="methodPaymentCVC" + countMethodPayment;
	inputCVC.placeholder="### or ####";
	div.appendChild(inputCVC);
	
	let spanCVC=document.createElement("span");
	span.id="errorCVC" + countMethodPayment;
	div.appendChild(spanCVC);
				
	let button=document.createElement("input");
	button.type="button";
	button.value="-";
	button.addEventListener("click", function() {div.parentNode.removeChild(div); countMethodPayment--;});
	div.appendChild(button);
	
	
	
inputPAN.addEventListener("change", validateFormElement(inputPAN, PANPattern, spanPAN, errorPANMessage));
inputScadenza.addEventListener("change", validateFormElement(inputScadenza, ScadenzaPattern, spanScadenza, errorScadenzaMessage));
inputCVC.addEventListener("change", validateFormElement(inputCVC, CVCPattern, spanCVC, errorCVCMessage));
count++;
container.appendChild(div);
	

	
}

function validateRegistrationForm() {
	
	
	
	
	
}




