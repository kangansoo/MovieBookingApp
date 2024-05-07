package com.kosa.member.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.kosa.common.base.DBConnection;
import com.kosa.member.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {
   private Connection conn;
   private PreparedStatement pstmt;
   private CallableStatement cstmt;
   private ResultSet rs;

   public MemberDAOImpl() {
      conn = DBConnection.getConnection();
   }

   @Override
   public List<MemberVO> selectMember(MemberVO memberVO) throws SQLException {
	   List<MemberVO> memList = new ArrayList<MemberVO>();
	   StringBuilder query = new StringBuilder();
	   query.append("SELECT * FROM MEMBER WHERE 1=1 ");
	   if(memberVO != null && memberVO.getId() != null) {
		   query.append("AND ID = ?");
		   pstmt = conn.prepareStatement(query.toString());
		   pstmt.setString(1, memberVO.getId());
	   } else {
		   pstmt = conn.prepareStatement(query.toString());
	   }
	   rs = pstmt.executeQuery();
	   while(rs.next()) {
		   int memberNo = rs.getInt("member_no");
		   String memberName = rs.getString("member_name");
		   String telNo = rs.getString("tel_no");
		   String email = rs.getString("email");
		   String id = rs.getString("id");
		   String pwd = rs.getString("pwd");
		   MemberVO vo = new MemberVO(memberNo, memberName, telNo, email, id, pwd);
		   memList.add(vo);
	   }
      return memList;
   }

   @Override
   public void insertMember(MemberVO memberVO) throws SQLException {
      String query = "{ call RegisterMember(?, ?, ? ,?, ?) }";
      cstmt = conn.prepareCall(query);
      cstmt.setString(1, memberVO.getId());
      cstmt.setString(2, memberVO.getPwd());
      cstmt.setString(3, memberVO.getMemberName());
      cstmt.setString(4, memberVO.getTelNo());
      cstmt.setString(5, memberVO.getEmail());
      cstmt.execute();
   }

   @Override
   public void updateMember() throws SQLException {
      // TODO Auto-generated method stub

   }

   @Override
   public void deleteMember() throws SQLException {
      // TODO Auto-generated method stub

   }

   // 로그인
   @Override
   public boolean findByMemberId(String id, String pwd) throws SQLException {
      String query = "{ call login_proc(?, ?) }";
      cstmt = conn.prepareCall(query);
      cstmt.setString(1, id);
      cstmt.setString(2, pwd);

      boolean result = cstmt.execute();
      return result;
   }

   @Override
   public int checkDuplicateUserId(String id) throws SQLException {
      String query = "{ call Check_UserID_Duplicate(?, ?)}";
      cstmt = conn.prepareCall(query);
      cstmt.setString(1, id);
      cstmt.registerOutParameter(2, Types.INTEGER);

      cstmt.execute();

      return cstmt.getInt(2);
   }

}