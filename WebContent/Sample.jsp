<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "java.util.Date,java.text.SimpleDateFormat" %>

    <%
    //運勢をランダムで決定
    String[]luckArray = {"超スッキリ","スッキリ","最悪"};
    int index = (int)(Math.random() * 3);
    String luck = luckArray[index];

    //実行日を取得
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
    String today = sdf.format(date);
    %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>スッキリ占い</title>
</head>
<body>
<p><%= today %>の運勢は<%= luck %>です</p>
</body>
</html>