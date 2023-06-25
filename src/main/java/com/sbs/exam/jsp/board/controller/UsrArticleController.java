package com.sbs.exam.jsp.board.controller;

import com.sbs.exam.jsp.board.Rq;
import com.sbs.exam.jsp.board.mysqlutil.MysqlUtil;
import com.sbs.exam.jsp.board.mysqlutil.SecSql;

import java.util.List;
import java.util.Map;

public class UsrArticleController {
  private Rq rq;
  public UsrArticleController(Rq rq) {
    this.rq = rq;
  }

  public void actionList() {
    int page = rq.getIntParam("page", 1);
    int itemInAPage = 20;
    int limitFrom = (page - 1) * itemInAPage;

    SecSql sql = new SecSql();
    sql.append("SELECT COUNT(*) AS cnt");
    sql.append("FROM article");

    int totalCount = MysqlUtil.selectRowIntValue(sql);
    int totalPage = (int) Math.ceil((double) totalCount / itemInAPage);

    sql = new SecSql();
    sql.append("SELECT A.*");
    sql.append("FROM article AS A");
    sql.append("ORDER BY A.id DESC");
    sql.append("LIMIT ?, ?", limitFrom, itemInAPage);

    List<Map<String, Object>> articleRows = MysqlUtil.selectRows(sql);

    rq.setAttr("articleRows", articleRows);
    rq.setAttr("page", page);
    rq.setAttr("totalPage", totalPage);

    rq.jsp("../article/list");
  }
}
