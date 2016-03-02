<!DOCTYPE html>
<html>
    <head>
        <link type="text/css" rel="stylesheet" href="css/main.css"/>
		
        
    </head>
    <body>
		<%@include file="navbar.jsp" %>
		
		<!-- caption will show when you hover on a picture -->
		<script>
			$( document ).ready(function() {
				$("[rel='tooltip']").tooltip();    
 
				$('.thumbnail').hover(
					function(){
						$(this).find('.caption').slideDown(250); //.fadeIn(250)
					},
					function(){
						$(this).find('.caption').slideUp(250); //.fadeOut(205)
					}
				); 
			});
		</script>

		<!-- header -->
		<%@page import="java.util.*"%>
		<%@page import="Beans.*"%>
        <%@page import="DBAccess.*"%>
		<div class="col-lg-12">
			<h1 class="page-header text-center">
                            <%  String branchName = (String)request.getAttribute("branchName");
                                if(branchName.substring(branchName.length()-1).equals("s")){
                            %>
                                <%=branchName%>' Products
                            <%}
                                else{
                            %>
                                <%=request.getAttribute("branchName")%>'s Products
                            <%}%>
			</h1>
		</div>
		
		<!-- items -->
		<div class="container">
		<% 
                List <ProductBean> branchProductList = (List)request.getAttribute("branchProductList"); 
                for(int i = 0; i < branchProductList.size(); i++) {
                %>
                <% if(i%4==0&&i>0){ %>
                     </div>
                <%} if(i%4==0){ %>
                <div class="row">
                <%}%>
                    <div class=" content">
			<div class="col-md-6">
                            <div class="col-md-6">
                                <img class="img-responsive img" src="image?pid=<%=branchProductList.get(i).getProductID()%>" alt="">
                            </div>
                            <div class="col-md-6">
                                <h3><%=branchProductList.get(i).getProductName()%></h3>
                                <h4>Price: <%= branchProductList.get(i).getMSRP()%></h4>
                                <h4>Discount Rate: <%= branchProductList.get(i).getDiscountRate()%>%</h4>
                                <a href="productRetrieve?pid=<%=branchProductList.get(i).getProductID()%>" class="label label-danger" rel="tooltip" title="View Product"> View Product</a>
                                <%  if(session.getAttribute("userID")!=null){  %>
                                <a href="editBranchProduct.jsp" class="label label-info" rel="tooltip" title="View Product"> Edit Product</a>
                                <% } %>
                            </div>
                            
			</div>
                    </div>
		<%}%>
                </div>
		<!-- pagination -->
		<nav class="text-center">
			<div class="col-lg-12">
				<ul class="pagination">
					<li class="pag_prev">
						<a href="#" aria-label="Previous">
							<span aria-hidden="true">&laquo;</span>
						</a>
					</li>
					<li class="pag_next">
						<a href="#" aria-label="Next">
							<span aria-hidden="true">&raquo;</span>
						</a>
					</li>
				</ul>
			</div>
		</nav>

		<!-- creating a pagination -->
		<script>
			$( document ).ready(function() { 
    

    pageSize = 12;
    pagesCount = $(".content").length;
    var currentPage = 1;
    
    /////////// PREPARE NAV ///////////////
    var nav = '';
    var totalPages = Math.ceil(pagesCount / pageSize);
    for (var s=0; s<totalPages; s++){
        nav += '<li class="numeros"><a href="#">'+(s+1)+'</a></li>';
    }
    $(".pag_prev").after(nav);
    $(".numeros").first().addClass("active");
    //////////////////////////////////////

    showPage = function() {
        $(".content").hide().each(function(n) {
            if (n >= pageSize * (currentPage - 1) && n < pageSize * currentPage)
                $(this).show();
        });
    }
    showPage();


    $(".pagination li.numeros").click(function() {
        $(".pagination li").removeClass("active");
        $(this).addClass("active");
        currentPage = parseInt($(this).text());
        showPage();
    });

    $(".pagination li.pag_prev").click(function() {
        if($(this).next().is('.active')) return;
        $('.numeros.active').removeClass('active').prev().addClass('active');
        currentPage = currentPage > 1 ? (currentPage-1) : 1;
        showPage();
    });

    $(".pagination li.pag_next").click(function() {
        if($(this).prev().is('.active')) return;
        $('.numeros.active').removeClass('active').next().addClass('active');
        currentPage = currentPage < totalPages ? (currentPage+1) : totalPages;
        showPage();
    });
});
		</script>
		
		
    </body>
</html>