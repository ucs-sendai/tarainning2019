<%--
システム名：社員管理システム
ファイル名：deleteEmpMain
処理概要  ：削除する社員を選ぶ
プロジェクト名：Hrsm(社員削除)
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
<title>社員削除</title>
</head>
<body>
	<jsp:include page="/title.jsp" />
	<jsp:include page="/header.jsp" />
	<h1>削除社員選択</h1>
	<form action="/Hrsm/DeleteEmp" method="get">
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

			<tr>
				<td><h3>資格</h3></td>
				<td>&emsp;<select style="width: 154px;" name="licenseId">
						<option value=""></option>
						<c:forEach var="license" items="${licenseMap}">
							<c:choose>
								<c:when
									test="${searchCondition.license.licenseId == license.key}">
									<option value="${license.key}" selected>${license.value}</option>
								</c:when>
								<c:otherwise>
									<option value="${license.key}">${license.value}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
				</select>
				</td>
				<td></td>
			</tr>
		</table>
		<pre>　　　　　　　　　　　　　　　　　　　　<input type="submit" value="検索"></pre>
		<input type="hidden" name="action" value="searched">
	</form>

	${message}

	<c:if test="${not empty searchResultList }">
		<form name="chBox" action="/Hrsm/DeleteEmp" method="post">
			<div class="table1Div"><table class="table1">
				<tr>
					<th>削除</th>
					<th>社員ID</th>
					<th>氏名</th>
					<th>部門</th>
				</tr>

				<c:forEach var="searchResult" items="${searchResultList}">
					<tr>
						<td><input type="checkBox" name="emp"
							value="${searchResult.empId}"></td>
						<td>${searchResult.empId}</td>
						<td>${searchResult.empName}</td>
						<td>${searchResult.dept.deptName}</td>
					</tr>
				</c:forEach>
			</table>
			</div>
			<div>
			<pre>　　　　　　　　　　　　　　　　　　　　<input type="submit" value="削除"></pre>
			</div>
		</form>
	</c:if>
	<jsp:include page="/footer.jsp" />
</body>
</html>