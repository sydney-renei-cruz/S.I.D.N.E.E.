<%-- 
    Document   : editProduct
    Created on : 02 2, 16, 9:28:24 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html  >
<html lang="en">
    <head>
		<link rel="stylesheet" type="text/css" href="css/main.css">
                <%@include file="WEB-INF/jsp/navbar.jsp" %>
    </head>
    <body>
	<div class="container text-center">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header"> Edit Branch </h1>
				</div>
			</div>
			<div class="row">
				<!-- left column -->
				<div class="col-md-3">
					<div class="text-center">
						<img src="//placehold.it/100" class="avatar img-circle" alt="avatar">
						<h6 style="font-family: arial !important;">Upload product photo...</h6>
						<input type="file" class="form-control">
					</div>
				</div>
				<div class="col-md-9 personal-info">
					<div class="alert alert-info alert-dismissable">
						<a class="panel-close close" data-dismiss="alert">x</a> 
						<i class="fa fa-coffee"></i>
							<strong> Everything </strong> is required.
					</div>
					<h3>Product Info</h3>
        
					<form class="form-horizontal" role="form" style="font-size: 1.1em; font-family: 'arial' !important;">
						<div class="form-group">
							<label class="col-lg-3 control-label">Product Name:</label>
							<div class="col-lg-8">
								<input class="form-control" type="text" class="productName">
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">Retail Price:</label>
							<div class="col-lg-8">
								<input class="form-control" type="text" class="retailPrice">
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">Branch Number:</label>
							<div class="col-lg-8">
								<input class="form-control" type="text" class="branchNum">
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">Stock:</label>
							<div class="col-lg-8">
								<input class="form-control" type="text" class="stock">
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">MSRP:</label>
							<div class="col-lg-8">
								<input class="form-control" type="text" class="MSRP">
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">Description:</label>
							<div class="col-lg-8">
								<input class="form-control" type="text" class="Description">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label"></label>
							<div class="col-md-8">
								<input type="button" class="btn btn-primary" value="Finish">
								<span></span>
								<input type="reset" class="btn btn-default" value="Cancel">
							</div>
						</div>
					</form>
				</div>			
			</div>
		</div>
		<span id="top-link-block" class="hidden">
    <a href="#top" class="well well-sm"  onclick="$('html,body').animate({scrollTop:0},'slow');return false;">
        <i class="glyphicon glyphicon-chevron-up"></i>
    </a>
</span><!-- /top-link-block -->
        <script>
			// Only enable if the document has a long scroll bar
			// Note the window height + offset
			if ( ($(window).height() + 100) < $(document).height() ) {
				$('#top-link-block').removeClass('hidden').affix({
			// how far to scroll down before link "slides" into view
				offset: {top:100}
				});
			}
		</script>
    </body>
</html>