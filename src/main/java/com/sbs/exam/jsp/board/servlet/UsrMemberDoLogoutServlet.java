package com.sbs.exam.jsp.board.servlet;

import com.sbs.exam.jsp.board.Rq;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/member/doLogout")
public class UsrMemberDoLogoutServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    Rq rq = new Rq(req, resp);

    HttpSession session = req.getSession();
    session.removeAttribute("loginedMemberId");

    rq.print("<script>alert('로그아웃 되었습니다.'); location.replace('../home/main');</script>");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);
  }
}
