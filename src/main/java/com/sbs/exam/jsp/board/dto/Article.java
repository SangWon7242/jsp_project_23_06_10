package com.sbs.exam.jsp.board.dto;


import lombok.*;


@Data
@AllArgsConstructor
@ToString
public class Article {
  int id;
  String regDate;
  String updateDate;
  String title;
  String content;
}
