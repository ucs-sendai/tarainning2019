<!--
システム名：社員管理システム
jspファイル名：searchMain.jsp
処理概要：検索条件画面
プロジェクト名：HrsmUcs(社員検索)
作成者 ：蔭山雄志
作成日付 ：2019/06/25(火)
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社員検索</title>
<link rel="stylesheet" style="text/css" href="/Hrsm/include.css">
<link rel="stylesheet" style="text/css" href="/Hrsm/include404.css">
</head>
<body>
	<jsp:include page="/title.jsp"></jsp:include>
	<jsp:include page="/header.jsp"></jsp:include>
	<h1>社員検索</h1>
	<form action="/Hrsm/SearchMain" method="get">
	   <div  style="height:400px; width:600px;">
		<table>
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
						<option value=""></option>
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

			<tr>
				<td></td>
				<td></td>
				<td><input type="submit" value="検索" /></td>
			</tr>
		</table>
		</div>
	</form>
	<c:out value="${message}" />
	<c:if test="${not empty searchResultList}">
	<div class="table1Div">
		<table class="table1">
			<tr>
				<th style="width:24%;">社員ID</th>
				<th style="width:38%;">氏名</th>
				<th style="width:38%;">部門</th>
			</tr>
			<c:forEach var="List" items="${searchResultList }">
				<tr>
					<td style="text-align:center"><c:out value="${List.empId }" /></td>
					<td><c:out value="${List.empName }" /></td>
					<td><c:out value="${List.dept.deptName }" /></td>
				</tr>
			</c:forEach>
		</table>
		</div>
	</c:if>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>