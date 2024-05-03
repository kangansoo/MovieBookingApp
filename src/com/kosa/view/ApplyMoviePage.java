package com.kosa.view;

import javax.swing.*;

public class ApplyMoviePage extends JFrame {
    private JComboBox<String> movieComboBox;
    private JButton applyButton;

    public ApplyMoviePage() {
        setTitle("영화 신청하기");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        // 패널 생성
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null); // 레이아웃 매니저를 null로 설정하여 수동으로 컴포넌트 배치

        // 영화 선택 셀렉트 박스 생성
        String[] movies = {"영화1", "영화2", "영화3"}; // 예시 영화 목록
        movieComboBox = new JComboBox<>(movies);
        movieComboBox.setBounds(75, 30, 250, 30); // 셀렉트 박스 위치와 크기 설정
        mainPanel.add(movieComboBox);

        // '신청하기' 버튼 생성
        applyButton = new JButton("신청하기");
        applyButton.setBounds(75, 100, 250, 30); // 버튼 위치와 크기 설정
        mainPanel.add(applyButton);

        // 프레임에 패널 추가
        getContentPane().add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ApplyMoviePage applyMoviePage = new ApplyMoviePage();
            applyMoviePage.setVisible(true);
        });
    }
}

