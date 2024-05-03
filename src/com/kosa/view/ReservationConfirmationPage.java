package com.kosa.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReservationConfirmationPage extends JFrame {
    private JPanel reservationPanel;

    public ReservationConfirmationPage() {
        setTitle("영화 예매 확인");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 400); // 프레임 크기 고정
        setLocationRelativeTo(null);
        setResizable(false);

        // 예매 내역을 담을 패널 생성
        reservationPanel = new JPanel();
        reservationPanel.setLayout(new BoxLayout(reservationPanel, BoxLayout.Y_AXIS));

        // 스크롤 패널 생성
        JScrollPane scrollPane = new JScrollPane(reservationPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // 프레임에 스크롤 패널 추가
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // 예매 내역 추가 (예시로 3개의 예매 내역을 추가)
        addReservation("영화 제목1", "2024-04-30", "14:00", "상영관1", "A1, A2");
        addReservation("영화 제목2", "2024-05-01", "15:30", "상영관2", "B3, B4");
        addReservation("영화 제목3", "2024-05-02", "17:00", "상영관3", "C5, C6");

        // 확인 버튼 추가
        JButton confirmButton = new JButton("확인");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // 확인 버튼이 눌리면 현재 창을 닫음
            }
        });
        getContentPane().add(confirmButton, BorderLayout.SOUTH);
    }

    // 예매 내역을 추가하는 메소드
    private void addReservation(String movieTitle, String date, String time, String theater, String seats) {
        JPanel reservationInfoPanel = new JPanel();
        reservationInfoPanel.setPreferredSize(new Dimension(300, 150)); // 예매 내역 패널 크기 설정
        reservationInfoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // 테두리 추가

        // 예매 내역 정보 추가
        JLabel titleLabel = new JLabel("영화 제목: " + movieTitle);
        JLabel dateLabel = new JLabel("상영 날짜: " + date);
        JLabel timeLabel = new JLabel("상영 시간: " + time);
        JLabel theaterLabel = new JLabel("상영관: " + theater);
        JLabel seatsLabel = new JLabel("좌석: " + seats);

        // 패널에 정보 추가
        reservationInfoPanel.add(titleLabel);
        reservationInfoPanel.add(dateLabel);
        reservationInfoPanel.add(timeLabel);
        reservationInfoPanel.add(theaterLabel);
        reservationInfoPanel.add(seatsLabel);

        // 전체 패널에 예매 내역 패널 추가
        reservationPanel.add(reservationInfoPanel);

        // 갱신
        reservationPanel.revalidate();
        reservationPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ReservationConfirmationPage reservationConfirmationPage = new ReservationConfirmationPage();
            reservationConfirmationPage.setVisible(true);
        });
    }
}
