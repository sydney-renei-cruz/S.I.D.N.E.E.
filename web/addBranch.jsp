<%-- 
    Document   : addProduct
    Created on : 02 2, 16, 12:38:49 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link type="text/css" rel="stylesheet" href="css/main.css"/>
    </head>
    <body>
        <%@include file="WEB-INF/jsp/navbar.jsp" %>
        <div class="container text-center">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header"> ADD Branch </h1>
				</div>
			</div>
                        <form class="form-horizontal" role="form" style="font-size: 1.1em; font-family: 'arial' !important;" action="addBranch" method="post" enctype="multipart/form-data">
                            <div class="row">
                                    <!-- left column -->
                                    <div class="col-md-3">
                                            <div class="text-center">
                                                    <img src="//placehold.it/100" class="avatar" alt="avatar">
                                                    <h6 style="font-family: arial !important;">Upload product photo...</h6>
                                                    <input type="file" class="form-control" name="image" required>
                                            </div>
                                    </div>
                                    <div class="col-md-9 personal-info">
                                            <div class="alert alert-info alert-dismissable">
                                                    <a class="panel-close close" data-dismiss="alert">x</a> 
                                                    <i class="fa fa-coffee"></i>
                                                            <strong> Everything </strong> is required.
                                            </div>
                                            <h3>Product Info</h3>
                                                    <div class="form-group">
                                                            <label class="col-lg-3 control-label">Branch Number:</label>
                                                            <div class="col-lg-8">
                                                                    <input class="form-control" type="number" min="0" name="productID" required>
                                                            </div>
                                                    </div>
                                                    <div class="form-group">
                                                            <label class="col-lg-3 control-label">Branch Name:</label>
                                                            <div class="col-lg-8">
                                                                    <input class="form-control" type="text" name="productName" required>
                                                            </div>
                                                    </div>
                                                    <div class="form-group">
                                                            <label class="col-lg-3 control-label">Branch Address:</label>
                                                            <div class="col-lg-8">
                                                                    <input class="form-control" type="text" name="productName" required>
                                                            </div>
                                                    </div>
                                                    <div class="form-group">
                                                            <label class="col-lg-3 control-label">Branch Phone Number</label>
                                                            <div class="col-lg-8">
                                                                    <input class="form-control" type="number" step="any" min="0" name="discountRate" required>
                                                            </div>
                                                    </div>
                                                    <div class="form-group">
                                                            <label class="col-md-3 control-label"></label>
                                                            <div class="col-md-8">
                                                                    <input type="submit" class="btn btn-primary" value="Finish">
                                                                    <span></span>
                                                                    <input type="reset" class="btn btn-default" value="Cancel">
                                                    </div>
                                            </div>
                                    </div>			
                            </div>
                        </form>
		</div>
    </body>
</html>
