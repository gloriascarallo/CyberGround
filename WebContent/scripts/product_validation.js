// Pattern base per alcuni campi testo
const namePattern = /^.{1,45}$/; // nome prodotto 1-45 caratteri (corretto 1 invece di 0)
const pricePattern = /^(?!$)(\d+)(\.\d{1,2})?$/;  // ≥ 0, con decimali
const discountPattern = /^(100(\.00?)?|[0-9]?[0-9](\.[0-9]{1,2})?)$/; // 0–100
const datePattern = /^\d{4}-\d{2}-\d{2}$/; // formato YYYY-MM-DD
const imagePattern = /^.+\.(jpg|jpeg|png|gif)$/i;
const supplierPattern = /^[\w\s]{2,45}$/;  // lettere, numeri, underscore e spazi, da 2 a 45 caratteri
const categoryPattern = /^[\w\s]{2,45}$/;  // lettere, numeri, underscore e spazi, da 2 a 45 caratteri
const descriptionPattern = /^.{0,500}$/; // descrizione max 500 caratteri
const quantityPattern = /^[0-9]+$/; // solo numeri interi positivi
const idPattern = /^[0-9]+$/;
const yearPattern = /^20\d{2}$/;

// Messaggi di errore
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

// Funzione helper per mostrare messaggi di errore 
function displayError(input) {
  let errorSpan = input.nextElementSibling;
  if (!errorSpan || errorSpan.tagName.toLowerCase() !== "span") {
    errorSpan = document.createElement("span");
    input.parentNode.insertBefore(errorSpan, input.nextSibling);
  }
  return errorSpan;
}

// Funzione generica di validazione
function validateFormElement(formElement, pattern, errorMessage) {
  const span = displayError(formElement);
  if (formElement.value.match(pattern)) {
    span.style.color = "black";
    span.textContent = "";
    return true;
  } else {
    span.style.color = "red";
    span.textContent = errorMessage;
    return false;
  }
}

// Funzione di validazione specifica per campi con condizioni particolari
function validatePriceField(input) {
  const span = displayError(input);
  if ((pricePattern.test(input.value) && Number(input.value) >= 0)) {
    span.textContent = "";
    return true;
  } else {
    span.style.color = "red";
    span.textContent = errorPriceMessage;
    return false;
  }
}

function validateDiscountField(input) {
  const span = displayError(input);
  if (
    input.value === "" || 
    ( !isNaN(input.value) && Number(input.value) >= 0 && Number(input.value) <= 100)
  ) {
    span.textContent = "";
    return true;
  } else {
    span.style.color = "red";
    span.textContent = errorDiscountMessage;
    return false;
  }
}

function validateDateField(input) {
  const span = displayError(input);
  if (input.value === "") {
    span.textContent = "";
    return true;
  }
  // Controllo formato base con regex
  if (!datePattern.test(input.value)) {
    span.style.color = "red";
    span.textContent = errorDateMessage;
    return false;
  }
  // Controllo data valida JS
  const dateVal = new Date(input.value);
  if (!(dateVal instanceof Date && !isNaN(dateVal))) {
    span.style.color = "red";
    span.textContent = errorDateMessage;
    return false;
  }
  span.textContent = "";
  return true;
}

function validateQuantityField(input) {
  const span = displayError(input);
  if (quantityPattern.test(input.value) && Number(input.value) >= 0) {
    span.textContent = "";
    return true;
  } else {
    span.style.color = "red";
    span.textContent = errorQuantityMessage;
    return false;
  }
}

function validateYearField(input) {
  const span = displayError(input);
  if (input.value === "") {
    span.textContent = "";
    return true;
  }
  if (yearPattern.test(input.value)) {
    const yearNum = Number(input.value);
    if (yearNum >= 2000 && yearNum <= 2100) {
      span.textContent = "";
      return true;
    }
  }
  span.style.color = "red";
  span.textContent = errorYearMessage;
  return false;
}

function validateImageField(input) {
  const span = displayError(input);
  if (input.files.length === 0) {
    span.textContent = "";
    return true;
  }
  const allowedTypes = ["image/jpeg", "image/png", "image/gif"];
  if (allowedTypes.includes(input.files[0].type)) {
    span.textContent = "";
    return true;
  } else {
    span.style.color = "red";
    span.textContent = errorImageMessage;
    return false;
  }
}

// Funzione principale per validare tutto al submit
function validateProductForm() {
  const form = document.querySelector(".product-form");

  let valid = true;

  valid = validateFormElement(form.elements["name"], namePattern, errorProductNameMessage) && valid;
  valid = validatePriceField(form.elements["price"]) && valid;
  valid = validateDiscountField(form.elements["discountPercentage"]) && valid;
  valid = validateDateField(form.elements["dateExpirationDiscount"]) && valid;
  valid = validateFormElement(form.elements["description"], descriptionPattern, errorDescriptionMessage) && valid;
  valid = validateFormElement(form.elements["supplier"], supplierPattern, errorSupplierMessage) && valid;
  valid = validateFormElement(form.elements["category"], categoryPattern, errorCategoryMessage) && valid;
  valid = validateQuantityField(form.elements["quantityAvailable"]) && valid;
  valid = validateImageField(form.elements["productImgFile"]) && valid;

  return valid;
}

// Aggiungo evento submit al form
document.querySelector(".product-form").addEventListener("submit", function(event) {
  if (!validateProductForm()) {
    event.preventDefault();
  }
});  

