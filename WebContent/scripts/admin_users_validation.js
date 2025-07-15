// Pattern per validazione
const usernamePattern = /^\w{0,45}$/; // Alfanumerici + underscore, 1-45 caratteri
const IDPattern = /^\d+$/; // Solo numeri interi

// Messaggi d'errore
const errorUsernameMessage = "L'username deve contenere da 1 a 45 caratteri alfanumerici o underscore.";
const errorIDMessage = "L'ID deve essere un numero intero positivo.";

// Funzione generica per validare un campo
function validateFormElement(input, pattern, errorSpan, errorMessage) {
  if (input.value.match(pattern)) {
    errorSpan.textContent = "";
    return true;
  } else {
    errorSpan.textContent = errorMessage;
    errorSpan.style.color = "red";
    return false;
  }
}

// Validazione filtro username
function validateUsernameForm(event) {
  const usernameInput = document.getElementById("username");
  const errorSpan = document.getElementById("errorUsername");

  const valid = validateFormElement(usernameInput, usernamePattern, errorSpan, errorUsernameMessage);
  if (!valid) event.preventDefault();
}

// Validazione filtro per ID
function validateIdForm(event) {
  const idInput = document.getElementById("id");
  const errorSpan = document.getElementById("errorID");

  const valid = validateFormElement(idInput, IDPattern, errorSpan, errorIDMessage);
  if (!valid) event.preventDefault();
}

// Aggiunge <span> per messaggi d’errore se non esistono
function prepareValidationSpans() {
  const usernameInput = document.getElementById("username");
  if (usernameInput && !document.getElementById("errorUsername")) {
    const span = document.createElement("span");
    span.id = "errorUsername";
    usernameInput.insertAdjacentElement("afterend", span);
  }

  const idInput = document.getElementById("id");
  if (idInput && !document.getElementById("errorID")) {
    const span = document.createElement("span");
    span.id = "errorID";
    idInput.insertAdjacentElement("afterend", span);
  }
}

// Collega le funzioni di validazione al submit
document.addEventListener("DOMContentLoaded", function () {
  prepareValidationSpans();

  const usernameForm = document.querySelector('form[action$="Filter_registeredusers_byUsername"]');
  const idForm = document.querySelector('form[action$="UploadUser"]');

  if (usernameForm) {
    usernameForm.addEventListener("submit", validateUsernameForm);
  }

  if (idForm) {
    idForm.addEventListener("submit", validateIdForm);
  }
});
