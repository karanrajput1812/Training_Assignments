<html>
	<head>
		<title>Register Page</title>
	</head>
	<body>
		<center>
			<h1>Welcome to Wissen Banking Services</h1>
		</center>
		<h2>Please Register</h2>
		<form action="register_emp" method="POST">
			Customer ID : <input type="text" name ="cid"><br>
			Name : <input type="text" name ="uname"><br>
			Password : <input type="password" name ="pwd"><br>
			Confirm Password : <input type="password" name ="cpwd"><br>
			<br>
			<input type="submit" value="SUBMIT">
			<input type="button" value="EXIT"> 
		</form>
		<h2>${error} </h2>
		<h4> Already have an account? <a href="login">Login here</a> </h4>
	</body>
</html>

