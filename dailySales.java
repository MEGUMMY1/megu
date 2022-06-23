package vendingMachine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;


public class dailySales extends JFrame{
	public dailySales() throws IOException{		
		JFrame frame = new JFrame("일별 매출");
		frame.setBounds(400, 150, 800, 600);
		
	    JPanel panel = new JPanel();
		panel.setLayout(null);
		frame.add(panel, BorderLayout.CENTER);
		panel.setBackground(new Color(255, 241, 232));
		frame.add(panel);
		
		// 파일 입출력
		File file = new File("dailySales.txt");
		if(!file.exists())
			file.createNewFile();
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
		PrintWriter pw = new PrintWriter(bw, true);
		BufferedReader br = new BufferedReader(new FileReader(file));
		
     	// 날짜
     	Calendar calendar = Calendar.getInstance();
     	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd",Locale.KOREA);
     	JLabel date = new JLabel(sdf1.format(calendar.getTime()));
	    
	    // 폰트
	 	Font font = new Font(Font.SANS_SERIF, Font.BOLD, 23);
		
		// 북쪽
        Panel pNorth = new Panel();
        pNorth.setBackground(new Color(255, 241, 232));
        pNorth.setLayout(null);
        pNorth.setSize(0, 100);
        pNorth.setFont(font);
		
        // 뒤로가기
        JButton back_bt = new JButton("뒤로가기");
        back_bt.setBounds(650, 10, 110, 30);
        pNorth.add(back_bt);
        
        back_bt.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		frame.dispose();	// 현재 창 닫기
        		new admin();	// 관리자 화면
        	}
        });
        
        // 엔터키로 버튼 클릭
     	JRootPane rootPane = frame.getRootPane();
     	rootPane.setDefaultButton(back_bt);
     	
     	// 중앙
        TextArea ta = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
        ta.setBackground(Color.white);
        ta.setEditable(false);
        ta.setFont(font);
        ta.setText("\n   날짜\t\t금액\n");
        
        frame.add(pNorth, BorderLayout.NORTH);
        frame.add(ta, BorderLayout.CENTER);
        
        // 화면 출력    	
     	int todayTotal = profit.getProfit();
     	
     	ta.append("========================\n");
     	bw.write(sdf1.format(calendar.getTime()) + " : " + todayTotal + "원\n");
     	bw.close();
     	
     	while(true) {
     		char[]buf = new char[4];
     		int ret = br.read(buf);
     		if(ret==-1) break;
     		ta.append(String.valueOf(buf));
     	}
     	br.close();
     	
        frame.setVisible(true);
	    frame.setLocationRelativeTo(null);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
