<html>
	<head>
		<title>Login Page</title>
	</head>
	<body>
		<center>
			<h1>Welcome to Wissen Banking Services</h1>
		</center>
		<h2>Please Login</h2>
		<form action="signin" method="POST">
			Customer ID : <input type="text" name ="cid"><br>
			Password : <input type="password" name ="pwd"><br>
			<br>
			<input type="submit" value="SUBMIT">
			<input type="button" value="EXIT"> 
		</form>
		<h2>${error} </h2>
		<h4> Don’t have an account? <a href="register">Register here</a> </h4>
	</body>
</html>

