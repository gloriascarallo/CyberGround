const idPattern = /^[0-9]+$/;
const errorIdMessage = "Inserisci un ID numerico valido. (solo numeri interi positivi)";

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
  
  function validateIdProduct(event) {
	const idInput = document.getElementById("idProduct");
		  if (idInput && idInput.value !== "") {
		    const span = displayError(idInput);
		    if (!idPattern.test(idInput.value)) {
		      span.textContent = errorIdMessage;
		      span.style.color = "red";
		      valid = false;
		    } else {
		      span.textContent = "";
		    }
		  }
		  
		  // Se associata a evento submit
		    if (event && !valid) {
		      event.preventDefault();
		    }

		    return !!valid;
		   
  }