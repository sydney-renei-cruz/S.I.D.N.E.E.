<%-- 
    Document   : index
    Created on : 01 29, 16, 12:12:53 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <%@include file="WEB-INF/jsp/commonHeadTags.jsp" %>
		<link rel="stylesheet" type="text/css" href="css/homepage.css"/>
                
		<link rel="stylesheet" type="text/css" href="css/default-navbar.css"/>
        
	</head>
	<body>
            
        <!-- header -->
            <div class="container-fluid">
	<div class="row">
				<div class="col-sm-12 text-center">
					<div class="box-container">
						<h1>S.I.D.N.E.E.</h1>
						<!-- search bar -->
						<div id="custom-search-input" style="margin-bottom: 2%;">
							<form class="input-group text-center search-bar" role="search" method="post" action="Search">
								<input type="text" class="form-control" placeholder="Search me..." name="query" id="srch">
								<div class="input-group-btn">
									<button class="btn btn-default" type="submit">
										<i class="glyphicon glyphicon-search"></i>
									</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<!-- navbar like buttons below header -->
		<div class="container-fluid" role="navigation">
			<!-- set toggle buttons -->
			<div class="navbar-header homenav">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#homnav">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
			</div>
			<!-- collect the nav links, forms ,etc. for toggling -->
			<div class="collapse navbar-collapse text-center" id="homnav">
				<!-- navbar buttons -->
				<div class="col-sm-4 dropdown" style="padding-right: 4.5%;">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown"> <h2 class="home_navbut">Product</h2></a>
					<ul class="dropdown-menu">
						<li><a href="allProductsRetrieve"><h2>All Products</h2></a></li>
                                                <%  if(session.getAttribute("userID")!=null){  %>
						<li><a href="AddProduct"><h2>Add Products</h2></a></li>
                                                <% } %>
					</ul>
				</div>
				<div class="col-sm-4" style="border-left: 10px solid #E4717A; border-right: 10px solid #E4717A;">
                                    <%  if(session.getAttribute("userID")==null){  %>
                                                        <a href="Login"> <h2 class="home_navbut">Account</h2> </a>
                                                    <% } %>
                                                    <% if(session.getAttribute("userID")!=null){ %>
                                                    <a href="userRetrieve"> <h2 class="home_navbut">Account</h2></a>
                                                    <% } %>
					
				</div>
				<div class="col-sm-4 dropdown" style="padding-left: 3%;">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown"> <h2 class="home_navbut">Branch</h2> </a>
					<ul class="dropdown-menu" style="margin-left: 0%; padding: 0 20% !important;">
						<li><a href="allBranchesRetrieve"><h2>All Branches</h2></a></li>
                                                <%  if(session.getAttribute("userID")!=null){  %>
                                                <li><a href="AddBranch"><h2>Add Branch</h2></a></li>
                                                <li><a href="AddProductBranch"><h2>Add Product to Branch</h2></a></li>
                                                <% } %>
					</ul>
				</div>
			</div>
		</div>
		<!-- script for dropdown hover effect -->
		<script>
                    $(function() {
                        //caches a jQuery object containing the header element
                        var header = $('#myNavbar');
                        $(window).scroll(function() {
                            var scroll = $(window).scrollTop();

                            if (scroll >= 500) {
                                header.removeClass('noBackground').addClass('blackBackground').fadeIn();
                            } else {
                                header.removeClass('blackBackground').fadeOut().addClass('noBackground');
                            }
                    });
		</script>
                <!-- end of header -->
                
                
		<!-- Navigation -->
		<nav class="navbar  navbar-fixed-top" role="navigation" id="myNavbar">
			<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="indexRetrieveProductNBranch">S.I.D.N.E.E.</a>
				</div>
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<!-- navbar buttons-->
					<ul class="nav navbar-nav navbar-left">
						<li style="overflow: hidden;">
							<form class="search-bar navbar-form" method="post" action="Search" role="search" style="border: 1px solid transparent;">
								<div class="input-group">
									<input type="text" class="form-control" placeholder="Search Me!" name="query" id="srch">
									<div class="input-group-btn">
										<button class="btn btn-default" type="submit" onClick="location.href='productPage.html'">
											<i class="glyphicon glyphicon-search"></i>
										</button>
									</div>
								</div>
							</form>
						</li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
							<a class="dropdown-toggle" data-toggle="dropdown">Product
								<span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<li><a href="allProductsRetrieve"><span class="glyphicon glyphicon-eye-open"> All Products</span></a></li>
                                                                <%  if(session.getAttribute("userID")!=null){  %>
								<li><a href="AddProduct"><span class="glyphicon glyphicon-plus"> Add Product</span></a></li>
                                                                <% } %>
							</ul>
						</li>
						<li class="dropdown">
							<a class="dropdown-toggle" data-toggle="dropdown">Branch
								<span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<li><a href="allBranchesRetrieve"><span class="glyphicon glyphicon-eye-open"> All Branches</span></a></li>
                                                                <%  if(session.getAttribute("userID")!=null){  %>
                                                                <li><a href="AddBranch"><span class="glyphicon glyphicon-plus"> Add Branch</span></a></li>
                                                                <li><a href="AddProductBranch"><span class="glyphicon glyphicon-plus"> Add Product to Branch</span></a></li>
                                                                <% } %>
							</ul>
						</li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">About <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="aboutPage.jsp#aboutWebsite"><span class="glyphicon glyphicon-globe"> The Website</span></a></li>
								<li><a href="aboutPage.jsp#aboutDevelopers"><span class="glyphicon glyphicon-fire"> The Developers</span></a></li>
							</ul>
						</li>
                                                <script>
                                                    function getCookie(cname){
                                                        var name = cname + "=";
                                                        var ca = document.cookie.split(';');
                                                        for(var i=0; i<ca.length; i++) {
                                                            var c = ca[i];
                                                            while (c.charAt(0)==' ') c = c.substring(1);
                                                            if (c.indexOf(name) == 0) return c.substring(name.length,c.length);
                                                        }
                                                        return "Account";
                                                    }                                                   
                                                </script>
						<li>
                                                    <%  if(session.getAttribute("userID")==null){  %>
                                                        <a href="Login"> <span class="glyphicon glyphicon-user"></span> <span id="uname">Account</span></a>
                                                    <% } %>
                                                    <% if(session.getAttribute("userID")!=null){ %>
                                                    <a href="userRetrieve"> <span class="glyphicon glyphicon-user"></span> <span id="uname"><script>getCookie("username")</script></span></a>
                                                    <% } %>
						</li>
					</ul>
				</div>
			</div>
		</nav>
		<!-- fade in and fade out of navbar -->
		<script>
			(function($){
				$(document).ready(function(){
					//hide .navbar
					$(".navbar").hide();
					
					//fade in .navbar
					$(function(){
						$(window).scroll(function(){
							//set distance
							if($(this).scrollTop() > 500){
								$(".navbar").fadeIn();
							}else{
								$(".navbar").fadeOut();
							}
						});
					});
				});
			}(jQuery));
		</script>
		
		<!-- feature carousel -->
		<div class="boCa container-fluid">
			<div class="row">
				<div class="col-lg-12 bg-red">
					<h2 class="page-header">
						Check These Products Now!
					</h2>
				</div>
                                <div class="col-lg-12 bg-red">
                                    <%@page import="java.util.*"%>
                                    <%@page import="Beans.*"%>
                                    <%@page import="DBAccess.*"%>
                                    <% 
                                        List <ProductBean> randomProductList = (List)request.getAttribute("randomProductList"); 
                                        for(int i = 0; i<randomProductList.size();i++) {
                                    %>
                                    <% if(i%4==0&&i>0){ %>
                                </div>
                                    <%} if(i%4==0){ %>
                                    <div class="row">
                                        <% } %>
                                        <div class="content">
                                            <div class="col-md-3" style="padding-bottom:20px;">
                                                <div class="thumbnail" >
                                                    
                                                    <img class="img-responsive img" src="image?pid=<%=randomProductList.get(i).getProductID()%>" alt="">
                                                </div>
                                                <h4 class="productName"><%= randomProductList.get(i).getProductName() %></h4>
                                                <a href="productRetrieve?pid=<%=randomProductList.get(i).getProductID()%>" class="label label-danger" rel="tooltip" title="View Product"> View Product</a>
                                            </div>
                                        </div>
                                                
                                        <%}%>
                                    </div>
				<div class="col-lg-12 bg-red">
					<hr>
				</div>
			</div>
		</div>
		<!-- carousel auto-cycle -->
		<script>
			$(document).ready(function() {
				$('.carousel').carousel({
					interval: 3000
				});
			});
		</script>
	</body>
</html>
