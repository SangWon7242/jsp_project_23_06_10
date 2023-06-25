package com.sbs.exam.jsp.board.servlet;

import com.sbs.exam.jsp.board.Rq;
import com.sbs.exam.jsp.board.mysqlutil.MysqlUtil;
import com.sbs.exam.jsp.board.mysqlutil.SecSql;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/article/doModify")
public class UsrArticleDoModifyServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    MysqlUtil.setDBInfo("localhost", "sbsst", "sbs123414", "jspboard");
    MysqlUtil.setDevMode(true);

    Rq rq = new Rq(req, resp);

    int id = rq.getIntParam("id", 0);
    String title = rq.getParam("title", "");
    String content = rq.getParam("content", "");

    if(id == 0) {
      rq.appendBody("<script>alert('잘못 된 요청입니다.'); history.back();</script>");
      return;
    }

    if(title.length() == 0) {
      rq.appendBody("<script>alert('제목을 입력해주세요.'); history.back();</script>");
      return;
    }

    if(content.length() == 0) {
      rq.appendBody("<script>alert('내용을 입력해주세요.'); history.back();</script>");
      return;
    }

    SecSql sql = new SecSql();
    sql.append("UPDATE article");
    sql.append("SET updateDate = NOW()");
    sql.append(", title = ?", title);
    sql.append(", content = ?", content);
    sql.append("WHERE id = ?", id);

    MysqlUtil.update(sql);

    rq.appendBody("<script>alert('%d번 글이 수정되었습니다.'); location.replace('detail?id=%d');</script>".formatted(id, id));

    MysqlUtil.closeConnection();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);
  }
}
