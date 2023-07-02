package com.sbs.exam.jsp.board.service;

import com.sbs.exam.jsp.board.dto.Member;
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

  public Member getMemberByLoginId(String loginId) {
    return memberRepository.getMemberByLoginId(loginId);
  }

  public ResultData login(String loginId, String loginPw) {
    Member member = getMemberByLoginId(loginId);

    if(member == null) {
      return ResultData.from("F-1", "존재하지 않는 회원의 아이디입니다.");
    }

    if(member.getLoginPw().equals(loginPw) == false) {
      return ResultData.from("F-2", "비밀번호가 일치하지 않습니다.");
    }

    return ResultData.from("S-1", "로그인 되었습니다.", "member", member);
  }
}
