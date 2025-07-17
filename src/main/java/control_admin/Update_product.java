package control_admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.ProductBean;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Objects;
import java.util.UUID;

import dao.ProductDaoDataSource;

/**
 * Servlet implementation class Update_product
 */
@WebServlet("/Update_product")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024,    // 1MB
	    maxFileSize = 1024 * 1024 * 5,      // 5MB
	    maxRequestSize = 1024 * 1024 * 10   // 10MB
	)
public class Update_product extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIRECTORY = "images";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update_product() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        for (String token : contentDisp.split(";")) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return null;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/admin/view/products.jsp");
		return;
	
}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String errors="";
		int id=Integer.parseInt(request.getParameter("idProduct"));
		ProductBean product=null;
		ProductDaoDataSource ds=new ProductDaoDataSource();
		boolean modified=false;
		try {
			product=ds.doRetrieveByKey(id);
			if(product==null) {
				
				errors += "Prodotto non trovato.<br>";
		        request.setAttribute("errors", errors);
		        request.getRequestDispatcher("/admin/view/update_product.jsp").forward(request, response); 
		        return;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			request.getRequestDispatcher("/error/500.html").forward(request, response);
			return;
		}
		
		String name=request.getParameter("name");
		double price=0;
		String priceStr = request.getParameter("price");
		String discountStr = request.getParameter("discountPercentage");
		Double discountPercentage = null;
		String expirationStr = request.getParameter("dateExpirationDiscount");
		Date dateExpirationDiscount=null;
		String description=request.getParameter("description");
		String supplier=request.getParameter("supplier");
		String category=request.getParameter("category");
		int quantityAvailable=1;
		String quantityAvailableStr=request.getParameter("quantityAvailable");
		String imageUrl=product.getImagePath();
		
		
		try {
            // Get the file part from the request
            Part filePart = request.getPart("productImgFile"); // Make sure your HTML input has name="productImgFile"
            if (filePart != null && filePart.getSize() > 0) {
                // Carico nuova immagine

                String fileName = getFileName(filePart);

                if (fileName == null || fileName.isEmpty()) {
                    errors += "Nessun file immagine selezionato.<br>";
                } else if (!filePart.getContentType().startsWith("image/")) {
                    errors += "Il file caricato non è un'immagine valida.<br>";
                } else {
                    String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) uploadDir.mkdirs();

                    String fileExtension = "";
                    int dotIndex = fileName.lastIndexOf('.');
                    if (dotIndex > 0) fileExtension = fileName.substring(dotIndex);

                    String uniqueFileName = UUID.randomUUID().toString() + fileExtension;
                    Path filePath = Paths.get(uploadPath, uniqueFileName);

                    try (InputStream input = filePart.getInputStream()) {
                        Files.copy(input, filePath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                    }

                    imageUrl = request.getContextPath() + "/" + UPLOAD_DIRECTORY + "/" + uniqueFileName;
                }
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
            errors += "Errore nel caricamento dell'immagine: " + e.getMessage() + "<br>";
        }

            
		
if(name==null || name.trim().equals("")) {
			
			errors+="Inserisci il nome del prodotto.<br>";
		} 

if (priceStr== null || priceStr.trim().isEmpty()) {
    errors += "Inserisci il prezzo.<br>";
} else {
    try {
       price = Double.parseDouble(priceStr);
        if (price < 0) {
            errors += "Il prezzo non può essere negativo.<br>";
        }
    } catch (NumberFormatException e) {
        errors += "Il prezzo deve essere un numero valido.<br>";
    }
}

if (discountStr != null && !discountStr.trim().isEmpty()) {
    try {
        discountPercentage = Double.parseDouble(discountStr);
    } catch (NumberFormatException e) {
        errors += "Il valore dello sconto non è valido.<br>";
    }
}


if (expirationStr != null && !expirationStr.trim().isEmpty()) {
    try {
        dateExpirationDiscount = Date.valueOf(expirationStr); 
    } catch (IllegalArgumentException e) {
        errors += "Formato data non valido.<br>";
    }
}

if(description==null || description.trim().equals("")) {
	
	errors+="Inserisci la descrizione<br>";
} 	


if(supplier==null || supplier.trim().equals("")) {
	
	errors+="Inserisci il fornitore<br>";
} 
		
	
	

if(category==null || category.trim().equals("")) {
	
	errors+="Inserisci la categoria.<br>";
} 

if (quantityAvailableStr== null || quantityAvailableStr.trim().isEmpty()) {
    errors += "Inserisci la quantità disponibile.<br>";
} else {
    try {
        quantityAvailable = Integer.parseInt(quantityAvailableStr);
        if (quantityAvailable <= 0) {
            errors += "La quantità disponibile non può essere minore di 1.<br>";
        }
    } catch (NumberFormatException e) {
        errors += "La quantità disponibile deve essere un numero valido.<br>";
    }
}
if(!errors.equals("")) {
	
	request.setAttribute("errors", errors);
	request.getRequestDispatcher("/admin/view/update_product.jsp").forward(request, response); 
	return;
}


if(!product.getName().equals(name)) {
	
	product.setName(name);
	modified=true;
	
}


if (Double.compare(product.getPrice(), price) != 0) {
    product.setPrice(price);
    modified = true;
}

if (!Objects.equals(product.getDiscountPercentage(), discountPercentage)) {
    product.setDiscountPercentage(discountPercentage);
    modified = true;
}

if (!Objects.equals(product.getDateExpirationDiscount(), dateExpirationDiscount)) {
    product.setDateExpirationDiscount(dateExpirationDiscount);
    modified = true;
}

if(!product.getCategoryName().equals(category)) {
	
	product.setCategoryName(category);
	modified=true;
	
}

if(product.getQuantityAvailable()!=(quantityAvailable)) {
	
	product.setQuantityAvailable(quantityAvailable);
	modified=true;
	
}

if(!product.getDescription().equals(description)) {
	
	product.setDescription(description);
	modified=true;
	
}

if(!product.getSupplier().equals(supplier)) {
	
	product.setSupplier(supplier);
	modified=true;
	
}

if (!Objects.equals(product.getImagePath(), imageUrl)) {
    product.setImagePath(imageUrl);
    modified = true;
}


if(modified) {
	
	try {
		ds.doUpdate(product);
	} catch (SQLException e) {
		
		e.printStackTrace();
		request.getRequestDispatcher("/error/500.html").forward(request, response);
		return;
		}
	}

response.sendRedirect(request.getContextPath()+"/admin/view/products.jsp"); 
return;

  }
}


