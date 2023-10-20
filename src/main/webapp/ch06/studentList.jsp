<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>
<h2>Product List</h2>
<hr>
<table>
    <tr>
        <th>id</th><th>이름</th><th>대학</th><th>생일</th><th>이메일</th>
    </tr>
    <c:forEach var="s" items="${students}">
        <tr>
            <td>${s.id}</td>
            <td>${s.name}</td>
            <td>${s.univ}</td>
            <td>${s.birth}</td>
            <td>${s.email}</td>
            <td><a href="/student?action=update&id=${s.id}">수정하기</a></td>
            <td><a href="/student?action=delete&id=${s.id}">삭제하기</a></td>
        </tr>
    </c:forEach>
</table>
<hr>
<h2>학생 추가</h2>
<hr>
<form action="/student?action=insert" method="post">
<%--    <p>id<input type="text" name="id"></p>--%>
    <p>name<input type="text" name="name"></p>
    <p>univ<input type="text" name="univ"></p>
    <p>birth<input type="text" name="birth"></p>
    <p>email<input type="text" name="email"></p>
    <input type="submit" value="등록">
</form>
</body>
</html>