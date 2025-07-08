function filterByPrice() {
  const idCart = document.querySelector('input[name="idCart"]').value;
  const priceMin = document.getElementById("priceMin").value;
  const priceMax = document.getElementById("priceMax").value;

  const url = `/Filter_cart_by?action=price&idCart=${idCart}&priceMin=${priceMin}&priceMax=${priceMax}`;
  sendAjaxRequest(url);
}

function filterByDate() {
  const idCart = document.querySelector('input[name="idCart"]').value;
  const dateAdded = document.getElementById("dateAdded").value;

  const url = `/Filter_cart_by?action=date&idCart=${idCart}&dateAdded=${dateAdded}`;
  sendAjaxRequest(url);
}

function sendAjaxRequest(url) {
  const xhr = new XMLHttpRequest();
  xhr.open("GET", url, true);
  xhr.setRequestHeader("Accept", "application/json");

  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
      const response = JSON.parse(xhr.responseText);
      displayResults(response);
    }
  };

  xhr.send();
}

function displayResults(data) {
  const container = document.getElementById("results");

  if (data.error) {
    container.innerHTML = `<p style="color: red;">${data.error}</p>`;
    return;
  }

  if (!data.products || data.products.length === 0) {
    container.innerHTML = "<p>Nessun prodotto trovato.</p>";
    return;
  }

  let html = "<table border='1' cellpadding='8'><tr><th>Nome</th><th>Prezzo</th><th>Data Aggiunta</th></tr>";

  data.products.forEach(product => {
    html += `<tr>
               <td>${product.name}</td>
               <td>${product.price.toFixed(2)}</td>
			   <td>${product.quantity}<td>
               <td>${product.dateAdded}</td>
	
             </tr>`;
  });

  html += "</table>";
  container.innerHTML = html;
  }