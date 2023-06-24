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

@WebServlet("/usr/member/doJoin")
public class UsrMemberDoJoinServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    MysqlUtil.setDBInfo("localhost", "sbsst", "sbs123414", "jspboard");
    MysqlUtil.setDevMode(true);

    Rq rq = new Rq(req, resp);

    String loginId = rq.getParam("loginId", "");
    String loginPw = rq.getParam("loginPw", "");
    String name = rq.getParam("name", "");

    SecSql sql = new SecSql();
    sql.append("SELECT COUNT(*) AS cnt");
    sql.append("FROM `member`");
    sql.append("WHERE loginId = ?", loginId);

    boolean isJoinAvailable = MysqlUtil.selectRowIntValue(sql) == 0;

    if(isJoinAvailable == false) {
     rq.appendBody("<script>alert('%s (은)는 이미 사용중인 아이디입니다.'); history.back();</script>".formatted(loginId));
     return;
    }

    sql = new SecSql();
    sql.append("INSERT INTO `member`");
    sql.append("SET regDate = NOW()");
    sql.append(", updateDate = NOW()");
    sql.append(", loginId = ?", loginId);
    sql.append(", loginPw = ?", loginPw);
    sql.append(", name = ?", name);

    int id = MysqlUtil.insert(sql);

    rq.appendBody("<script>alert('%d번 회원이 생성되었습니다.'); location.replace('/home/main');</script>".formatted(id, id));

    MysqlUtil.closeConnection();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);
  }
}
