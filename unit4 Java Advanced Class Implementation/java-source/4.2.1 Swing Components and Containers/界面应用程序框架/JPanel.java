import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * @author author name
 * @version 1.0.0
 */
public class JPanelName extends JPanel {

	//Ҫ���ֵ����
	private JLabel labelLeft;
	private JLabel labelCenter;
	private JLabel labelRight;
        ...
	/**
	 * Creates a window.
	 *
	 * @param args  not used.
	 */
	public static void main(String[] args) {
		
		//���ô���ı���
		JFrame frame = new JFrame("title");
		
		//frame.setContentPane(new JPanelName());
		frame.add(new JPanelName());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();    // Adjust the size of the window
		frame.setVisible(true);
	}

	/**
	 * Creates three {@link JLabel} components .
	 */
	public JPanelName() {
		//�ڵ�ǰ����ϲ��ָ����������ʾ�ڴ�����
		setLayout(...);
		setBackground(...);
		add(...);	
	}
}
/*!End Snippet:file*/