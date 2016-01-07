<!DOCTYPE html>
<html>
    <head>
        <link type="text/css" rel="stylesheet" href="css/main.css"/>
		
        
        <script src="js/main.js"></script>
		
		<!-- navbar -->
		<script>
            topBar();
        </script>
		
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
			<div class="col-md-3">
                            <div class="thumbnail">
							<!-- caption will show when you hover on a picture-->
				<div class="caption">
                                    <h3 class="productName"><%=branchProductList.get(i).getProductName()%></h3>
                                    <p><a href="productRetrieve?pid=<%=branchProductList.get(i).getProductID()%>" class="label label-danger" rel="tooltip" title="View Product"> View Product</a>
				</div>
                                <img class="img-responsive img" src="image?pid=<%=branchProductList.get(i).getProductID()%>" alt="">
                            </div>
			</div>
                    </div>
		<%}%>
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
		
		
		<!-- back to top link -->
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