package com.sbs.exam.jsp.board.controller;

import com.sbs.exam.jsp.board.Rq;
import com.sbs.exam.jsp.board.dto.Article;
import com.sbs.exam.jsp.board.service.ArticleService;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class UsrArticleController extends Controller {
  private ArticleService articleService;

  public UsrArticleController() {
    articleService = new ArticleService();
  }

  @Override
  public void performAction(Rq rq) {
    switch (rq.getActionMethodName()) {
      case "list":
        actionList(rq);
      case "write":
        actionWrite(rq);
      case "doWrite":
        actionDoWrite(rq);
        break;
    }
  }

  private void actionWrite(Rq rq) {
    rq.jsp("../article/write");
  }

  private void actionDoWrite(Rq rq) {
    String title = rq.getParam("title", "");
    String content = rq.getParam("content", "");

    if(title.length() == 0) {
      rq.historyBack("제목을 입력해주세요.");
      return;
    }

    if(content.length() == 0) {
      rq.historyBack("내용을 입력해주세요.");
      return;
    }

    HttpSession session = rq.getReq().getSession();

    if(session.getAttribute("loginedMemberId") == null) {
      rq.locationReplace("로그인 후 이용해주세요.", "../member/login");
      return;
    }

    int loginedMemberId = (int) session.getAttribute("loginedMemberId");

    int id = articleService.write(title, content, loginedMemberId);;

    rq.print("<script>alert('%d번 글이 생성되었습니다.'); location.replace('detail?id=%d');</script>".formatted(id, id));
  }

  public void actionList(Rq rq) {
    int page = rq.getIntParam("page", 1);
    int totalPage = articleService.getForPrintListTotalPage();
    List<Article> articles = articleService.getForPrintArticleRows(page);

    rq.setAttr("articles", articles);
    rq.setAttr("page", page);
    rq.setAttr("totalPage", totalPage);

    rq.jsp("../article/list");
  }
}
