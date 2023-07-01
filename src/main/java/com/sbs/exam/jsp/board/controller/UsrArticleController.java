package com.sbs.exam.jsp.board.controller;

import com.sbs.exam.jsp.board.Rq;
import com.sbs.exam.jsp.board.dto.Article;
import com.sbs.exam.jsp.board.service.ArticleService;

import java.util.List;
import java.util.Map;

public class UsrArticleController {
  private Rq rq;
  private ArticleService articleService;

  public UsrArticleController(Rq rq) {
    this.rq = rq;
    articleService = new ArticleService();
  }

  public void actionList() {
    int page = rq.getIntParam("page", 1);
    int totalPage = articleService.getForPrintListTotalPage();
    List<Article> articles = articleService.getForPrintArticleRows(page);

    rq.setAttr("articles", articles);
    rq.setAttr("page", page);
    rq.setAttr("totalPage", totalPage);

    rq.jsp("../article/list");
  }
}
