package com.sbs.exam.jsp.board.controller;

import com.sbs.exam.jsp.board.Rq;
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
    List<Map<String, Object>> articleRows = articleService.getForPrintArticleRows(page);

    rq.setAttr("page", page);
    rq.setAttr("articleRows", articleRows);
    rq.setAttr("totalPage", totalPage);

    rq.jsp("../article/list");
  }
}
