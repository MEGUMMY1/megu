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
import javax.swing.JPasswordField;
import javax.swing.JRootPane;

public class changePW extends JFrame{
	
	public changePW() {
		JFrame frame = new JFrame("��ȣ ����");
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
		
		JLabel label = new JLabel("�ؾ�ȣ ���� : Ư������ �� ���ڰ� ���Ե� 8�ڸ� �̻��");
		JLabel newPW = new JLabel("�ű� ��ȣ �Է� : ");
		JPasswordField txtPass = new JPasswordField(10);
		JButton change = new JButton("����");
		// ����Ű�� ��ư Ŭ��
		JRootPane rootPane = frame.getRootPane();
	    rootPane.setDefaultButton(change);
	    
	    // ��ȣ ���� ���� ���
	    label.setFont(font);
	    label.setBounds(190, 215, 500, 20);
	    panel.add(label);
	    
	    // �ű� ��ȣ �Է� :
	    newPW.setFont(font);
	    newPW.setBounds(190, 240, 130, 20);
	    panel.add(newPW);
	    
	    // ��ȣ �Է� â
	    txtPass.setFont(font);
	    txtPass.setBounds(330, 240, 180, 20);
	    panel.add(txtPass);
	    
	    // ���� ��ư
	    change.setFont(font);
		change.setBounds(520, 240, 100, 20);
	    panel.add(change);
	    
	    
	    change.addActionListener( new ActionListener() {
	        
	    	public void actionPerformed(ActionEvent e) {
	    		String pw = txtPass.getText();
	    		// �־��� ����ǥ�������κ��� ���� ����(Ư�����ڿ� ���ڷ� �̷��������)
	    		Pattern passPattern1 = Pattern.compile("^(?=.*\\d)(?=.*\\W).{8,20}$");     
	    		// ���Ͽ� ��Ī�� ���ڿ��� �Է��� Matcher����
	    		Matcher passMatcher1 = passPattern1.matcher(pw);  
	    				
	    		if(!passMatcher1.find()){      // ������ ��ġ�ϴ� ���� ���ڿ��� ��������������
	    			JOptionPane.showMessageDialog( null, "��ȣ ������ Ȯ�����ּ���.", "��ȣ ���� ����", JOptionPane.ERROR_MESSAGE);
	    			frame.dispose();	// ���� â �ݱ�
		        	new changePW();	// â �ٽ� ����
	    		}
	    		else {       // ������ ��ġ�Ѵٸ�
	    			JOptionPane.showMessageDialog( null, "��ȣ�� ���� �Ǿ����ϴ�." );
	    			// ������ ��й�ȣ ����
	    			login.setPass(pw);   
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
