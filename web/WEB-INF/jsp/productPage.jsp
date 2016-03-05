<!DOCTYPE html  >
<html lang="en">
    <head>
		<link rel="stylesheet" type="text/css" href="css/main.css">
		<link rel="stylesheet" type="text/css" href="css/one-page-wonder.css">
	
    </head>
    <body>
		<%@include file="navbar.jsp" %>
                
                <%@page import="java.util.*"%>
                <%@page import="Beans.*"%>
                <%@page import="DBAccess.*"%>
                <%@page import="java.text.*"%>
		<style>
			body{
				font-family: Segoe UI, impact;
			}
			.lead, .description{
				color: #E4717A;
			}
			.thumbnail{
				width: 100%;
				border: 1px solid black;
				height: 450px;
			}
			.thumbnail img{
				width: 100%;
				height: 100%;
			}
		</style>
		<!-- Page Content -->
                <% 
                    ProductBean product = (ProductBean)request.getAttribute("product");
                %>
		<div class="container">
			<hr class="featurette-divider">
			<!-- First Featurette -->
			<div class="featurette" id="about">
				<div class="row" style="margin-top: 35px;">
					<div class="col-sm-6">
						<span class="thumbnail">
							<img class="featurette-image img-responsive pull-left productimg" src="image?pid=<%=product.getProductID()%>">
						</span>
					</div>
                                    
					<div class="col-sm-6">
                                            <% DecimalFormat df = new DecimalFormat("#.##");%>
						<h2 class="featurette-heading productName"><%=product.getProductName()%></h2>
						<h5 style="margin-top:-10px"><label class="description Headtext">
                                                        <%=product.getDescription().split("[.]+")[0]%>.
                                                    </label></h5>
						<hr style="margin: 0px 0px 70px 0px;">
						<p class="lead" style="font-size: 2em;"> Price: <label class="MSRP"><%= df.format(product.getMSRP() - (product.getMSRP()*product.getDiscountRate())/100)%></label> <br> MSRP: <%=product.getMSRP()%> Discount rate: <%=product.getDiscountRate()%>%</p>
						<hr style="margin: 70px 0px 0px 0px;">
                                                <%  if(session.getAttribute("userID")!=null){  %>
                                                    <form action="editProduct">
                                                        <input type="hidden" name="productID" value="${product.getProductID()}">
                                                        <button type="submit" class="btn btn-primary">Edit</button>
                                                    </form>
                                                <%}%>
					</div>
				</div>
			</div>
			<div class="container-fluid" style="margin-top: 30px;">		
				<div class="col-md-12 product-info">
						<ul id="myTab" class="nav nav-tabs nav_tabs">
							<li class="active"><a href="#service-one" data-toggle="tab" style="color: #336699;">DESCRIPTION</a></li>
							<li><a href="#service-two" data-toggle="tab" style="color: #336699;">COMPARISON TABLE</a></li>
						</ul>
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane fade in active" id="service-one">
							<section class="container Description">
								<%=product.getDescription()%>
							</section>
						</div>
						<div class="tab-pane fade" id="service-two">
							<section class="container-fluid">
								<div class="table-responsive">          
									<table class="table" style="width: 100%;">
										<col width="2%" />
										<col width="30%" />
										<col width="26%" />
										<col width="10%" />
										<thead>
											<tr>	
												<th>#</th>
												<th>Branch</th>
												<th>Price</th>
												<th>In Stock?</th>
												<th>Discounted Price</th>
                                                                                                <%  if(session.getAttribute("userID")!=null){  %>
                                                                                                <th></th>
                                                                                                <% } %>
											</tr>
										</thead>
										<tbody class="compTable">
                                                                                    <% 
                                                                                        ArrayList<ProductInventoryBean> productData = (ArrayList)request.getAttribute("productBranchData"); 
                                                                                        
                                                                                        for(int j = 0; j<productData.size(); j++){
                                                                                            
                                                                                            %>
                                                                                            <tr>
                                                                                                <td>
                                                                                                    <%=(j+1)%>
                                                                                                </td>
                                                                                                <td>
                                                                                                    <%=productData.get(j).getBranchNum()%>
                                                                                                </td>
                                                                                                <td>
                                                                                                    
                                                                                                    <%=df.format(product.getMSRP() - ((product.getMSRP()*productData.get(j).getBranchDiscountRate())/100))%>
                                                                                                </td>
                                                                                                <td>
                                                                                                    <%=productData.get(j).getStock()%>
                                                                                                </td>
                                                                                                <td>
                                                                                                    <%=productData.get(j).getBranchDiscountRate()%>%
                                                                                                </td>
                                                                                                <%  if(session.getAttribute("userID")!=null){  %>
                                                                                                <td>
                                                                                                    <a href="editBranchProduct.jsp" class="label label-info" rel="tooltip" title="Edit Product"> Edit</a>
                                                                                                </td>
                                                                                                <%}%>
                                                                                    
                                                                                       <%}%>
                                                                                    
										</tbody>
									</table>
								</div>
							</section>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- /.container -->
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