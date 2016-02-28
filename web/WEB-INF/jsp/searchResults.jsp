<%-- 
    Document   : searchResults
    Created on : Feb 25, 2016, 11:55:53 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <h1>Search: </h1>
        <form action="Search" method="POST">
            Query: <input type="search" name="query">
            <button type="submit"> Submit </button>
        </form>
        <br>
        <br>
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>MSRP</th>
                <th>Description</th>
            </tr>
        <c:forEach items="${queryResults}" var="product" varStatus="loop">
            <tr>
                <td>${product.productID}</td>
                <td>${product.productName}</td>
                <td>${product.MSRP}</td>
                <td>${product.description}</td>
            </tr>
        </c:forEach>
        </table>
    </body>
</html>
