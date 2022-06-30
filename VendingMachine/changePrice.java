package vendingMachine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;

public class changePrice extends JFrame {
	public changePrice() {
		JFrame frame = new JFrame("상품 가격 변경");
		frame.setBounds(400, 150, 800, 600);
	    
		JPanel panel = new JPanel();
		panel.setLayout(null);
		frame.add(panel, BorderLayout.CENTER);
		panel.setBackground(new Color(255, 241, 232));
		frame.add(panel);
		
		// 폰트
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 16);
		panel.setFont(font);
		
		// 뒤로가기
        JButton back_bt = new JButton("뒤로가기");
        back_bt.setBounds(650, 10, 110, 30);
        panel.add(back_bt);
        
        back_bt.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		frame.dispose();	// 현재 창 닫기
        		new admin();	// 관리자 화면
        	}
        });
        
        JLabel notice = new JLabel("< 물:0, 커피:1, 이온음료:2, 고급커피:3, 탄산음료:4 >");
        JLabel lNum = new JLabel("변경할 메뉴의 번호를 입력하세요 :");
		JTextField chNum = new JTextField();	// 메뉴 번호
		
		JLabel lPrice = new JLabel("변경 가격 :");
		JTextField chPrice = new JTextField();	// 가격
		
		JButton bt = new JButton("변경");		// 변경버튼
		
		// 엔터키로 버튼 클릭
		JRootPane rootPane = frame.getRootPane();
		rootPane.setDefaultButton(bt);
			    
		// 공지 label
		notice.setFont(font);
		notice.setBounds(170, 200, 600, 20);
		panel.add(notice);
		
		// 메뉴 번호 label
		lNum.setFont(font);
		lNum.setBounds(170, 250, 300, 20);
		panel.add(lNum);
			    
		// 메뉴 번호 text field
		chNum.setFont(font);
		chNum.setBounds(450, 250, 70, 20);
		panel.add(chNum);

		
		// 변경 가격 label
		lPrice.setFont(font);
		lPrice.setBounds(170, 300, 300, 20);
		panel.add(lPrice);
			    
		// 변경 가격 text field
		chPrice.setFont(font);
		chPrice.setBounds(300, 300, 200, 20);
		panel.add(chPrice);
		
		// 변경 버튼
	    bt.setFont(font);
	    bt.setBounds(540, 260, 70, 50);
	    panel.add(bt);
	    
	    // 변경 버튼 이벤트
	    bt.addActionListener( new ActionListener() {
	        
	    	public void actionPerformed(ActionEvent e) {
	    		int i = Integer.valueOf(chNum.getText());	// 메뉴 인덱스
	    		int price = Integer.valueOf(chPrice.getText());
	    		
	    		if(i > Machine_main.returnLength()){      // 변경하려는 인덱스가 존재하는 인덱스보다 크면
	    			JOptionPane.showMessageDialog( null, "메뉴의 번호를 확인해주세요.", "메뉴 번호 오류", JOptionPane.ERROR_MESSAGE);
	    			frame.dispose();	// 현재 창 닫기
		        	new changeMenu();	// 창 다시 띄우기
	    		}
	    		else {       
	    			JOptionPane.showMessageDialog( null, "가격이 변경 되었습니다." );
	    			// 메뉴 가격 변경
	    			Machine_main.setPrice(i, price); 
	    			frame.dispose();	// 현재 창 닫기
		        	new insertMoney();	// 관리자 창 띄우기
	    		}
	    		
	    	}
	
	    } );
	    frame.setVisible(true);
	    frame.setLocationRelativeTo(null);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
