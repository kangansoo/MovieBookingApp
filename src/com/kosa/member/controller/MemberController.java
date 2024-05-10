package com.kosa.member.controller;

import java.sql.SQLException;

import com.kosa.member.vo.MemberVO;

public interface MemberController {
   // 로그인
	MemberVO login(String id, String pwd) throws SQLException;
   // 아이디 중복 확인
   int checkUserID(String id) throws SQLException;
   //회원가입
   void registerNewMember(MemberVO memberVO) throws SQLException;
   MemberVO getLoggedInMember();
}