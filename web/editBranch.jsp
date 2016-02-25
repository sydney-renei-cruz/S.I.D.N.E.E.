<%-- 
    Document   : editBranch
    Created on : 02 25, 16, 2:22:50 PM
    Author     : sydne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <link rel="stylesheet" type="text/css" href="css/main.css">
    </head>
    <body>
        <%@include file="WEB-INF/jsp/navbar.jsp" %>
		
		<div class="container text-center">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header"> Edit Branch ${branchNum}</h1>
				</div>
			</div>
                        <form class="form-horizontal" role="form" action = "editBranch" method = "post" style="font-size: 1.1em; font-family: 'arial' !important;" enctype="multipart/form-data">
			<div class="row">
				<!-- left column -->
				<div class="col-md-3">
					<div class="text-center">
						<img src="image?branchNum=${branchNum}" class="avatar img-circle" alt="avatar">
						<h6 style="font-family: arial !important;">Upload product photo...</h6>
						<input type="file" class="form-control" name="image">
					</div>
				</div>
				<div class="col-md-9 personal-info">
					<div class="alert alert-info alert-dismissable">
						<a class="panel-close close" data-dismiss="alert">x</a> 
						<i class="fa fa-coffee"></i>
							<strong> Everything but image </strong> is required.
					</div>
					<h3>Branch Info </h3>
						<div class="form-group">
							<label class="col-lg-3 control-label">Branch Name:</label>
							<div class="col-lg-8">
								<input class="form-control" type="text" name="branchName" value="${branchName}" required>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">Branch Address:</label>
							<div class="col-lg-8">
								<input class="form-control" type="text" name="branchAddress" value="${branchAddress}" required>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">Branch Phone Number:</label>
							<div class="col-lg-8">
								<input class="form-control" type="text" name="branchPhoneNum" value="${branchPhoneNum}" required>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label"></label>
							<div class="col-md-8">
                                                            <input type="hidden" value="${branchNum}" name="branchNum">
                                                            <input type="submit" class="btn btn-primary" value="Submit">
								<span></span>
								<input type="reset" class="btn btn-default" value="Cancel">
							</div>
						</div>
					</form>
				</div>			
			</div>
		</div>
</html>
