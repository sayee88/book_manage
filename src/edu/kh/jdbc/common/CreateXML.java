package edu.kh.jdbc.common;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class CreateXML {
	
	public static void main(String[] args) {
		
		Properties prop = new Properties();
		
		
		
		try {
			
			FileOutputStream fos = new FileOutputStream("manager-sql.xml");
														// 파일 이름
			
			prop.storeToXML(fos, "Manager Service SQL"); // xml파일 생성
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		} 
		
		
		
		
		
		
	}

}
