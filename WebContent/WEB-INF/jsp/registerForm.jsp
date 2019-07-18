<%--
システム名    ：社員管理システム
jspファイル名 ：registerForm
処理概要      ：登録したい社員の情報を入力する
プロジェクト名：HrsmUcs(社員登録)
作成者        ：ティンザーテッ
作成日付      ：2019/06/25(火)
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="jp.ucs.model.Dept"%>

<!DOCTYPE html >
<html>
<head>

<meta charset="UTF-8">
<link rel="stylesheet" style="text/css" href="/HrsmUcs/include.css">
<title>社員管理システム</title>
</head>
<body>
	<jsp:include page="/title.jsp"></jsp:include>
	<jsp:include page="/header.jsp"></jsp:include>

	<h1>社員登録</h1>

	<form action="/HrsmUcs/RegisterEmp" method="post">
		<table>
			<tr>
				<th>氏名</th>

				<td><input type="text" name="empName"
					value="${registerEmp.empName}"></td>
			</tr>

			<tr>
				<td>　</td>
				<c:if test="${not empty errorMsg1 }">

					<td style="text-align: left"><font color="#EE0000">(${errorMsg1})</font></td>
				</c:if>
			</tr>

			<tr>
				<th>ふりがな</th>

				<td><input type="text" name="ruby" value="${registerEmp.ruby}"></td>
			</tr>

			<tr>
				<td>　</td>
				<c:if test="${not empty errorMsg2 }">

					<td style="text-align: left"><font color="#EE0000">(${errorMsg2})
					</font></td>
				</c:if>
			</tr>


			<tr>
				<th>部門</th>

				<td><select name="dept" size="1">
						<option value="${registerEmp.dept.deptId}">${registerEmp.dept.deptName }</option>
						<c:forEach var="dept" items="${deptMap}">
							<option value="${dept.key}">${dept.value}</option>
						</c:forEach>
				</select></td>
			</tr>

			<tr>
				<td>　</td>
				<c:if test="${not empty errorMsg3 }">
					<td style="text-align: left"><font color="#EE0000">(${errorMsg3})
					</font></td>
				</c:if>
			</tr>

			<tr>
				<th>パスワード</th>

				<td><input type="password" name="pass"
					value="${registerEmp.pass}"></td>
			</tr>

			<tr>
				<td>　</td>
				<c:if test="${not empty errorMsg4 }">
					<td style="text-align: left"><font color="#EE0000">(${errorMsg4})
					</font></td>
				</c:if>
			</tr>

			<tr>
				<th>入社年月日</th>

				<td><input type="text" name="entryDate"
					placeholder="yyyy/MM/dd" value="${registerEmp.entryDate }"></td>
			</tr>

			<tr>
				<td>　</td>
				<c:if test="${not empty errorMsg5}">
					<td style="text-align: left"><font color="#EE0000">(${errorMsg5})
					</font></td>
				</c:if>
			</tr>

			<tr>
				<td>　</td>
				<td align="center"><input type="submit" value="登録"></td>
			</tr>
		</table>
	</form>
	<br><jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>







