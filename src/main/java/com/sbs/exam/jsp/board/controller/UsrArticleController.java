package com.sbs.exam.jsp.board.controller;

import com.sbs.exam.jsp.board.Rq;
import com.sbs.exam.jsp.board.dto.Article;
import com.sbs.exam.jsp.board.service.ArticleService;

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
        break;
    }
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
