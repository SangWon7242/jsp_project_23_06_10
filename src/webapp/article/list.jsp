<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.sbs.exam.jsp.board.dto.Article" %>

<%
  List<Article> articles = (List<Article>) request.getAttribute("articles");
  int cPage = (int) request.getAttribute("page");
  int totalPage = (int) request.getAttribute("totalPage");
%>

<!doctype html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>JSP BOARD - 리스트</title>

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
      </tr>
    </thead>
    <tbody>
    <% for( Article article : articles) { %>
      <tr>
        <td><%= article.getId() %>번</td>
        <td><%= article.getRegDate() %></td>
        <td><%= article.getUpdateDate() %></td>
        <td>
          <a href="detail?id=<%= article.getId() %>">
            <%= article.getTitle() %>
          </a>
        </td>
      </tr>
      <% } %>
    </tbody>
  </table>

  <style>
    .page > a.red {
      color: red;
    }
  </style>

  <div class="page">
    <% if(cPage > 1) { %>
      <a href="list?page=1">◀</a>
    <% } %>

    <%
    int pageMenuSize = 5;
    int from = cPage - pageMenuSize;

    if(from < 1) {
    from = 1;
    }

    int end = cPage + 10;

    if(end > totalPage) {
      end = totalPage;
    }

    for(int i = from; i <= end; i++ ) {
    %>
      <a class="<%= cPage == i ? "red" : "" %>" href="list?page=<%=i%>"><%=i%></a>
    <% } %>

    <% if(cPage < totalPage) { %>
      <a href="list?page=<%=totalPage%>">▶</a>
    <% } %>

  </div>

</body>
</html>