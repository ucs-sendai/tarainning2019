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
<link rel="stylesheet" style="text/css" href="/HrsmUcs/include.css">
<meta charset="UTF-8">
<title>社員情報変更</title>
</head>
<body>
    <jsp:include page="/title.jsp" />
    <jsp:include page="/header.jsp" />
    <h1>社員情報変更</h1>
    <form action="/HrsmUcs/ChangeEntry" method="post">
        <table>
            <c:choose>
                <c:when test="${empty afterEmp }">
                    <tr>
                        <td>社員ID</td>
                        <td><c:out value="${beforeEmp.empId}" /></td>
                    </tr>
                    <tr>
                        <td>氏名</td>
                        <td><input type="text" name="name"
                            value="${beforeEmp.empName}"></td>
                    </tr>
                    <tr>
                        <td>ふりがな</td>
                        <td><input type="text" name="ruby" value="${beforeEmp.ruby}"></td>
                    </tr>
                    <tr>
                        <td>部門</td>
                        <td><select style="width: 154px;" name="dept">
                                <c:forEach var="dept" items="${deptMap}">
                                    <c:choose>
                                        <c:when test="${beforeEmp.dept.deptId == dept.key}">
                                            <option value="${dept.key}" selected>${dept.value}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${dept.key}">${dept.value}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                        </select></td>
                    </tr>
                    <tr>
                        <td>パスワード</td>
                        <td><input type="password" style="width: 150px;" name="pass"
                            value="${beforeEmp.pass}"></td>
                    </tr>
                    <tr>
                        <td>入社年月日</td>
                        <td><input type="text" name="date"
                            value="${beforeEmp.entryDate}"></td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td>社員ID</td>
                        <td><c:out value="${afterEmp.empId}" /></td>
                    </tr>
					<tr>
						<td>氏名</td>
						<td><input type="text" name="name"
							value="${afterEmp.empName}"></td>
						<c:choose>
							<c:when test="${ not empty errorMsgMap.name}">
								<tr>
									<td></td>
									<td><font color="#EE0000"><c:out value="${errorMsgMap.name}" /></font></td>
								</tr>
							</c:when>
						</c:choose>
					</tr>
					<tr>
                        <td>ふりがな</td>
                        <td><input type="text" name="ruby" value="${afterEmp.ruby}"></td>
                        <c:choose>
                            <c:when test="${ not empty errorMsgMap.ruby}">
                                <tr>
                                    <td></td>
                                    <td><font color="#EE0000"><c:out value="${errorMsgMap.ruby}" /></font></td>
                                </tr>
                            </c:when>
                        </c:choose>
                    </tr>
                    <tr>
                        <td>部門</td>
                        <td><select style="width: 154px;" name="dept">
                                <c:forEach var="dept" items="${deptMap}">
                                    <c:choose>
                                        <c:when test="${afterEmp.dept.deptId == dept.key}">
                                            <option value="${dept.key}" selected>${dept.value}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${dept.key}">${dept.value}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                        </select></td>
                    </tr>
                    <tr>
                        <td>パスワード</td>
                        <td><input type="password" style="width: 144px;" name="pass"
                            value="${afterEmp.pass}"></td>
                            <c:choose>
                            <c:when test="${ not empty errorMsgMap.pass}">
                                <tr>
                                    <td></td>
                                    <td><font color="#EE0000"><c:out value="${errorMsgMap.pass}" /></font></td>
                                </tr>
                            </c:when>
                        </c:choose>
                    </tr>
                    <tr>
                        <td>入社年月日</td>
                        <td><input type="text" name="date"
                            value="${afterEmp.entryDate}"></td>
                            <c:choose>
                            <c:when test="${ not empty errorMsgMap.date}">
                                <tr>
                                    <td></td>
                                    <td><font color="#EE0000"><c:out value="${errorMsgMap.date}" /></font></td>
                                </tr>
                            </c:when>
                        </c:choose>
                    </tr>
                </c:otherwise>
            </c:choose>
        </table>
        <input type="submit" value="登録">
    </form>
    <jsp:include page="/footer.jsp" />
</body>
</html>