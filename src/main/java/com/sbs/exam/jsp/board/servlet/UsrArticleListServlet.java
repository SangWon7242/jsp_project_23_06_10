package com.sbs.exam.jsp.board.servlet;

import com.sbs.exam.jsp.board.Rq;
import com.sbs.exam.jsp.board.mysqlutil.MysqlUtil;
import com.sbs.exam.jsp.board.mysqlutil.SecSql;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/article/list")
public class UsrArticleListServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    MysqlUtil.setDBInfo("localhost", "sbsst", "sbs123414", "jspboard");
    MysqlUtil.setDevMode(true);

    Rq rq = new Rq(req, resp);

    // 공통 속성 시작
    HttpSession session = req.getSession();

    boolean isLogined = false;
    int loginedMemberId = -1;
    Map<String, Object> loginedMemberRow = null;

    if(session.getAttribute("loginedMemberId") != null) {
      loginedMemberId = (int) session.getAttribute("loginedMemberId");
      isLogined = true;

      SecSql sql = new SecSql();
      sql.append("SELECT * FROM `member`");
      sql.append("WHERE id = ?", loginedMemberId);
      loginedMemberRow = MysqlUtil.selectRow(sql);
    }

    req.setAttribute("isLogined", isLogined);
    req.setAttribute("loginedMemberId", loginedMemberId);
    req.setAttribute("loginedMemberRow", loginedMemberRow);
    // 공통 속성 끝

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

    req.setAttribute("articleRows", articleRows);
    req.setAttribute("page", page);
    req.setAttribute("totalPage", totalPage);

    rq.jsp("../article/list");

    MysqlUtil.closeConnection();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);
  }
}
