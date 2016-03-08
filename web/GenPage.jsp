<%-- 
    Document   : errorPage
    Created on : 02 2, 16, 9:08:08 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="WEB-INF/jsp/navbar.jsp"%>
        <link type="text/css" rel="stylesheet" href="css/main.css"/>
    </head>
    <body>
        <div class="col-lg-12">
            <h1 class="page-header text-center">
                ${title}
            </h1>
            <h2 class="text-center">
                <br>
                <br>
                ${msg}
                <br>
                <br>
            </h2>
            <a href="indexRetrieveProductNBranch" class="text-center go-back"><span class="glyphicon glyphicon-chevron-left"></span>Go back to home</a>
            <a href="allProductsRetrieve" class="text-center go-back"><span class="glyphicon glyphicon-chevron-left"></span>Go back to all products</a>
            <a href="allBranchesRetrieve" class="text-center go-back"><span class="glyphicon glyphicon-chevron-left"></span>Go back to all branches</a>
        </div>
    </body>
</html>
