package com.sbs.exam.jsp.board.controller;

import com.sbs.exam.jsp.board.Rq;
import com.sbs.exam.jsp.board.dto.Article;
import com.sbs.exam.jsp.board.dto.ResultData;
import com.sbs.exam.jsp.board.service.ArticleService;
import com.sbs.exam.jsp.board.service.MemberService;
import com.sbs.exam.jsp.board.util.MysqlUtil;
import com.sbs.exam.jsp.board.util.SecSql;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class UsrMemberController extends Controller {
  private MemberService memberService;

  public UsrMemberController() {
    memberService = new MemberService();
  }

  @Override
  public void performAction(Rq rq) {
    switch (rq.getActionMethodName()) {
      case "join":
        actionJoin(rq);
      case "doJoin":
        actionDoJoin(rq);
      case "login":
        actionLogin(rq);
      case "doLogin":
        actionDoLogin(rq);
      default:
        rq.println("존재하지 않는 페이지 입니다.");
        break;
    }
  }

  private void actionLogin(Rq rq) {

  }

  private void actionDoLogin(Rq rq) {
  }

  private void actionJoin(Rq rq) {
    rq.jsp("../member/join");
  }

  private void actionDoJoin(Rq rq) {
    String loginId = rq.getParam("loginId", "");
    String loginPw = rq.getParam("loginPw", "");
    String name = rq.getParam("name", "");



    boolean isJoinAvailable = memberService.isJoinAvailable(loginId);

    if (isJoinAvailable == false) {
      rq.historyBack("%s (은)는 이미 사용중인 아이디입니다.".formatted(loginId));
      return;
    }

    ResultData joinRd = memberService.join(loginId, loginPw, name);

    rq.replace(joinRd.getMsg(), "../home/main");
  }

}
