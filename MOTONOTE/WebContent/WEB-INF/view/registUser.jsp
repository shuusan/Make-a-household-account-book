<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録 - MOTONOTE -</title>
<link rel="stylesheet" href="css/loginStyle.css">
</head>
<body>
	<div class="form-wrapper">
		<h1>新規登録</h1>
		<form action="/MOTONOTE/Account" method="post">
			<p style="color: blue"><%=session.getAttribute("comment") %></p>
			<div class="form-item">
				<label for="userid"></label> <input type="text" name="userid"
					required="required" placeholder="User ID"></input>
			</div>
			<div class="form-item">
				<label for="password"></label> <input type="password"
					name="password" required="required" placeholder="Password" pattern="{8,}"></input>
			</div>
			<div class="form-item">
				<label for="email"></label> <input type="email" name="email"
					required="required" placeholder="E-mail"></input>
			</div>
			<div class="button-panel">
				<input type="submit" class="button" title="Registration" value="Registration"></input>
			</div>
		</form>
		<div class="form-footer">
			<p>
				<a href="/MOTONOTE/Start">戻る</a>
			</p>
		</div>
	</div>
</body>
</html>