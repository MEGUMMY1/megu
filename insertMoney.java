package vendingMachine;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.Image.*;

import javax.imageio.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;

public class insertMoney extends JFrame {
	int count = 0;
	static int coinTotal = 0;
	static int billTotal = 0;
	static int sum = 0;
	String show = "";
	
	// 배열 설정
	private static setMoney[] money = new setMoney[5];
	
	// setting 함수
	public static void initMoney() {
		for(int i=0; i<money.length; i++) 
			money[i] = new setMoney();
					
	 	  	// 화폐 단위별 개수 설정 (5개)
	 	  	money[0].setcountOfMoney(5);
	 	  	money[1].setcountOfMoney(5);
	 	  	money[2].setcountOfMoney(5);
	 	  	money[3].setcountOfMoney(5);
	 	  	money[4].setcountOfMoney(5);
	 	  		
			// 화폐 단위별 금액 설정
			money[0].setunitOfMoney(1000);	// money[0]:1000원
			money[1].setunitOfMoney(500);	// money[1]:500원
			money[2].setunitOfMoney(100);	// money[2]:100원
			money[3].setunitOfMoney(50);	// money[3]:50원
			money[4].setunitOfMoney(10);	// money[4]:10원
			
			// 화폐 단위별 금액 설정
			money[0].setMoney("1000원");	// money[0]:1000원
			money[1].setMoney("500원 ");	// money[1]:500원
			money[2].setMoney("100원 ");	// money[2]:100원
			money[3].setMoney("50원  ");	// money[3]:50원
			money[4].setMoney("10원  ");	// money[4]:10원
	}
	
	
	// 동전별 개수 반환 함수
	public static int returnCountOfMoney(int i) {
		   return money[i].getcountOfMoney();
	}
	
	// 잔액 반환 함수
	public static String returnMoney(int i) {
		return money[i].getMoney();
	}
	
	// 동전별 금액 반환 함수
	public static int returnUnitOfMoney(int i) {
		return money[i].getunitOfMoney();
	}
	
	// 지폐별 개수 setting 함수
	public static void setCountOfMoney(int i, int countOfMoney) {
		money[i].setcountOfMoney(countOfMoney);
	}
	
	// 지불 금액 합 반환 함수
	public static int payTotal() {
		return coinTotal + billTotal;
	}
	
	// 지불 금액 초기화 함수
	public static void setpayTotal() {
		coinTotal = 0;
		billTotal = 0;
		sum = 0;
	}
	
	// 지폐 투입 상한선 점검 함수
	public boolean billError(int billTotal, TextArea ta) throws Exception{
    	// 금액 상한선 조건
    	if(billTotal > 3000) {
        	JOptionPane.showMessageDialog( null, "지폐 최대 투입은 3,000원 입니다.", "투입 금액 오류", JOptionPane.ERROR_MESSAGE);
        	return true;
        }
    	else {
    		ta.append("       " + money[0].getMoney() + "       " + count + "         " + money[0].getunitOfMoney() * count
                    + "원" + "\n");
    		return false;
    	}
    }
	
	// 금액 투입 상한선 점검 함수
	public boolean coinError(int sum, TextArea ta, int j) throws Exception{
		// 금액 상한선 조건
		if(sum >= 5000) {
			JOptionPane.showMessageDialog( null, "5,000원 이하로 투입하세요.", "투입 금액 오류", JOptionPane.ERROR_MESSAGE);
			return true;
		}
		else {
			ta.append("       " + money[j].getMoney() + "       " + count + "         " + money[j].getunitOfMoney() * count
					+ "원" + "\n");
			return false;
		}
	}

	public insertMoney() {
        
        JFrame frame = new JFrame("돈을 투입해주세요");
        frame.setBounds(400, 150, 800, 600);
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(false);
 
        // 폰트
        Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
        Font font1 = new Font(Font.MONOSPACED, Font.BOLD, 22);
        
        // 북쪽
        Panel pNorth = new Panel();
        pNorth.setBackground(new Color(255, 241, 232));
        pNorth.setLayout(null);
        pNorth.setSize(0, 300);
        pNorth.setFont(font);
        
        // 관리자 모드 로그인 창
        JButton bt4 = new JButton("관리자 모드");
        bt4.setBounds(650, 10, 110, 30);
        pNorth.add(bt4);
        
        bt4.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		frame.dispose();	// 현재 창 닫기
        		new login();	// 로그인 창 띄우기
        	}
        });

        // 배열 설정 부분
        JButton bt[] = new JButton[money.length];
        TextField suja[] = new TextField[money.length];
        Button minus[] = new Button[money.length];
        Button plus[] = new Button[money.length];
        JButton ok[] = new JButton[money.length];
        Label l[] = new Label[money.length];
        ImageIcon icon[] = new ImageIcon[money.length];
        
        // 버튼 설정 부분
        for (int i = 0; i < money.length; i++) {

            // 메뉴 버튼
            bt[i] = new JButton(money[i].getMoney());
            bt[i].setBounds(25 + i * 150, 50, 100, 100);
            
            icon[i] = new ImageIcon((i+5) + ".jpg");		
            bt[i].setIcon(icon[i]);
            
            // 숫자 txt 버튼
            suja[i] = new TextField("  0");
            suja[i].setBackground(Color.white);
            suja[i].setEditable(false);
            suja[i].setBounds(bt[i].getX() + 30, bt[i].getY() + 110, 40, 20);

            // "-" 버튼
            minus[i] = new Button("-");
            minus[i].setBounds(bt[i].getX(), suja[i].getY(), 20, 20);
            minus[i].setEnabled(false);

            // "+" 버튼
            plus[i] = new Button("+");
            plus[i].setBounds(bt[i].getX() + (100 - 20), suja[i].getY(), 20, 20);
            plus[i].setEnabled(false);

            // 투입 버튼
            ok[i] = new JButton("투입");
            ok[i].setBounds(bt[i].getX(), suja[i].getY() + 30, 100, 20);
            ok[i].setEnabled(false);

            pNorth.add(bt[i]);
            pNorth.add(suja[i]);
            pNorth.add(minus[i]);
            pNorth.add(plus[i]);
            pNorth.add(ok[i]);
        }
        
        // 중앙
        TextArea ta = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
        ta.setText("         금액      수량        총액\n\n");
        ta.setBackground(Color.white);
        ta.setEditable(false);
        ta.setFont(font1);

        // 남쪽
        Panel pSouth = new Panel();
        pSouth.setFont(font);
        pSouth.setBackground(new Color(255, 241, 232));

        // 하단 버튼
        JButton INSERTbt = new JButton("돈 투입");
        JButton RESETbt = new JButton("초기화");
        JButton CLOSEbt = new JButton("닫기");
        pSouth.add(INSERTbt);
        pSouth.add(RESETbt);
        pSouth.add(CLOSEbt);
        
        // 엔터키로 버튼 클릭
        JRootPane rootPane = frame.getRootPane();
        rootPane.setDefaultButton(INSERTbt);	// 구매버튼
        
        // 돈 투입 버튼
        INSERTbt.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if(sum > 5000) {
                	JOptionPane.showMessageDialog( null, "5,000원 이하로 투입하세요.", "투입 금액 오류", JOptionPane.ERROR_MESSAGE);
                	sum = 0; count = 0;
                	frame.dispose();	// 현재 창 닫기
            		new insertMoney();	// 창 새로 띄우기
                }
                else {
                	frame.dispose();	// 현재 창 닫기
                	new Machine_main();	// 음료 선택 창 띄우기
        		}
        	}
        });
        
        // 초기화 버튼
        RESETbt.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();	// 현재 창 닫기
        		new insertMoney();	// 창 새로 띄우기
            }
        });
        
        // 닫기버튼
        CLOSEbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	for(int i=0; i<money.length; i++) {
          		  money[i] = null;
          	  }
                System.exit(0);
            }
        });
        
        // 컴포넌트
        frame.add(pNorth, BorderLayout.NORTH);
        frame.add(ta, BorderLayout.CENTER);
        frame.add(pSouth, BorderLayout.SOUTH);
        frame.setVisible(true);
        
        // 이벤트단
        // 천원 투입 버튼 이벤트
        ok[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	// 잔고 수정해주기
            	int temp = money[0].getcountOfMoney();
            	money[0].setcountOfMoney(temp + count);
            	// 금액 합 구하기
            	billTotal += (money[0].getunitOfMoney()*count);
            	
            	try{
            		if(billError(billTotal, ta) == true) {
            			// 잔고 수정해주기
                    	int temp2 = money[0].getcountOfMoney();
            			money[0].setcountOfMoney(temp - count);
            			// 금액 합 정정
                    	billTotal -= (money[0].getunitOfMoney()*count);
            			frame.dispose();	// 현재 창 닫기
            			new insertMoney();	// 창 새로 띄우기
            		}
            	}catch(Exception e1) {
            		ta.append("       " + money[0].getMoney() + "       " + count + "         " + money[0].getunitOfMoney() * count
                            + "원" + "\n");
                    ok[0].setEnabled(false);
            	}
            }
        });
        
        // 동전 투입 버튼 이벤트
        for(int i = 1; i < money.length; i++) {
        	int j = i;
        	ok[i].addActionListener(new ActionListener() {
        		@Override
        		public void actionPerformed(ActionEvent e) {
        			// 잔고 수정해주기
                	int temp = money[j].getcountOfMoney();
                	money[j].setcountOfMoney(temp + count);
                	
                	// 금액 합 구하기
        			coinTotal += (money[j].getunitOfMoney()*count);
        			sum = coinTotal + billTotal;
        			
        			try{
        				if(coinError(sum, ta, j) == true) {
        					// 잔고 수정해주기
                        	int temp2 = money[j].getcountOfMoney();
                			money[j].setcountOfMoney(temp - count);
                			// 금액 합 정정
                			coinTotal -= (money[j].getunitOfMoney()*count);
                			sum = coinTotal + billTotal;
                			frame.dispose();	// 현재 창 닫기
                			new insertMoney();	// 창 새로 띄우기
                		}
                	} catch(Exception e1) {
                		ta.append("       " + money[j].getMoney() + "       " + count + "         " + money[j].getunitOfMoney() * count
        						+ "원" + "\n");
                		ok[j].setEnabled(false);
                	}
        		}
        	});
        }
        
        for(int i = 0; i < money.length; i++) {
            int j = i;

            // 메뉴 버튼 이벤트
            bt[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    minus[j].setEnabled(true);
                    plus[j].setEnabled(true);
                    bt[j].setEnabled(false);
                    ok[j].setEnabled(true);

                    count = 0;
                }
            });
            
            // "-" 버튼 이벤트
            minus[i].addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (count > 0) {
                        count = count - 1;
                        suja[j].setText(count + "");
                        ok[j].setEnabled(true);
                    } else {
                        minus[j].setEnabled(false);
                    }
                }
            });
            
            // "+" 버튼 이벤트
            plus[i].addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    count = count + 1;
                    suja[j].setText(count + "");
                    ok[j].setEnabled(true);
                    if (count > 0) {
                        minus[j].setEnabled(true);
                    }
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
}



