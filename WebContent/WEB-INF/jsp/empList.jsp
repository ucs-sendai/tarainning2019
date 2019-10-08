<%--
システム名    ：社員管理システム
jspファイル名 ：empList
処理概要      ：社員一覧表を表示させる
プロジェクト名：HrsmUcs(社員一覧)
作成者        ：倉石寿洋
作成日付      ：2019/06/25(火)
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社員一覧</title>
<link rel="stylesheet" style="text/css" href="/Hrsm/include.css">
<link rel="stylesheet" style="text/css" href="/Hrsm/include404.css">

</head>
<body>
	<jsp:include page="/title.jsp"></jsp:include>
	<jsp:include page="/header.jsp"></jsp:include>
	<h1>社員一覧</h1>
	<div class="table1Div" >
		<table class="table1">
			<tr>
				<th style="width: 24%;">社員ID</th>
				<th style="width: 38%;">氏名</th>
				<th style="width: 38%;">部門</th>
			</tr>
			<c:forEach var="employee" items="${employeeList}">
				<tr>
					<td style="text-align: center"><c:out
							value="${employee.empId}" /></td>
					<td><c:out value="${employee.empName}" /></td>
					<td><c:out value="${employee.dept.deptName}" /></td>
				</tr>
			</c:forEach>

		</table>
	</div>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>