<!DOCTYPE html>
<html lang="en">
	<head>
		<title>S.I.D.N.E.E.</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="description" content="">
		<meta name="author" content="">
		<link rel="stylesheet" type="text/css" href="css/loginPage.css">
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<!-- jQuery -->
		<script src="js/jquery2.js"></script>
		<!-- Bootstrap Core JavaScript -->
		<script src="js/bootstrap.js"></script>
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
					<a href="index.html" class="text-center go-back"><span class="glyphicon glyphicon-chevron-left"></span>Go back to home</a>
				</div>
			</div>
		</div>
	</body>
</html>