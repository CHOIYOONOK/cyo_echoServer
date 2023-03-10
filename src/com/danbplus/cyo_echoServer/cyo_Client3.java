package com.danbplus.cyo_echoServer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

public class cyo_Client3 {
	 	private Socket             soc;    // 서버에 연결할 소켓
	    private BufferedReader     br;        // 서버가 보낸 메세지를 읽을 리더
	    private PrintWriter     pw;        // 서버로 메세지를 보낼 라이터
	    private Scanner            scan;    // 사용자가 입력한 데이터를 읽을 스캐너
	    static String ip;
	    static String strPort;
	    int port = Integer.parseInt(strPort);
	    
	    public cyo_Client3() throws Exception{
	        init();
	    }
	    
	    public void init() throws Exception{
	        
	        try{
	        	 // TODO Auto-generated method stub
	        	
	        	soc = new Socket(ip, port);    // 자신의 아이피로 포트를 통해 서버에 접속시도
	            System.out.println(getTime() + "서버접속");
	            
	            // 연결된 서버로부터 데이터를 받아올 준비를 한다
	            br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
	            // 연결된 서버로 데이터를 보낼 준비를 한다
	            pw = new PrintWriter(soc.getOutputStream());
	            
	            scan = new Scanner(System.in);
	            
	            System.out.println("채팅가능");
	            String inputData = "";
	            
	            while(!inputData.equals("exit")){
	                System.out.printf(getTime()+"to Server > ");
	                // 사용자가 입력한 내용을 읽어와 서버로 보냅니다
	                
	                inputData = scan.nextLine();
	                pw.println(inputData);
	                pw.flush(); // 프린트라이터 메모리를 초기화시켜 내부에 있던 데이터를 서버로 전송
	                System.out.println(getTime() + "from Server > "+br.readLine());
	            }
	            
	            soc.close(); // 소켓을 닫는다.
	            
	        }catch(ConnectException ce){
	            System.out.println("서버가 동작하고 있지않아 프로그램을 종료");
	            System.exit(0);
	        
	        }catch(Exception e){
	            e.printStackTrace();
	        }finally {
	        	if(soc != null) soc.close();
	        	if(br != null) br.close();
	        	if(pw != null) pw.close();
			}
	        
	    }
	    
	    public String getTime(){
	        SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]");
	        return f.format(new Date());
	    }
	 
	    public static void main(String[] args) throws Exception {
	    	FileReader resources= new FileReader("config/test.properties");
			Properties properties = new Properties();
	        
	        try {
	            properties.load(resources);
	            System.out.println("클라이언트 ip == "+properties.getProperty("ip"));
	            System.out.println("클라이언트 port == "+properties.getProperty("port"));
	            try {
					ip = properties.getProperty("ip");
					strPort = properties.getProperty("port");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    	
	        new cyo_Client3();
	    }
}
