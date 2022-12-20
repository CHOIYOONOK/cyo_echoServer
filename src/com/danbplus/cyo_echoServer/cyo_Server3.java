package com.danbplus.cyo_echoServer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketImpl;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class cyo_Server3 {
	    private ServerSocket      server_Soc;    //서버소켓
	    private BufferedReader  br;            // 클라이언트로부터 전달받은 메세지를 읽어들일 버퍼메모리를 가진 리더
	    private PrintWriter     pw;            // 클라이언트로 메세지를 보낼 프린트 라이터
	    private Socket          soc;           // 클라이언트 소켓
	    
	    
	    
	    public cyo_Server3() throws IOException{
			server_Soc = new ServerSocket(8888); //소켓을 생성하여 지정된 포트 번호에 연결 이미 사용중인 포트번호를 지정하면 오류발생
            System.out.println(getTime()+"서버준비");
            System.out.println(getTime()+"클라이언트 대기");
	        init();
	    }
	    
	    public void init() throws IOException{
	    	
	    	try{
	            soc = server_Soc.accept(); // 연결 요청을 기다리다 요청이 들어오면 수락하고 새 소켓 객체를 반환
	            System.out.println("Client has accepted");
	            
	            // (네트워크 입출력 스트림)
	            br = new BufferedReader(new InputStreamReader(soc.getInputStream())); //클라이언트로부터 데이터를 읽어올 준비를 합니다
	            pw = new PrintWriter(soc.getOutputStream());    // 클라이언트로 데이터를 보낼 준비를 합니다
	            
	            String readData = "";
	            
	            while(!(readData = br.readLine()).equals(null)){ //상대쪽에서 접속을 끊을때까지 기다립니다. 서버로부터 한행의 문자열 수신
	                 System.out.println(getTime()+"from Client > "+readData); //클라리언트가 보낸 메세지를 읽습니다.
	                 pw.println(readData); // 읽은 메세지 그대로 클라이언트 한테 보냅니다.
	                 pw.flush(); // 프린트라이터 메모리를 초기화 시킵니다. 이 메소드가 행해져야 실질적으로 데이터가 전송됨(일정양이 차면 스트림으로 보냄 버퍼를 사용할때 사용)
	            }
	            
	            
	            soc.close(); // 소켓과 소켓서버를 닫는다.
	        }catch(SocketException | NullPointerException se){
	            System.out.println("클라이언트가 연결을 종료.");
	            System.exit(0);
	        
	        }catch(Exception e){
	            e.printStackTrace();
	        }finally {
	        	if(soc != null) soc.close();
	        	if(br != null) br.close();
	        	if(pw != null) pw.close();
			}
	    }
	    
	    static String getTime(){
	        SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]");
	        return f.format(new Date());
	    }
	    
	    public static void main(String[] args) throws IOException {
			/*
			 * File path = new File(
			 * "C:\\\\WorkSpace__\\\\cyo_echoServer\\\\src\\\\com\\\\danbplus\\\\properties\\\\properties.java"
			 * ); try(FileReader file = new FileReader(path)) {
			 * 
			 * Properties prop = new Properties(); prop.load(file); // 파일 열어줌
			 * System.out.println(prop.getProperty("ip"));
			 * System.out.println(prop.getProperty("port"));
			 * 
			 * } catch(Exception e) {
			 * 
			 * System.out.println("실패"); }
			 */
	 		new cyo_Server3();
	 
	    }
	 
}
