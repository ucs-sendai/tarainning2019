<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<link rel="stylesheet" style="text/css" href="/Hrsm/include.css"/>
<meta charset="UTF-8">
<title>メニュー画面(管理者)</title>
</head>
<body>
	<form action="/Hrsm/EntryMenu" method="post">
		<jsp:include page="/title.jsp"></jsp:include>

	</form>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>