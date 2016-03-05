<%-- 
    Document   : aboutPage
    Created on : 02 3, 16, 2:56:11 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="WEB-INF/jsp/commonHeadTags.jsp" %>
        <link type="text/css" rel="stylesheet" href="css/aboutPage.css"/>
        <link rel="stylesheet" type="text/css" href="css/default-navbar.css"/>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
		<div class="col-sm-12 text-center">
                    <div class="box-container">
                        <div class="btn-group">
                            <a href="indexRetrieveProductNBranch" class="btn btn-warning">Home</a>
                            
                            <div class="btn-group dropdown">
                                <button type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown">Product <span class="caret"></span></button>
                                <ul class="dropdown-menu">
                                    <li><a href="allProductsRetrieve">All Products</a></li>
                                    <%  if(session.getAttribute("userID")!=null){  %>
                                    <li><a href="AddProduct">Add Product</a></li>
                                    <% } %>
                                </ul>
                            </div>
                            <div class="btn-group dropdown">
                                <button type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown">Branch <span class="caret"></span></button>
                                <ul class="dropdown-menu">
                                    <li><a href="allBranchesRetrieve">All Branches</a></li>
                                    <%  if(session.getAttribute("userID")!=null){  %>
                                    <li><a href="addBranch">Add Branch</a></li>
                                    <li><a href="AddProductBranch">Add Product to Branch</a></li>
                                    <% } %>
                                </ul>
                            </div>
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
                                                <%  if(session.getAttribute("userID")==null){  %>
                                                        <a href="Login" class="btn btn-warning"> <span class="glyphicon glyphicon-user"></span> <span id="uname">Account</span></a>
                                                    <% } %>
                                                    <% if(session.getAttribute("userID")!=null){ %>
                                                    <a href="userRetrieve" class="btn btn-warning"> <span class="glyphicon glyphicon-user"></span> <span id="uname"><script>getCookie("username")</script></span></a>
                                                    <% } %>
                            
                        </div>
			<h1>S.I.D.N.E.E.</h1>
                        <div>
                            <a href="aboutPage.jsp#aboutWebsite" data-target="#aboutWebsite" class="btn btn-warning"><h2>The Website</h2></a>
                            <a href="aboutPage.jsp#aboutDevelopers" data-target="#aboutDevelopers" class="btn btn-warning"><h2>The Developers</h2></a>
                        </div>             
                    </div>
		</div>
                
                <div id="aboutWebsite" class="col-sm-12 text-center">
                    <div>
                        <h1><span class="glyphicon glyphicon-info-sign logo"></span></h1>
			<h2>What is <strong style="font-size: 1.5em;">S.I.D.N.E.E.</strong>?</h2>
			<h2 id="home_description"><strong style="font-size: 1.5em;">S.I.D.N.E.E.</strong> is an online system that aims to help employees access 
			and keep track of updated and uniform data regarding the conpany's 
			inventory of products in all branches of the company.</h2>
                    </div>
                </div>
                <div id="aboutDevelopers" class="col-sm-12 text-center">
                    <h1 style="font-weight: bold; font-size: 5em;"> The Developers</h1>
                    <div id="myCarousel" class="carousel slide" data-ride="carousel">
			<!--wrapper-->
			<div class="col-md-4">
                            <div class="carousel-inner">
				<div class="active item" data-slide-number="0"><img class="img" src="img/tj.jpg"></div>
				<div class="item" data-slide-number="1"><img class="img" src="img/syd.jpg"></div>
				<div class="item" data-slide-number="2"><img class="img" src="img/jacob.jpg"></div>
				<div class="item" data-slide-number="3"><img class="img" style="height: 500px;" src="img/rom.jpg"></div>
                            </div>			
			</div>
                            <div id="carousel-text"></div>
				<div class="col-md-8">
                                    <div style="display: none;" id="slide-content">
					<div id="slide-content-0">
                                            <h1 style="font-size: 4em;">TJ Seachon</h1>
                                            <h2>Project Manager</h2>
					</div>
					<div id="slide-content-1">
                                            <h1 style="font-size: 4em;">Syd Cruz</h1>
                                            <h2>Front-End Developer</h2>
					</div>
					<div id="slide-content-2">
                                            <h1 style="font-size: 4em;">Jacob Dyogi</h1>
                                            <h2>Back-End Developer</h2>
					</div>
					<div id="slide-content-3">
                                            <h1 style="font-size: 4em;">Rom Ricafranca</h1>
                                            <h2>Database Administrator</h2>
					</div>
								
                                    </div>
                                    <!-- Controls -->
                                    <div class="carousel-controls-mini" style="margin-right: 13%;">
					<a href="#myCarousel" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
					<a href="#myCarousel" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
                                    </div>
				</div>
						
                    </div>
                    <script>
			$('#myCarousel').carousel({
                            interval: 5000
                        });

			$('#carousel-text').html($('#slide-content-0').html());

			//Handles the carousel thumbnails
			$('[id^=carousel-selector-]').click( function(){
                            var id_selector = $(this).attr("id");
                            var id = id_selector.substr(id_selector.length-1);
                            var id = parseInt(id);
                            $('#myCarousel').carousel(id);
			});


			// When the carousel slides, auto update the text
			$('#myCarousel').on('slid.bs.carousel', function (e) {
                            var id = $('.item.active').data('slide-number');
                            $('#carousel-text').html($('#slide-content-'+id).html());
			});
                    </script>		
		</div>
            </div>
	</div>
        <!-- slide over effect when you click The Website and The Developers -->
				<script>
					// Add scrollspy to <body>
					$('body').scrollspy({target: ".navbar", offset: 50});

					// Add smooth scrolling to all links inside a navbar
					$("#myNavbar a").on('click', function(event){

						// Prevent default anchor click behavior
						//will prevent any actions, including href
						//event.preventDefault();
				
				
						// Store hash (#)
						var hash = this.hash;

						// Using jQuery's animate() method to add smooth page scroll
						// The optional number (800) specifies the number of milliseconds it takes to scroll to the specified area (the speed of the animation)
						$('html, body').animate({
							scrollTop: $(hash).offset().top
						}, 800, function(){
							// Add hash (#) to URL when done scrolling (default click behavior)
							window.location.hash = hash;
						});
					});	
			
					// Only enable if the document has a long scroll bar
					// Note the window height + offset
					if ( ($(window).height() + 100) < $(document).height() ) {
						$('#top-link-block').removeClass('hidden').affix({
						// how far to scroll down before link "slides" into view
						offset: {top:100}
						});
					}
				</script>
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
							<form class="search-bar navbar-form" role="search" action="Search" method="POST" style="border: 1px solid transparent;">
								<div class="input-group">
									<input type="text" class="form-control" placeholder="Search Me!" name="query" id="srch">
									<div class="input-group-btn">
										<button class="btn btn-default" type="submit">
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
								<li><a href="addProduct.jsp"><span class="glyphicon glyphicon-plus"> Add Product</span></a></li>
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
                                                                <li><a href="addBranch.jsp"><span class="glyphicon glyphicon-plus"> Add Branch</span></a></li>
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
						<li>
                               <a href="loginPage.html"><span class="glyphicon glyphicon-user"></span> Account</a>
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
