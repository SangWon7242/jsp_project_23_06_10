package com.sbs.exam.jsp.board.service;

import com.sbs.exam.jsp.board.dto.Article;
import com.sbs.exam.jsp.board.repository.ArticleRepository;

import java.util.List;

public class ArticleService {

  private ArticleRepository articleRepository;

  public ArticleService() {
    articleRepository = new ArticleRepository();
  }

  public int getItemInAPage() {
    return 20;
  }
  public int getForPrintListTotalPage() {
    int itemInAPage = getItemInAPage();
    int totalCount = articleRepository.getTotalCount();
    int totalPage = (int) Math.ceil((double) totalCount / itemInAPage);

    return totalPage;
  }

  public List<Article> getForPrintArticleRows(int page) {
    int itemInAPage = getItemInAPage();
    int limitFrom = (page - 1) * itemInAPage;

    List<Article> articleRows = articleRepository.getArticleRows(itemInAPage, limitFrom);

    return articleRows;
  }
}
