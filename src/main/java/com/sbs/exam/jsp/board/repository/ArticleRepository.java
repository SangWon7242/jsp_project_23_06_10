package com.sbs.exam.jsp.board.repository;

import com.sbs.exam.jsp.board.dto.Article;
import com.sbs.exam.jsp.board.util.MysqlUtil;
import com.sbs.exam.jsp.board.util.SecSql;

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

  public int write(String title, String content, int loginedMemberId) {
    SecSql sql = new SecSql();
    sql.append("INSERT INTO article");
    sql.append("SET regDate = NOW()");
    sql.append(", updateDate = NOW()");
    sql.append(", title = ?", title);
    sql.append(", content = ?", content);
    sql.append(", memberId = ?", loginedMemberId);

    int id = MysqlUtil.insert(sql);

    return id;
  }

  public Article getForPrintArticleById(int id) {
    SecSql sql = new SecSql();
    sql.append("SELECT *");
    sql.append("FROM article");
    sql.append("WHERE id = ?", id);

    return new Article(MysqlUtil.selectRow(sql));
  }

  public void modify(int id, String title, String content) {
    SecSql sql = new SecSql();
    sql.append("UPDATE article");
    sql.append("SET updateDate = NOW()");
    sql.append(", title = ?", title);
    sql.append(", content = ?", content);
    sql.append("WHERE id = ?", id);

    MysqlUtil.update(sql);
  }

  public void delete(int id) {
    SecSql sql = new SecSql();
    sql.append("DELETE");
    sql.append("FROM article");
    sql.append("WHERE id = ?", id);

    MysqlUtil.delete(sql);
  }
}
