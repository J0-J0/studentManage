<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员登录</title>

<script type="text/javascript">
	function reset() {
		var managerName = document.getElementById("managerName");
		var password = document.getElementById("password");
		managerName.value = "";
		password.value = "";
	}
</script>

<style type="text/css">
.d {
	border: 2px black solid;
	height: 750px;
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
			<form action="managerLogin" method="post">
				<table align="center" >
					<tr>
						<th colspan="2">管理员登录</th>
					</tr>
					<tr>
						<td>用户名：</td>
						<td><input type="text" id="managerName" name="managerName"  value = "${managerName} "/></td>
					</tr>
					<tr>
						<td>密 码：</td>
						<td><input type="password" id="password" name="password" value = "${password}" /></td>
					</tr>
					<tr>
						<td><input type="submit" value="提交"  /></td>
						<td>
							<input type="button" value="重置" onclick="reset()"style = "margin-left: 30px; " />
						</td>
					</tr>
					<tr>
						<td colspan = "2"><font color = "red">${error }</font></td>
					</tr>
				</table>
			</form>
		</div>
		<div style = "margin-top: 475px; float:left;">
		<img alt="小表情" src="images/2-1.jpg">
			<p>用户名是 testManager</p>
		</div>
		<div style = "margin-top: 475px; float:right; ">
			<img alt="小表情" src="images/2.jpeg">
			<p>密码是  123456</p>
		</div>
	</div>
</body>
</html>