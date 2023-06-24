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

@WebServlet("/usr/article/doWrite")
public class UsrArticleDoWriteServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    MysqlUtil.setDBInfo("localhost", "sbsst", "sbs123414", "jspboard");
    MysqlUtil.setDevMode(true);

    Rq rq = new Rq(req, resp);

    String title = rq.getParam("title", "");
    String content = rq.getParam("content", "");

    if(title.length() == 0) {
      rq.appendBody("<script>alert('제목을 입력해주세요.'); history.back();</script>");
      return;
    }

    if(content.length() == 0) {
      rq.appendBody("<script>alert('내용을 입력해주세요.'); history.back();</script>");
      return;
    }

    HttpSession session = req.getSession();

    if(session.getAttribute("loginedMemberId") == null) {
      rq.appendBody("<script>alert('로그인 후 이용해주세요.'); location.replace('../member/login');</script>");
      return;
    }

    int loginedMemberId = (int) session.getAttribute("loginedMemberId");

    SecSql sql = new SecSql();
    sql.append("INSERT INTO article");
    sql.append("SET regDate = NOW()");
    sql.append(", updateDate = NOW()");
    sql.append(", title = ?", title);
    sql.append(", content = ?", content);
    sql.append(", memberId = ?", loginedMemberId);

    int id = MysqlUtil.insert(sql);

    rq.appendBody("<script>alert('%d번 글이 생성되었습니다.'); location.replace('detail?id=%d');</script>".formatted(id, id));

    MysqlUtil.closeConnection();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);
  }
}
