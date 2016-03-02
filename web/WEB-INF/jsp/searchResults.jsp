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
                <div class="col-lg-12 page-header" style="margin-top: 0 !important; font-size: 2em !important;">
                    <div class="col-lg-2"></div>
                    <div class="col-lg-2">Name</div>
                    <div class="col-lg-2">MSRP</div>
                    <div class="col-lg-4">Description</div>
                    <div class="col-lg-1">View</div>
                    <%  if(session.getAttribute("userID")!=null){  %>
                    <div class="col-lg-1">Edit</div>
                    <% } %>
                </div>
                <c:forEach items="${queryResults}" var="product" varStatus="loop">
                <div class="col-lg-12 page-header" style="margin-top: 0 !important; font-size: 1em !important;">
                    <div class="col-lg-2 srch-img"><img class="img-responsive img" src="image?pid=${product.productID}" alt=""></div>
                        <div class="col-lg-2">${product.productName}</div>
                        <div class="col-lg-2">${product.MSRP}</div>
                        <div class="col-lg-4" >${product.description}</div>
                        <div class="col-lg-1"><a href="productRetrieve?pid=${product.productID}" class="label label-danger" rel="tooltip" title="View Product"> View Product</a></div>
                        <%  if(session.getAttribute("userID")!=null){  %>
                            <div class="col-lg-1">
                                <a href="editBranchProduct.jsp" class="label label-info" rel="tooltip" title="View Product"> Edit Product</a>
                            </div>
                        <%}%>                    
                </div>
                        </c:forEach>
            
            </div>
            
        </div>
       
        
    </body>
</html>
