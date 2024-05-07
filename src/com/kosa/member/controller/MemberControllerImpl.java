package com.kosa.member.controller;

import java.sql.SQLException;

import com.kosa.member.dao.MemberDAO;
import com.kosa.member.dao.MemberDAOImpl;
import com.kosa.member.vo.MemberVO;

public class MemberControllerImpl implements MemberController {
   private MemberDAO memberDAO;

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
    		  return target;
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

}