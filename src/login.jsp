<!DOCTYPE html>
<html>

<head>

  <meta charset="UTF-8">

  <title>Login</title>

  <link href="lib/css/bootstrap.css" rel="stylesheet">
  <link href="lib/css/navbar.css" rel="stylesheet">
  <link href="lib/css/housing.css" rel="stylesheet">
  <script src="lib/js/jquery-1.11.2.min.js"> </script>
  <script src="lib/js/bootstrap.js"> </script>

</head>

<body>

<%@ include file="navbar.jsp" %>

<!-- Creating login div -->
	<div class="form-wrapper">
		<form action="/Housing/login" method="post">
			<h2 class="title">Login</h2>
			<h3 class="subtitle">Welcome Back!</h3>
			<input type="email" name="email" placeholder="Email" required/>
			<input type="password" name="pass" placeholder="Password" required/>

			<!-- creating the remember-me checkbox -->
			<div class="checkbox-container invisible">
			<label class="checkbox-label">
				<input type="checkbox" class="checkbox" value="remember" name="remember"/> Remember me
			</label>
			</div>		

			<input type="submit" name="submit" class="submit action-button" value="Submit" />
			<a class="btn action-button" href="/Housing/signup">Sign up</a>
		</form>
	</div>
<!-- Finished creating login div -->

</body>
</html>