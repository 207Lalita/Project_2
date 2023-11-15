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


if (isChecked) {

    if (quantityParam != null) {
        String quantityParamValue = request.getParameter(quantityParam);

       
        if (quantityParamValue != null && quantityParamValue.matches("\\d{1,}")) {
            int quantity = Integer.parseInt(quantityParamValue);
            shoppingcart.addProduct(product, quantity);
        } 
}


       
        response.sendRedirect("DVDCartList.jsp");
    }
        }
    }
}




