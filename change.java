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

public class change extends JFrame {
	static int total = 0;
	static int save = 0;
	// 거스름돈 반환 함수
	public static int returnChange() {
		return total;
	}
	
	// 잔돈 합계 초기화 함수
	public static void setTotal() {
		save = total;
		total = 0;
	}
	
	public change() {
		JFrame frame = new JFrame("거스름돈");
		frame.setBounds(400, 150, 800, 600);
	    
	    // 폰트
	 	Font font = new Font(Font.SANS_SERIF, Font.BOLD, 23);
		
		// 북쪽
        Panel pNorth = new Panel();
        pNorth.setBackground(new Color(255, 241, 232));
        pNorth.setLayout(null);
        pNorth.setSize(0, 100);
        pNorth.setFont(font);
		
		// 메인 화면
        JButton main_bt = new JButton("메인 화면");
        main_bt.setBounds(650, 10, 110, 30);
        pNorth.add(main_bt);
        
        main_bt.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		insertMoney.setpayTotal();
        		frame.dispose();	// 현재 창 닫기
        		new insertMoney();	// 초기 화면
        	}
        });
        
        // 엔터키로 버튼 클릭
     	JRootPane rootPane = frame.getRootPane();
     	rootPane.setDefaultButton(main_bt);
        
        // 중앙
        TextArea ta = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
        ta.setText("\n\t\t 단위\t\t\t수량\t\t금액\n\n");
        ta.setBackground(Color.white);
        ta.setEditable(false);
        ta.setFont(font);
        
        frame.add(pNorth, BorderLayout.NORTH);
        frame.add(ta, BorderLayout.CENTER);
        
        int c1000=0, c500=0, c100=0, c50=0, c10=0, ctemp=0;	// change
        int payTotal = insertMoney.payTotal();	// 지불 총액
        int buyTotal = Machine_main.buyTotal();	// 구매 총액
        total = payTotal - buyTotal;	// 거스름돈 총액
        
        c1000 = total / 1000; 
        ctemp = insertMoney.returnCountOfMoney(0);
        insertMoney.setCountOfMoney(0, ctemp-c1000);
        	
        c500 = total%1000 / 500;
        ctemp = insertMoney.returnCountOfMoney(1);
        insertMoney.setCountOfMoney(1, ctemp-c500);
        	
        c100 = total%1000 %500/ 100;
        ctemp = insertMoney.returnCountOfMoney(2);
        insertMoney.setCountOfMoney(2, ctemp-c100);
        	
        c50  = total%1000 %500% 100/50;
        ctemp = insertMoney.returnCountOfMoney(3);
        insertMoney.setCountOfMoney(3, ctemp-c50);
            
        c10  = total%1000 %500% 100%50/10;
        ctemp = insertMoney.returnCountOfMoney(4);
        insertMoney.setCountOfMoney(4, ctemp-c10);
        	
        
        ta.append("\t\t" + insertMoney.returnMoney(0) + "\t\t\t" + c1000 + "개\t\t\t" + (insertMoney.returnUnitOfMoney(0)*c1000) + "원\n");
        ta.append("\t\t" + insertMoney.returnMoney(1) + "\t\t\t" + c500 + "개\t\t\t" + (insertMoney.returnUnitOfMoney(1)*c500) + "원\n");
        ta.append("\t\t" + insertMoney.returnMoney(2) + "\t\t\t" + c100 + "개\t\t\t" + (insertMoney.returnUnitOfMoney(2)*c100) + "원\n");
        ta.append("\t\t" + insertMoney.returnMoney(3) + "\t\t\t" + c50 + "개\t\t\t" + (insertMoney.returnUnitOfMoney(3)*c50) + "원\n");
        ta.append("\t\t" + insertMoney.returnMoney(4) + "\t\t\t" + c10 + "개\t\t\t" + (insertMoney.returnUnitOfMoney(4)*c10) + "원\n");
        ta.append("\n\t=========================================\n\n");
        ta.append("\t\t\t\t\t\t\t" + total + "원이 반환됩니다.\n");
        ta.append("\t\t\t\t\t\t\t(Enter키를 누르세요...)\n");
        
        setTotal();	// 잔돈 초기화
        
	    frame.setVisible(true);
	    frame.setLocationRelativeTo(null);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}