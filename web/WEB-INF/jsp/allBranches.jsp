<%-- 
    Document   : allBranches
    Created on : 02 25, 16, 2:19:31 PM
    Author     : sydne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link type="text/css" rel="stylesheet" href="css/main.css"/>
    </head>
    <body>
        <%@include file="navbar.jsp" %>
        <div class="col-lg-12">
            <h1 class="page-header text-center">
                All Branches
			</h1>
        </div>
    <div class="container">
        <%@page import="java.util.*"%>
	<%@page import="Beans.*"%>
        <%@page import="DBAccess.*"%>
        <% 
            List <BranchBean> branchList = (List)request.getAttribute("branchList"); 
            for(BranchBean branch: branchList) {
        %>
            <div class="row">
            <div class="content">
            <div class="col-md-7 branch-img">
            <a href="branchProductRetrieve?branch=<%=branch.getBranchNum()%>">
            <img class="img-responsive" src="image?branchNum=<%=branch.getBranchNum()%>" alt="">
            </a>
            </div>
            <div class="col-md-5">
                <h2 style="color: black;"><%=branch.getBranchName()%></h2>
            <h5><label class="address"><%=branch.getBranchAddress()%></label></h5>
            <h5><label class="phoneNumber"><%=branch.getBranchPhoneNum()%></label></h5>
            <p></p>
            <a class="btn btn-primary" href="branchProductRetrieve?branch=<%=branch.getBranchNum()%>">View Products <span class="glyphicon glyphicon-chevron-right"></span></a>
            <%  if(session.getAttribute("userID")!=null){  %>
                                                <form action="editBranch" method="post">
                                                    <input type="hidden" name="branchNum" value="<%=branch.getBranchNum()%>">
                                                    <button type="submit" class="btn btn-primary">Edit</button>
                                                </form>
                                                <%}
                                                %>
            <hr>
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
			$( document ).ready(function() { 
    

    pageSize = 5;
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
