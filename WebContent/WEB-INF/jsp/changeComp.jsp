<%--  システム名 社員管理システム
クラス名 changeComp.jsp
処理概要 社員情報変更の完了を知らせるクラス
作成日 2019年6月25日
作成者名 小西香菜子 --%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html >
<html>
<head>
<link rel="stylesheet" style="text/css" href="/Hrsm/include.css">
<meta charset="UTF-8">
<title>変更完了</title>
</head>
<body>
	<jsp:include page="/title.jsp" />
	<jsp:include page="/header.jsp" />
	<h1>変更完了</h1>
	<h3>社員ID<c:out value ="${afterEmp.empId}"/>の社員情報を変更しました。</h3>
	<jsp:include page="/footer.jsp" />
</body>
</html>