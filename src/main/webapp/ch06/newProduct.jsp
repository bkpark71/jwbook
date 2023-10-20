<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>
<h2>Product Info 추가</h2>
<hr>
<form action="/pcontrol?action=insert" method="post">
    <p>id<input type="text" name="id"></p>
    <p>name<input type="text" name="name"></p>
    <p>maker<input type="text" name="maker"></p>
    <p>price<input type="text" name="price"></p>
    <p>date<input type="text" name="date"></p>
    <input type="submit" value="등록">
</form>
</body>
</html>