<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%
    String[]weatherArray = {"晴れ","曇り","雨"};
    int index = (int)(Math.random());
    String weather = weatherArray[index];

    %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>お天気予想</title>
</head>
<body>
<h1>お天気を予想します</h1>
<p>天気は、<%= weather %>です。</p>
</body>
</html>