<%--
システム名    ：社員管理システム
jspファイル名 ：registerFin
処理概要      ：登録完了の表示
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
	<jsp:include page="/header.jsp"></jsp:include>

	<h1>登録完了</h1>
	<h3>
		社員ID :
		<c:out value="${registerEmp.empId}"></c:out>

		<c:out value="${registerEmp.empName}"></c:out>
		さん
	</h3>
	<h3>を登録しました。</h3>

	<a href="/Hrsm/RegisterEmp">戻る</a>
	<jsp:include page="/footer.jsp"></jsp:include>

</body>
</html>