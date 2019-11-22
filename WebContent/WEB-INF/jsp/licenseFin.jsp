<%--
システム名    ：社員管理システム
jspファイル名 ：licenseFin
処理概要      ：資格登録完了の表示
プロジェクト名：HrsmUcs(資格登録)
作成者        ：高原 優
作成日付      ：2019/11/19(火)
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" style="text/css" href="/Hrsm/include.css">
<meta charset="UTF-8">

<title>社員管理システム</title>
</head>
<body>

	<jsp:include page="/title.jsp"></jsp:include>
	<jsp:include page="/header.jsp"></jsp:include>

	<h1>資格登録完了</h1>


	<table>
		<h3>

		<tr>
			<td>社員名</td>
			<td><c:out value="(${license.empName})" /><br></td>
		</tr>

		<tr>
			<td>資格ID</td>
			<td><c:out value="(${license.licenseId})" /><br></td>
		</tr>


		<tr>
			<td>資格</td>
			<td><c:out value="(${license.licenseName})" /><br></td>
		</tr>

		<tr>
			<td>を登録しました</td>
		</tr>


		</h3>
	</table>
	<a href="/Hrsm/RegisterLicense">戻る</a>



	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>