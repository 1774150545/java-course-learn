//display a JFrame

//First ,we'll import the Swing package,so that the Java 
//compiler will know what a JFrame is: 
//import java.awt.*;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Toolkit;

public class FrameTest {
	
	public static void main(String[] args) {
		
		//instantiate a JFrame object.		
		JFrame theFrame = new JFrame("Hello!!!");
		
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//set the size of the frame
		//width,height in pixels 
		theFrame.setSize(800,600);
		//Technique for centering a frame on the screen.
		/*
		Dimension frameSize = theFrame.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int centerX = screenSize.width/2;
		int centerY = screenSize.height/2;
		int halfWidth = frameSize.width/2;
		int halfHeight = frameSize.height/2;
		
		theFrame.setLocation(centerX-halfWidth,centerY-halfHeight);
		*/
		/*
		// Adjust the size of the window
		theFrame.pack();
		*/		
		//make the frame appear by invoking
		// its setVisible() method
		theFrame.setVisible(true);
		
		
	}
}