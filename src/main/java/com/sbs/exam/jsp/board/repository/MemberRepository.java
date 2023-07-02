package com.sbs.exam.jsp.board.repository;

import com.sbs.exam.jsp.board.dto.Article;
import com.sbs.exam.jsp.board.util.MysqlUtil;
import com.sbs.exam.jsp.board.util.SecSql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MemberRepository {
  public boolean isJoinAvailable(String loginId) {
    SecSql sql = new SecSql();
    sql.append("SELECT COUNT(*) AS cnt");
    sql.append("FROM `member`");
    sql.append("WHERE loginId = ?", loginId);

    return MysqlUtil.selectRowIntValue(sql) == 0;
  }

  public int join(String loginId, String loginPw, String name) {
    SecSql sql = new SecSql();
    sql.append("INSERT INTO `member`");
    sql.append("SET regDate = NOW()");
    sql.append(", updateDate = NOW()");
    sql.append(", loginId = ?", loginId);
    sql.append(", loginPw = ?", loginPw);
    sql.append(", name = ?", name);

    int id = MysqlUtil.insert(sql);

    return id;
  }
}
