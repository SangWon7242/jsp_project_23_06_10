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
  <title>JSP BOARD - 게시물 상세보기</title>

  <!-- daisy UI 불러오기 -->
  <link href="https://cdn.jsdelivr.net/npm/daisyui@3.1.7/dist/full.css" rel="stylesheet" type="text/css" />
  <!-- 테일윈드 불러오기 -->
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
  <%@include file="../part/topBar.jspf"%>

  <table border="1" style="border-collapse: collapse; text-align: center;">
    <colgroup>
      <col width="50px">
      <col width="200px">
      <col width="200px">
      <col width="100px">
    </colgroup>
    <thead>
    <tr>
      <th>번호</th>
      <th>작성날짜</th>
      <th>수정날짜</th>
      <th>제목</th>
      <th>내용</th>
    </tr>
    </thead>
    <tbody>
      <tr>
        <td><%= article.getId() %>번</td>
        <td><%= article.getRegDate() %></td>
        <td><%= article.getUpdateDate() %></td>
        <td><%= article.getTitle() %></td>
        <td><%= article.getContent() %></td>
      </tr>
    </tbody>
  </table>

  <div>
    <a href="doDelete?id=${param.id}" onClick="if(!confirm('정말 삭제하시겠습니까?')) { return false; }">삭제</a>
    <a href="modify?id=${param.id}">수정</a>
    <a href="list">리스트</a>
  </div>

</body>
</html>