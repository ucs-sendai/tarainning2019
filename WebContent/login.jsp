<%--
 システム名：社員管理システム
 ファイル名：login
 処理概要：ログイン画面操作
 プロジェクト名：HrsmUcs(共通)
 作成者：スピェッウォン
 作成日付：2019/06/21(金)
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" style="text/css" href="/Hrsm/include.css"/>
<meta charset="UTF-8">
<title>社員管理システム</title>
</head>
<body>
<jsp:include page="/title.jsp"/>
<form action="/Hrsm/EntryMenu" method="post">
<table>
    <tr>
        <th>社員ID</th>
        <td><input type="text" name="emp_id"></td>
    </tr>
    <tr>
        <th>パスワード</th>
        <td><input type="password" name="pass"></td>
    </tr>
    <tr>
     <!--  <c:choose>
            <c:when test="${not empty errorMsg}">-->

              <!-- <td colspan="2" > <c:out value="(${errorMsg})" /></td>-->
         <!-- </c:when> -->
          <!--  <c:otherwise>
                <td colspan="2"><p></p></td>
            </c:otherwise>
        </c:choose> -->
    </tr>
    <tr>
        <td colspan="2" style="text-align:center"><input type="submit" value="ログイン"></td>
    </tr>
</table>
</form>
<jsp:include page="/footer.jsp"/>
</body>

</html>