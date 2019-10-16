<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%--  システム名 社員管理システム
クラス名 changeCheck.jsp
処理概要 社員情報変更確認の画面
作成日 2019年6月25日
作成者名 高原　優 --%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" style="text/css" href="/Hrsm/include.css">
<meta charset="UTF-8">
<title>社員情報変更</title>
</head>
<body>
	<jsp:include page="/title.jsp"/>
	<h1>変更確認</h1>
	<p>社員情報を変更しますか?</p>
	<table>
		<tr>
			<td><h3>社員ID</h3></td>
			<td>:</td>
			<td><c:out value="${afterEmp.empId}" /></td>
		</tr>
		<tr>
			<td><h3>氏名</h3></td>
			<td>:</td>
			<td><c:out value="${afterEmp.empName}"/></td>
		</tr>
		<tr>
			<td><h3>ふりがな</h3></td>
			<td>:</td>
			<td><c:out value="${afterEmp.ruby}"/></td>
		</tr>
		<tr>
			<td><h3>部門</h3></td>
			<td>:</td>
			<td><c:out value="${afterEmp.dept.deptName}"/></td>
		</tr>
		<tr>
			<td><h3>パスワード</h3></td>
			<td>:</td>
			<td><c:out value="${afterEmp.pass}"/></td>
		</tr>
		<tr>
			<td><h3>入社年月日</h3></td>
			<td>:</td>
			<td><c:out value="${afterEmp.entryDate}"/></td>
		</tr>
    </table>

    <a href="/Hrsm/ChangeComp?action=no">いいえ</a>
    <a href="/Hrsm/ChangeComp?action=yes">はい</a>
    <jsp:include page="/footer.jsp" />
</body>
</html>