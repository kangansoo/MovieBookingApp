package com.kosa.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.kosa.request.controller.RequestController;
import com.kosa.request.controller.RequestControllerImpl;

public class RequestMoviePage extends JFrame {
	private JComboBox<String> movieComboBox;
	private JButton requestButton;
	private RequestController requestController;

	public RequestMoviePage() {
		setTitle("영화 신청하기");
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 변경된 부분
		setSize(400, 250);
		setLocationRelativeTo(null);

		// RequestController 초기화
		requestController = new RequestControllerImpl();

		// 패널 생성
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null); // 레이아웃 매니저를 null로 설정하여 수동으로 컴포넌트 배치

		// 영화 선택 셀렉트 박스 생성
		movieComboBox = new JComboBox<>();
		movieComboBox.setBounds(75, 30, 250, 30); // 셀렉트 박스 위치와 크기 설정
		mainPanel.add(movieComboBox);

		// '신청하기' 버튼 생성
		requestButton = new JButton("신청하기");
		requestButton.setBounds(75, 100, 250, 30); // 버튼 위치와 크기 설정
		mainPanel.add(requestButton);

		requestButton.addActionListener(new RequestActionListener());

		// 프레임에 패널 추가
		getContentPane().add(mainPanel);

		// 영화 목록 가져와서 셀렉트 박스에 추가
		try {
			List<String> movies = requestController.requestedMovieList();
			for (String movie : movies) {
				movieComboBox.addItem(movie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private class RequestActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				String selectedMovie = ((String) movieComboBox.getSelectedItem()).trim().split("\\s+")[0];
				requestController.requestMovie(selectedMovie); // 선택한 영화의 신청 수 증가
				JOptionPane.showMessageDialog(RequestMoviePage.this, selectedMovie + "의 신청이 완료되었습니다.", "신청 완료",
						JOptionPane.INFORMATION_MESSAGE);
				dispose(); // 현재 페이지 닫기
			} catch (SQLException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(RequestMoviePage.this, "신청 처리 중 오류가 발생하였습니다.", "오류",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}