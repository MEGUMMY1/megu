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
		JFrame frame = new JFrame("암호 변경");
		frame.setBounds(400, 150, 800, 600);
	    
		JPanel panel = new JPanel();
		panel.setLayout(null);
		frame.add(panel, BorderLayout.CENTER);
		panel.setBackground(new Color(255, 241, 232));
		frame.add(panel);
		
		// 폰트
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 16);
		panel.setFont(font);
		
		// 뒤로가기
        JButton back_bt = new JButton("뒤로가기");
        back_bt.setBounds(650, 10, 110, 30);
        panel.add(back_bt);
        
        back_bt.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		frame.dispose();	// 현재 창 닫기
        		new admin();	// 관리자 화면
        	}
        });
		
		JLabel label = new JLabel("※암호 조건 : 특수문자 및 숫자가 포함된 8자리 이상※");
		JLabel newPW = new JLabel("신규 암호 입력 : ");
		JPasswordField txtPass = new JPasswordField(10);
		JButton change = new JButton("변경");
		// 엔터키로 버튼 클릭
		JRootPane rootPane = frame.getRootPane();
	    rootPane.setDefaultButton(change);
	    
	    // 암호 조건 문장 출력
	    label.setFont(font);
	    label.setBounds(190, 215, 500, 20);
	    panel.add(label);
	    
	    // 신규 암호 입력 :
	    newPW.setFont(font);
	    newPW.setBounds(190, 240, 130, 20);
	    panel.add(newPW);
	    
	    // 암호 입력 창
	    txtPass.setFont(font);
	    txtPass.setBounds(330, 240, 180, 20);
	    panel.add(txtPass);
	    
	    // 변경 버튼
	    change.setFont(font);
		change.setBounds(520, 240, 100, 20);
	    panel.add(change);
	    
	    
	    change.addActionListener( new ActionListener() {
	        
	    	public void actionPerformed(ActionEvent e) {
	    		String pw = txtPass.getText();
	    		// 주어진 정규표현식으로부터 패턴 생성(특수문자와 숫자로 이루어졌는지)
	    		Pattern passPattern1 = Pattern.compile("^(?=.*\\d)(?=.*\\W).{8,20}$");     
	    		// 패턴에 매칭할 문자열을 입력해 Matcher생성
	    		Matcher passMatcher1 = passPattern1.matcher(pw);  
	    				
	    		if(!passMatcher1.find()){      // 패턴이 일치하는 다음 문자열이 존재하지않으면
	    			JOptionPane.showMessageDialog( null, "암호 조건을 확인해주세요.", "암호 조건 오류", JOptionPane.ERROR_MESSAGE);
	    			frame.dispose();	// 현재 창 닫기
		        	new changePW();	// 창 다시 띄우기
	    		}
	    		else {       // 패턴이 일치한다면
	    			JOptionPane.showMessageDialog( null, "암호가 변경 되었습니다." );
	    			// 관리자 비밀번호 변경
	    			login.setPass(pw);   
	    			frame.dispose();	// 현재 창 닫기
		        	new insertMoney();	// 관리자 창 띄우기
	    		}
	    	}
	
	    } );
	    
	    frame.setVisible(true);
	    frame.setLocationRelativeTo(null);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
