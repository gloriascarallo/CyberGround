const namePattern=/^[A-z]+$/g;
const telephonePattern=/^([0-9]{3}-[0-9]{7})$/g;
const emailPattern=/^\S+@\S+.\S+$/g;
const addressPattern=/^\w+(\s\w+)+$/g;
const methodPaymentPattern=/^[0-9]{4}-[0-9]{4}-{0-9}{4}$/g;
const errorNameMessage="Devi inserire almeno una lettera";
const errorTelephoneMessage="Un numero di telefono valido deve avere formato ###-#######";
const errorEmailMessage="Un'email valida deve avere formato username@domain.ext";
const errorAddressMessage="Un indirizzo valido deve essere formato da sequenze di lettere o numeri separati da spazi"
const errorMethodPaymentMessage="Un metodo di pagamento valido deve avere formato ####-####-####-####";

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
	label.htmlFor="methodPayment" + countMethodPayment;
	label.appendChild(document.createTextNode("Inserisci un indirizzo: "));
	div.appendChild(label);
	
	let input=document.createElement("input");
	input.type="text";
	input.name="methodPayment" + countMethodPayment;
	input.id="methodPayment" + countMethodPayment;
	input.placeholder="Inserisci il tuo indirizzo";
	div.appendChild(input);
	
	let button=document.createElement("input");
	button.type="button";
	button.value="-";
	button.addEventListener("click", function() {div.parentNode.removeChild(div); countMethodPayment--;});
	div.appendChild(button);
	
	let span=document.createElement("span");
	span.id="errorMethodPayment" + countMethodPayment;
	div.appendChild(span);
	
	
input.addEventListener("change", validateFormElement(input, methodPaymentPattern, span, errorMethodPaymentMessage));
count++;
container.appendChild(div);
	

	
}

function validate() {
	
	
	
	
	
}




