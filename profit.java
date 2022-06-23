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
import javax.swing.JRootPane;

public class profit extends JFrame {

    static int buyTotal = Machine_main.returnResult();	// ���� �Ѿ�
    
    // ���� ��ȯ �Լ�
    public static int getProfit() {
    	return buyTotal;
    }
    
	public profit() {
		JFrame frame = new JFrame("���� Ȯ��");
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
        ta.setBackground(Color.white);
        ta.setEditable(false);
        ta.setFont(font);
        
        frame.add(pNorth, BorderLayout.NORTH);
        frame.add(ta, BorderLayout.CENTER);
        
        
        ta.append("\n\n\n\t\t\t\t< �������� ���Ǳ� >\n");
        ta.append("\t=========================================\n\n");
        ta.append("\t\t\t\t���� ���� >> " + buyTotal + "��\n\n");
        ta.append("\t=========================================\n");
        ta.append("\t\t\t\t\t\t\t(EnterŰ�� ��������...)");
        
        
	    frame.setVisible(true);
	    frame.setLocationRelativeTo(null);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}