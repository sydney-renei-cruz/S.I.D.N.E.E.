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
								<form class="search-bar navbar-form" role="search">\
									<div class="input-group">\
										<input type="text" class="form-control" placeholder="Search Me!" name="pid" id="srch">\
										<div class="input-group-btn">\
											<button class="btn btn-default" type="submit" onClick="location.href="productPage.html"">\
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
                                <a href="Login"><span class="glyphicon glyphicon-user"></span> Account</a>\
							</li>\
						</ul>\
					</div>\
				</div>\
			</nav>\
	');}
	
function allProductsScript(){
    
    $.get('allProductsRetrieve', function(data){
        data = data.split("::::");
        dataIn = [];
        for(i = 0; i<(data.length-1); i++){
            dataIn[i] = JSON.parse(data[i]);
        }
            
        rowCount = 0;
    
        for(i = 0; i<=((data.length/5)); i++){
            addRow(rowCount);
                    
            for(j = 0; j<4; j++){
                JSONInput = dataIn[(i*4) + j];
                if((i*4) + j < dataIn.length){
                    addEntry(JSONInput.productID, JSONInput.productName, rowCount);
                }
            }
                    
            rowCount = rowCount + 1;
            closeDiv();
                
        }
    });
};

function branchProductsScript(){
    var res = location.href.split("?branch=")[1];
                            
    if(res === undefined){
        alert("No parameters");
        window.history.back;
        window.location.href = "allProducts.html";
    }
    
    else{
        res = res.split("&")[0];
        res = "branchProductRetrieve?branch=" + res;
        
        $.get(res, function(data){
            data = data.split("::::");
            $(".branchName").text(data[data.length-1]);
            dataIn = [];
            for(i = 0; i<(data.length-1); i++){
                dataIn[i] = JSON.parse(data[i]);
            }
            
            rowCount = 0;
    
            for(i = 0; i<=((data.length/5)); i++){
                addRow(rowCount);
                    
                for(j = 0; j<4; j++){
                    JSONInput = dataIn[(i*4) + j];
                    if((i*4) + j < dataIn.length){
                        addEntry(JSONInput.productID, JSONInput.productName, rowCount);
                    }
                }
                    
                rowCount = rowCount + 1;
                closeDiv();
                
            }
        });
    }
};
        
function addRow(rowCount){
    $(".container").append("<div class=\"row row"+ rowCount +"\">");
}
        
function addEntry(data1, data2, rowCount){
     $(".row" + rowCount).append("<div class=\"content\"><div class=\"col-md-3\"><div class=\"thumbnail\"><div class=\"caption\"><h3 class=\"productName\">" + data2 +"</h3><p><a href=\"productPage.html?pid="+ data1 +"\" class=\"label label-danger\" rel=\"tooltip\" title=\"View Product\"> View Product</a></p></div><img class=\"img-responsive img\" src=\"img/products/"+data1+".png\" alt=\"\"></div></div></div>");
}
        
function closeDiv(){
    $(".container").append("</div>");
    alert(1);
}
        
function productPageScript(){
    var res = location.href.split("?pid=")[1];
                            
    if(res === undefined){
        alert("No parameters");
        window.history.back;
        window.location.href = "allProducts.html";
    }
    
    else{
        res = res.split("&")[0];
        var resTable = "branchRetrieve?pid=" + res;
        res = "productRetrieve?pid=" + res;
                            
        $.get(res, function(data){
            retval = JSON.parse(data);
            
            $('.productName').text(retval.productName);
            
            $('.MSRP').text(retval.MSRP);
                                
            $('.Headtext').text(retval.Description.split(".")[0]);
                                
            $('.Description').text(retval.Description);
                                
            $('.productimg').attr("src","img/products/" + res.split("pid=")[1] + ".png");
        })
                            
        $.get(resTable, function(data){
            var price = parseFloat($('.MSRP').text());
            var jsonret = data.split("::::");
            for(i = 0; i<jsonret.length-1; i++){
                jsonret[i] = JSON.parse(jsonret[i]);
                $('.compTable').append("<tr><td>" + (i+1) + "</td><td>" + jsonret[i].BranchNum + "</td><td>" + ((price) - (price * ((jsonret[i].BranchDiscountRate)/100))).toFixed(2) + "</td><td>" + jsonret[i].Stock + "</td><td>" + jsonret[i].BranchDiscountRate+"%</td></tr>");
            }
        })
    }
}

function onHover(){
    $(window).bind("load", function() {
        $("[rel='tooltip']").tooltip();    
 
        $('.thumbnail').hover(
        function(){
        $(this).find('.caption').slideDown(250); //.fadeIn(250)
	},  
            function(){
                $(this).find('.caption').slideUp(250); //.fadeOut(205)
        }); 
    });
}

function addBranchEntry(num, name, address, pnum, rowCount){
    $(".row" + rowCount).append("<div class=\"content\"><div class=\"col-md-7\"><a href=\"#\"><img class=\"img-responsive\" src=\"img/branches/" + num +".png\" alt=\"\"></a></div><div class=\"col-md-5\"><h2 style=\"color: black;\">"+ name +"</h2><h5><label class=\"address\">"+ address +"</label></h5><h5><label class=\"phoneNumber\">"+ pnum +"</label></h5><p></p><a class=\"btn btn-primary\" href=\"branchProducts.html?branch="+ num +"\">View Products <span class=\"glyphicon glyphicon-chevron-right\"></span></a><hr></div></div>");
}

function allBranchesScript(){
    
    $.get('allBranchesRetrieve', function(data){
        data = data.split("::::");
        dataIn = [];
        console.log(data);
        for(i = 0; i<(data.length-1); i++){
            dataIn[i] = JSON.parse(data[i]);
        }
        console.log(dataIn);
        rowCount = 0;
    
        for(i = 0; i<dataIn.length; i++){
            addRow(rowCount);
            JSONInput = dataIn[i];
            addBranchEntry(JSONInput.branchNum, JSONInput.branchName, JSONInput.branchAddress, JSONInput.branchPhoneNum, rowCount);

                    
            rowCount = rowCount + 1;
            closeDiv();
        }
    });
};