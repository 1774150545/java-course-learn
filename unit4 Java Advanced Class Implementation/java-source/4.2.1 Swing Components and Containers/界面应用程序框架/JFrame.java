
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * @author author name
 * @version 1.0.0
 */

public class JFrameName extends JFrame {
	
	//����һ����������ָ���������Ƕ��
	private Container contentPane;
	
	//Ҫ���ֵ����
	private JPanel buttonPanel;
	private JTextField input1TextField;	
	...
	
	public static void main(String[] args) {
		
		//����һ�����岢��ʾ
		JFrame frame = new JFrameName();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 400); // Adjust the size of the window
		frame.setVisible(true);		
	}
	//Constructor.
	public JFrameName(){
		//���ô���ı���
		super("title");
			
		this.setSize(250,100);
		
		//�ڵ�ǰ�����ϲ��ָ����������ʾ	
		this.setLayout(...);
		this.add(...);
	}		
}
