package com.sbs.exam.jsp.board.controller;

import com.sbs.exam.jsp.board.Rq;
import com.sbs.exam.jsp.board.dto.Member;
import com.sbs.exam.jsp.board.dto.ResultData;
import com.sbs.exam.jsp.board.service.MemberService;
import jakarta.servlet.http.HttpSession;

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
      case "doLogout":
        actionDoLogout(rq);
      default:
        rq.println("존재하지 않는 페이지 입니다.");
        break;
    }
  }

  private void actionDoLogout(Rq rq) {
    HttpSession session = rq.getReq().getSession();
    session.removeAttribute("loginedMemberId");

    rq.replace("로그아웃 되었습니다.", "../home/main");
  }

  private void actionLogin(Rq rq) {
    rq.jsp("../member/login");
  }

  private void actionDoLogin(Rq rq) {
    String loginId = rq.getParam("loginId", "");
    String loginPw = rq.getParam("loginPw", "");

    if(loginId.length() == 0) {
      rq.historyBack("loginId를 입력해주세요.");
      return;
    }

    if(loginPw.length() == 0) {
      rq.historyBack("loginPw를 입력해주세요.");
      return;
    }

    ResultData loginRd = memberService.login(loginId, loginPw);

    if(loginRd.isFail()) {
      rq.historyBack(loginRd.getMsg());
      return;
    }

    Member member = (Member) loginRd.getBody().get("member");

    rq.setSessionAttr("loginedMemberId", member.getLoginId());

    rq.replace(loginRd.getMsg(), "../home/main");
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
