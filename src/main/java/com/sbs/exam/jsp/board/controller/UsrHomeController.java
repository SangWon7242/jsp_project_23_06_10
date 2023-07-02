package com.sbs.exam.jsp.board.controller;

import com.sbs.exam.jsp.board.Rq;

public class UsrHomeController extends Controller {
  @Override
  public void performAction(Rq rq) {
    switch (rq.getActionMethodName()) {
      case "main":
        actionMain(rq);
      default:
        rq.println("존재하지 않는 페이지 입니다.");
        break;
    }
  }

  private void actionMain(Rq rq) {
    rq.jsp("../home/main");
  }

}
