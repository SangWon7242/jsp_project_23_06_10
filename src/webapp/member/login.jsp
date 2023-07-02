<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>JSP BOARD - 로그인</title>

  <!-- daisy UI 불러오기 -->
  <link href="https://cdn.jsdelivr.net/npm/daisyui@3.1.7/dist/full.css" rel="stylesheet" type="text/css" />
  <!-- 테일윈드 불러오기 -->
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>

  <script>
    let LoginForm__submitDone = false;

    function LoginForm_submit(form) {
      if( LoginForm__submitDone ) {
        alert('처리 중입니다.');
        return;
      }

      form.loginId.value = form.loginId.value.trim();

      if(form.loginId.value.length == 0) {
       alert('로그인 아이디를 입력해주세요.');
       form.loginId.focus();

       return;
      }

      form.loginPw.value = form.loginPw.value.trim();

      if(form.loginPw.value.length == 0) {
       alert('로그인 비번을 입력해주세요.');
       form.loginPw.focus();

       return;
      }

      form.submit();
      LoginForm_submit = true;
    }
  </script>

  <%@include file="../part/topBar.jspf"%>

  <form action="doLogin" method="POST" onsubmit="LoginForm_submit(this); return false;">
    <div>
      로그인 아이디 : <input autocomplete="off" type="text" name="loginId" placeholder="로그인 아이디를 입력해주세요.">
    </div>
    <div>
      로그인 비밀번호 : <input autocomplete="off" type="password" name="loginPw" placeholder="로그인 패스워드 입력해주세요.">
    </div>

    <div>
      <button type="submit">로그인</button>
      <button type="button">
        <a href="/home/main">취소</a>
      </button>
    </div>
  </form>
</body>
</html>