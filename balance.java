package vendingMachine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class balance extends JFrame {
	public balance() {
		JFrame frame = new JFrame("잔고 확인");
		frame.setBounds(400, 150, 800, 600);
	    
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
        ta.setText("\n\t\t 단위\t\t\t수량\t\t금액\n");
        ta.setBackground(Color.white);
        ta.setEditable(false);
        ta.setFont(font);
        ta.append("\t===========================================\n\n");
        
        frame.add(pNorth, BorderLayout.NORTH);
        frame.add(ta, BorderLayout.CENTER);
        
        int total = 0;
        
        for(int i = 0; i < 5; i++) {
        	int result = insertMoney.returnUnitOfMoney(i) * insertMoney.returnCountOfMoney(i);	// 단위별 총액
        	total += result;	// 잔고 총액
        	ta.append("\t\t" + insertMoney.returnMoney(i) + "\t\t\t" + insertMoney.returnCountOfMoney(i) + "개\t\t\t" 
        				 + result + "원\n");
        }
        ta.append("\n\t===========================================\n\n");
        ta.append("\t\t\t\t\t현재 잔고 " + total + "원\n");
        
	    frame.setVisible(true);
	    frame.setLocationRelativeTo(null);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
