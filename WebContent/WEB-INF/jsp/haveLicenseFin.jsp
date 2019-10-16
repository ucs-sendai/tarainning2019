<%--
システム名    ：社員管理システム
jspファイル名 ：haveLicenseFin.jsp
処理概要      ：登録完了の表示
プロジェクト名：Hrsm(資格取得)
作成者        ：小西香菜子
作成日付      ：2019/10/08(火)
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

	<h1>資格登録完了</h1>
	<h3>
		社員ID :
		<c:out value="${empId}"></c:out>

		<c:out value="${empName}"></c:out>
		さん
	</h3>


	<h3>の資格を登録しました。</h3>　


	<jsp:include page="/footer.jsp"></jsp:include>

</body>
</html>