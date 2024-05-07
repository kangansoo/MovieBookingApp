package com.kosa.member.dao;

import java.sql.SQLException;
import java.util.List;

import com.kosa.member.vo.MemberVO;


public interface MemberDAO {
   // select
   List<MemberVO> selectMember() throws SQLException;

   // insert
   void insertMember(MemberVO memberVO) throws SQLException;

   // update
   void updateMember() throws SQLException;

   // delete
   void deleteMember() throws SQLException;

   // login
   void findByMemberId(String id, String pwd) throws SQLException;

   // check_UserId_Duplicate
   int checkDuplicateUserId(String id) throws SQLException;
   
   
}