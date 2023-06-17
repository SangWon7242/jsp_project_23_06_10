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
  <title>JSP BOARD - 게시물 상세보기</title>
</head>
<body>

  <h1>게시물 상세보기</h1>
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
        <td><%= articleRow.get("id") %>번</td>
        <td><%= articleRow.get("regDate") %></td>
        <td><%= articleRow.get("updateDate") %></td>
        <td><%= articleRow.get("title") %></td>
        <td><%= articleRow.get("content") %></td>
      </tr>
    </tbody>
  </table>

  <div>
    <a href="list">리스트로 이동</a>
  </div>

</body>
</html>