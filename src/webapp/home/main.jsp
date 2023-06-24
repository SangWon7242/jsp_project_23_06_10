<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
  boolean isLogined = (boolean) request.getAttribute("isLogined");
  int loginedMemberId = (int) request.getAttribute("loginedMemberId");
%>

<!doctype html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>JSP BOARD - 메인</title>
</head>
<body>

<h1>메인페이지</h1>
  <div>
    <a href="/usr/article/write">게시물 작성</a>
    &nbsp;
    <a href="/usr/article/list">리스트로 이동</a>
    &nbsp;
    <a href="/usr/member/join">회원가입</a>
  </div>

  <div>
    <% if(!isLogined) { %>
      <a href="/usr/member/login">로그인</a>
    <% } %>

    <% if(isLogined) { %>
      <%=loginedMemberId%>번 회원님 환영합니다.
      <a href="/usr/member/doLogout">로그아웃</a>
    <% } %>
  </div>

</body>
</html>