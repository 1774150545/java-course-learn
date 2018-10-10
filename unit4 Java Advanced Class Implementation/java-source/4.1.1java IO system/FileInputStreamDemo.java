//FileInputStreamDemo.java
import java.io.*;
public class FileInputStreamDemo {
	
	
	private static final String NEW_LINE = 
				System.getProperty("line.separator");
	
	public static void main(String[] args) throws IOException {
		//Ê¥µ®¿ìÀÖ£¡
		
		FileInputStream readFile = new FileInputStream("FileInputStreamDemo.java");
		//FileReader readFile = new FileReader("FileInputStreamDemo.java");
		int intTemp = readFile.read();
		while(intTemp!=-1){
			System.out.print((char)intTemp);
			intTemp = readFile.read();
		 }		
		readFile.close();
		/*
		FileInputStream readFile = new FileInputStream("FileInputStreamDemo.java");
		byte[] buffer = new byte[2048];
		int byteLength = readFile.read(buffer,0,2048);
		String str = new String(buffer,0,byteLength);
		System.out.println(str);
		readFile.close();
		*/		
	}
}






















