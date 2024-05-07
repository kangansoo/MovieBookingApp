package com.kosa.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.kosa.member.controller.MemberController;
import com.kosa.member.controller.MemberControllerImpl;

public class LoginForm extends JFrame {
   private JLabel usernameLabel;
   private JTextField usernameField;
   private JLabel passwordLabel;
   private JPasswordField passwordField;
   private JButton loginButton;
   private JButton registerButton;
   private MemberController controller;
   // 회원가입 창에 대한 참조를 저장하기 위한 변수
   private JDialog registrationDialog;

   public LoginForm() {
      controller = new MemberControllerImpl();
      // Create the username label and field
      usernameLabel = new JLabel("아이디:");
      usernameLabel.setBounds(94, 174, 56, 15);
      usernameField = new JTextField(15);
      usernameField.setBounds(155, 174, 171, 21);

      // Create the password label and field
      passwordLabel = new JLabel("비밀번호:");
      passwordLabel.setBounds(94, 215, 56, 15);
      passwordField = new JPasswordField(15);
      passwordField.setBounds(155, 215, 171, 21);

      // Create the login button
      loginButton = new JButton("로그인");
      loginButton.setBounds(155, 251, 171, 23);
      loginButton.addActionListener(new LoginActionListener());

      // Create the register button
      registerButton = new JButton("회원가입");
      registerButton.setBounds(155, 284, 171, 23);
      // 회원가입 버튼 생성 및 이벤트 리스너 설정
      registerButton = new JButton("회원가입");
      registerButton.setBounds(155, 284, 171, 23);
      registerButton.addActionListener(e -> openRegistrationForm());
      // Create the panel to hold the components
      JPanel panel = new JPanel();
      panel.setLayout(null);
      panel.add(usernameLabel);
      panel.add(usernameField);
      panel.add(passwordLabel);
      panel.add(passwordField);
      panel.add(loginButton);
      panel.add(registerButton);

      // Add the panel to the frame
      getContentPane().add(panel, BorderLayout.CENTER);

      // Set the size of the frame
      setSize(500, 400);

   }

   public static void main(String[] args) {
      LoginForm loginForm = new LoginForm();
      loginForm.setVisible(true);
   }

   // 로그인 버튼 리스너 핸들러 내부 클래스
   private class LoginActionListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         String userid = usernameField.getText();
         String password = new String(passwordField.getPassword());
         try {
            controller.login(userid, password);
            LoginForm.this.dispose(); // 로그인 창 닫기
            MainPage mainPage = new MainPage(); // 메인 페이지 생성
            mainPage.setVisible(true); // 메인 페이지 표시
         } catch (SQLException e1) {
            JOptionPane.showMessageDialog(LoginForm.this, "로그인 실패 \n" + e1.getMessage(), "로그인 에러",
                  JOptionPane.ERROR_MESSAGE);
         }
         System.out.println("로그인 성공");
      }
   }

   // 회원가입 창 띄우기
   private void openRegistrationForm() {
      if (registrationDialog == null || !registrationDialog.isDisplayable()) {
         registrationDialog = new JDialog(this, "회원가입", false); // false로 설정하여 모달리스로 만듦
         RegistrationForm registrationForm = new RegistrationForm();
         registrationDialog.setContentPane(registrationForm);
         registrationDialog.setSize(500, 400);
         registrationDialog.setVisible(true);
      } else {
         registrationDialog.toFront(); // 이미 열려있는 창을 앞으로 가져옴
      }
   }
}