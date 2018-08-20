<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
<link rel="stylesheet" href="css/loginStyle.css">
</head>
<body>
	<div class="form-wrapper">
		<h1>Sign In</h1>
		<form>
			<div class="form-item">
				<label for="userid"></label> <input type="text" name="userid"
					required="required" placeholder="User ID"></input>
			</div>
			<div class="form-item">
				<label for="password"></label> <input type="password"
					name="password" required="required" placeholder="Password"></input>
			</div>
			<div class="button-panel">
				<input type="submit" class="button" title="Sign In" value="Sign In"></input>
			</div>
		</form>
		<div class="form-footer">
			<p>
				<a href="#">Create an account</a>
			</p>
			<p>
				<a href="#">Forgot password?</a>
			</p>
		</div>
	</div>
</body>
</html>