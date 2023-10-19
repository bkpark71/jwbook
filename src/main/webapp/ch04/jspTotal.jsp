<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<h2>JSP 생성예제</h2>
<h4>1. jsp 주석</h4>
<!-- HTML 주석 : 화면에서는 보이지 않지만, 소스보기에서는 보임 -->
<%-- JSP 주석 : 화면과 소스보기에서 둘다 보이지 않음 --%>
<hr>
<h4>2. 배열과 함수 선언</h4>
<%!
    String[] members = {"홍길동","김연아","손흥민","박지성"};
    int num1 = 10;

    int calc(int num2) {
        return num1 + num2;
    }
%>
<hr>
<h4>3. 함수의 사용</h4>
    calc(10)의 결과는 <%= calc(10) %>
<hr>
<h4>4. include 지시어 사용</h4>
    <%@ include file = "../hello.jsp" %>
<hr>
<h4>5. 스크립트릿 (배열 데이터 출력)</h4>
    <ul>
    <% for(String member : members) { %>
        <li><%= member %></li>
    <% } %>
    </ul>
<hr>
</body>
</html>