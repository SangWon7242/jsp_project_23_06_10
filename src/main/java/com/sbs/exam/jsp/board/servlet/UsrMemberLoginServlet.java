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

@WebServlet("/member/login")
public class UsrMemberLoginServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

    rq.jsp("../member/login");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);
  }
}
