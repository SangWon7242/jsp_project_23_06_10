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
import java.util.Map;

@WebServlet("/usr/member/doLogin")
public class UsrMemberDoLoginServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    MysqlUtil.setDBInfo("localhost", "sbsst", "sbs123414", "jspboard");
    MysqlUtil.setDevMode(true);

    Rq rq = new Rq(req, resp);

    String loginId = rq.getParam("loginId", "");
    String loginPw = rq.getParam("loginPw", "");

    SecSql sql = new SecSql();
    sql.append("SELECT *");
    sql.append("FROM `member`");
    sql.append("WHERE loginId = ?", loginId);

    Map<String, Object> memberRow = MysqlUtil.selectRow(sql);

    if(memberRow.isEmpty()) {
      rq.appendBody("<script>alert('%s (은)는 존재하지 않는 아이디입니다.'); history.back();</script>".formatted(loginId));
      return;
    }

    if(((String) memberRow.get("loginPw")).equals(loginPw) == false) {
      rq.appendBody("<script>alert('로그인 비번이 틀렸습니다.'); history.back();</script>");
      return;
    }

    HttpSession session = req.getSession();
    session.setAttribute("loginedMemberId", memberRow.get("loginId"));

    rq.appendBody("<script>alert('%s 님 로그인 되었습니다.'); location.replace('/home/main');</script>".formatted(loginId));

    MysqlUtil.closeConnection();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);
  }
}
