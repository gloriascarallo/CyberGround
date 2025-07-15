  // Pattern base per alcuni campi testo (adattati a esigenze)
  const namePattern = /^.{0,45}$/; // nome prodotto 1-45 caratteri
  const pricePattern = /^(?!$)(\d+)(\.\d{1,2})?$/;  // ≥ 0, con decimali
  const discountPattern = /^(100(\.00?)?|[0-9]?[0-9](\.[0-9]{1,2})?)$/; // 0–100
  const datePattern = /^\d{4}-\d{2}-\d{2}$/; // formato YYYY-MM-DD
  const imagePattern = /^.+\.(jpg|jpeg|png|gif)$/i;
  const supplierPattern = /^[\w\s]{2,45}$/;  // lettere, numeri, underscore e spazi, da 2 a 45 caratteri
  const categoryPattern = /^[\w\s]{2,45}$/;  // lettere, numeri, underscore e spazi, da 2 a 45 caratteri
  const descriptionPattern = /^.{0,500}$/; // descrizione max 500 caratteri
  const quantityPattern = /^[0-9]+$/; // solo numeri interi
  const idPattern = /^[0-9]+$/;
  const yearPattern = /^(20[0-9]{2})$/;

  const errorProductNameMessage = "Il nome prodotto deve contenere da 1 a 45 caratteri.";
  const errorPriceMessage = "Il prezzo deve essere un numero positivo.";
  const errorDiscountMessage = "Lo sconto deve essere compreso tra 0 e 100.";
  const errorDateMessage = "La data deve essere valida.";
  const errorDescriptionMessage = "La descrizione non deve superare 500 caratteri.";
  const errorSupplierMessage = "Il nome del fornitore deve contenere tra 2 e 45 caratteri (solo lettere, numeri e spazi)";
  const errorCategoryMessage = "La categoria deve contenere tra 2 e 45 caratteri (solo lettere, numeri e spazi)";
  const errorQuantityMessage = "La quantità deve essere un numero intero positivo.";
  const errorIdMessage = "Inserisci un ID numerico valido. (solo numeri interi positivi)";
  const errorYearMessage = "Inserisci un anno compreso tra 2000 e 2100.";
  const errorImageMessage = "Il file immagine deve essere un formato valido (jpeg, png, gif).";
  
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
  
  function validateProductsForm(event) {
	let valid = true;

	  const supplierInput = document.getElementById("supplierInput");
	  const supplierSpan = document.getElementById("errorSupplier");
	  if (supplierInput) {
	    valid &= validateFormElement(supplierInput, supplierPattern, supplierSpan, errorSupplierMessage);
	  }

	  const nameInput = document.getElementById("name");
	  const nameSpan = document.getElementById("errorName");
	  if (nameInput) {
	    valid &= validateFormElement(nameInput, namePattern, nameSpan, errorProductNameMessage);
	  }

	  const priceMinInput = document.getElementById("min");
	  if (priceMinInput && priceMinInput.value !== "") {
	    const span = displayError(priceMinInput);
	    if (!pricePattern.test(priceMinInput.value)) {
	      span.textContent = errorPriceMessage;
	      span.style.color = "red";
	      valid = false;
	    } else {
	      span.textContent = "";
	    }
	  }

	  const priceMaxInput = document.getElementById("max");
	  if (priceMaxInput && priceMaxInput.value !== "") {
	    const span = displayError(priceMaxInput);
	    if (!pricePattern.test(priceMaxInput.value)) {
	      span.textContent = errorPriceMessage;
	      span.style.color = "red";
	      valid = false;
	    } else {
	      span.textContent = "";
	    }
	  }

	  const yearInput = document.getElementById("year");
	  if (yearInput && yearInput.value !== "") {
	    const span = displayError(yearInput);
	    if (!yearPattern.test(yearInput.value)) {
	      span.textContent = errorYearMessage;
	      span.style.color = "red";
	      valid = false;
	    } else {
	      span.textContent = "";
	    }
	  }

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

  function validateProductForm() {
    let form = document.querySelector(".product-form");

    let nameInput = form.elements["name"];
    let priceInput = form.elements["price"];
    let discountInput = form.elements["discountPercentage"];
    let dateInput = form.elements["dateExpirationDiscount"];
    let descriptionInput = form.elements["description"];
    let supplierInput = form.elements["supplier"];
    let categoryInput = form.elements["category"];
    let quantityInput = form.elements["quantityAvailable"];
    let imgInput = form.elements["productImgFile"];

    let valid = true;

    // Clear previous errors (optional: potresti aggiungere span di errore sotto ogni input)

    // Nome prodotto
    if (!validateFormElement(nameInput, namePattern, displayError(nameInput), errorProductNameMessage)) valid = false;

    // Prezzo (numero positivo)
    if (!(priceInput.value && !isNaN(priceInput.value) && Number(priceInput.value) >= 0)) {
      displayError(priceInput).innerHTML = errorPriceMessage;
      displayError(priceInput).style.color = "red";
      valid = false;
    } else {
      displayError(priceInput).innerHTML = "";
    }

    // Sconto (0-100)
    if (!(discountInput.value === "" || ( !isNaN(discountInput.value) && Number(discountInput.value) >= 0 && Number(discountInput.value) <= 100))) {
      displayError(discountInput).innerHTML = errorDiscountMessage;
      displayError(discountInput).style.color = "red";
      valid = false;
    } else {
      displayError(discountInput).innerHTML = "";
    }

    // Data sconto (opzionale ma valida se presente)
    if (dateInput.value !== "") {
      const dateVal = new Date(dateInput.value);
      if (!(dateVal instanceof Date && !isNaN(dateVal))) {
        displayError(dateInput).innerHTML = errorDateMessage;
        displayError(dateInput).style.color = "red";
        valid = false;
      } else {
        displayError(dateInput).innerHTML = "";
      }
    } else {
      displayError(dateInput).innerHTML = "";
    }

    // Descrizione
    if (!validateFormElement(descriptionInput, descriptionPattern, displayError(descriptionInput), errorDescriptionMessage)) valid = false;

    // Fornitore
    if (!validateFormElement(supplierInput, supplierPattern, displayError(supplierInput), errorSupplierMessage)) valid = false;

    // Categoria
    if (!validateFormElement(categoryInput, categoryPattern, displayError(categoryInput), errorCategoryMessage)) valid = false;

    // Quantità (intero positivo)
    if (!validateFormElement(quantityInput, quantityPattern, displayError(quantityInput), errorQuantityMessage) || Number(quantityInput.value) < 0) {
      displayError(quantityInput).innerHTML = errorQuantityMessage;
      displayError(quantityInput).style.color = "red";
      valid = false;
    } else {
      displayError(quantityInput).innerHTML = "";
    }

    // Controllo file immagine (opzionale)
    if (imgInput.files.length > 0) {
      const allowedTypes = ["image/jpeg", "image/png", "image/gif"];
      if (!allowedTypes.includes(imgInput.files[0].type)) {
        displayError(imgInput).innerHTML = errorImageMessage;
        displayError(imgInput).style.color = "red";
        valid = false;
      } else {
        displayError(imgInput).innerHTML = "";
      }
    } else {
      displayError(imgInput).innerHTML = "";
    }

    return valid;
  }

  // Funzione helper per mostrare messaggi di errore (usa un <span> subito dopo input o crealo)
  function displayError(input) {
    let errorSpan = input.nextElementSibling;
    if (!errorSpan || errorSpan.tagName.toLowerCase() !== "span") {
      errorSpan = document.createElement("span");
      input.parentNode.insertBefore(errorSpan, input.nextSibling);
    }
    return errorSpan;
  }

  // Aggiungo evento submit al form per validare prima di inviare
  document.querySelector(".product-form").addEventListener("submit", function(event) {
    if (!validateProductForm()) {
      event.preventDefault();
    }
  });
  
  