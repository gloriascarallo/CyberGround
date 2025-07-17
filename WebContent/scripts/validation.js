const namePattern=/^[A-z]{1,20}$/; // nome utente, 1-20 caratteri
const usernamePattern=/^\w{1,45}$/; // username, 1-45 caratteri
const passwordPattern=/^\w{5,20}$/; // password, 5-20 caratteri
const telephonePattern=/^[0-9]{10}$/; // numero di telefono, formato ##########
const emailPattern=/^\S+@\S+\.\S+$/; // email, formato username@domain.ext
const addressPattern = /^(?! )[A-Za-z0-9_ ]{1,45}(?<! )$/; // indirizzo, sequenze di lettere o numeri separati da spazi, da 1 a 45 caratteri
const PANPattern=/^[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}$/; // pan, formato ####-####-####-####
const ScadenzaPattern = /^(0[1-9]|1[0-2])\/([0-9]{2})$/; // data di scandenza, formato ##-##
const CVCPattern=/^[0-9]{3,4}$/; // cvc, formato ### o ####
const IDPattern = /^\d+$/;
const errorNameMessage="Devi inserire almeno una lettera e non devi superare 20 lettere";
const errorUsernameMessage="Un username valido deve contenere almeno un carattere e non deve superarne 45";
const errorPasswordMessage="Una password valida deve contenere minimo 5 caratteri e al massimo 20";
const errorTelephoneMessage="Un numero di telefono valido deve avere formato ##########";
const errorEmailMessage="Un'email valida deve avere formato username@domain.ext";
const errorAddressMessage="Un indirizzo valido deve essere formato da sequenze di lettere o numeri separati da spazi"
const errorPANMessage="Un metodo di pagamento valido deve avere formato ####-####-####-####";
const errorScadenzaMessage="Una data di scadenza valida deve avere formato ##/## e una data di scadenza superiore all'attuale";
const errorCVCMessage="Un CVC valido deve avere formato ### o ####";
const errorIDMessage = "L'ID ordine deve contenere solo numeri";
const errorScadenzaPassataMessage = "La data di scadenza non può essere nel passato";

function validateScadenza(value) {
	const match = value.match(ScadenzaPattern);
	    if (!match) return false;

	    const month = parseInt(match[1], 10);
	    const year = parseInt(match[2], 10);

	    // Mese deve essere tra 1 e 12
	    if (month < 1 || month > 12) return false;

	    const now = new Date();
	    const currentYear = now.getFullYear() % 100; // Ultime due cifre dell’anno
	    const currentMonth = now.getMonth() + 1;     // JavaScript: 0 = gennaio, quindi +1

	    // Scadenza nel passato?
	    if (year < currentYear || (year === currentYear && month < currentMonth)) {
	        return false;
	    }

	    return true;
}



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
	button.addEventListener("click", function() {div.parentNode.removeChild(div);});
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
	button.addEventListener("click", function() {div.parentNode.removeChild(div);});
	div.appendChild(button);
	
	
	
inputPAN.addEventListener("change", function() {validateFormElement(inputPAN, PANPattern, spanPAN, errorPANMessage);});
inputScadenza.addEventListener("change", function() {
    const validPattern = validateFormElement(inputScadenza, ScadenzaPattern, spanScadenza, errorScadenzaMessage);
    if (validPattern) {
        if (!validateScadenza(inputScadenza.value)) {
            spanScadenza.style.color = "red";
            spanScadenza.innerHTML = errorScadenzaMessage;
        } else {
            spanScadenza.style.color = "black";
            spanScadenza.innerHTML = "";
        }
    }
});
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
	let valid = true;
	
	const usernameEl = document.getElementsByName("username")[0];
	const nameEl = document.getElementsByName("name")[0];
	const lastNameEl = document.getElementsByName("lastName")[0];
	const telephoneEl = document.getElementsByName("telephone")[0];
	const emailEl = document.getElementsByName("email")[0];
	const passwordEl = document.getElementsByName("password")[0];
	const addressesEl = document.getElementsByName("address");
	const panEl = document.getElementsByName("methodPaymentPAN");
	const expirationDateEl = document.getElementsByName("methodPaymentScadenza");
	const cvcEl = document.getElementsByName("methodPaymentCVC");

	
		
		for (let i = 0; i < addressesEl.length; i++) {
				const input = addressesEl[i];
				const suffix = input.id.replace("address", "");  
				const span = document.getElementById("errorAddress" + suffix);
				if (!validateFormElement(input, addressPattern, span, errorAddressMessage)) {
					valid = false;
				}
			}
		
	

		for (let i = 0; i < panEl.length; i++) {
			const input = panEl[i];
			const suffix = input.id.replace("methodPaymentPAN", "");
			const span = document.getElementById("errorPAN" + suffix);
			if (!validateFormElement(input, PANPattern, span, errorPANMessage)) {
				valid = false;
			}
		}
	

		for (let i = 0; i < expirationDateEl.length; i++) {
			const input = expirationDateEl[i];
			const suffix = input.id.replace("methodPaymentScadenza", "");
			const span = document.getElementById("errorScadenza" + suffix);
			if (!validateFormElement(input, ScadenzaPattern, span, errorScadenzaMessage) || (!validateScadenza(input.value))) {
				
			valid = false;
			}
		}
	

		for (let i = 0; i < cvcEl.length; i++) {
			const input = cvcEl[i];
			const suffix = input.id.replace("methodPaymentCVC", "");
			const span = document.getElementById("errorCVC" + suffix);
			if (!validateFormElement(input, CVCPattern, span, errorCVCMessage)) {
				valid = false;
			}
		}
		
		if (!validateFormElement(nameEl, namePattern, document.getElementById('errorName'), errorNameMessage)) {
		    valid = false;
		  }
		  if (!validateFormElement(usernameEl, usernamePattern, document.getElementById('errorUsername'), errorUsernameMessage)) {
		    valid = false;
		  }
		  if (!validateFormElement(lastNameEl, namePattern, document.getElementById('errorLastName'), errorNameMessage)) {
		    valid = false;
		  }
		  if (!validateFormElement(telephoneEl, telephonePattern, document.getElementById('errorTelephone'), errorTelephoneMessage)) {
		    valid = false;
		  }
		  if (!validateFormElement(emailEl, emailPattern, document.getElementById('errorEmail'), errorEmailMessage)) {
		    valid = false;
		  }
		  if (!validateFormElement(passwordEl, passwordPattern, document.getElementById('errorPassword'), errorPasswordMessage)) {
		    valid = false;
		  }
	

	return (
		valid
	);
}

window.addEventListener("DOMContentLoaded", () => {
  const scadenzaInput = document.getElementById("methodPaymentScadenza1");
  const scadenzaSpan = document.getElementById("errorScadenza1");

  if (scadenzaInput && scadenzaSpan) {
    scadenzaInput.addEventListener("change", function () {
      // Valida pattern
      const isValid = validateFormElement(scadenzaInput, ScadenzaPattern, scadenzaSpan, errorScadenzaMessage);

      // Se pattern valido, controlla se data è nel passato
      if (isValid) {
        if (!validateScadenza(scadenzaInput.value)) {
          scadenzaSpan.style.color = "red";
          scadenzaSpan.innerText = errorScadenzaPassataMessage;
        } else {
          scadenzaSpan.style.color = "black";
          scadenzaSpan.innerText = "";
        }
      }
    });
  }
});

	
	function validateAdd_addressForm() {
		
		const addressEl=document.getElementsByName("address")[0];
		
		return (validateFormElement(addressEl, addressPattern, document.getElementById('errorAddress'), errorAddressMessage));
		
	}
	
	function validateAdd_method_paymentForm() {
		
		const panEl=document.getElementsByName("PAN")[0];
		const ScadenzaEl=document.getElementsByName("Scadenza")[0];
		const cvcEl=document.getElementsByName("CVC")[0];
		const spanScadenza = document.getElementById("errorScadenza");

		    const validPattern = validateFormElement(ScadenzaEl, ScadenzaPattern, spanScadenza, errorScadenzaMessage);

		    if (validPattern && !validateScadenza(ScadenzaEl.value)) {
		        spanScadenza.style.color = "red";
		        spanScadenza.innerHTML = errorScadenzaMessage;
		        return false;
		    }

		    return (
		        validateFormElement(panEl, PANPattern, document.getElementById("errorPAN"), errorPANMessage) &&
		        validPattern &&
		        validateFormElement(cvcEl, CVCPattern, document.getElementById("errorCVC"), errorCVCMessage)
		    );
		
		
		
	}
	
	function validateProduct_refundForm() {
	    let valid = true;

	    const addressEl = document.getElementById("ritiro");
	    const spanAddress = document.getElementById("errorAddress");
	    if (!validateFormElement(addressEl, addressPattern, spanAddress, errorAddressMessage)) {
	        valid = false;
	    }

	    const panEl = document.getElementById("PAN");
	    const spanPan = document.getElementById("errorPAN");
	    if (!validateFormElement(panEl, PANPattern, spanPan, errorPANMessage)) {
	        valid = false;
	    }

	    const scadenzaEl = document.getElementById("methodPaymentScadenza1");
	    const spanScadenza = document.getElementById("errorScadenza1");
	    const validPattern = validateFormElement(scadenzaEl, ScadenzaPattern, spanScadenza, errorScadenzaMessage);
	    if (validPattern) {
	        if (!validateScadenza(scadenzaEl.value)) {
	            spanScadenza.style.color = "red";
	            spanScadenza.innerHTML = errorScadenzaPassataMessage;
	            valid = false;
	        } else {
	            spanScadenza.innerHTML = "";
	        }
	    } else {
	        valid = false;
	    }

	    const cvcEl = document.getElementById("CVC");
	    const spanCvc = document.getElementById("errorCVC");
	    if (!validateFormElement(cvcEl, CVCPattern, spanCvc, errorCVCMessage)) {
	        valid = false;
	    }

	    return valid;
	}

	
	function validateRefundForm() {
		  const idInput = document.getElementById('id');
		  const errorSpan = document.getElementById('errorID');
		  return validateFormElement(idInput, IDPattern, errorSpan, errorIDMessage);
}





