package com.sbs.exam.jsp.board.dto;


import lombok.Data;
import lombok.ToString;

import java.util.Map;


@Data
@ToString
public class Member {
  private int id;
  private String regDate;
  private String updateDate;
  private String loginId;
  private String loginPw;
  private String name;

  public Member(Map<String, Object> row) {
    this.id = (int) row.get("id");
    this.regDate = (String) row.get("regDate");
    this.updateDate = (String) row.get("updateDate");
    this.loginId = (String) row.get("loginId");;
    this.loginPw = (String) row.get("loginPw");;
    this.name = (String) row.get("name");;
  }
}
