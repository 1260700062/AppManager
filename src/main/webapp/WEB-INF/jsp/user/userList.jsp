<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>userList</title>
</head>
<body>
	<!-- 查询 -->
	<form action="searchUser" method="post">
		<input type="text" name="userName" value="${param.userName }">
		<input type="submit">
	</form>
	<br/>
	<!-- 展示 userlist-->
	<table border="1">
		<c:forEach items="${userlist }" var="user">
			<tr>
					<td>${user.userName } </td>
					<td>${user.userCode }</td>
					<td>${user.phone }</td>
			</tr>
		</c:forEach>
	</table>
	
	
</body>
</html>