<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<%
  List<Map<String, Object>> articleRows = (List<Map<String, Object>>) request.getAttribute("articleRows");
%>

<!doctype html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>JSP BOARD - 리스트</title>
</head>
<body>
  <h1>게시물 리스트 v1</h1>
  <ul>
    <li>
      <%= articleRows.get(0).get("id") %>번
      <%= articleRows.get(0).get("regDate") %>
      <%= articleRows.get(0).get("updateDate") %>
      <%= articleRows.get(0).get("title") %>
    </li>
    <li>
      <%= articleRows.get(1).get("id") %>번
      <%= articleRows.get(1).get("regDate") %>
      <%= articleRows.get(1).get("updateDate") %>
      <%= articleRows.get(1).get("title") %>
    </li>
    <li>
      <%= articleRows.get(2).get("id") %>번
      <%= articleRows.get(2).get("regDate") %>
      <%= articleRows.get(2).get("updateDate") %>
      <%= articleRows.get(2).get("title") %>
    </li>
  </ul>

  <h1>게시물 리스트 v2</h1>
  <% for(int i = 0; i < 3; i++) { %>
  <ul>
    <li>
      <%= articleRows.get(i).get("id") %>번
      <%= articleRows.get(i).get("regDate") %>
      <%= articleRows.get(i).get("updateDate") %>
      <%= articleRows.get(i).get("title") %>
    </li>
  </ul>
  <% } %>

  <h1>게시물 리스트 v3</h1>
  <% for(int i = 0; i < 3; i++) {
    Map<String, Object> articleRow = articleRows.get(i);
  %>
  <ul>
    <li>
      <%= articleRow.get("id") %>번
      <%= articleRow.get("regDate") %>
      <%= articleRow.get("updateDate") %>
      <%= articleRow.get("title") %>
    </li>
  </ul>
  <% } %>

  <h1>게시물 리스트 v4</h1>
  <% for(int i = 0; i < articleRows.size(); i++) {
  Map<String, Object> articleRow = articleRows.get(i);
  %>
  <ul>
    <li>
      <%= articleRow.get("id") %>번
      <%= articleRow.get("regDate") %>
      <%= articleRow.get("updateDate") %>
      <%= articleRow.get("title") %>
    </li>
  </ul>
  <% } %>

  <h1>게시물 리스트 v5</h1>
  <% for( Map<String, Object> articleRow : articleRows) { %>
  <ul>
    <li>
      <%= articleRow.get("id") %>번
      <%= articleRow.get("regDate") %>
      <%= articleRow.get("updateDate") %>
      <%= articleRow.get("title") %>
    </li>
  </ul>
  <% } %>

</body>
</html>