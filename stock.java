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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class stock extends JFrame {
	public stock() throws IOException {
		JFrame frame = new JFrame("��� Ȯ��");
		frame.setBounds(400, 150, 800, 600);
	    
	    // ���� �����
	 	File file = new File("Stock.txt");
	 	if(!file.exists())
	 		file.createNewFile();
	 		
	 	BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
	 	PrintWriter pw = new PrintWriter(bw, true);
	 	BufferedReader br = new BufferedReader(new FileReader(file));
	 		
	 	// ��¥
	    Calendar calendar = Calendar.getInstance();
	    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss",Locale.KOREA);
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
        ta.setText("\n\t\t ��ǰ��\t\t\t\t����\n");
        ta.setBackground(Color.white);
        ta.setEditable(false);
        ta.setFont(font);
        ta.append("\n\t=========================================\n\n");
        
        frame.add(pNorth, BorderLayout.NORTH);
        frame.add(ta, BorderLayout.CENTER);
        
        
        String [] productName = new String[5];
        int count = 0;
        for(int i = 0; i < 5; i++) {
        	ta.append("\t\t" + Machine_main.returnName(i) + "\t\t\t\t" + Machine_main.returnStock(i) + "��\n");
        	
        	if(Machine_main.returnStock(i) == 0) {
        		bw.write(sdf1.format(calendar.getTime()) + " : " + Machine_main.returnName(i) + " ǰ��\n");
        	}
        }
     	bw.close();
     	
        
	    frame.setVisible(true);
	    frame.setLocationRelativeTo(null);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
}
