package vendingMachine;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

public class login extends JFrame{
	private static String pass = "12345678@";	// �ʱ� ��й�ȣ
	
	// ��ȣ setting �Լ�
	public static void setPass(String newPW) {
		pass = newPW;
	}
	
	public login() {
		JFrame frame = new JFrame("������ �α���");
		frame.setBounds(400, 150, 800, 600);
        
		JPanel panel = new JPanel();
		panel.setLayout(null);
		frame.add(panel, BorderLayout.CENTER);
		panel.setBackground(new Color(255, 241, 232));
		
		frame.add(panel);
		
		// ��Ʈ
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 16);
		panel.setFont(font);
		
		JLabel pw = new JLabel("PASSWORD : ");
		JPasswordField txtPass = new JPasswordField(10);
		JButton logBtn = new JButton("LOG-IN");
		// ����Ű�� ��ư Ŭ��
		JRootPane rootPane = frame.getRootPane();
        rootPane.setDefaultButton(logBtn);
        
        pw.setFont(font);
        txtPass.setFont(font);
        logBtn.setFont(font);
		pw.setBounds(210, 240, 120, 20);
		txtPass.setBounds(320, 240, 170, 20);
		logBtn.setBounds(500, 240, 100, 20);
        panel.add(pw);
        panel.add(txtPass);
        panel.add(logBtn);
        
        
        logBtn.addActionListener( new ActionListener() {
            
        	public void actionPerformed(ActionEvent e) {
	            
	            if(pass.equals(txtPass.getText())) {
	            	JOptionPane.showMessageDialog( null, "�α��� �Ǿ����ϴ�." );
	            	frame.dispose();	// ���� â �ݱ�
	        		new admin();	// ������ â ����
	            } 
	            else {
	            	JOptionPane.showMessageDialog( null , "�α��� �����Ͽ����ϴ�.");
	            	frame.dispose();	// ���� â �ݱ�
	        		new login();	// �α��� â ����
	            }
        	}

        } );
        
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
}
