<%--
 システム名：社員管理システム
 ファイル名：delete
 処理概要：削除確認を知らせる
 プロジェクト名：Hrsm(社員削除)
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
	<h1>削除社員確認</h1>
	<h3>以下の社員を削除しますか?</h3>
	<div class="table1Div"><table class="table1">
		<tr>
			<th>社員ID</th>
			<th>氏名</th>
			<th>部門</th>
		</tr>
		<c:forEach var="delete" items="${deleteEmpList }">
			<tr>
				<td><c:out value="${delete.empId}" /></td>
				<td><c:out value="${delete.empName}" /></td>
				<td><c:out value="${delete.dept.deptName}" /></td>
			</tr>
		</c:forEach>
	</table>
	<a href="/Hrsm/DeleteEmp?action=no">いいえ</a>
	<a href="/Hrsm/DeleteEmp?action=yes">はい</a>
	</div>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>