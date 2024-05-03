package com.kosa.view;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.event.*;

public class RegistrationForm extends JFrame {
   private JTextField idField;
   private JPasswordField passwordField;
   private JTextField nameField;
   private JTextField emailField;
   private JTextField phoneField;
   private JButton checkButton;
   private JButton registerButton;

   public RegistrationForm() {
      setTitle("회원가입");
      setSize(new Dimension(500, 400)); // 윈도우 크기를 500x400으로 설정
      setResizable(false);
      getContentPane().setLayout(null);

      JLabel idLabel = new JLabel("아이디:");
      idLabel.setBounds(67, 94, 80, 25);
      getContentPane().add(idLabel);

      idField = new JTextField(20);
      idField.setBounds(140, 94, 174, 25);
      getContentPane().add(idField);

      checkButton = new JButton("중복확인");
      checkButton.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      	}
      });
      checkButton.setBounds(313, 94, 99, 25);
      getContentPane().add(checkButton);

      JLabel passwordLabel = new JLabel("비밀번호:");
      passwordLabel.setBounds(67, 124, 80, 25);
      getContentPane().add(passwordLabel);

      passwordField = new JPasswordField(20);
      passwordField.setBounds(139, 124, 273, 25);
      getContentPane().add(passwordField);

      JLabel nameLabel = new JLabel("이름:");
      nameLabel.setBounds(67, 154, 80, 25);
      getContentPane().add(nameLabel);

      nameField = new JTextField(20);
      nameField.setBounds(139, 154, 273, 25);
      getContentPane().add(nameField);

      JLabel emailLabel = new JLabel("이메일:");
      emailLabel.setBounds(67, 184, 80, 25);
      getContentPane().add(emailLabel);

      emailField = new JTextField(20);
      emailField.setBounds(139, 184, 273, 25);
      getContentPane().add(emailField);

      JLabel phoneLabel = new JLabel("전화번호:");
      phoneLabel.setBounds(67, 214, 80, 25);
      getContentPane().add(phoneLabel);

      phoneField = new JTextField(20);
      phoneField.setBounds(139, 214, 273, 25);
      getContentPane().add(phoneField);

      registerButton = new JButton("회원가입");
      registerButton.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      	}
      });
      registerButton.setBounds(140, 260, 272, 25);
      getContentPane().add(registerButton);

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
   }

   public static void main(String[] args) {
      new RegistrationForm();
   }
}
