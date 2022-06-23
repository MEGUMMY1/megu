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

public class cashCollection extends JFrame {
	public cashCollection() {
		JFrame frame = new JFrame("현금 수금");
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
        
        int [] gtemp = {0,0,0,0,0};
    	int [] ggtemp = {1000,500,100,50,10};
    	int sum=0;
    	for(int i=0; i<5; i++) {
    		gtemp[i] = insertMoney.returnCountOfMoney(i);	// 지폐 별 개수
            if(gtemp[i] > 5) {	// 최소 금액 화폐 별 5장
            	gtemp[i] = (gtemp[i]-5) * ggtemp[i];
            	sum += gtemp[i];	// 인출 총액
            	insertMoney.setCountOfMoney(i, 5);	// 5장으로 초기화
        	}
    	}
        
        ta.append("\n\n\n\t\t화폐 단위별 5개를 제외한 현금을 수금합니다.\n");
        ta.append("\t=========================================\n\n");
        ta.append("\t\t\t\t인출 총액 >> " + sum + "원\n\n");
        ta.append("\t=========================================\n");
        ta.append("\t\t\t\t\t\t\t(Enter키를 누르세요...)");
        
        
	    frame.setVisible(true);
	    frame.setLocationRelativeTo(null);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}