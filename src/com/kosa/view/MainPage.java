package com.kosa.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.kosa.member.vo.MemberVO;
import javax.swing.SwingConstants;
import java.awt.Font;

public class MainPage extends JFrame {
	private JButton applyButton;
	private JButton bookButton;
	private JButton viewButton;
	private MemberVO memberVO;

	public MainPage() {
		// 테스트용 코드
	      memberVO = new MemberVO();
	      memberVO.setMemberName("홍길동");
	      //이후삭제
	      
		setTitle("영화 예매 시스템");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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
		getContentPane().add(mainPanel);
	}
	
	public MainPage(MemberVO memberVO) {
		this();
		this.memberVO = memberVO;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			MainPage mainPage = new MainPage();
			mainPage.setVisible(true);

			// '보고 싶은 영화 신청하기' 버튼에 ActionListener 추가
			mainPage.applyButton.addActionListener(e -> {
				RequestMoviePage requestMoviePage = new RequestMoviePage(); // ApplyMoviePage 인스턴스 생성
				requestMoviePage.setVisible(true); // ApplyMoviePage를 보이도록 설정
				mainPage.dispose(); // 현재 페이지를 닫음
			});
			mainPage.bookButton.addActionListener(e -> {
				ReservationForm reservationForm = new ReservationForm(); // ApplyMoviePage 인스턴스 생성
				reservationForm.setVisible(true); // ApplyMoviePage를 보이도록 설정
				mainPage.dispose(); // 현재 페이지를 닫음
			});
			mainPage.viewButton.addActionListener(e -> {
				ReservationConfirmationPage reservationConfirmationPage = new ReservationConfirmationPage(); // ApplyMoviePage
																												// 인스턴스
																												// 생성
				reservationConfirmationPage.setVisible(true); // ApplyMoviePage를 보이도록 설정
				mainPage.dispose(); // 현재 페이지를 닫음
			});
		});
	}
}
