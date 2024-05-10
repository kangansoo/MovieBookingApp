package com.kosa.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.kosa.member.controller.MemberController;
import com.kosa.member.controller.MemberControllerImpl;
import com.kosa.member.vo.MemberVO;

public class MainPage extends JFrame {
	private JButton applyButton;
	private JButton bookButton;
	private JButton viewButton;
	private MemberVO memberVO;
	private MemberController memberController;

	public MainPage(MemberVO memberVO) {
		this();
		this.memberVO = memberVO;
	}

	public MainPage() {
		
		memberController = new MemberControllerImpl();
		memberVO = memberController.getLoggedInMember();

		setTitle("영화 예매 시스템");
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);

		// 패널 생성
		JPanel mainPanel = new JPanel();

		// 이미지 라벨 생성
		ImageIcon imageIcon = new ImageIcon("placeholder_image.jpg"); // 이미지 파일의 경로 설정
		mainPanel.setLayout(null);

		// 회원 이름 라벨 생성
		JLabel nameLabel = new JLabel(memberVO.getMemberName() + "님 안녕하세요");
		nameLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setBounds(75, 63, 250, 41);
		mainPanel.add(nameLabel);

		// 버튼 생성
		applyButton = new JButton("보고 싶은 영화 신청하기");
		applyButton.setBounds(75, 124, 250, 30);
		mainPanel.add(applyButton);

		bookButton = new JButton("예매하기");
		bookButton.setBounds(75, 164, 250, 30);
		mainPanel.add(bookButton);

		viewButton = new JButton("예매 내역 조회");
		viewButton.setBounds(75, 204, 250, 30);
		mainPanel.add(viewButton);
		// 프레임에 패널 추가

		// 버튼에 액션 리스너 추가
		applyButton.addActionListener(new ApplyAction());
		bookButton.addActionListener(new BookAction());
		viewButton.addActionListener(new ViewAction());

		getContentPane().add(mainPanel);
	}

	// 내부 클래스로 액션 리스너 구현
	private class ApplyAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			RequestMoviePage requestMoviePage = new RequestMoviePage();
			requestMoviePage.setVisible(true);
		}
	}

	private class BookAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ReservationForm reservationForm = new ReservationForm();
			reservationForm.setVisible(true);
		}
	}

	private class ViewAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ReservationConfirmationPage reservationConfirmationPage = new ReservationConfirmationPage();
			reservationConfirmationPage.setVisible(true);
		}
	}
}
