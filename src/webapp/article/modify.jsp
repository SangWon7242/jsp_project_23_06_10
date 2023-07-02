<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="com.sbs.exam.jsp.board.dto.Article" %>

<%
Article article = (Article) request.getAttribute("article");
%>

<!doctype html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>JSP BOARD - 게시물 수정</title>

  <!-- daisy UI 불러오기 -->
  <link href="https://cdn.jsdelivr.net/npm/daisyui@3.1.7/dist/full.css" rel="stylesheet" type="text/css" />
  <!-- 테일윈드 불러오기 -->
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>

  <%@include file="../part/topBar.jspf"%>

  <form action="doModify" method="POST">
    <input type="hidden" name="redirectUri" value="../article/detail?id=[NEW_ID]" />
    <input type="hidden" name="id" value="<%= Integer.parseInt(request.getParameter("id")) %>">

    <div>
      제목 : <input autocomplete="off" type="text" name="title" placeholder="제목을 입력해주세요." value="<%= article.getTitle() %>">
    </div>
    <div>
      내용 : <textarea autocomplete="off" type="text" name="content" placeholder="내용을 입력해주세요."><%= article.getContent() %></textarea>
    </div>

    <div>
      <button type="submit">수정</button>
      <a href="list">취소</a>
    </div>
  </form>
</body>
</html>