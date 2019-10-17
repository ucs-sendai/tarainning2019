<%--
システム名：社員管理システム
ファイル名：error
処理概要  ：DBエラー処理
プロジェクト名：HrsmUcs(社員検索)
作成者    ：野島知博
作成日付  ：2019/06/24(月)
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>エラーメッセージ</title>
<link rel="stylesheet" style="text/css" href="/HrsmUcs/include.css">
</head>
<body>
    <form action="/HrsmUcs/EntryMenu" method="post">
        <jsp:include page="/title.jsp"></jsp:include>
    </form>
        <p>${errorMsg}</p>
    <jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>