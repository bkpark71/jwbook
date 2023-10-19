<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%
    int n1 = Integer.parseInt(request.getParameter("n1"));
    int n2 = Integer.parseInt(request.getParameter("n2"));
    String op = request.getParameter("op");
    int result = 0;
    switch (op) {
      case "+" : result = n1 + n2 ; break;
      case "-" : result = n1 - n2 ; break;
      case "*" : result = n1 * n2 ; break;
      case "/" : result = n1 / n2 ; break;
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<h2>계산기 JSP</h2>
계산 결과 : <%= result %>
</body>
</html>