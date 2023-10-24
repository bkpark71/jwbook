<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>뉴스 상세 보기</title>
<%--    부트 스트랩, css , javascript library --%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</head>
<body>
<h2>${news.title}</h2>
<hr>
<div class="card w-75 mx-auto">
    <img class="card-img-top" src="${news.img}" alt="${news.img}">
    <div class="card-body">
        <h4 class="card-title">Date : ${news.date}</h4>
        <p class="card-text">Content : ${news.content}</p>
    </div>
</div>
<hr>
<a href="javascript:history.back()"><< Back</a>
</body>
</html>
