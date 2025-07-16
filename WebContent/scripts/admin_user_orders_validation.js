 document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("orderDateForm");
  if (!form) return;

  const dateX = document.getElementById("dateX");
  const dateY = document.getElementById("dateY");
  const errorX = document.getElementById("errorDateX");
  const errorY = document.getElementById("errorDateY");

  form.addEventListener("submit", function (event) {
    let valid = true;

    // Reset stile ed errori
    [errorX, errorY].forEach(span => {
      span.textContent = "";
      span.style.color = "red"; 
    });

    if (!dateX.value) {
      errorX.textContent = "Inserisci una data iniziale.";
      valid = false;
    }

    if (!dateY.value) {
      errorY.textContent = "Inserisci una data finale.";
      valid = false;
    }

    if (dateX.value && dateY.value) {
      const from = new Date(dateX.value);
      const to = new Date(dateY.value);
      if (from > to) {
        const msg = "La data iniziale non può essere successiva alla data finale.";
        errorX.textContent = msg;
        errorY.textContent = msg;
        valid = false;
      }
    }

    if (!valid) {
      event.preventDefault();
    }
  });
});
