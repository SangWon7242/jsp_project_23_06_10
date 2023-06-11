package com.sbs.exam.jsp.board.servlet;

import com.sbs.exam.jsp.board.Rq;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/gugudan")
public class GugudanServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Rq rq = new Rq(req, resp);

    int dan = rq.getIntParam("dan", 9);
    int limit = rq.getIntParam("limit", 9);

    req.setAttribute("dan", dan);
    req.setAttribute("limit", limit);

    // request에 정보를 담는다.
    // gugudan2.jsp에게 나머지 작업을 토스
    RequestDispatcher requestDispatcher = req.getRequestDispatcher("/gugudan2.jsp");
    requestDispatcher.forward(req, resp);

  }
}
