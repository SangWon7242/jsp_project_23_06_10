package com.sbs.exam.jsp.board.controller;

import com.sbs.exam.jsp.board.Rq;
import com.sbs.exam.jsp.board.dto.Article;
import com.sbs.exam.jsp.board.dto.ResultData;
import com.sbs.exam.jsp.board.service.ArticleService;
import com.sbs.exam.jsp.board.util.MysqlUtil;
import com.sbs.exam.jsp.board.util.SecSql;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Map;

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
      case "detail":
        actionDetail(rq);        
      default:
        rq.println("존재하지 않는 페이지 입니다.");
        break;
    }
  }

  private void actionDetail(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if(id == 0) {
      rq.historyBack("잘못 된 요청입니다.");
      return;
    }

    Article article = articleService.getForPrintArticleById(id);
    rq.setAttr("article", article);

    rq.jsp("../article/detail");
  }

  private void actionWrite(Rq rq) {
    rq.jsp("../article/write");
  }

  private void actionDoWrite(Rq rq) {
    String title = rq.getParam("title", "");
    String content = rq.getParam("content", "");
    String redirectUri = rq.getParam("redirectUri", "../member/list");

    if(title.length() == 0) {
      rq.historyBack("제목을 입력해주세요.");
      return;
    }

    if(content.length() == 0) {
      rq.historyBack("내용을 입력해주세요.");
      return;
    }

    HttpSession session = rq.getReq().getSession();
    int loginedMemberId = (int) session.getAttribute("loginedMemberId");

    if(session.getAttribute("loginedMemberId") == null) {
      rq.replace("로그인 후 이용해주세요.", "../member/login");
      return;
    }

    ResultData writeRd = articleService.write(title, content, loginedMemberId);
    int id = (int) writeRd.getBody().get("id");
    redirectUri = redirectUri.replace("[NEW_ID]", id + "");

    rq.replace(writeRd.getMsg(), redirectUri);
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
