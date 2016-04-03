<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
body {
	font-size: 13px;
}

li {
	padding: 10px;
}
</style>
</head>
<body>
	<form action="/api/game/getPublicGames" method="post">
		<ul>
			<li>
				userId：<input name="loginUserId" />
			</li>
			<li>
				token：<input name="loginUserToken" />
			</li>
			<li>
				<input type="submit" value="提交" />
			</li>
		</ul>
	</form>
</body>
</html>