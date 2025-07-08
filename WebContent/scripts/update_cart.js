function updateCartAjax(action, productId) {
    const params = "action=" + action + "&product_incartID=" + productId;
    loadAjaxDoc("/UpdateCart", "GET", params, function(request) {
        try {
            const response = JSON.parse(request.responseText);

            if (response.result === "OK") {
				if (action === "remove" || response.quantity === 0) {
				                    document.getElementById("product-" + productId).remove();
				                } else{
                const qty = response.quantity;
                const prodTotal = response.productTotal;
                const cartTotal = response.cartTotal;
 
                    document.getElementById("quantity-" + productId).textContent = qty;
                    document.getElementById("total-" + productId).textContent = prodTotal.toFixed(2);
                }

                document.getElementById("cart-total").textContent = cartTotal.toFixed(2);
            } else {
                alert("Errore: risposta non OK");
            }
        } catch (e) {
            alert("Errore nel parsing JSON:\n" + e.message);
        }
    });
}