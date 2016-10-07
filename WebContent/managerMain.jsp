<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import = "java.util.*, com.jojo.model.Student"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员界面</title>
</head>
<body>

<div></div>
<div>
	<table align = "center">
	<tr>
		<th>学号</th>
		<th>姓名</th>
		<th>班级</th>
		<th>专业</th>
		<th>系科</th>
		<th>账号</th>
	</tr>
		<% 
			List<Student> students =  (List<Student>)session.getAttribute("students"); 
			for(Student s : students){
				%>
				<tr>
					<td><%out.print(s.getStudentID()); %></td>
					<td><%out.print(s.getStudentName()); %></td>
					<td><%out.print(s.getClassName()); %></td>
					<td><%out.print(s.getMajor()); %></td>
					<td><%out.print(s.getDepartment()); %></td>
					<td><%out.print(s.getUserID()); %></td>
				</tr>
				<%
			}
		%>
	</table>
	
	<form action = "studentDelete">
		<table>
			<tr>
				<td><input type = "text" id = "deleteNumber" name = "deleteNumber" /></td>
				<td><input type = "submit" value = "删除" /></td>
			</tr>
			<tr><td colspan = "2">删除格式：1,2,3....</td></tr>
		</table>
	</form>
</div>
</body>
</html>