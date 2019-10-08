<%--
システム名    ：社員管理システム
jspファイル名 ：regConfirm
処理概要      ：申請されたデータの内容を表示して確認する
プロジェクト名：HrsmUcs(社員登録)
作成者        ：ティンザーテッ
作成日付      ：2019/06/25(火)
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html >
<html>
<head>
<link rel="stylesheet" style="text/css" href="/Hrsm/include.css">
<meta charset="UTF-8">
<title>社員管理システム</title>
</head>
<body>
	<jsp:include page="/title.jsp"></jsp:include>
	<h1>登録確認</h1>
	<h3>以下の情報を登録しますか？</h3>
	<table>
		<tr>
			<th><h3>氏名</h3></th>
			<th>:</th>
			<th><h3>
					<c:out value="${registerEmp.empName }"></c:out>
				</h3></th>

		</tr>

		<tr>
			<th><h3>ふりがな</h3></th>
			<th>:</th>
			<th><h3>
					<c:out value="${registerEmp.ruby}"></c:out>
				</h3></th>

		</tr>

		<tr>
			<th><h3>部門</h3></th>
			<th>:</th>
			<th><h3>
					<c:out value="${registerEmp.dept.deptName }"></c:out>
				</h3></th>

		</tr>

		<tr>
			<th><h3>パスワード</h3></th>
			<th>:</th>
			<th><h3>
					<c:out value="${registerEmp.pass}"></c:out>
				</h3></th>

		</tr>

		<tr>
			<th><h3>入社年月日</h3></th>
			<th>:</th>
			<th><h3>
					<c:out value="${registerEmp.entryDate }"></c:out>
				</h3></th>

		</tr>

		<tr>
			<th><a href="/Hrsm/RegisterEmp?action=no">いいえ </a></th>
			<th></th>
			<th><a href="/Hrsm/RegisterEmp?action=yes">はい </a></th>

		</tr>
	</table>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>