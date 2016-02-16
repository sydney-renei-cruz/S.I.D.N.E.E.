<%-- 
    Document   : errorSearchPage
    Created on : 02 2, 16, 12:14:54 PM
    Author     : user
--%>

<!DOCTYPE html>
<html>
    <head>
        <%@include file="WEB-INF/jsp/navbar.jsp" %>
        <link type="text/css" rel="stylesheet" href="css/main.css"/>
    </head>
    <body>
        <!-- header -->
	<div class="col-lg-12">
            <h1 class="page-header text-center">
		Oops!
            </h1>
            <h2 class="text-center">
                No results found.
            </h2>
        </div>
        <div class="col-lg-12">
            <div id="custom-search-input" style="margin-bottom: 2%;">
							<form class="input-group text-center">
								<input type="text" class="form-control" placeholder="Search me again" name="srch" id="srch">
								<div class="input-group-btn">
									<button class="btn btn-default" type="submit">
										<i class="glyphicon glyphicon-search"></i>
									</button>
								</div>
							</form>
						</div>
        </div>
        <a href="index.jsp" class="text-center go-back"><span class="glyphicon glyphicon-chevron-left"></span>Go back to home</a>
    </body>
</html>
