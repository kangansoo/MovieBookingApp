package com.kosa.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import com.kosa.member.controller.MemberController;
import com.kosa.member.controller.MemberControllerImpl;
import com.kosa.member.vo.MemberVO;
import com.kosa.ticket.controller.TicketController;
import com.kosa.ticket.controller.TicketControllerImpl;

public class ReservationConfirmationPage extends JFrame {
    private JPanel reservationPanel;
    private MemberVO memberVO;
    private MemberController memberController;
    private TicketController ticketController;
    private List<List<String>> ticketInfoList;

    public ReservationConfirmationPage() {
        setTitle("영화 예매 확인");
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 변경된 부분
        setSize(500, 400); // 프레임 크기 고정
        setLocationRelativeTo(null);
        setResizable(false);
        memberController = new MemberControllerImpl();
        memberVO = memberController.getLoggedInMember();
        ticketController = new TicketControllerImpl();
        ticketInfoList = new ArrayList<>();
        ticketInfoList = ticketController.getTicketInfo(memberVO.getMemberNo());

        // 예매 내역을 담을 패널 생성
        reservationPanel = new JPanel();
        reservationPanel.setLayout(new BoxLayout(reservationPanel, BoxLayout.Y_AXIS));

        // 스크롤 패널 생성
        JScrollPane scrollPane = new JScrollPane(reservationPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // 프레임에 스크롤 패널 추가
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // 예매 내역 추가 (예시로 3개의 예매 내역을 추가)
        for (List<String> ticketInfo : ticketInfoList) {
            addReservation(ticketInfo.get(0), ticketInfo.get(1), ticketInfo.get(2), ticketInfo.get(3), ticketInfo.get(4));
        }
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
        titleLabel.setBounds(23, 48, 219, 15);
        JLabel dateLabel = new JLabel("상영 날짜: " + date);
        dateLabel.setBounds(282, 48, 154, 15);
        JLabel timeLabel = new JLabel("상영 시간: " + time);
        timeLabel.setBounds(22, 76, 120, 15);
        JLabel theaterLabel = new JLabel("상영관: " + theater);
        theaterLabel.setBounds(154, 76, 125, 15);
        JLabel seatsLabel = new JLabel("좌석: " + seats);
        seatsLabel.setBounds(282, 76, 142, 15);
        reservationInfoPanel.setLayout(null);

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
