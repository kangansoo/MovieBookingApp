package com.kosa.member.controller;

public class MemberControllerImpl implements MemberController {

	@Override
	public void login(String id, String pwd) {
		private MemberDAO memberDAO;
		
		public MemberControllerImpl() {
			memberDAO = new MemberDAOImpl();
		}
		
		@Override
		public void login(String id, String pwd) {
			try {
				memberDAO.findByMemberId(id, pwd);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
