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
   public void login(String id, String pwd) throws SQLException {
      memberDAO.findByMemberId(id, pwd);
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