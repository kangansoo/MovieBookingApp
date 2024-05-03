package com.kosa.view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Date;
import java.util.Properties;
import javax.swing.*;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class ReservationForm extends JFrame {
    private JComboBox<String> movieComboBox;
    private JComboBox<String> theaterComboBox;
    private JComboBox<String> timeComboBox;
    private JDatePickerImpl datePicker;
    private JButton nextButton;

    public ReservationForm() {
        setTitle("영화 예매 페이지");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(670, 550);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();

        JLabel movieLabel = new JLabel("영화 제목:");
        movieLabel.setBounds(12, 189, 76, 30);
        movieComboBox = new JComboBox<>(new String[]{"영화1", "영화2", "영화3"});
        movieComboBox.setBounds(12, 221, 244, 30);
        mainPanel.setLayout(null);
        mainPanel.add(movieLabel);
        mainPanel.add(movieComboBox);

        JLabel theaterLabel = new JLabel("상영관:");
        theaterLabel.setBounds(12, 261, 76, 30);
        theaterComboBox = new JComboBox<>(new String[]{"상영관1", "상영관2", "상영관3"});
        theaterComboBox.setBounds(12, 291, 244, 30);
        mainPanel.add(theaterLabel);
        mainPanel.add(theaterComboBox);

        JLabel timeLabel = new JLabel("상영 시간:");
        timeLabel.setBounds(12, 331, 76, 30);
        timeComboBox = new JComboBox<>(new String[]{"10:00", "13:00", "16:00", "19:00"});
        timeComboBox.setBounds(12, 361, 244, 30);
        mainPanel.add(timeLabel);
        mainPanel.add(timeComboBox);

        JLabel dateLabel = new JLabel("날짜:");
        dateLabel.setBounds(12, 124, 76, 30);
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.setBounds(12, 152, 244, 30);
        mainPanel.add(dateLabel);
        mainPanel.add(datePicker);

        nextButton = new JButton("다음");
        nextButton.setBounds(236, 456, 195, 47);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // '다음' 버튼 눌렀을 때의 동작 구현
                // 선택된 영화, 상영관, 상영 시간, 날짜 정보를 가져올 수 있습니다.
                String selectedMovie = (String) movieComboBox.getSelectedItem();
                String selectedTheater = (String) theaterComboBox.getSelectedItem();
                String selectedTime = (String) timeComboBox.getSelectedItem();
                Date selectedDate = (Date) datePicker.getModel().getValue();
                // 이 정보들을 이용하여 다음 단계를 처리할 수 있습니다.
            }
        });
        mainPanel.add(nextButton);

        // 이미지를 표시할 패널 추가
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setPreferredSize(new Dimension(200, 300)); // 이미지 크기 조절
        ImageIcon icon = createImageIcon("https://shorturl.at/ELPS7"); // 이미지 로드
        if (icon != null) {
            JLabel imageLabel = new JLabel(icon);
            imagePanel.add(imageLabel, BorderLayout.CENTER);
        }
        mainPanel.add(imagePanel, BorderLayout.EAST); // 오른쪽에 추가

        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    // 이미지를 로드하는 메서드
    protected ImageIcon createImageIcon(String path) {
        try {
            URL url = new URL(path);
            Image image = ImageIO.read(url);
            return new ImageIcon(image);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ReservationForm form = new ReservationForm();
            form.setVisible(true);
        });
    }
}

