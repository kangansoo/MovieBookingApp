package com.kosa.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ReservationCompletePage extends JFrame {
    private JPanel reservationPanel;

    public ReservationCompletePage() {
        setTitle("예매 완료");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300); // 프레임 크기 고정
        setResizable(false); // 크기 수정 불가능하도록 설정
        setLocationRelativeTo(null);

        // 예매 내역을 담을 패널 생성
        reservationPanel = new JPanel();
        reservationPanel.setLayout(new BoxLayout(reservationPanel, BoxLayout.Y_AXIS));

        // 스크롤 패널 생성
        JScrollPane scrollPane = new JScrollPane(reservationPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // 프레임에 스크롤 패널 추가
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // 예매 내역 추가
        addReservation("영화 제목1", "2024-04-30", "14:00", "상영관1", 2, new String[]{"A1", "A2"});
    }

    // 예매 내역을 추가하는 메소드
    private void addReservation(String movieTitle, String date, String time, String theater, int numberOfPeople, String[] seats) {
        JPanel reservationInfoPanel = new JPanel();
        reservationInfoPanel.setPreferredSize(new Dimension(300, 150)); // 예매 내역 패널 크기 설정
        reservationInfoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // 테두리 추가

        // 예매 내역 정보 추가
        JLabel titleLabel = new JLabel("영화 제목: " + movieTitle);
        JLabel dateLabel = new JLabel("상영 날짜: " + date);
        JLabel timeLabel = new JLabel("상영 시간: " + time);
        JLabel theaterLabel = new JLabel("상영관: " + theater);
        JLabel numberOfPeopleLabel = new JLabel("인원: " + numberOfPeople);
        JLabel seatsLabel = new JLabel("좌석: " + String.join(", ", seats));

        // 패널에 정보 추가
        reservationInfoPanel.add(titleLabel);
        reservationInfoPanel.add(dateLabel);
        reservationInfoPanel.add(timeLabel);
        reservationInfoPanel.add(theaterLabel);
        reservationInfoPanel.add(numberOfPeopleLabel);
        reservationInfoPanel.add(seatsLabel);

        // 전체 패널에 예매 내역 패널 추가
        reservationPanel.add(reservationInfoPanel);

        // 갱신
        reservationPanel.revalidate();
        reservationPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ReservationCompletePage reservationCompletePage = new ReservationCompletePage();
            reservationCompletePage.setVisible(true);
        });
    }
}


