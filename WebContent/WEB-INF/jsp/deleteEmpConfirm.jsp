<%--
 システム名：社員管理システム
 ファイル名：delete
 処理概要：削除確認を知らせる
 プロジェクト名：HrsmUcs(社員削除)
 作成者：今村凌
 作成日付：2019/06/20(木)
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" style="text/css" href="/Hrsm/include.css">
<link rel="stylesheet" style="text/css" href="/Hrsm/include404.css">
<jsp:include page="/title.jsp"></jsp:include>
<title>社員削除</title>
</head>
<body>
	<h1>社員削除完了</h1>
	<h3>以下の社員を削除しました</h3>
	<div class="table1Div">
		<table class="table1">
			<tr>
				<th>社員ID</th>
				<th>氏名</th>
				<th>部門</th>
			<tr>
				<td><c:out value="${deleteEmp.empId}" /></td>
				<td><c:out value="${deleteEmp.empName}" /></td>
				<td><c:out value="${deleteEmp.dept.deptName}" /></td>
			</tr>
		</table>
		<%--
		<a href="/Hrsm/EntryMenu">いいえ</a> <a href="/Hrsm/DeleteEmp">はい</a>
		--%>
		<a href="/Hrsm/DeleteEmp">戻る</a>
	</div>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>