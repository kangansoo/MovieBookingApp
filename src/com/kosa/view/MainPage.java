package com.kosa.view;

import javax.swing.*;
import java.awt.*;

public class MainPage extends JFrame {
    private JButton applyButton;
    private JButton bookButton;
    private JButton viewButton;

    public MainPage() {
        setTitle("영화 예매 시스템");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // 패널 생성
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null); // 레이아웃 매니저를 null로 설정하여 수동으로 컴포넌트 배치

        // 이미지 라벨 생성
        ImageIcon imageIcon = new ImageIcon("placeholder_image.jpg"); // 이미지 파일의 경로 설정
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(75, 30, 250, 100); // 이미지 위치와 크기 설정
        mainPanel.add(imageLabel);

        // 버튼 생성
        applyButton = new JButton("보고 싶은 영화 신청하기");
        applyButton.setBounds(75, 150, 250, 30); // 버튼 위치와 크기 설정
        mainPanel.add(applyButton);

        bookButton = new JButton("예매하기");
        bookButton.setBounds(75, 190, 250, 30); // 버튼 위치와 크기 설정
        mainPanel.add(bookButton);

        viewButton = new JButton("예매 내역 조회");
        viewButton.setBounds(75, 230, 250, 30); // 버튼 위치와 크기 설정
        mainPanel.add(viewButton);

        // 프레임에 패널 추가
        getContentPane().add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainPage mainPage = new MainPage();
            mainPage.setVisible(true);
        });
    }
}

