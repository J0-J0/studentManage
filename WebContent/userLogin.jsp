<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>

<script type="text/javascript">
	function reset() {
		var userName = document.getElementById("userName");
		var password = document.getElementById("password");
		userName.value = "";
		password.value = "";
	}
</script>

<style type="text/css">
.d {
	background-image: url("images/userLoginBackground.jpg");
	border: 2px black solid;
	height: 751px;
	width: 1240px;
	background-repeat: no-repeat;
	margin-left: 45px;
}

.pos{
	position: relative;
	top: 275px;
}
</style>

</head>
<body>
	<div class="d">
		<div class = "pos">
			<form action="userLogin" method="post">
				<table align="center" >
					<tr>
						<th colspan="2">用户登录</th>
					</tr>
					<tr>
						<td>用户名：</td>
						<td><input type="text" id="userName" name="userName"  value = "${userName} "/></td>
					</tr>
					<tr>
						<td>密 码：</td>
						<td><input type="password" id="password" name="password" value = "${password}" /></td>
					</tr>
					<tr>
						<td><input type="submit" value="提交"  /></td>
						<td>
							<input type="button" value="重置" onclick="reset()"style = "margin-left: 30px; " />
							<a href = "userRegister.jsp "><input type = "button" value = "注册"  style = "margin-left: 50px;" /></a>
						</td>
					</tr>
					<tr>
						<td colspan = "2"><font color = "red">${error }</font></td>
					</tr>
				</table>
			</form>
		</div>
		<div style = "margin-top: 475px; ">
			<a href = "managerLogin.jsp"><img alt="小表情" src="images/2.jpeg" ></a>
			<p>用户名是 testUser1</p>
			<p>密码是  123456</p>
		</div>
	</div>
</body>
</html>