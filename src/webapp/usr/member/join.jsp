<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>JSP BOARD - 회원가입</title>
</head>
<body>

  <script>
    let JoinForm__submitDone = false;

    function JoinForm_submit(form) {
      if( JoinForm__submitDone ) {
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

      form.loginPwConfirm.value = form.loginPwConfirm.value.trim();

      if(form.loginPwConfirm.value.length == 0) {
       alert('로그인 비번 확인을 입력해주세요.');
       form.loginPwConfirm.focus();

       return;
      }

       if(form.loginPw.value != form.loginPwConfirm.value) {
       alert('로그인 비번이 일치하지 않습니다.');
       form.loginPwConfirm.focus();

       return;
      }

      form.name.value = form.name.value.trim();

      if(form.name.value.length == 0) {
       alert('이름을 입력해주세요.');
       form.name.focus();

       return;
      }

      form.submit();
      JoinForm_submit = true;
    }
  </script>

  <h1>회원가입</h1>

  <form action="doJoin" method="POST" onsubmit="JoinForm_submit(this); return false;">
    <div>
      로그인 아이디 : <input type="text" name="loginId" placeholder="로그인 아이디를 입력해주세요.">
    </div>
    <div>
      로그인 비밀번호 : <input type="password" name="loginPw" placeholder="로그인 패스워드 입력해주세요.">
    </div>
    <div>
      로그인 비번 확인 : <input type="password" name="loginPwConfirm" placeholder="로그인 비밀번호 확인">
    </div>
    <div>
      이름 : <input type="text" name="name" placeholder="이름을 입력해주세요.">
    </div>

    <div>
      <button type="submit">회원가입</button>
      <button type="button">
        <a href="/home/main">취소</a>
      </button>
    </div>
  </form>
</body>
</html>