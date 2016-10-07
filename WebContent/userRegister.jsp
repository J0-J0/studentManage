<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import = "com.jojo.model.User, com.jojo.model.Student" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生信息页面</title>

<script type="text/javascript">

function register(){
	form.action = "userRegister";
	form.submit();
}
function update(){
	form.action = "userUpdate";
	form.submit();
}

</script>

<style type="text/css">
div {
	border: 2px black solid;
	margin: 5px auto auto auto;
}
</style>

</head>
<body>
	<%
		User currentUser = (User)session.getAttribute("currentUser");
		Student currentStudent = (Student)session.getAttribute("currentStudent");
	%>

	<div style="height: 100px; text-align: center;">还没想好放什么</div>


	<div>
		<form action="">
			<table align = "center" cellspacing="20px">
			<tr><th colspan = "2">学生信息<th></tr>
			<tr>
				<td>用户名：</td>
				<td><input type = "text" id = "userName" name = "userName" value = "<%if(currentUser != null) out.print(currentUser.getUserName()); %>" /></td>
			</tr>
			<tr>
				<td>密   码：</td>
				<td><input type = "password" id = "password" name = "password" value = "<%if(currentUser != null) out.print(currentUser.getPassword()); %>" /></td>
			</tr>
			<tr>
				<td>学   号：</td>
				<td><input type = "text" id = "studentID" name = "studentID" value = "<%if(currentStudent != null) out.print(currentStudent.getStudentID()); %>" /></td>
			</tr>
			<tr>
				<td>姓   名：</td>
				<td><input type = "text" id = "studentName" name = "studentName" value = "<%if(currentStudent != null) out.print(currentStudent.getStudentName()); %>" /></td>
			</tr>
			<tr>
				<td>班级名：</td>
				<td><input type = "text" id = "className" name = "className" value = "<%if(currentStudent != null) out.print(currentStudent.getClassName()); %>" /></td>
			</tr>
			<tr>
				<td>专   业：</td>
				<td><input type = "text" id = "major" name = "major" value = "<%if(currentStudent != null) out.print(currentStudent.getMajor()); %>" /></td>
			</tr>
			<tr>
				<td>系   科：</td>
				<td><input type = "text" id = "department" name = "department" value = "<%if(currentStudent != null) out.print(currentStudent.getDepartment()); %>" /></td>
			</tr>
				<tr>
					<td><input type="button" value="新生注册" onclick="register()" /></td>
					<td><input type="button" value="老生更新" onclick="update()" /></td>
				</tr>
			</table>


		</form>
	</div>
</body>
</html>