package com.kosa.member.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {

   private int memberNo;
   private String memberName;
   private String telNo;
   private String email;
   private String id;
   private String pwd;

   public MemberVO(String memberName, String telNo, String email, String id, String pwd) {
      super();
      this.memberName = memberName;
      this.telNo = telNo;
      this.email = email;
      this.id = id;
      this.pwd = pwd;
   }

}