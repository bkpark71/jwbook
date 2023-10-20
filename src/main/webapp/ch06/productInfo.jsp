<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>
<h2>Product 상세정보</h2>
<form action="/pcontrol?action=update" method="post">
<ul>
    <li>id : ${p.id}</li>
    <li>name : ${p.name}</li>
    <li>maker : ${p.maker}</li>
    <li>price : ${p.price}</li>
    <li>date : ${p.date}</li>
</ul>
    <input type="text" hidden value="${p.id}" name="id">
    <input type="submit" value="수정하기">
</form>
</body>
</html>
