package com.kosa.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class ReservationCompletePage extends JFrame {
    private static JPanel reservationPanel;
    static private int numOfPeople;
    static private String movieTitle;
    static private String theater;
    static private String time;
    static private String date;
    static private ArrayList<String> selectedSeats = new ArrayList<String>();

    public ReservationCompletePage(String movieTitle, String theater, String date, String time, int numOfPeople, ArrayList<String> selectedSeats) {
        setTitle("예매 완료");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300); // 프레임 크기 고정
        setResizable(false); // 크기 수정 불가능하도록 설정
        setLocationRelativeTo(null);
        
        this.movieTitle = movieTitle;
        this.theater = theater;
        this.time = time;
        this.date = date;
        this.numOfPeople = numOfPeople;
        this.selectedSeats = selectedSeats;

        // 예매 내역을 담을 패널 생성
        reservationPanel = new JPanel();
        reservationPanel.setLayout(new BoxLayout(reservationPanel, BoxLayout.Y_AXIS));

        // 스크롤 패널 생성
        JScrollPane scrollPane = new JScrollPane(reservationPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // 프레임에 스크롤 패널 추가
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // 예매 내역 추가
        addReservation(movieTitle, date, time, theater, numOfPeople, selectedSeats);
    }

    // 예매 내역을 추가하는 메소드
    private void addReservation(String movieTitle, String date, String time, String theater, int numOfPeople,
            ArrayList<String> selectedSeats) {
        JPanel reservationInfoPanel = new JPanel();
        reservationInfoPanel.setPreferredSize(new Dimension(300, 150)); // 예매 내역 패널 크기 설정
        reservationInfoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // 테두리 추가

        // 예매 내역 정보 추가
        JLabel titleLabel = new JLabel("영화 제목: " + movieTitle);
        JLabel dateLabel = new JLabel("상영 날짜: " + date);
        JLabel timeLabel = new JLabel("상영 시간: " + time);
        JLabel theaterLabel = new JLabel("상영관: " + theater);
        JLabel numberOfPeopleLabel = new JLabel("인원: " + numOfPeople);
        
        // 좌석 정보 추가
        StringBuilder seatsInfo = new StringBuilder("좌석: ");
        for (String seat : selectedSeats) {
            seatsInfo.append(seat).append(", ");
        }
        // 마지막에 추가된 ", "를 제거합니다.
        if (!selectedSeats.isEmpty()) {
            seatsInfo.setLength(seatsInfo.length() - 2);
        }
        JLabel seatsLabel = new JLabel(seatsInfo.toString());

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
            ReservationCompletePage reservationCompletePage = new ReservationCompletePage(movieTitle, theater, date, time, numOfPeople, selectedSeats);
            reservationCompletePage.setVisible(true);
        });
    }
}
