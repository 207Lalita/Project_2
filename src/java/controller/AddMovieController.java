/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author ACER
 */
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Products;
import model.ProductsTable;
import model.Shoppingcart;

@WebServlet("/AddMovieController")
public class AddMovieController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Shoppingcart shoppingcart = (Shoppingcart) session.getAttribute("shoppingcart");

        if (shoppingcart == null) {
            shoppingcart = new Shoppingcart();
            session.setAttribute("shoppingcart", shoppingcart);
        }

        List<Products> movies = ProductsTable.findAllProducts();
        Iterator<Products> itr = movies.iterator();

        while (itr.hasNext()) {
            Products product = itr.next();
            String checkboxName = "selectedProducts";
            String quantityParam = "quantity" + product.getId();

            // Check if the checkbox is checked
            // Check if the checkbox is checked
String[] selectedProducts = request.getParameterValues(checkboxName);
boolean isChecked = false;

if (selectedProducts != null) {
    for (String selectedProduct : selectedProducts) {
        if (selectedProduct.equals(String.valueOf(product.getId()))) {
            isChecked = true;
            break;
        }
    }
}

// If checked, add the product to the shopping cart
if (isChecked) {
    // Check if quantityParam is not null
    if (quantityParam != null) {
        String quantityParamValue = request.getParameter(quantityParam);

        // Check if quantityParamValue is not null and has a valid format
        if (quantityParamValue != null && quantityParamValue.matches("\\d{1,}")) {
            int quantity = Integer.parseInt(quantityParamValue);
            shoppingcart.addProduct(product, quantity);
        } 
}


        // Redirect back to the ShoppingCart.jsp page
        response.sendRedirect("DVDCartList.jsp");
    }
        }
    }
}




