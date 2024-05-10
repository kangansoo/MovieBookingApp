package com.kosa.member.controller;

import java.sql.SQLException;

import com.kosa.member.dao.MemberDAO;
import com.kosa.member.dao.MemberDAOImpl;
import com.kosa.member.vo.MemberVO;

public class MemberControllerImpl implements MemberController {
   private MemberDAO memberDAO;
   private static MemberVO loggedInMember; // 싱글톤으로 관리할 로그인한 회원 객체

   public MemberControllerImpl() {
      memberDAO = new MemberDAOImpl();
   }

   @Override
   public MemberVO login(String id, String pwd) throws SQLException {
      memberDAO.findByMemberId(id, pwd);
      MemberVO vo = new MemberVO();
      vo.setId(id);
      for(MemberVO target :  memberDAO.selectMember(vo)) {
    	  if(id.equals(vo.getId())) {
    		  loggedInMember = target; // 로그인한 회원 객체를 싱글톤으로 설정
    		  return loggedInMember;
    	  }
      }
     return vo;
   }

   @Override
   public int checkUserID(String id) throws SQLException {
      return memberDAO.checkDuplicateUserId(id);
   }

   @Override
   public void registerNewMember(MemberVO memberVO) throws SQLException {
      memberDAO.insertMember(memberVO);
   }
   
   // 로그인한 회원 객체를 반환하는 메서드
   public MemberVO getLoggedInMember() {
      return loggedInMember;
   }
}
