package com.sbs.exam.jsp.board.service;

import com.sbs.exam.jsp.board.repository.ArticleRepository;

import java.util.List;
import java.util.Map;

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

  public List<Map<String, Object>> getForPrintArticleRows(int page) {
    int itemInAPage = getItemInAPage();
    int limitFrom = (page - 1) * itemInAPage;

    List<Map<String, Object>> articleRows = articleRepository.getArticleRows(itemInAPage, limitFrom);

    return articleRows;
  }
}
