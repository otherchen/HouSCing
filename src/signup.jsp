<!DOCTYPE html>
<html>

<head>

  <meta charset="UTF-8">

  <title>Signup</title>

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
		<form action="/Housing/signup" method="post">
			<h2 class="title">Sign Up</h2>
			<h3 class="subtitle">Please fill out all fields.</h3>
			
			<input type="text" name="first-name" placeholder="First Name" required/>
			<input type="text" name="last-name" placeholder="Last Name" required/>
			<input type="email" name="email" placeholder="Email" required/>
			<input type="password" name="pass" placeholder="Password" required/>
			<input type="password" name="pass-confirm" placeholder="Confirm Password" required/>		

			<input type="submit" name="submit" class="submit action-button" value="Submit" />
		</form>
	</div>
<!-- Finished creating login div -->

</body>
</html>