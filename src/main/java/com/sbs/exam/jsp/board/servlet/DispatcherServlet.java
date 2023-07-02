package com.sbs.exam.jsp.board.servlet;

import com.sbs.exam.jsp.board.Rq;
import com.sbs.exam.jsp.board.controller.UsrArticleController;
import com.sbs.exam.jsp.board.controller.UsrHomeController;
import com.sbs.exam.jsp.board.util.MysqlUtil;
import com.sbs.exam.jsp.board.util.SecSql;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

@WebServlet("/usr/*")
public class DispatcherServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    MysqlUtil.setDBInfo("localhost", "sbsst", "sbs123414", "jspboard");
    MysqlUtil.setDevMode(true);

    Rq rq = new Rq(req, resp);

    // 모든 요청을 들어가기 전에 무조건 해야 하는 일 시작
    HttpSession session = req.getSession();

    boolean isLogined = false;
    int loginedMemberId = -1;
    Map<String, Object> loginedMemberRow = null;

    if (session.getAttribute("loginedMemberId") != null) {
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
    // 모든 요청을 들어가기 전에 무조건 해야 하는 일 끝

    switch (rq.getControllerTypeName()) {
      case "usr":
        UsrArticleController usrArticleController = new UsrArticleController();
        UsrHomeController usrHomeController = new UsrHomeController();
        switch (rq.getControllerName()) {
          case "home":
            usrHomeController.performAction(rq);
            break;
          case "article":
            usrArticleController.performAction(rq);
            break;
        }
    }

    MysqlUtil.closeConnection();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);
  }
}
