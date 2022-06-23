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
	
	// �迭 ����
	private static setMoney[] money = new setMoney[5];
	
	// setting �Լ�
	public static void initMoney() {
		for(int i=0; i<money.length; i++) 
			money[i] = new setMoney();
					
	 	  	// ȭ�� ������ ���� ���� (5��)
	 	  	money[0].setcountOfMoney(5);
	 	  	money[1].setcountOfMoney(5);
	 	  	money[2].setcountOfMoney(5);
	 	  	money[3].setcountOfMoney(5);
	 	  	money[4].setcountOfMoney(5);
	 	  		
			// ȭ�� ������ �ݾ� ����
			money[0].setunitOfMoney(1000);	// money[0]:1000��
			money[1].setunitOfMoney(500);	// money[1]:500��
			money[2].setunitOfMoney(100);	// money[2]:100��
			money[3].setunitOfMoney(50);	// money[3]:50��
			money[4].setunitOfMoney(10);	// money[4]:10��
			
			// ȭ�� ������ �ݾ� ����
			money[0].setMoney("1000��");	// money[0]:1000��
			money[1].setMoney("500�� ");	// money[1]:500��
			money[2].setMoney("100�� ");	// money[2]:100��
			money[3].setMoney("50��  ");	// money[3]:50��
			money[4].setMoney("10��  ");	// money[4]:10��
	}
	
	
	// ������ ���� ��ȯ �Լ�
	public static int returnCountOfMoney(int i) {
		   return money[i].getcountOfMoney();
	}
	
	// �ܾ� ��ȯ �Լ�
	public static String returnMoney(int i) {
		return money[i].getMoney();
	}
	
	// ������ �ݾ� ��ȯ �Լ�
	public static int returnUnitOfMoney(int i) {
		return money[i].getunitOfMoney();
	}
	
	// ���� ���� setting �Լ�
	public static void setCountOfMoney(int i, int countOfMoney) {
		money[i].setcountOfMoney(countOfMoney);
	}
	
	// ���� �ݾ� �� ��ȯ �Լ�
	public static int payTotal() {
		return coinTotal + billTotal;
	}
	
	// ���� �ݾ� �ʱ�ȭ �Լ�
	public static void setpayTotal() {
		coinTotal = 0;
		billTotal = 0;
		sum = 0;
	}
	
	// ���� ���� ���Ѽ� ���� �Լ�
	public boolean billError(int billTotal, TextArea ta) throws Exception{
    	// �ݾ� ���Ѽ� ����
    	if(billTotal > 3000) {
        	JOptionPane.showMessageDialog( null, "���� �ִ� ������ 3,000�� �Դϴ�.", "���� �ݾ� ����", JOptionPane.ERROR_MESSAGE);
        	return true;
        }
    	else {
    		ta.append("       " + money[0].getMoney() + "       " + count + "         " + money[0].getunitOfMoney() * count
                    + "��" + "\n");
    		return false;
    	}
    }
	
	// �ݾ� ���� ���Ѽ� ���� �Լ�
	public boolean coinError(int sum, TextArea ta, int j) throws Exception{
		// �ݾ� ���Ѽ� ����
		if(sum >= 5000) {
			JOptionPane.showMessageDialog( null, "5,000�� ���Ϸ� �����ϼ���.", "���� �ݾ� ����", JOptionPane.ERROR_MESSAGE);
			return true;
		}
		else {
			ta.append("       " + money[j].getMoney() + "       " + count + "         " + money[j].getunitOfMoney() * count
					+ "��" + "\n");
			return false;
		}
	}

	public insertMoney() {
        
        JFrame frame = new JFrame("���� �������ּ���");
        frame.setBounds(400, 150, 800, 600);
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(false);
 
        // ��Ʈ
        Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
        Font font1 = new Font(Font.MONOSPACED, Font.BOLD, 22);
        
        // ����
        Panel pNorth = new Panel();
        pNorth.setBackground(new Color(255, 241, 232));
        pNorth.setLayout(null);
        pNorth.setSize(0, 300);
        pNorth.setFont(font);
        
        // ������ ��� �α��� â
        JButton bt4 = new JButton("������ ���");
        bt4.setBounds(650, 10, 110, 30);
        pNorth.add(bt4);
        
        bt4.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		frame.dispose();	// ���� â �ݱ�
        		new login();	// �α��� â ����
        	}
        });

        // �迭 ���� �κ�
        JButton bt[] = new JButton[money.length];
        TextField suja[] = new TextField[money.length];
        Button minus[] = new Button[money.length];
        Button plus[] = new Button[money.length];
        JButton ok[] = new JButton[money.length];
        Label l[] = new Label[money.length];
        ImageIcon icon[] = new ImageIcon[money.length];
        
        // ��ư ���� �κ�
        for (int i = 0; i < money.length; i++) {

            // �޴� ��ư
            bt[i] = new JButton(money[i].getMoney());
            bt[i].setBounds(25 + i * 150, 50, 100, 100);
            
            icon[i] = new ImageIcon((i+5) + ".jpg");		
            bt[i].setIcon(icon[i]);
            
            // ���� txt ��ư
            suja[i] = new TextField("  0");
            suja[i].setBackground(Color.white);
            suja[i].setEditable(false);
            suja[i].setBounds(bt[i].getX() + 30, bt[i].getY() + 110, 40, 20);

            // "-" ��ư
            minus[i] = new Button("-");
            minus[i].setBounds(bt[i].getX(), suja[i].getY(), 20, 20);
            minus[i].setEnabled(false);

            // "+" ��ư
            plus[i] = new Button("+");
            plus[i].setBounds(bt[i].getX() + (100 - 20), suja[i].getY(), 20, 20);
            plus[i].setEnabled(false);

            // ���� ��ư
            ok[i] = new JButton("����");
            ok[i].setBounds(bt[i].getX(), suja[i].getY() + 30, 100, 20);
            ok[i].setEnabled(false);

            pNorth.add(bt[i]);
            pNorth.add(suja[i]);
            pNorth.add(minus[i]);
            pNorth.add(plus[i]);
            pNorth.add(ok[i]);
        }
        
        // �߾�
        TextArea ta = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
        ta.setText("         �ݾ�      ����        �Ѿ�\n\n");
        ta.setBackground(Color.white);
        ta.setEditable(false);
        ta.setFont(font1);

        // ����
        Panel pSouth = new Panel();
        pSouth.setFont(font);
        pSouth.setBackground(new Color(255, 241, 232));

        // �ϴ� ��ư
        JButton INSERTbt = new JButton("�� ����");
        JButton RESETbt = new JButton("�ʱ�ȭ");
        JButton CLOSEbt = new JButton("�ݱ�");
        pSouth.add(INSERTbt);
        pSouth.add(RESETbt);
        pSouth.add(CLOSEbt);
        
        // ����Ű�� ��ư Ŭ��
        JRootPane rootPane = frame.getRootPane();
        rootPane.setDefaultButton(INSERTbt);	// ���Ź�ư
        
        // �� ���� ��ư
        INSERTbt.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if(sum > 5000) {
                	JOptionPane.showMessageDialog( null, "5,000�� ���Ϸ� �����ϼ���.", "���� �ݾ� ����", JOptionPane.ERROR_MESSAGE);
                	sum = 0; count = 0;
                	frame.dispose();	// ���� â �ݱ�
            		new insertMoney();	// â ���� ����
                }
                else {
                	frame.dispose();	// ���� â �ݱ�
                	new Machine_main();	// ���� ���� â ����
        		}
        	}
        });
        
        // �ʱ�ȭ ��ư
        RESETbt.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();	// ���� â �ݱ�
        		new insertMoney();	// â ���� ����
            }
        });
        
        // �ݱ��ư
        CLOSEbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	for(int i=0; i<money.length; i++) {
          		  money[i] = null;
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
        // õ�� ���� ��ư �̺�Ʈ
        ok[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	// �ܰ� �������ֱ�
            	int temp = money[0].getcountOfMoney();
            	money[0].setcountOfMoney(temp + count);
            	// �ݾ� �� ���ϱ�
            	billTotal += (money[0].getunitOfMoney()*count);
            	
            	try{
            		if(billError(billTotal, ta) == true) {
            			// �ܰ� �������ֱ�
                    	int temp2 = money[0].getcountOfMoney();
            			money[0].setcountOfMoney(temp - count);
            			// �ݾ� �� ����
                    	billTotal -= (money[0].getunitOfMoney()*count);
            			frame.dispose();	// ���� â �ݱ�
            			new insertMoney();	// â ���� ����
            		}
            	}catch(Exception e1) {
            		ta.append("       " + money[0].getMoney() + "       " + count + "         " + money[0].getunitOfMoney() * count
                            + "��" + "\n");
                    ok[0].setEnabled(false);
            	}
            }
        });
        
        // ���� ���� ��ư �̺�Ʈ
        for(int i = 1; i < money.length; i++) {
        	int j = i;
        	ok[i].addActionListener(new ActionListener() {
        		@Override
        		public void actionPerformed(ActionEvent e) {
        			// �ܰ� �������ֱ�
                	int temp = money[j].getcountOfMoney();
                	money[j].setcountOfMoney(temp + count);
                	
                	// �ݾ� �� ���ϱ�
        			coinTotal += (money[j].getunitOfMoney()*count);
        			sum = coinTotal + billTotal;
        			
        			try{
        				if(coinError(sum, ta, j) == true) {
        					// �ܰ� �������ֱ�
                        	int temp2 = money[j].getcountOfMoney();
                			money[j].setcountOfMoney(temp - count);
                			// �ݾ� �� ����
                			coinTotal -= (money[j].getunitOfMoney()*count);
                			sum = coinTotal + billTotal;
                			frame.dispose();	// ���� â �ݱ�
                			new insertMoney();	// â ���� ����
                		}
                	} catch(Exception e1) {
                		ta.append("       " + money[j].getMoney() + "       " + count + "         " + money[j].getunitOfMoney() * count
        						+ "��" + "\n");
                		ok[j].setEnabled(false);
                	}
        		}
        	});
        }
        
        for(int i = 0; i < money.length; i++) {
            int j = i;

            // �޴� ��ư �̺�Ʈ
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
            
            // ����
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
        }
	}
}



