<%--
システム名：社員管理システム
ファイル名：adminMenu
処理概要  ：メニュー画面(管理)
プロジェクト名：HrsmUcs(共通)
作成者    ：スピェッウォン
作成日付  ：2019/06/19(水)
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<link rel="stylesheet" style="text/css" href="/Hrsm/include.css"/>
<meta charset="UTF-8">
<title>メニュー画面(管理者)</title>
</head>
<body>
	<form action="/Hrsm/EntryMenu" method="post">
		<jsp:include page="/title.jsp"></jsp:include>
		<table>
			<tr>
				<td></td>
				<td><c:out value="(${employee.empName})" /><br> <a
					href="/Hrsm/Logout">ログアウト</a></td>
			</tr>
			<tr>
				<td style="text-align: left">
				    <a href="/Hrsm/EmpList">社員一覧</a><br>
					<a href="/Hrsm/SearchMain">社員検索</a><br>
					<a href="/Hrsm/RegisterEmp">社員登録</a><br>
					<a href="/Hrsm/DeleteEmp">社員削除</a><br>
				    <a href = "/Hrsm/ChangeStart">社員情報変更</a><br>
					<a href = "/Hrsm/HaveLicenseSelect">資格取得</a><br>
					部門管理(工事中)<br>
					資格管理(工事中)<br>
				</td>
			</tr>
		</table>
	</form>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>