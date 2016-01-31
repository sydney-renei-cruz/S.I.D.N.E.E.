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

function topBar(){
    document.write('<meta charset="utf-8">\
			<title>S.I.D.N.E.E.</title>\
			<meta name="viewport" content="width=device-width, initial-scale=1">\
			<meta http-equiv="X-UA-Compatible" content="IE=edge">\
			<meta name="description" content="">\
			<meta name="author" content="">\
			<link rel="stylesheet" href="css/bootstrap.min.css">\
			<link type="text/css" rel="stylesheet" href="css/default-navbar.css">\
			<link type="text/css" rel="stylesheet" href="css/backtotop.css">\
			<!-- jQuery -->\
			<script src="js/jquery2.js"></script>\
			<!-- Bootstrap Core JavaScript -->\
			<script src="js/bootstrap.js"></script>\
		</head>\
		<body>\
			<!-- Navigation -->\
			<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation" id="myNavbar">\
				<div class="container-fluid">\
				<!-- Brand and toggle get grouped for better mobile display -->\
					<div class="navbar-header">\
						<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">\
							<span class="sr-only">Toggle navigation</span>\
							<span class="icon-bar"></span>\
							<span class="icon-bar"></span>\
							<span class="icon-bar"></span>\
						</button>\
						<a class="navbar-brand" href="index.html#home" data-target="#home">S.I.D.N.E.E.</a>\
					</div>\
					<!-- Collect the nav links, forms, and other content for toggling -->\
					<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">\
						<!-- navbar buttons-->\
						<ul class="nav navbar-nav navbar-left">\
							<li style="margin-left: 20px;">\
								<form class="search-bar navbar-form" role="search" method="get" action="productRetrieve">\
									<div class="input-group">\
										<input type="text" class="form-control" placeholder="Search Me!" name="pid" id="srch">\
										<div class="input-group-btn">\
											<button class="btn btn-default" type="submit" onClick="location.href="productRetrieve"">\
												<i class="glyphicon glyphicon-search"></i>\
											</button>\
										</div>\
									</div>\
								</form>\
							</li>\
						</ul>\
						<ul class="nav navbar-nav navbar-right">\
							<li>\
								<a href="index.html#myCarousel" data-target="#myCarousel">About</a>\
							</li>\
							<li class="dropdown">\
								<a class="dropdown-toggle" data-toggle="dropdown">Product\
									<span class="caret"></span>\
								</a>\
								<ul class="dropdown-menu">\
									<li><a href="allProductsRetrieve"><span class="glyphicon glyphicon-eye-open"> All Products</span></a></li>\
									<li><a href="addProduct.html"><span class="glyphicon glyphicon-plus"> Add Product</span></a></li>\
								</ul>\
							</li>\
							<li class="dropdown">\
								<a class="dropdown-toggle" data-toggle="dropdown">Branch\
									<span class="caret"></span>\
								</a>\
								<ul class="dropdown-menu">\
									<li><a href="allBranchesRetrieve"><span class="glyphicon glyphicon-eye-open"> All Branches</span></a></li>\
								</ul>\
							</li>\
							<li>\
                                <a href="Login"><span class="glyphicon glyphicon-user"></span> <element id="uname">' + getCookie("username") +'</element></a>\
							</li>\
						</ul>\
					</div>\
				</div>\
			</nav>\
	');
}