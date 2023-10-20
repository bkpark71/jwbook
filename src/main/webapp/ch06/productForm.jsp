<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>
<h2>Product Info update</h2>
<hr>
<form action="/pcontrol?action=update" method="post">
    <input type="text" readonly value="${p.id}" name="id">
    <input type="text" readonly value="${p.name}" name="name">
    <input type="text" readonly value="${p.maker}" name="maker">
    <input type="text" value="${p.price}" name="price">
    <input type="text" readonly value="${p.date}" name="date">
    <input type="submit" value="수정">
</form>
</body>
</html>