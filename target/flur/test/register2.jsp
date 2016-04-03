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
	<form enctype="multipart/form-data" action="/api/user/updateInfo"
		method="post">
		<ul>
			<li>
				userId：<input name="loginUserId" />
			</li>
			<li>
				token：<input name="loginUserToken" />
			</li>
			<li>
				生日：<input name="birthday" />
			</li>
			<li>
				职业：<input name="job" />
			</li>
			<li>
				头像：<input type="file" name="photo" />
			</li>
			<li>
				<input type="submit" value="提交" />
			</li>
		</ul>
	</form>
</body>
</html>