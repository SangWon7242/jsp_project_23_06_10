package com.sbs.exam.jsp.board.service;

import com.sbs.exam.jsp.board.dto.ResultData;
import com.sbs.exam.jsp.board.repository.ArticleRepository;
import com.sbs.exam.jsp.board.repository.MemberRepository;
import com.sbs.exam.jsp.board.util.Util;

public class MemberService {

  private MemberRepository memberRepository;

  public MemberService() {
    memberRepository = new MemberRepository();
  }

  public boolean isJoinAvailable(String loginId) {
    return memberRepository.isJoinAvailable(loginId);
  }

  public ResultData join(String loginId, String loginPw, String name) {
    int id = memberRepository.join(loginId, loginPw, name);
    return ResultData.from("S-1", Util.f("%d번 회원이 생성되었습니다.", id), "id", id);
  }
}
