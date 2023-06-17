<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>JSP BOARD - 게시물 작성</title>
</head>
<body>

  <h1>게시물 작성</h1>

  <form action="doWrite" method="POST">
    <div>
      제목 : <input autocomplete="off" type="text" name="title" placeholder="제목을 입력해주세요.">
    </div>
    <div>
      내용 : <textarea autocomplete="off" type="text" name="content" placeholder="내용을 입력해주세요."></textarea>
    </div>

    <div>
      <button type="submit">작성</button>
      <a href="list">취소</a>
    </div>
  </form>
</body>
</html>