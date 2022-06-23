package vendingMachine;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.WindowConstants;

public class admin extends JFrame{
	// 메뉴 변경, 가격 변경, 재고 추가, 수익 확인, 잔고 확인, 현금 수금, 암호 변경, 재고 확인, 일별 매출, 월별 매출
	public admin() {
        JFrame frame = new JFrame("관리자 메뉴");
        frame.setBounds(400, 150, 800, 600);
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(false);
 
        // 폰트
        Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
        Font font1 = new Font(Font.MONOSPACED, Font.BOLD, 22);
        
        Panel panel = new Panel();
        panel.setBackground(new Color(255, 241, 232));
        panel.setLayout(null);
        panel.setSize(0, 600);
        panel.setFont(font);
        
        // 뒤로가기
        JButton back_bt = new JButton("뒤로가기");
        back_bt.setBounds(650, 10, 110, 30);
        panel.add(back_bt);
        
        back_bt.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		frame.dispose();	// 현재 창 닫기
        		new insertMoney();	// 초기화면
        	}
        });
        
        // 엔터키로 버튼 클릭
     	JRootPane rootPane = frame.getRootPane();
     	rootPane.setDefaultButton(back_bt);

        // 배열 설정 부분
        String admin_menu[] = { "메뉴 변경", "가격 변경", "재고 추가", "수익 확인", "잔고 확인", "현금 수금", 
        		"암호 변경", "재고 확인", "일별 매출", "월별 매출" };
        JButton bt[] = new JButton[admin_menu.length];
        ImageIcon icon[] = new ImageIcon[admin_menu.length];
        
        // 버튼 설정 부분
        for (int i = 0; i < admin_menu.length; i++) {

            // 메뉴 버튼
            bt[i] = new JButton(admin_menu[i]);
            if (i < 5) {
                bt[i].setBounds(60 + i * 140, 110, 100, 100);
            } else {
                bt[i].setBounds(60 + (i - 5) * 140, 300, 100, 100);
            }
            
            icon[i] = new ImageIcon((i+10) + ".jpg");		
            bt[i].setIcon(icon[i]);

            panel.add(bt[i]);
        }
        
        // 컴포넌트
        frame.add(panel, BorderLayout.NORTH);
        frame.setVisible(true);

        // 메뉴 버튼 이벤트
        // 1. 메뉴 변경
        bt[0].addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
	            bt[0].setEnabled(true);
	            frame.dispose();	// 현재 창 닫기
	            new changeMenu();	// 메뉴 변경
        	}
        });
        
        // 2. 가격 변경
        bt[1].addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		bt[1].setEnabled(true);
        		frame.dispose();	// 현재 창 닫기
        		new changePrice();	// 가격 변경
        	}
        });
        
        // 3. 재고 추가
        bt[2].addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		bt[2].setEnabled(true);
        		frame.dispose();	// 현재 창 닫기
        		new addStock();	// 재고 추가
        	}
        });
        
        // 4. 수익 확인
        bt[3].addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		bt[3].setEnabled(true);
        		frame.dispose();	// 현재 창 닫기
        		new profit();	// 수익 확인
        	}
        });
        
        // 5. 잔고 확인
        bt[4].addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		bt[4].setEnabled(true);
        		frame.dispose();	// 현재 창 닫기
        		new balance();	// 잔고 확인
        	}
        });
        
        // 6. 현금 수금
        bt[5].addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		bt[5].setEnabled(true);
        		frame.dispose();	// 현재 창 닫기
        		new cashCollection();	// 현금 수금
        	}
        });
        
        // 7. 암호 변경
        bt[6].addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		bt[6].setEnabled(true);
        		frame.dispose();	// 현재 창 닫기
        		new changePW();	// 암호 변경
        	}
        });
        
        // 8. 재고 확인
        bt[7].addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		bt[7].setEnabled(true);
        		frame.dispose();	// 현재 창 닫기
        		try {
					new stock();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	// 재고 확인
        	}
        });
        
        // 9. 일별 매출
        bt[8].addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		bt[8].setEnabled(true);
        		frame.dispose();	// 현재 창 닫기
        		try {
					new dailySales();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	// 일별 매출
        	}
        });
        
        // 10. 월별 매출
        bt[9].addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		bt[9].setEnabled(true);
        		frame.dispose();	// 현재 창 닫기
        		try {
					new monthlySales();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	// 월별 매출
        	}
        });
            
        // 끄기
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
	}
}