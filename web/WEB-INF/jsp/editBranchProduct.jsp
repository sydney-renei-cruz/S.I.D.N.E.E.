<%-- 
    Document   : editBranchProduct
    Created on : 02 25, 16, 1:18:35 PM
    Author     : sydne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/main.css">
    </head>
    <body>
        <%@include file="navbar.jsp" %>
        <div class="container text-center">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header"> Edit Product Branch</h1>
				</div>
			</div>
                        <form class="form-horizontal" role="form" action = "AddProductBranch" method = "post" style="font-size: 1.1em; font-family: 'arial' !important;" enctype="multipart/form-data">
			<div class="row">
				<!-- left column -->
					<div class="alert alert-info alert-dismissable">
						<a class="panel-close close" data-dismiss="alert">x</a> 
						<i class="fa fa-coffee"></i>
							<strong> Everything </strong> is required.
					</div>
					<h3>Select product and branch </h3>
        
						<div class="form-group">
							<label class="col-lg-3 control-label">Branch:</label>
							<div class="col-lg-8">
                                                            ${branch}
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">Product:</label>
							<div class="col-lg-8">
                                                            ${product}
                                                        </div>
						</div>
                                                <div class="form-group">
							<label class="col-lg-3 control-label">Branch Discount Rate:</label>
							<div class="col-lg-8">
								<input class="form-control" type="number" step="0.01" min=0 max=100 name="branchDiscountRate" value="${bdr}" required>
							</div>
						</div>
                                                <div class="form-group">
							<label class="col-lg-3 control-label">Stock:</label>
							<div class="col-lg-8">
								<input class="form-control" type="number" step="1" min=0 name="stock" value="${stock}" required>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label"></label>
							<div class="col-md-8">
                                                            <input type="submit" class="btn btn-primary" value="Submit">
								<span></span>
								<input type="reset" class="btn btn-default" value="Cancel">
							</div>
						</div>
					</form>	
			</div>
		</div>
        <span id="top-link-block" class="hidden">
			<a href="#top" class="well well-sm"  onclick="$('html,body').animate({scrollTop:0},'slow');return false;">
			<i class="glyphicon glyphicon-chevron-up"></i>
			</a>
		</span>
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
