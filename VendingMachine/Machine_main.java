package vendingMachine;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.Image.*;

import javax.imageio.*;
import javax.swing.*;
import javax.swing.event.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;

public class Machine_main extends JFrame {
   int count = 0;
   int total = 0;
   static int saveResult = 0;
   static int result = 0;
   String show = "";
   
   // 배열 설정 부분
   private static setProduct[] product = new setProduct[5];

   // setting 함수
   public static void initProduct() {
 	  for(int i=0; i < product.length; i++) 
 		  	product[i] = new setProduct();
				
			// 이름과 가격 설정
			product[0].setName("　물　　");	product[0].setPrice(450);	product[0].setStock(3);
			product[1].setName("　커피　");	product[1].setPrice(500);	product[1].setStock(3);
			product[2].setName("이온음료");	product[2].setPrice(550);	product[2].setStock(3);
			product[3].setName("고급커피");	product[3].setPrice(700);	product[3].setStock(3);
			product[4].setName("탄산음료");	product[4].setPrice(750);	product[4].setStock(3);
	}
  
   // 메뉴 변경 함수
   public static void setMenu(int i, String name, int price, int stock) {
	   product[i].setName(name);
	   product[i].setPrice(price);
	   product[i].setStock(stock);
   }
   
   // 가격 변경 함수
   public static void setPrice(int i, int price) {
	   product[i].setPrice(price);
   }
   
   // 재고 추가 함수
   public static void addStock(int i, int stock) {
	   int amount = product[i].getStock();
	   product[i].setStock(amount + stock);
   }
   
   // 재고 반환 함수
   public static int returnStock(int i) {
	   return product[i].getStock();
   }
   
   // 상품명 반환 함수
   public static String returnName(int i) {
	   return product[i].getName();
   }
   
   // 제품 개수 반환 함수
   public static int returnLength() {
	   return product.length;
   }
   
   // 구매 물품 총액 반환 함수
   public static int buyTotal() {
	   return result;
   }
   
   // 합계 반환 함수(수익확인용)
   public static int returnResult() {
	   return saveResult;
   }
   
   // 구매 물품 총액 setting 함수
   public static void setResult() {
	   saveResult = result;
	   result = 0;
	   
	   
   }
   
   public Machine_main(){      

      // 디자인단
      // 프레임 설정단
      JFrame frame = new JFrame("혜진이의 자판기");
      frame.setBounds(400, 150, 800, 600);
      frame.setBackground(Color.black);
      setVisible(false);
      
      // 폰트
      Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
      Font font1 = new Font(Font.MONOSPACED, Font.BOLD, 22);
      
      // 북쪽
      Panel pNorth = new Panel();
      pNorth.setBackground(new Color(233, 244, 255));
      pNorth.setLayout(null);
      pNorth.setSize(0, 300);
      pNorth.setFont(font);
      
      JButton bt[] = new JButton[product.length];
      TextField suja[] = new TextField[product.length];
      Button minus[] = new Button[product.length];
      Button plus[] = new Button[product.length];
      JButton ok[] = new JButton[product.length];
      Label price[] = new Label[product.length];
      Label name[] = new Label[product.length];
      ImageIcon icon[] = new ImageIcon[product.length];
      
      // 버튼 설정 부분
      for (int i = 0; i < product.length; i++) {
          // 메뉴 버튼
          bt[i] = new JButton(product[i].getName());
          bt[i].setBounds(25 + i * 150, 50, 100, 100);
          
          // 투입 금액에 따른 음료 선택
          if(insertMoney.payTotal() < product[i].getPrice()) {	// 금액 부족
        	  icon[i] = new ImageIcon("cant.jpg");
        	  bt[i].setIcon(icon[i]);
        	  bt[i].setEnabled(false);
          }
          else if((product[i].getStock() == 0) || (product[i].getStock() < 0)) {	// 품절
        	  icon[i] = new ImageIcon("out.jpg");
        	  bt[i].setIcon(icon[i]);
        	  bt[i].setEnabled(false);
        	  // 재고소진날짜
          }
          else {
	          icon[i] = new ImageIcon(i + ".jpg");
	          bt[i].setIcon(icon[i]);
          }
          
          // 숫자 txt 버튼
          suja[i] = new TextField("  0");
          suja[i].setBackground(Color.white);
          suja[i].setEditable(false);
          suja[i].setBounds(bt[i].getX() + 30, bt[i].getY() + 175, 40, 20);

          // "-" 버튼
          minus[i] = new Button("-");
          minus[i].setBounds(bt[i].getX(), suja[i].getY(), 20, 20);
          minus[i].setEnabled(false);

          // "+" 버튼
          plus[i] = new Button("+");
          plus[i].setBounds(bt[i].getX() + (100 - 20), suja[i].getY(), 20, 20);
          plus[i].setEnabled(false);

          // 상품명
          name[i] = new Label(product[i].getName());
          name[i].setBounds(bt[i].getX() + 15, suja[i].getY() - 70, 100, 20);
          
          // 가격
          price[i] = new Label(product[i].getPrice() + "원");
          price[i].setBounds(bt[i].getX() + 20, suja[i].getY() - 45, 100, 20);
          
          // 확인 버튼
          ok[i] = new JButton("확인");
          ok[i].setBounds(bt[i].getX(), suja[i].getY() + 30, 100, 20);
          ok[i].setEnabled(false);
          
          pNorth.add(bt[i]);
          pNorth.add(suja[i]);
          pNorth.add(minus[i]);
          pNorth.add(plus[i]);
          pNorth.add(name[i]);
          pNorth.add(price[i]);
          pNorth.add(ok[i]);
      }
      
      // 중앙
      TextArea ta = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
      ta.setText("    상품명        가격      수량        합계\n\n");
      ta.setBackground(Color.white);
      ta.setEditable(false);
      ta.setFont(font1);

      // 남쪽
      Panel pSouth = new Panel();
      pSouth.setFont(font);
      pSouth.setBackground(new Color(233, 244, 255));

      // 하단 버튼
      JButton BUYbt = new JButton("구매");
      JButton RESETbt = new JButton("초기화");
      JButton CLOSEbt = new JButton("닫기");
      pSouth.add(BUYbt);
      pSouth.add(RESETbt);
      pSouth.add(CLOSEbt);
      
      // 엔터키로 버튼 클릭
      JRootPane rootPane = frame.getRootPane();
      rootPane.setDefaultButton(BUYbt);	// 구매버튼
      
      // BUYbt 구매버튼
      BUYbt.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {
        	  int payTotal = insertMoney.payTotal();
        	  String[] buttons = {"음료 재선택", "금액 재투입"};	// 0, 1
              
        	  // 고른 음료 금액의 합이 지불 금액보다 클시
        	  if(result > payTotal) {
        		  int num = JOptionPane.showOptionDialog(null, "돈이 부족합니다!", "금액 오류",
                          JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE, null, buttons, "음료 재선택");
        		  
        		  switch(num) {
        		  case 0:	// 음료 재선택
        			// 재고 수정해주기
        			  for(int i=0;i<product.length;i++) {
                          int temp = product[i].getStock();
                          product[i].setStock(temp + count);
        			  }
        			  count = 0; total = 0; result = 0;
        			  frame.dispose();	// 현재 창 닫기
                      new Machine_main();	// 음료 재선택
                      break;
                      
        		  case 1:	// 금액 재투입
        			  count = 0; total = 0; result = 0;
        			  frame.dispose();	// 현재 창 닫기
                      new insertMoney();	// 금액 재투입
                      break;
        		  }
        	  }
        	  else {	// 음료 금액의 합이 지불 금액보다 작을시
	              for (int i = 0; i < product.length; i++) {
	                  bt[i].setEnabled(true);
	                  minus[i].setEnabled(false);
	                  plus[i].setEnabled(false);
	                  suja[i].setText("0");
	              }
	              frame.dispose();	// 현재 창 닫기
	              new change();	// 거스름돈
	              insertMoney.setpayTotal();	// 지불 금액 초기화
	              setResult();	// 지불 금액 초기화
        	  }
          }
      });
      
      // RESETbt 초기화 버튼
      RESETbt.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
        	  result = 0; total = 0; count = 0;
              initProduct();	// 선택 내역 초기화
        	  frame.dispose();	// 현재 창 닫기
              new Machine_main();	// 현재 창 재출력
                  
          }
      });
      
      //CLOSEbt 닫기버튼
      CLOSEbt.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
        	  for(int i=0; i<product.length; i++) {
        		  product[i] = null;	// 메모리 반환
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
      for (int i = 0; i < product.length; i++) {
          int j = i;

          // 메뉴 버튼 이벤트
          bt[i].addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
            	  
            	  
                  minus[j].setEnabled(true);
                  plus[j].setEnabled(true);
                  bt[j].setEnabled(false);
                  ok[j].setEnabled(true);

                  count = 0;  total = 0;
                  
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
          
          //확인 버튼 이벤트
          ok[i].addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
            	  if((product[j].getStock()-count) >= 0) {
            		  total += (product[j].getPrice()*count);
                      show = bt[j].getActionCommand();
                      ta.append("   " + show + "       " + product[j].getPrice() + "        " + count + "         " + total
                              + "원" + "\n");
                      ok[j].setEnabled(false);
                      result += total;
	              }
            	  // 재고 수정해주기
                  int temp = product[j].getStock();
                  product[j].setStock(temp - count);
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
   
   public static void main( String args[] ) {
	   initProduct();	// 제품 초기화
	   insertMoney.initMoney();	// 화폐 초기화
	   new insertMoney();	// 돈 투입 화면
   }


}