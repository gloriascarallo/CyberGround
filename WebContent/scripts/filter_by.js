function loadProducts(params) {
  // Costruisco la query string dai parametri passati (es: {action:"supplier", supplier:"ACME"})
  let query = "?";
  for (let key in params) {
    query += encodeURIComponent(key) + "=" + encodeURIComponent(params[key]) + "&";
  }
  query = query.slice(0, -1); // tolgo l'ultimo &

  let xhr = new XMLHttpRequest();
  xhr.open("GET", "/Filter_by" + query, true);
  xhr.setRequestHeader("Accept", "application/json");

  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4) {
      if (xhr.status === 200) {
        try {
          let data = JSON.parse(xhr.responseText);
          if (data.error) {
            document.getElementById("result").innerHTML = "<p style='color:red;'>" + data.error + "</p>";
          } else if (!data.products || data.products.length === 0) {
            document.getElementById("result").innerHTML = "<p>Nessun prodotto trovato.</p>";
          } else {
            let html = "<table border='1'><tr><th>Nome</th><th>Prezzo</th><th>Anno</th><th>Fornitore</th></tr>";

            for (let i = 0; i < data.products.length; i++) {
              let p = data.products[i];
              html += "<tr>"
                   + "<td>" + p.name + "</td>"
                   + "<td>" + p.price + "</td>"
                   + "<td>" + p.dateUpload + "</td>"
                   + "<td>" + p.supplier + "</td>"
				   + "<td>" + p.imagePath + "</td>"
                   + "</tr>";
            }
            html += "</table>";
            document.getElementById("result").innerHTML = html;
          }
        } catch(e) {
          document.getElementById("result").innerHTML = "<p style='color:red;'>Errore nel parsing della risposta JSON.</p>";
        }
      } else {
        document.getElementById("result").innerHTML = "<p style='color:red;'>Errore nel caricamento dati dal server.</p>";
      }
    }
  };

  xhr.send();
}
