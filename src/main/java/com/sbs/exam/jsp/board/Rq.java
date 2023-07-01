package com.sbs.exam.jsp.board;

import com.sbs.exam.jsp.board.util.Util;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.Getter;
import lombok.ToString;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@ToString
public class Rq {
  @Getter
  private final HttpServletRequest req;
  @Getter
  private final HttpServletResponse resp;
  @Getter
  private boolean isInvalid = false;
  @Getter
  private String controllerTypeName;
  @Getter
  private String controllerName;
  @Getter
  private String actionMethodName;

  public Rq(HttpServletRequest req, HttpServletResponse resp) {
    this.req = req;
    this.resp = resp;

    try {
      req.setCharacterEncoding("UTF-8");
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
    resp.setCharacterEncoding("UTF-8");
    resp.setContentType("text/html; charset UTF-8");

    String requestUri = req.getRequestURI();
    String[] requestUriBits = requestUri.split("/");

    int minBitsCount = 4;

    if (requestUriBits.length < minBitsCount) {
      isInvalid = true;
      return;
    }

    int controllerTypeNameIndex = 1;
    int controllerNameIndex = 2;
    int actionMethodNameIndex = 3;

    this.controllerTypeName = requestUriBits[controllerTypeNameIndex];
    this.controllerName = requestUriBits[controllerNameIndex];
    this.actionMethodName = requestUriBits[actionMethodNameIndex];
  }

  public int getIntParam(String paramName, int defaultValue) {
    String value = req.getParameter(paramName);

    if(value == null) {
      return defaultValue;
    }

    try {
      return Integer.parseInt(value);
    } catch (NumberFormatException e) {
      return defaultValue;
    }
  }

  public void print(String str) {
    try {
      resp.getWriter().append(str);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void println(String str) {
    print(str + "\n");
  }

  public void printf(String format, Object... args) {
    print(Util.f(format, args));
  }

  public void historyBack(String msg) {
    println("<script>");
    printf("alert('%s');\n", msg);
    printf("history.back();");
    println("</script>");
  }

  public void replace(String msg, String redirectUri) {
    println("<script>");
    printf("alert('%s');\n", msg);
    printf("location.replace('%s');", redirectUri);
    println("</script>");
  }

  public String getParam(String paramName, String defaultValue) {
    String value = req.getParameter(paramName);

    if(value == null) {
      return defaultValue;
    }

    return value;
  }

  public void jsp(String jspPath) {
    RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/" + jspPath + ".jsp");

    try {
      requestDispatcher.forward(req, resp);
    } catch (ServletException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void setAttr(String key, Object value) {
    req.setAttribute(key, value);
  }
}
