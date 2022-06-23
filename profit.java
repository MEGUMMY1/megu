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
import javax.swing.JRootPane;

public class profit extends JFrame {

    static int buyTotal = Machine_main.returnResult();	// 구매 총액
    
    // 수익 반환 함수
    public static int getProfit() {
    	return buyTotal;
    }
    
	public profit() {
		JFrame frame = new JFrame("수익 확인");
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
        ta.setBackground(Color.white);
        ta.setEditable(false);
        ta.setFont(font);
        
        frame.add(pNorth, BorderLayout.NORTH);
        frame.add(ta, BorderLayout.CENTER);
        
        
        ta.append("\n\n\n\t\t\t\t< 혜진이의 자판기 >\n");
        ta.append("\t=========================================\n\n");
        ta.append("\t\t\t\t현재 수익 >> " + buyTotal + "원\n\n");
        ta.append("\t=========================================\n");
        ta.append("\t\t\t\t\t\t\t(Enter키를 누르세요...)");
        
        
	    frame.setVisible(true);
	    frame.setLocationRelativeTo(null);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}