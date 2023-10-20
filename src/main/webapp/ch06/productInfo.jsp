<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<script>
    function checkDelete(pid){
      let r = confirm("삭제하시겠습니까?");
      if (r) {
         location.href = "/pcontrol?action=delete&id=" + pid;
      }
    }
</script>
<body>
<h2>Product 상세정보</h2>
<ul>
    <li>id : ${p.id}</li>
    <li>name : ${p.name}</li>
    <li>maker : ${p.maker}</li>
    <li>price : ${p.price}</li>
    <li>date : ${p.date}</li>
</ul>
    <a href="/pcontrol?action=update&id=${p.id}">수정하기</a>
    <a href="/pcontrol?action=delete&id=${p.id}">삭제하기</a>
<%--    <button oncick="checkDelete('${p.id}')">삭제하기</button>--%>
</body>
</html>
