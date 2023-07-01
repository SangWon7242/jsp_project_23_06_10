package com.sbs.exam.jsp.board.repository;

import com.sbs.exam.jsp.board.dto.Article;
import com.sbs.exam.jsp.board.mysqlutil.MysqlUtil;
import com.sbs.exam.jsp.board.mysqlutil.SecSql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArticleRepository {
  public int getTotalCount() {
    SecSql sql = new SecSql();
    sql.append("SELECT COUNT(*) AS cnt");
    sql.append("FROM article");

    int totalCount = MysqlUtil.selectRowIntValue(sql);

    return totalCount;
  }

  public List<Article> getArticleRows(int itemInAPage, int limitFrom) {
    SecSql sql = new SecSql();
    sql.append("SELECT A.*");
    sql.append("FROM article AS A");
    sql.append("ORDER BY A.id DESC");
    sql.append("LIMIT ?, ?", limitFrom, itemInAPage);

    List<Map<String, Object>> articleRows = MysqlUtil.selectRows(sql);

    List<Article> articles = new ArrayList<>();

    for(Map<String, Object> articleRow : articleRows) {
      articles.add(new Article(articleRow));
    }

    return articles;
  }
}
