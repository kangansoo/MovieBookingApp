package com.kosa.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.kosa.member.controller.MemberController;
import com.kosa.member.controller.MemberControllerImpl;
import com.kosa.member.vo.MemberVO;
import com.kosa.movie.controller.MovieController;
import com.kosa.movie.controller.MovieControllerImpl;
import com.kosa.reserve.controller.ReserveController;
import com.kosa.reserve.controller.ReserveControllerImpl;
import com.kosa.schedule.schedulecontroller.ScheduleController;
import com.kosa.schedule.schedulecontroller.ScheduleControllerImpl;
import com.kosa.seatselection.controller.SeatSelectionController;
import com.kosa.seatselection.controller.SeatSelectionControllerImpl;
import com.kosa.ticket.controller.TicketController;
import com.kosa.ticket.controller.TicketControllerImpl;

public class SeatSelectionPage extends JFrame {
	private JLabel selectedSeatsLabel;
	private JLabel movieInfoLabel;
	private JLabel availableSeatsLabel;
	private JPanel seatPanel;
	private JPanel gridPanel; // Declaring gridPanel as a field
	private ArrayList<String> selectedSeats;
	private int remainingSeats;
	private int numOfPeople;
	static private String movieTitle;
	static private String theater;
	static private String time;
	static private String date;
	private SeatSelectionController controller;
	private List<String> unabledSeats;
	private MemberVO memberVO;
	private int reserveNo;
	private ReserveController reserveController;
	private ScheduleController scheduleController;
	private MovieController movieController;
	private TicketController ticketController;
	private MemberController memberController;
	private int charge;

	public SeatSelectionPage(String selectedMovie, String selectedTheater, String selectedTime, String selectedDate) {
		movieTitle = selectedMovie;
		theater = selectedTheater;
		time = selectedTime;
		date = selectedDate;
		remainingSeats = 49;
		// 컨트롤러 초기화
		controller = new SeatSelectionControllerImpl();
		reserveController = new ReserveControllerImpl();
		scheduleController = new ScheduleControllerImpl();
		movieController = new MovieControllerImpl();
		ticketController = new TicketControllerImpl();
		memberController = new MemberControllerImpl();
		// memberVO 객체 싱글톤 인스턴스로 관리
		memberVO = memberController.getLoggedInMember();
		remainingSeats -= controller.selectedSeat(movieTitle, date, theater, time).size();
		
		setTitle("좌석 선택 페이지");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(550, 500); // 가로와 세로 크기 수정
		setLocationRelativeTo(null);

		selectedSeats = new ArrayList<String>();

		JPanel mainPanel = new JPanel();

		// 좌석 선택 표
		gridPanel = new JPanel(new GridLayout(8, 8, 1, 1)); // 행과 열의 간격을 1로 설정
		gridPanel.setPreferredSize(new Dimension(200, 200)); // 표 전체 크기 설정

		// 열 제목 추가
		JPanel emptyPanel = new JPanel(); // 열 제목을 위한 빈 패널 생성
		gridPanel.add(emptyPanel); // 빈 패널을 추가하여 왼쪽으로 옮김

		for (int i = 0; i < 7; i++) {
			JLabel colLabel = new JLabel(String.valueOf(i + 1), SwingConstants.CENTER);
			gridPanel.add(colLabel);
		}

		for (char row = 'A'; row <= 'G'; row++) { // 행을 알파벳으로 설정
			// 행 번호 추가
			JLabel rowLabel = new JLabel(String.valueOf(row), SwingConstants.CENTER);
			gridPanel.add(rowLabel);

			for (int col = 1; col <= 7; col++) { // 열을 숫자로 설정
				String seat = row + String.valueOf(col);
				JCheckBox seatCheckBox = new JCheckBox();
				seatCheckBox.setPreferredSize(new Dimension(10, 10)); // 좌석 크기 설정
				seatCheckBox.setOpaque(false); // 배경 투명화
				seatCheckBox.setText(""); // 텍스트 비움
				seatCheckBox.setName(row + "-" + col); // 행-열 형태의 이름 설정
				seatCheckBox.addActionListener(e -> {
					JCheckBox clickedCheckBox = (JCheckBox) e.getSource();
					String[] seatName = clickedCheckBox.getName().split("-");
					String seatNumber = seatName[0] + seatName[1];
					if (clickedCheckBox.isSelected()) {
						selectedSeats.add(seatNumber); // 선택된 좌석을 리스트에 추가
						remainingSeats--; // 선택한 좌석 수만큼 잔여 좌석 수 감소
					} else {
						selectedSeats.remove(seatNumber); // 선택 취소된 좌석을 리스트에서 제거
						remainingSeats++; // 선택 취소한 좌석 수만큼 잔여 좌석 수 증가
					}
					updateSelectedSeatsLabel();
					updateAvailableSeatsLabel();
					numOfPeople = selectedSeats.size();
				});
				gridPanel.add(seatCheckBox);
			}
		}
		mainPanel.setLayout(null);
		seatPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // FlowLayout을 사용하여 좌측 정렬
		seatPanel.setBounds(0, 93, 210, 218);
		seatPanel.add(gridPanel); // 그리드 패널을 좌석 선택 패널에 추가

		mainPanel.add(seatPanel);

		// 선택한 좌석 표시
		selectedSeatsLabel = new JLabel("선택한 좌석: ");
		selectedSeatsLabel.setVerticalAlignment(SwingConstants.TOP);
		selectedSeatsLabel.setBounds(235, 68, 77, 228); // JLabel 크기 고정
		mainPanel.add(selectedSeatsLabel);

		// 예매 영화 정보 표시
		String movieInfo = "영화: " + movieTitle + ", 상영관: " + theater + ", 상영 날짜: " + date + ", 상영 시간: " + time;
		movieInfoLabel = new JLabel("<html>예매 영화 정보: <br>" + movieInfo + "</html>");
		movieInfoLabel.setVerticalAlignment(SwingConstants.TOP);
		movieInfoLabel.setBounds(344, 66, 155, 175);
		mainPanel.add(movieInfoLabel);

		getContentPane().add(mainPanel);
		availableSeatsLabel = new JLabel("잔여 좌석 수: " + remainingSeats);
		availableSeatsLabel.setBounds(35, 339, 103, 15);
		mainPanel.add(availableSeatsLabel);

		JLabel lblNewLabel = new JLabel("좌석 선택");
		lblNewLabel.setBounds(35, 68, 77, 15);
		mainPanel.add(lblNewLabel);

		// 예매하기 버튼 추가
		JButton reserveButton = new JButton("예매하기");
		reserveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(SeatSelectionPage.this, "예매하시겠습니까?", "예매 확인",
						JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					// 예매하기 버튼을 눌렀을 때의 동작 구현
					try {

						reserveNo = reserveController.getReserveNo(numOfPeople, memberVO.getMemberNo()); // reserve 테이블
						int scheduleNo = scheduleController.requestScheduleNo(selectedDate, selectedTime, // SCHEDULE_NO
								selectedTheater);
						int movieNo = movieController.getMovieNo(movieTitle);
						System.out.println("movieNo: " + movieNo);
						charge = reserveController.getCharge(reserveNo, memberVO.getMemberNo());

						for (String s : selectedSeats) {
							HashMap<String, Integer> target = new HashMap<String, Integer>();
							String row = String.valueOf(s.charAt(0));
							int column = Integer.parseInt(String.valueOf(s.charAt(1)));
							target.putAll(controller.chooseSeat(row, column, theater)); // seatNo, screenNo MAP으로 저장됨
							int seatNo = target.get("seatNo");
							int screenNo = target.get("screenNo");
							// 티켓 테이블에 데이터 저장 --
							ticketController.addTicket(reserveNo, scheduleNo, screenNo, seatNo);

						}

					} catch (SQLException er) {
						er.printStackTrace();
					}
					// 예매 정보는 movieInfo 변수에 저장된 데이터를 사용할 수 있습니다.
					ReservationCompletePage reservationCompletePage = new ReservationCompletePage(movieTitle, theater,
							date, time, numOfPeople, selectedSeats, charge);
					reservationCompletePage.setVisible(true);
					dispose(); // 현재 페이지 닫기
				}
			}
		});
		reserveButton.setBounds(223, 404, 97, 23);
		mainPanel.add(reserveButton);

		unabledSeats = controller.selectedSeat(movieTitle, date, theater, time);
		System.out.println(unabledSeats);
		// 가져온 좌석을 순회하며 해당 좌석을 비활성화 처리
		for (String seat : unabledSeats) {
			// 좌석의 문자와 숫자를 분리하여 행(row)과 열(column)로 저장
			String[] seatInfo = seat.split(" ");
			String row = seatInfo[0];
			int column = Integer.parseInt(seatInfo[1]);

			// 좌석을 비활성화 처리
			disableSeat(row, column);
		}
	}

	// 선택한 좌석 업데이트
	private void updateSelectedSeatsLabel() {
		StringBuilder selectedSeatsText = new StringBuilder("<html>선택한 좌석: <br>");
		for (String seat : selectedSeats) {
			selectedSeatsText.append(seat).append("<br>");
		}
		selectedSeatsText.append("</html>");
		selectedSeatsLabel.setText(selectedSeatsText.toString());
	}

	// 잔여 좌석 업데이트
	private void updateAvailableSeatsLabel() {
		availableSeatsLabel.setText("잔여 좌석 수: " + remainingSeats);
	}

	// 좌석을 비활성화 처리하는 메서드
	private void disableSeat(String row, int column) {
		// 그리드 패널에서 모든 컴포넌트를 가져옵니다.
		Component[] components = gridPanel.getComponents();
		for (Component component : components) {
			// 해당 컴포넌트가 JCheckBox인 경우에만 처리합니다.
			if (component instanceof JCheckBox) {
				JCheckBox checkBox = (JCheckBox) component;
				// 해당 체크박스의 이름을 가져옵니다.
				String checkBoxName = checkBox.getName();
				// 좌석 번호를 생성하여 비교합니다.
				String seatNumber = row + "-" + column;
				// 행과 열 정보가 일치하는 경우 해당 체크박스를 비활성화 처리합니다.
				if (seatNumber.equals(checkBoxName)) {
					checkBox.setEnabled(false);
					break;
				}
			}
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			SeatSelectionPage seatSelectionPage = new SeatSelectionPage(movieTitle, theater, time, date);
			seatSelectionPage.setVisible(true);
		});
	}
}