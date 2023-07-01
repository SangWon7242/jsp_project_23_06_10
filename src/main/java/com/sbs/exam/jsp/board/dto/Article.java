package com.sbs.exam.jsp.board.dto;


import lombok.*;

import java.util.Map;


@Data
@ToString
public class Article {
  private int id;
  private String regDate;
  private String updateDate;
  private String title;
  private String content;
  private int memberId;

  public Article(Map<String, Object> row) {
    this.id = (int) row.get("id");
    this.regDate = (String) row.get("regDate");
    this.updateDate = (String) row.get("updateDate");
    this.title = (String) row.get("title");;
    this.content = (String) row.get("content");;
    this.memberId = (int) row.get("memberId");;
  }
}
