package vendingMachine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;

public class changeMenu extends JFrame{
	
	public changeMenu() {
		JFrame frame = new JFrame("��ǰ�� ����");
		frame.setBounds(400, 150, 800, 600);
	    
		JPanel panel = new JPanel();
		panel.setLayout(null);
		frame.add(panel, BorderLayout.CENTER);
		panel.setBackground(new Color(255, 241, 232));
		frame.add(panel);
		
		// ��Ʈ
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 16);
		panel.setFont(font);
		
		// �ڷΰ���
        JButton back_bt = new JButton("�ڷΰ���");
        back_bt.setBounds(650, 10, 110, 30);
        panel.add(back_bt);
        
        back_bt.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		frame.dispose();	// ���� â �ݱ�
        		new admin();	// ������ ȭ��
        	}
        });
		
        // label, text field, button
		JLabel lNum = new JLabel("������ �޴��� ��ȣ�� �Է��ϼ��� :");
		JTextField chNum = new JTextField();	// ��ȣ
		
		JLabel notice = new JLabel("< ��:0, Ŀ��:1, �̿�����:2, ���Ŀ��:3, ź������:4 >");
		JLabel lName = new JLabel("���� �̸� :");
		JTextField chName = new JTextField();	// �̸�
		JLabel lPrice = new JLabel("���� ���� :");
		JTextField chPrice = new JTextField();	// ����
		JLabel lStock = new JLabel("���� ���� :");
		JTextField chStock = new JTextField();	// ����
		
		JButton bt = new JButton("����");		// �����ư
		
           
		// ����Ű�� ��ư Ŭ��
		JRootPane rootPane = frame.getRootPane();
	    rootPane.setDefaultButton(bt);
	    
	    // ���� label
	 	notice.setFont(font);
	 	notice.setBounds(170, 150, 600, 20);
	 	panel.add(notice);
	    
	    // �޴� ��ȣ label
	    lNum.setFont(font);
	    lNum.setBounds(170, 200, 300, 20);
	    panel.add(lNum);
	    
	    // �޴� ��ȣ text field
	    chNum.setFont(font);
	    chNum.setBounds(450, 200, 70, 20);
	    panel.add(chNum);

	    // ���� �̸� label
	    lName.setFont(font);
	    lName.setBounds(170, 250, 300, 20);
	    panel.add(lName);
	    
	    // ���� �̸� text field
	    chName.setFont(font);
	    chName.setBounds(300, 250, 200, 20);
	    panel.add(chName);
	    
	    // ���� ���� label
	    lPrice.setFont(font);
	    lPrice.setBounds(170, 300, 300, 20);
	    panel.add(lPrice);
	    
	    // ���� ���� text field
	    chPrice.setFont(font);
	    chPrice.setBounds(300, 300, 200, 20);
	    panel.add(chPrice);
	    
	    // ���� ���� label
	    lStock.setFont(font);
	    lStock.setBounds(170, 350, 300, 20);
	    panel.add(lStock);
	    
	    // ���� ���� text field
	    chStock.setFont(font);
	    chStock.setBounds(300, 350, 200, 20);
	    panel.add(chStock);
	    
	    // ���� ��ư
	    bt.setFont(font);
	    bt.setBounds(540, 210, 70, 150);
	    panel.add(bt);
	    
	    // ���� ��ư �̺�Ʈ
	    bt.addActionListener( new ActionListener() {
	        
	    	public void actionPerformed(ActionEvent e) {
	    		int i = Integer.valueOf(chNum.getText());	// �޴� �ε���
	    		String name = chName.getText();
	    		int price = Integer.valueOf(chPrice.getText());
	    		int stock = Integer.valueOf(chStock.getText());
	    		
	    		if(i > Machine_main.returnLength()){      // �����Ϸ��� �ε����� �����ϴ� �ε������� ũ��
	    			JOptionPane.showMessageDialog( null, "�޴��� ��ȣ�� Ȯ�����ּ���.", "�޴� ��ȣ ����", JOptionPane.ERROR_MESSAGE);
	    			frame.dispose();	// ���� â �ݱ�
		        	new changeMenu();	// â �ٽ� ����
	    		}
	    		else {       
	    			JOptionPane.showMessageDialog( null, "�޴��� ���� �Ǿ����ϴ�." );
	    			// �޴� �̸� ����
	    			Machine_main.setMenu(i, name, price, stock); 
	    			frame.dispose();	// ���� â �ݱ�
		        	new insertMoney();	// ������ â ����
	    		}
	    		
	    	}
	
	    } );
	    
	    frame.setVisible(true);
	    frame.setLocationRelativeTo(null);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
