<!DOCTYPE html>
<html lang="en">
	<head>

            <link rel="stylesheet" type="text/css" href="css/loginPage.css">
            <%@include file="commonHeadTags.jsp" %>
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-sm-6 col-md-4 col-md-offset-4">
					<h1 class="text-center login-title">Welcome!</h1>
					<div class="account-wall">
						<form class="form-signin" method="post" action="Login">
							<img class="profile-img" src="img/user1.png" alt="">
                                                        <div align="center" style="color: #FF0000;">${errorMessage}</div>
							<input type="text" name="username" class="form-control" placeholder="username" required autofocus>
							<input type="password" name="password" class="form-control" placeholder="password" required>
							<button class="btn btn-lg btn-primary btn-block" type="submit">
								Sign In
							</button>
							<label class="checkbox pull-left" style="margin-left: 20px;">
								<input type="checkbox" value="remember-me">
								Remember me?
							</label>
						</form>
					</div>
					<a href="indexRetrieveProductNBranch" class="text-center go-back"><span class="glyphicon glyphicon-chevron-left"></span>Go back to home</a>
				</div>
			</div>
		</div>
	</body>
</html>