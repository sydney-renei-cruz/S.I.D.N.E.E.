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
        <link type="text/css" rel="stylesheet" href="css/main.css"/>
        <title>S.I.D.N.E.E.</title>
    </head>
    <body>
        <%@include file="navbar.jsp" %>
        <div class="container ">
            <div class="col-lg-12 page-header text-center">
                Search Results
            </div>
            <div class="col-lg-12">
            <table>
                
                    <tr>
                        <th>Name</th>
                        <th>Name</th>
                        <th>Name</th>
                    </tr>
               
            </table>
            </div>
            <table>
            <tr>
                <th>Name</th>
                <th>MSRP</th>
                <th>Description</th>
            </tr>
        <c:forEach items="${queryResults}" var="product" varStatus="loop">
            <tr>
                <td>${product.productName}</td>
                <td>${product.MSRP}</td>
                <td>${product.description}</td>
            </tr>
        </c:forEach>
        </table>
            
        </div>
        <br>
        <br>
        
    </body>
</html>
