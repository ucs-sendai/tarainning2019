<%--
システム名：社員管理システム
ファイル名：deleteEmpResult
処理概要：削除完了を知らせる
プロジェクト名:HrsmUcs(社員削除)
氏名    ：松井彩乃
作成日付：2019/06/21(金)
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社員削除</title>

<link rel="stylesheet" style="text/css" href="/HrsmUcs/include.css">

</head>
<body>

<jsp:include page="/title.jsp"/>
<jsp:include page="/header.jsp"/>

<h1>削除完了</h1>
<h3>社員を削除しました</h3>


<a href="/HrsmUcs/DeleteEmp">戻る</a>
<jsp:include page="/footer.jsp"/>

</body>
</html>