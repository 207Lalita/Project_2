/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import model.Products;
import model.Shoppingcart;
import model.ShoppingcartPK;
import model.ShoppingcartTable;
import utilities.UpdatedShoppingCartList;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ACER
 */
@WebServlet("/ConfirmOrderController")
public class ConfirmOrderController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int checkUser = 1;

        synchronized(this.getServletContext()) {
            UpdatedShoppingCartList.finishUpdating(this.getServletContext(), checkUser);
            this.getServletContext().notifyAll(); 
        }

        double totalPrice  = 0;
        String totalText = "";

        ShoppingcartPK cartPK = (ShoppingcartPK) request.getSession().getAttribute("cartPK");
        List<Shoppingcart> movList = ShoppingcartTable.findListShoppingcartById(cartPK);
        for (Shoppingcart cart : movList) {
            Products mov = cart.getProducts();
            totalPrice += mov.getPrice() * cart.getQuantity();
        }

        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        totalText = "The total amount is $" + decimalFormat.format(totalPrice);
        
        request.setAttribute("totalText", totalText);
        request.getRequestDispatcher("RemoveShoppingcartController").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

