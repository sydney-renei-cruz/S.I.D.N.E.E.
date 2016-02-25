<!DOCTYPE html  >
<html lang="en">
    <head>
		<link rel="stylesheet" type="text/css" href="css/main.css">
                
                <%@page import="java.util.LinkedList"%>
                <%@page import="Beans.*"%>
    </head>
    <body>
		<%@include file="WEB-INF/jsp/navbar.jsp" %>
		<div class="container text-center">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header"> Add Product to Branch</h1>
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
                                                            <select name="branch">
                                                            <% LinkedList<BranchBean> branchList = (LinkedList) request.getAttribute("branchList"); 
                                                            for(BranchBean bb: branchList){ %>
                                                                <option value="<%=bb.getBranchNum()%>"><%=bb.getBranchNum()%>: <%=bb.getBranchName()%></option>
                                                            <%}
                                                            %>
                                                            </select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">Product:</label>
							<div class="col-lg-8">
                                                            <select name="product">
                                                                    <% LinkedList<ProductBean> productList = (LinkedList) request.getAttribute("productList"); 
                                                            for(ProductBean pb: productList){ %>
                                                                <option value="<%=pb.getProductID()%>"><%=pb.getProductID()%>: <%=pb.getProductName()%></option>
                                                            <%}
                                                            %>
                                                            </select>
                                                        </div>
						</div>
                                                <div class="form-group">
							<label class="col-lg-3 control-label">Branch Discount Rate:</label>
							<div class="col-lg-8">
								<input class="form-control" type="number" step="0.01" min=0 max=100 name="branchDiscountRate" required>
							</div>
						</div>
                                                <div class="form-group">
							<label class="col-lg-3 control-label">Stock:</label>
							<div class="col-lg-8">
								<input class="form-control" type="number" step="1" min=0 name="stock" required>
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
    </body>
</html>