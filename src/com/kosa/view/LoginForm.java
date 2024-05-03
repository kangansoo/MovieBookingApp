package com.kosa.view;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginForm extends JFrame {
    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public LoginForm() {

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

        // Create the register button
        registerButton = new JButton("회원가입");
        registerButton.setBounds(155, 284, 171, 23);

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
}