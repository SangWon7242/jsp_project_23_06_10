<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.Map" %>

<%
Map<String, Object> articleRow = (Map<String, Object>) request.getAttribute("articleRow");
%>

<!doctype html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>JSP BOARD - 게시물 수정</title>
</head>
<body>

  <h1>게시물 수정</h1>

  <%@include file="../part/topBar.jspf"%>

  <form action="doModify" method="POST">
    <input type="hidden" name="id" value="<%= Integer.parseInt(request.getParameter("id")) %>">

    <div>
      제목 : <input autocomplete="off" type="text" name="title" placeholder="제목을 입력해주세요." value="<%= (String) articleRow.get("title") %>">
    </div>
    <div>
      내용 : <textarea autocomplete="off" type="text" name="content" placeholder="내용을 입력해주세요."><%= (String) articleRow.get("content") %></textarea>
    </div>

    <div>
      <button type="submit">수정</button>
      <a href="list">취소</a>
    </div>
  </form>
</body>
</html>