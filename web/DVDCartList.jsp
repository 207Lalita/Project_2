<%@page import="model.ProductsTable"%>
<%@page import="java.util.Iterator"%>
<%@page import="model.ShoppingcartPK"%>
<%@page import="model.Shoppingcart"%>
<%@page import="model.ShoppingcartTable"%>
<%@page import="java.util.Collections"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="model.Products" %>
<%@page import="java.util.List"%>

<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Shopping Cart</title>
</head>
<body>
    <jsp:useBean id="shoppingcart" class="model.Shoppingcart" scope="session"/>
    
    <%
    List<Products> movies = ProductsTable.findAllProducts();
    Iterator<Products> itr = movies.iterator();
    int total = 0;
    %>

    <form name="AddToCart" action="ConfirmOrderController" method="post">
        <center>
            <h1>Shopping Cart</h1>
            <table border="1">
                <tr>
                    <th>Movie name</th>
                    <th>Rating</th>
                    <th>Year</th>
                    <th>Price/Unit</th>
                    <th>Quantity</th>
                    <th>Price</th>
                </tr>
                
                <%
                    while(itr.hasNext()) {
                        Products product = itr.next();
                %>

                <tr>
                    <td><%= product.getMovie() %></td>
                    <td><%= product.getRating() %></td>
                    <td><%= product.getYearcreate() %></td>
                    <td><%= product.getPrice() %></td>
                    <td><%= shoppingcart.getQuantity() %></td>
                    <td><%= shoppingcart.getQuantity() * product.getPrice() %></td>
                </tr>

                <%
                    total += shoppingcart.getQuantity() * product.getPrice();
                }
                %>

                <tr>
                    <td colspan="5" align="right"><b>Total:</b></td>
                    <td><%= total %></td>
                </tr>
            </table>
            
            <a href="index_1.html"><input type="button" value="Back to homepage"></a>
            <a href="ShoppingCart.jsp"><input type="submit" value="Add to Cart"></a>
        </center>
    </form>
</body>
</html>



