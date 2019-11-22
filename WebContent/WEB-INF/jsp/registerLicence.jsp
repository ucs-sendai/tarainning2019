<%--
システム名    ：社員管理システム
jspファイル名 ：registerLicence
処理概要      ：登録したい資格の情報を入力する
プロジェクト名：HrsmUcs(資格登録)
作成者        ：高原 優
作成日付      ：2019/11/19(火)
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="jp.ucs.bean.EmployeeBean"%>
<%@ page import="jp.ucs.bean.DeptBean"%>
<%@ page import="jp.ucs.bean.LicenseBean"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" style="text/css" href="/Hrsm/include.css">
<title>社員管理システム</title>
</head>
<body>
	<jsp:include page="/title.jsp"></jsp:include>
	<jsp:include page="/header.jsp"></jsp:include>

	<h1>資格登録</h1>

	<form action="/Hrsm/RegisterLicense" method="post">

		<tr>
			<c:if test="${not empty errorMsg}">
				<c:forEach var="errorMsg" items="${errorMsg}">
					<p style="text-align: left">
						<font color="#EE0000">(${errorMsg})</font>
					</p>
				</c:forEach>

			</c:if>
		</tr>

		<table>

			<tr>
				<td>社員ID</td>
				<%--<td><input type="text" name="empId" value="${EmpInfo.empId}" /><br></td>
			--%>
				<td><c:out value="${EmpInfo.empId}" /></td>

			</tr>

			<tr>
				<td>社員名</td>
				<%-- <td><input type="text" name="empName"
					value="${EmpInfo.empName}" /><br></td>--%>
				<td><c:out value="${EmpInfo.empName}" /></td>
			</tr>

			<tr>
				<td>取得資格</td>
				<td><select name="license" size="1">
						<option value="null"></option>

						<c:forEach var="license" items="${licenseMap}">
							<option value="${license.key}">${license.value}</option>
						</c:forEach>

				</select></td>
			</tr>

			<tr>
				<td>資格取得日</td>
				<td><input type="text" name="licenseData"
					placeholder="yyyy/MM/dd" value=""></td>
			</tr>

			<tr>
				<td></td>
				<td align="right"><input type="submit" value="登録"></td>
			</tr>
		</table>
	</form>
	<br><jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>