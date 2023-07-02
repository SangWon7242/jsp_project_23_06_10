package com.sbs.exam.jsp.board.service;

import com.sbs.exam.jsp.board.dto.Article;
import com.sbs.exam.jsp.board.dto.ResultData;
import com.sbs.exam.jsp.board.repository.ArticleRepository;
import com.sbs.exam.jsp.board.util.Util;
import org.w3c.dom.html.HTMLImageElement;

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

  public ResultData write(String title, String content, int loginedMemberId) {
    int id = articleRepository.write(title, content, loginedMemberId);

    return ResultData.from("S-1", Util.f("%d번 게시물이 생성되었습니다.", id), "id", id);
  }

  public Article getForPrintArticleById(int id) {
    return articleRepository.getForPrintArticleById(id);
  }

  public ResultData modify(int id, String title, String content) {
    articleRepository.modify(id, title, content);
    return ResultData.from("S-1", Util.f("%d번 게시물이 수정되었습니다.", id), "id", id);
  }

  public ResultData delete(int id) {
    articleRepository.delete(id);
    return ResultData.from("S-1", Util.f("%d번 게시물이 삭제되었습니다.", id), "id", id);
  }
}
