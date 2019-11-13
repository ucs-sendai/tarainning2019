<%--
システム名 社員管理システム
クラス名 changeEmp.jsp
処理概要 社員情報変更の画面
作成日 2019年6月25日
作成者名 高原 優
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" style="text/css" href="/Hrsm/include.css">
<meta charset="UTF-8">
<title>社員情報変更</title>
</head>
<body>
	<jsp:include page="/title.jsp" />
	<jsp:include page="/header.jsp" />
	<h1>社員情報変更</h1>
	<form action="/Hrsm/Entry" method="post">
		<table>

			<tr>
				<td>社員ID</td>
				<td><c:out value="${beforeEmp.empId}" /></td>
			</tr>
			<tr>
				<td>氏名</td>
				<%-- <td><c:out value="${beforeEmp.empName}" /></td> --%>
				<td><input type="text" name="name" value="${beforeEmp.empName}"></td>

				<%--
				<c:choose>
					<c:when test="${ not empty errorMsgMap.name}">
						<tr>
							<td></td>
							<td><font color="#EE0000"><c:out
										value="${errorMsgMap.name}" /></font></td>
						</tr>
					</c:when>
				</c:choose>
				--%>
			</tr>
			<tr>
				<td>ふりがな</td>
				<td><input type="text" name="ruby" value="${beforeEmp.ruby}"></td>
				<%--
				<c:choose>
					<c:when test="${ not empty errorMsgMap.ruby}">
						<tr>
							<td></td>
							<td><font color="#EE0000"><c:out
										value="${errorMsgMap.ruby}" /></font></td>
						</tr>
					</c:when>
				</c:choose>
				--%>
			</tr>
			<tr>
			<tr>
				<th>部門</th>
				<td><select name="dept" size="1">
						<option value="""selected">${beforeEmp.dept.deptName}</option>
						<c:forEach var="dept" items="${deptMap}">
							<option value="${dept.key}">${dept.value}</option>
						</c:forEach>
				</select></td>
			</tr>


			<%--
				<td><select name="dept" size="1">
						<option value="null"></option>
						<option value="zaimu">管理本部経理財務</option>
						<option value="kannrikikaku">管理本部営業企画</option>
						<option value="eikikaku">営業部営業企画</option>
						<option value="syougai">営業部渉外</option>
						<option value="zaimu">システム本部金融</option>
						<option value="sangyou">システム本部産業</option>
						<option value="osaka">大阪事業所</option>
						<option value="sendai">仙台事業所</option>
						<c:forEach var="dept" items="${deptMap}">
							<option value="${dept.key}">${dept.value}</option>
						</c:forEach>
				</select></td>
--%>
			</tr>

			<%--
				<c:forEach var="dept" items="${deptMap}">
					<option value="${dept.key}">${dept.value}</option>
				</c:forEach>
				--%>
			<tr>
				<td>パスワード</td>
				<td><input type="password" style="width: 144px;" name="pass"
					value="${beforeEmp.pass}"></td>
				<%--
				<c:choose>
					<c:when test="${ not empty errorMsgMap.pass}">
						<tr>
							<td></td>
							<td><font color="#EE0000"><c:out
										value="${errorMsgMap.pass}" /></font></td>
						</tr>
					</c:when>
				</c:choose>
				--%>
			</tr>
			<tr>
				<td>入社年月日</td>
				<td><input type="text" name="date"
					value="${beforeEmp.entryDate}"></td>
				<%--
				<c:choose>
					<c:when test="${ not empty errorMsgMap.date}">
						<tr>
							<td></td>
							<td><font color="#EE0000"><c:out
										value="${errorMsgMap.date}" /></font></td>
						</tr>
					</c:when>
				</c:choose>
				--%>
			</tr>


		</table>
		<input type="submit" value="変更">

		<%-- <a href="/Hrsm/Entry?action=yes">変更</a> --%>

		<%-- <p><a href="/Hrsm/ChangeComp">変更</a></p>
		<form action="yes" method="get"></form> --%>
	</form>
	<c:forEach var="message" items="${message}">
		<p style="text-align: left">
			<font color="#EE0000">(${message})</font>
		</p>
	</c:forEach>
	<%--
	${message}
--%>
	<jsp:include page="/footer.jsp" />
</body>
</html>