<%-- 
    Document   : registerUser
    Created on : 02 25, 16, 4:24:24 PM
    Author     : sydne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link type="text/css" rel="stylesheet" href="css/main.css">
        <%@include file="WEB-INF/jsp/navbar.jsp"%>
    </head>
    <body>
        <div class="container text-center">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header"> Register User </h1>
				</div>
			</div>
                        <form class="form-horizontal" role="form" style="font-size: 1.1em; font-family: 'arial' !important;" action="addProduct" method="post" enctype="multipart/form-data">
                            <div class="row">
                                    
                                    <div class="col-md-12 personal-info">
                                            <div class="alert alert-info alert-dismissable">
                                                    <a class="panel-close close" data-dismiss="alert">x</a> 
                                                    <i class="fa fa-coffee"></i>
                                                            <strong> Everything </strong> is required.
                                            </div>
                                            <h3>Product Info</h3>
                                                    <div class="form-group">
                                                            <label class="col-lg-3 control-label">User ID:</label>
                                                            <div class="col-lg-8">
                                                                    <input class="form-control" type="number" min="1" name="userID" required>
                                                            </div>
                                                    </div>
                                                    <div class="form-group">
                                                            <label class="col-lg-3 control-label">Username:</label>
                                                            <div class="col-lg-8">
                                                                    <input class="form-control" type="text" name="username" required>
                                                            </div>
                                                    </div>
                                                    <div class="form-group">
                                                            <label class="col-lg-3 control-label">Password: </label>
                                                            <div class="col-lg-8">
                                                                    <input class="form-control" type="password" name="password" required>
                                                            </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-lg-3 control-label">Add Photo</label>
                                                        <div class="col-lg-8">
                                                            <input type="file" class="form-control" name="image" style="padding: 0 0;" required>
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
