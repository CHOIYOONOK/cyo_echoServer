package com.danbplus.properties;

import java.util.Properties;

public class properties {

	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.setProperty("ip", "localhost");
		prop.setProperty("port", "8888");
		
		System.out.println("ip : " + prop.getProperty("ip"));
		System.out.println("port : " + prop.getProperty("port"));
		
	}
}