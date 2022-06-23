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
   
   // �迭 ���� �κ�
   private static setProduct[] product = new setProduct[5];

   // setting �Լ�
   public static void initProduct() {
 	  for(int i=0; i < product.length; i++) 
 		  	product[i] = new setProduct();
				
			// �̸��� ���� ����
			product[0].setName("��������");	product[0].setPrice(450);	product[0].setStock(3);
			product[1].setName("��Ŀ�ǡ�");	product[1].setPrice(500);	product[1].setStock(3);
			product[2].setName("�̿�����");	product[2].setPrice(550);	product[2].setStock(3);
			product[3].setName("���Ŀ��");	product[3].setPrice(700);	product[3].setStock(3);
			product[4].setName("ź������");	product[4].setPrice(750);	product[4].setStock(3);
	}
  
   // �޴� ���� �Լ�
   public static void setMenu(int i, String name, int price, int stock) {
	   product[i].setName(name);
	   product[i].setPrice(price);
	   product[i].setStock(stock);
   }
   
   // ���� ���� �Լ�
   public static void setPrice(int i, int price) {
	   product[i].setPrice(price);
   }
   
   // ��� �߰� �Լ�
   public static void addStock(int i, int stock) {
	   int amount = product[i].getStock();
	   product[i].setStock(amount + stock);
   }
   
   // ��� ��ȯ �Լ�
   public static int returnStock(int i) {
	   return product[i].getStock();
   }
   
   // ��ǰ�� ��ȯ �Լ�
   public static String returnName(int i) {
	   return product[i].getName();
   }
   
   // ��ǰ ���� ��ȯ �Լ�
   public static int returnLength() {
	   return product.length;
   }
   
   // ���� ��ǰ �Ѿ� ��ȯ �Լ�
   public static int buyTotal() {
	   return result;
   }
   
   // �հ� ��ȯ �Լ�(����Ȯ�ο�)
   public static int returnResult() {
	   return saveResult;
   }
   
   // ���� ��ǰ �Ѿ� setting �Լ�
   public static void setResult() {
	   saveResult = result;
	   result = 0;
	   
	   
   }
   
   public Machine_main(){      

      // �����δ�
      // ������ ������
      JFrame frame = new JFrame("�������� ���Ǳ�");
      frame.setBounds(400, 150, 800, 600);
      frame.setBackground(Color.black);
      setVisible(false);
      
      // ��Ʈ
      Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
      Font font1 = new Font(Font.MONOSPACED, Font.BOLD, 22);
      
      // ����
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
      
      // ��ư ���� �κ�
      for (int i = 0; i < product.length; i++) {
          // �޴� ��ư
          bt[i] = new JButton(product[i].getName());
          bt[i].setBounds(25 + i * 150, 50, 100, 100);
          
          // ���� �ݾ׿� ���� ���� ����
          if(insertMoney.payTotal() < product[i].getPrice()) {	// �ݾ� ����
        	  icon[i] = new ImageIcon("cant.jpg");
        	  bt[i].setIcon(icon[i]);
        	  bt[i].setEnabled(false);
          }
          else if((product[i].getStock() == 0) || (product[i].getStock() < 0)) {	// ǰ��
        	  icon[i] = new ImageIcon("out.jpg");
        	  bt[i].setIcon(icon[i]);
        	  bt[i].setEnabled(false);
        	  // ��������¥
          }
          else {
	          icon[i] = new ImageIcon(i + ".jpg");
	          bt[i].setIcon(icon[i]);
          }
          
          // ���� txt ��ư
          suja[i] = new TextField("  0");
          suja[i].setBackground(Color.white);
          suja[i].setEditable(false);
          suja[i].setBounds(bt[i].getX() + 30, bt[i].getY() + 175, 40, 20);

          // "-" ��ư
          minus[i] = new Button("-");
          minus[i].setBounds(bt[i].getX(), suja[i].getY(), 20, 20);
          minus[i].setEnabled(false);

          // "+" ��ư
          plus[i] = new Button("+");
          plus[i].setBounds(bt[i].getX() + (100 - 20), suja[i].getY(), 20, 20);
          plus[i].setEnabled(false);

          // ��ǰ��
          name[i] = new Label(product[i].getName());
          name[i].setBounds(bt[i].getX() + 15, suja[i].getY() - 70, 100, 20);
          
          // ����
          price[i] = new Label(product[i].getPrice() + "��");
          price[i].setBounds(bt[i].getX() + 20, suja[i].getY() - 45, 100, 20);
          
          // Ȯ�� ��ư
          ok[i] = new JButton("Ȯ��");
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
      
      // �߾�
      TextArea ta = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
      ta.setText("    ��ǰ��        ����      ����        �հ�\n\n");
      ta.setBackground(Color.white);
      ta.setEditable(false);
      ta.setFont(font1);

      // ����
      Panel pSouth = new Panel();
      pSouth.setFont(font);
      pSouth.setBackground(new Color(233, 244, 255));

      // �ϴ� ��ư
      JButton BUYbt = new JButton("����");
      JButton RESETbt = new JButton("�ʱ�ȭ");
      JButton CLOSEbt = new JButton("�ݱ�");
      pSouth.add(BUYbt);
      pSouth.add(RESETbt);
      pSouth.add(CLOSEbt);
      
      // ����Ű�� ��ư Ŭ��
      JRootPane rootPane = frame.getRootPane();
      rootPane.setDefaultButton(BUYbt);	// ���Ź�ư
      
      // BUYbt ���Ź�ư
      BUYbt.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {
        	  int payTotal = insertMoney.payTotal();
        	  String[] buttons = {"���� �缱��", "�ݾ� ������"};	// 0, 1
              
        	  // �� ���� �ݾ��� ���� ���� �ݾ׺��� Ŭ��
        	  if(result > payTotal) {
        		  int num = JOptionPane.showOptionDialog(null, "���� �����մϴ�!", "�ݾ� ����",
                          JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE, null, buttons, "���� �缱��");
        		  
        		  switch(num) {
        		  case 0:	// ���� �缱��
        			// ��� �������ֱ�
        			  for(int i=0;i<product.length;i++) {
                          int temp = product[i].getStock();
                          product[i].setStock(temp + count);
        			  }
        			  count = 0; total = 0; result = 0;
        			  frame.dispose();	// ���� â �ݱ�
                      new Machine_main();	// ���� �缱��
                      break;
                      
        		  case 1:	// �ݾ� ������
        			  count = 0; total = 0; result = 0;
        			  frame.dispose();	// ���� â �ݱ�
                      new insertMoney();	// �ݾ� ������
                      break;
        		  }
        	  }
        	  else {	// ���� �ݾ��� ���� ���� �ݾ׺��� ������
	              for (int i = 0; i < product.length; i++) {
	                  bt[i].setEnabled(true);
	                  minus[i].setEnabled(false);
	                  plus[i].setEnabled(false);
	                  suja[i].setText("0");
	              }
	              frame.dispose();	// ���� â �ݱ�
	              new change();	// �Ž�����
	              insertMoney.setpayTotal();	// ���� �ݾ� �ʱ�ȭ
	              setResult();	// ���� �ݾ� �ʱ�ȭ
        	  }
          }
      });
      
      // RESETbt �ʱ�ȭ ��ư
      RESETbt.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
        	  result = 0; total = 0; count = 0;
              initProduct();	// ���� ���� �ʱ�ȭ
        	  frame.dispose();	// ���� â �ݱ�
              new Machine_main();	// ���� â �����
                  
          }
      });
      
      //CLOSEbt �ݱ��ư
      CLOSEbt.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
        	  for(int i=0; i<product.length; i++) {
        		  product[i] = null;	// �޸� ��ȯ
        	  }
        	  
              System.exit(0);
          }
      });
      
      // ������Ʈ
      frame.add(pNorth, BorderLayout.NORTH);
      frame.add(ta, BorderLayout.CENTER);
      frame.add(pSouth, BorderLayout.SOUTH);
      frame.setVisible(true);

      // �̺�Ʈ��
      for (int i = 0; i < product.length; i++) {
          int j = i;

          // �޴� ��ư �̺�Ʈ
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
          
          // "-" ��ư �̺�Ʈ
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
          
          // "+" ��ư �̺�Ʈ
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
          
          //Ȯ�� ��ư �̺�Ʈ
          ok[i].addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
            	  if((product[j].getStock()-count) >= 0) {
            		  total += (product[j].getPrice()*count);
                      show = bt[j].getActionCommand();
                      ta.append("   " + show + "       " + product[j].getPrice() + "        " + count + "         " + total
                              + "��" + "\n");
                      ok[j].setEnabled(false);
                      result += total;
	              }
            	  // ��� �������ֱ�
                  int temp = product[j].getStock();
                  product[j].setStock(temp - count);
              }
          });
          
          // ����
          frame.addWindowListener(new WindowAdapter() {
              @Override
              public void windowClosing(WindowEvent e) {
                  System.exit(0);
              }
          });
          
      }
   }
   
   public static void main( String args[] ) {
	   initProduct();	// ��ǰ �ʱ�ȭ
	   insertMoney.initMoney();	// ȭ�� �ʱ�ȭ
	   new insertMoney();	// �� ���� ȭ��
   }


}