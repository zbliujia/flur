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
	<form action="/api/user/register" method="post">
		<ul>
			<li>
				手机：<input name="phone" />
			</li>
			<li>
				验证码：<input name="verifyCode" />
			</li>
			<li>
				昵称：<input name="name" />
			</li>
			<li>
				性别：<input name="gender" />
			</li>
			<li>
				密码：<input name="password" />
			</li>
			<li>
				<input type="submit" value="提交" />
			</li>
		</ul>
	</form>
</body>
</html>