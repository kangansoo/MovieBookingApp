package com.kosa.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class MovieReservationApp extends JFrame {
    private JComboBox<String> movieComboBox;
    private JComboBox<String> theaterComboBox;
    private JComboBox<String> timeComboBox;
    private JComboBox<Integer> peopleComboBox;

    public MovieReservationApp() {
        setTitle("영화 예매 프로그램");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridLayout(4, 2, 10, 10));
        setSize(607, 454);

        // 영화 제목 셀렉트 박스 초기화
        movieComboBox = new JComboBox<>();
        // 상영관 셀렉트 박스 초기화
        theaterComboBox = new JComboBox<>();
        // 상영 시간 셀렉트 박스 초기화
        timeComboBox = new JComboBox<>();
        // 인원 셀렉트 박스 초기화
        peopleComboBox = new JComboBox<>();

        // 영화 제목 셀렉트 박스 이벤트 리스너 추가
        movieComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 영화 제목이 선택되었을 때 호출되는 메서드
                updateTheaterComboBox();
            }
        });

        // 패널에 셀렉트 박스 추가
        getContentPane().add(new JLabel("영화 제목:"));
        getContentPane().add(movieComboBox);
        getContentPane().add(new JLabel("상영관:"));
        getContentPane().add(theaterComboBox);
        getContentPane().add(new JLabel("상영 시간:"));
        getContentPane().add(timeComboBox);
        getContentPane().add(new JLabel("인원:"));
        getContentPane().add(peopleComboBox);
        
        // 프레임 표시
        setVisible(true);
    }

    // 상영관 셀렉트 박스 업데이트 메서드
    private void updateTheaterComboBox() {
        String selectedMovie = (String) movieComboBox.getSelectedItem();
        // TODO: 선택된 영화에 해당하는 상영관 정보 조회 및 셀렉트 박스 업데이트
    }

    // 프로그램 실행 메서드
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MovieReservationApp();
            }
        });
    }
}


