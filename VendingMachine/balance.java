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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class balance extends JFrame {
	public balance() {
		JFrame frame = new JFrame("�ܰ� Ȯ��");
		frame.setBounds(400, 150, 800, 600);
	    
	    // ��Ʈ
	 	Font font = new Font(Font.SANS_SERIF, Font.BOLD, 23);
		
		// ����
        Panel pNorth = new Panel();
        pNorth.setBackground(new Color(255, 241, 232));
        pNorth.setLayout(null);
        pNorth.setSize(0, 100);
        pNorth.setFont(font);
		
		// �ڷΰ���
        JButton back_bt = new JButton("�ڷΰ���");
        back_bt.setBounds(650, 10, 110, 30);
        pNorth.add(back_bt);
        
        back_bt.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		frame.dispose();	// ���� â �ݱ�
        		new admin();	// ������ ȭ��
        	}
        });
        
        // ����Ű�� ��ư Ŭ��
     	JRootPane rootPane = frame.getRootPane();
     	rootPane.setDefaultButton(back_bt);
        
        // �߾�
        TextArea ta = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
        ta.setText("\n\t\t ����\t\t\t����\t\t�ݾ�\n");
        ta.setBackground(Color.white);
        ta.setEditable(false);
        ta.setFont(font);
        ta.append("\t===========================================\n\n");
        
        frame.add(pNorth, BorderLayout.NORTH);
        frame.add(ta, BorderLayout.CENTER);
        
        int total = 0;
        
        for(int i = 0; i < 5; i++) {
        	int result = insertMoney.returnUnitOfMoney(i) * insertMoney.returnCountOfMoney(i);	// ������ �Ѿ�
        	total += result;	// �ܰ� �Ѿ�
        	ta.append("\t\t" + insertMoney.returnMoney(i) + "\t\t\t" + insertMoney.returnCountOfMoney(i) + "��\t\t\t" 
        				 + result + "��\n");
        }
        ta.append("\n\t===========================================\n\n");
        ta.append("\t\t\t\t\t���� �ܰ� " + total + "��\n");
        
	    frame.setVisible(true);
	    frame.setLocationRelativeTo(null);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
