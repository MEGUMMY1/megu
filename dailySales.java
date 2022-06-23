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
		JFrame frame = new JFrame("�Ϻ� ����");
		frame.setBounds(400, 150, 800, 600);
		
	    JPanel panel = new JPanel();
		panel.setLayout(null);
		frame.add(panel, BorderLayout.CENTER);
		panel.setBackground(new Color(255, 241, 232));
		frame.add(panel);
		
		// ���� �����
		File file = new File("dailySales.txt");
		if(!file.exists())
			file.createNewFile();
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
		PrintWriter pw = new PrintWriter(bw, true);
		BufferedReader br = new BufferedReader(new FileReader(file));
		
     	// ��¥
     	Calendar calendar = Calendar.getInstance();
     	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd",Locale.KOREA);
     	JLabel date = new JLabel(sdf1.format(calendar.getTime()));
	    
	    // ��Ʈ
	 	Font font = new Font(Font.SANS_SERIF, Font.BOLD, 23);
		
		// ����
        Panel pNorth = new Panel();
        pNorth.setBackground(new Color(255, 241, 232));
        pNorth.setLayout(null);
        pNorth.setSize(0, 100);
        pNorth.setFont(font);
		
        // �ڷΰ���
        JButton back_bt = new JButton("�ڷΰ���");
        back_bt.setBounds(650, 10, 110, 30);
        pNorth.add(back_bt);
        
        back_bt.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		frame.dispose();	// ���� â �ݱ�
        		new admin();	// ������ ȭ��
        	}
        });
        
        // ����Ű�� ��ư Ŭ��
     	JRootPane rootPane = frame.getRootPane();
     	rootPane.setDefaultButton(back_bt);
     	
     	// �߾�
        TextArea ta = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
        ta.setBackground(Color.white);
        ta.setEditable(false);
        ta.setFont(font);
        ta.setText("\n   ��¥\t\t�ݾ�\n");
        
        frame.add(pNorth, BorderLayout.NORTH);
        frame.add(ta, BorderLayout.CENTER);
        
        // ȭ�� ���    	
     	int todayTotal = profit.getProfit();
     	
     	ta.append("========================\n");
     	bw.write(sdf1.format(calendar.getTime()) + " : " + todayTotal + "��\n");
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
