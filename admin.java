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
	// �޴� ����, ���� ����, ��� �߰�, ���� Ȯ��, �ܰ� Ȯ��, ���� ����, ��ȣ ����, ��� Ȯ��, �Ϻ� ����, ���� ����
	public admin() {
        JFrame frame = new JFrame("������ �޴�");
        frame.setBounds(400, 150, 800, 600);
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(false);
 
        // ��Ʈ
        Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
        Font font1 = new Font(Font.MONOSPACED, Font.BOLD, 22);
        
        Panel panel = new Panel();
        panel.setBackground(new Color(255, 241, 232));
        panel.setLayout(null);
        panel.setSize(0, 600);
        panel.setFont(font);
        
        // �ڷΰ���
        JButton back_bt = new JButton("�ڷΰ���");
        back_bt.setBounds(650, 10, 110, 30);
        panel.add(back_bt);
        
        back_bt.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		frame.dispose();	// ���� â �ݱ�
        		new insertMoney();	// �ʱ�ȭ��
        	}
        });
        
        // ����Ű�� ��ư Ŭ��
     	JRootPane rootPane = frame.getRootPane();
     	rootPane.setDefaultButton(back_bt);

        // �迭 ���� �κ�
        String admin_menu[] = { "�޴� ����", "���� ����", "��� �߰�", "���� Ȯ��", "�ܰ� Ȯ��", "���� ����", 
        		"��ȣ ����", "��� Ȯ��", "�Ϻ� ����", "���� ����" };
        JButton bt[] = new JButton[admin_menu.length];
        ImageIcon icon[] = new ImageIcon[admin_menu.length];
        
        // ��ư ���� �κ�
        for (int i = 0; i < admin_menu.length; i++) {

            // �޴� ��ư
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
        
        // ������Ʈ
        frame.add(panel, BorderLayout.NORTH);
        frame.setVisible(true);

        // �޴� ��ư �̺�Ʈ
        // 1. �޴� ����
        bt[0].addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
	            bt[0].setEnabled(true);
	            frame.dispose();	// ���� â �ݱ�
	            new changeMenu();	// �޴� ����
        	}
        });
        
        // 2. ���� ����
        bt[1].addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		bt[1].setEnabled(true);
        		frame.dispose();	// ���� â �ݱ�
        		new changePrice();	// ���� ����
        	}
        });
        
        // 3. ��� �߰�
        bt[2].addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		bt[2].setEnabled(true);
        		frame.dispose();	// ���� â �ݱ�
        		new addStock();	// ��� �߰�
        	}
        });
        
        // 4. ���� Ȯ��
        bt[3].addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		bt[3].setEnabled(true);
        		frame.dispose();	// ���� â �ݱ�
        		new profit();	// ���� Ȯ��
        	}
        });
        
        // 5. �ܰ� Ȯ��
        bt[4].addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		bt[4].setEnabled(true);
        		frame.dispose();	// ���� â �ݱ�
        		new balance();	// �ܰ� Ȯ��
        	}
        });
        
        // 6. ���� ����
        bt[5].addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		bt[5].setEnabled(true);
        		frame.dispose();	// ���� â �ݱ�
        		new cashCollection();	// ���� ����
        	}
        });
        
        // 7. ��ȣ ����
        bt[6].addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		bt[6].setEnabled(true);
        		frame.dispose();	// ���� â �ݱ�
        		new changePW();	// ��ȣ ����
        	}
        });
        
        // 8. ��� Ȯ��
        bt[7].addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		bt[7].setEnabled(true);
        		frame.dispose();	// ���� â �ݱ�
        		try {
					new stock();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	// ��� Ȯ��
        	}
        });
        
        // 9. �Ϻ� ����
        bt[8].addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		bt[8].setEnabled(true);
        		frame.dispose();	// ���� â �ݱ�
        		try {
					new dailySales();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	// �Ϻ� ����
        	}
        });
        
        // 10. ���� ����
        bt[9].addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		bt[9].setEnabled(true);
        		frame.dispose();	// ���� â �ݱ�
        		try {
					new monthlySales();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	// ���� ����
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