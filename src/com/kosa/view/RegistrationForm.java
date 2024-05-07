package com.kosa.view;

import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.kosa.member.controller.MemberController;
import com.kosa.member.controller.MemberControllerImpl;
import com.kosa.member.vo.MemberVO;

public class RegistrationForm extends JPanel {
   private JTextField idField;
   private JPasswordField passwordField;
   private JTextField nameField;
   private JTextField emailField;
   private JTextField phoneField;
   private JButton checkButton;
   private JButton registerButton;
   private MemberController controller;

   public RegistrationForm() {
      controller = new MemberControllerImpl();
      setPreferredSize(new Dimension(500, 400)); // 패널 크기를 500x400으로 설정
      setLayout(null);

      JLabel idLabel = new JLabel("아이디:");
      idLabel.setBounds(67, 94, 80, 25);
      add(idLabel);

      idField = new JTextField(20);
      idField.setBounds(140, 94, 174, 25);
      add(idField);

      checkButton = new JButton("중복확인");
      checkButton.addActionListener(new DuplicateCheckListener());
      checkButton.setBounds(313, 94, 99, 25);
      add(checkButton);

      JLabel passwordLabel = new JLabel("비밀번호:");
      passwordLabel.setBounds(67, 124, 80, 25);
      add(passwordLabel);

      passwordField = new JPasswordField(20);
      passwordField.setBounds(139, 124, 273, 25);
      add(passwordField);

      JLabel nameLabel = new JLabel("이름:");
      nameLabel.setBounds(67, 154, 80, 25);
      add(nameLabel);

      nameField = new JTextField(20);
      nameField.setBounds(139, 154, 273, 25);
      add(nameField);

      JLabel emailLabel = new JLabel("이메일:");
      emailLabel.setBounds(67, 184, 80, 25);
      add(emailLabel);

      emailField = new JTextField(20);
      emailField.setBounds(139, 184, 273, 25);
      add(emailField);

      JLabel phoneLabel = new JLabel("전화번호:");
      phoneLabel.setBounds(67, 214, 80, 25);
      add(phoneLabel);

      phoneField = new JTextField(20);
      phoneField.setBounds(139, 214, 273, 25);
      add(phoneField);

      registerButton = new JButton("회원가입");
      registerButton.setEnabled(false); // 초기에는 회원가입 버튼을 비활성화 상태로 설정
      registerButton.addActionListener(new RegistrationListener());
      registerButton.setBounds(140, 260, 272, 25);
      add(registerButton);
   }

   // 중복 확인 리스너
   private class DuplicateCheckListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
         String userId = idField.getText(); // 사용자가 입력한 ID를 가져옵니다.
         try {
            int duplicateCount = controller.checkUserID(userId);
            if (duplicateCount == 0) {
               // 사용 가능한 아이디일 경우
               JOptionPane.showMessageDialog(null, "사용가능한 아이디입니다.", "중복 확인", JOptionPane.INFORMATION_MESSAGE);
               registerButton.setEnabled(true);
            }
         } catch (java.sql.SQLException e1) {
            // 데이터베이스 오류 발생 시
            JOptionPane.showMessageDialog(null, e1.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
            registerButton.setEnabled(false);
         }
      }

   }

   // 회원가입 리스너
   private class RegistrationListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
         // 회원가입 로직 구현
         String id = idField.getText();
         String pwd = new String(passwordField.getPassword()); // 비밀번호 필드에서 문자열 추출
         String name = nameField.getText();
         String telNo = phoneField.getText();
         String email = emailField.getText();
         MemberVO vo = new MemberVO(name, telNo, email, id, pwd);
         try {
            controller.registerNewMember(vo);
            JOptionPane.showMessageDialog(RegistrationForm.this, "회원가입이 완료되었습니다.", "회원가입",
                  JOptionPane.INFORMATION_MESSAGE);
              // 회원가입 창을 닫습니다.
               ((Window) getRootPane().getParent()).dispose();
         } catch (SQLException e1) {
             JOptionPane.showMessageDialog(RegistrationForm.this, "회원가입 중 오류가 발생했습니다: " + e1.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
         }

      }
   }
}