<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<body>
<h2>로그인 성공</h2>
<%
    String id = "";
    id = session.getAttribute("id").toString();
    //out.println(id);
    if(id.equals("")) {
        out.println("id를 입력해주세요");
    } else {
        out.println(id + "님 반갑습니다.");
    }
%>
</body>
</html>
