<%--
システム名：社員管理システム
ファイル名：deleteEmpMain
処理概要  ：削除する社員を選ぶ
プロジェクト名：HrsmUcs(社員削除)
作成者    ：石井健斗、安田翔
作成日付  ：2019/06/26(水)
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" style="text/css" href="/Hrsm/include.css">
<link rel="stylesheet" style="text/css" href="/Hrsm/include404.css">
<meta charset="UTF-8">
<title>社員管理システム</title>
</head>
<body>
	<jsp:include page="/title.jsp" />
	<jsp:include page="/header.jsp" />

	<h1>社員削除</h1>

	<form action="/Hrsm/DeleteEmp" method="post">
		<table class="table0">
			<tr>
				<td><h3>社員ID</h3></td>
				<td>&emsp;<input type="text" name="empId"
					value="${searchCondition.empId}"></td>
			</tr>

			<tr>
				<td><h3>氏名</h3></td>
				<td>&emsp;<input type="text" name="empName"
					value="${searchCondition.empName}"></td>
			</tr>
			<tr>
				<td><h3>部門</h3></td>
				<td>&emsp;<select style="width: 154px;" name="deptId">
						<option value="1"></option>
						<c:forEach var="dept" items="${deptMap}">
							<c:choose>
								<c:when test="${searchCondition.dept.deptId == dept.key}">
									<option value="${dept.key}" selected>${dept.value}</option>
								</c:when>
								<c:otherwise>
									<option value="${dept.key}">${dept.value}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
				</select>
				</td>
				<td></td>
			</tr>


		</table>
		<pre>
			<input type="submit" value="削除">
		</pre>
		<%--
		<input type="submit" name="action" value="searched">
		 --%>
	</form>

	${message}


	<jsp:include page="/footer.jsp" />
</body>
</html>