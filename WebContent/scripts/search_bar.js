document.addEventListener("DOMContentLoaded", function () {
  const nameInput = document.getElementById("nameInput");
  const suggestionsBox = document.createElement("div");
  suggestionsBox.id = "suggestionsBox";
  suggestionsBox.style.border = "1px solid #ccc";
  suggestionsBox.style.maxHeight = "150px";
  suggestionsBox.style.overflowY = "auto";
  suggestionsBox.style.position = "absolute";
  suggestionsBox.style.backgroundColor = "white";
  suggestionsBox.style.zIndex = "999";
  suggestionsBox.style.width = nameInput.offsetWidth + "px";
  suggestionsBox.style.display = "none";

  nameInput.parentNode.insertBefore(suggestionsBox, nameInput.nextSibling);

  nameInput.addEventListener("input", function () {
    const query = nameInput.value.trim();
    if (query.length < 2) {
      suggestionsBox.style.display = "none";
      return;
    }

    fetch(`${window.contextPath || ''}/CyberGround/Filter_by?action=name&name=${encodeURIComponent(query)}`)
      .then((res) => res.json())
      .then((data) => {
        suggestionsBox.innerHTML = "";
        if (data.products && data.products.length > 0) {
          data.products.forEach((product) => {
            const div = document.createElement("div");
            div.textContent = product.idProduct + " - " + product.name;
            div.style.padding = "5px";
            div.style.cursor = "pointer";
			div.addEventListener("click", () => {
			  window.location.href = `/CyberGround/Product?idProduct=${product.idProduct}`;
			});
            suggestionsBox.appendChild(div);
          });
          suggestionsBox.style.display = "block";
        } else {
          suggestionsBox.style.display = "none";
        }
      })
      .catch((err) => {
        console.error("Errore AJAX:", err);
        suggestionsBox.style.display = "none";
      });
  });

  // Chiudi suggerimenti se clicchi fuori
  document.addEventListener("click", (e) => {
    if (!suggestionsBox.contains(e.target) && e.target !== nameInput) {
      suggestionsBox.style.display = "none";
    }
  });
});
