package propra2013.Gruppe54;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;


public class Server extends Thread{
	ServerSocket server = null;
	Socket client = null;
	PrintWriter out = null,out1 = null;
	BufferedReader in = null;
	List<Socket> clientList = new ArrayList<Socket>();

	
	public Server() throws IOException{
		try{
		server = new ServerSocket(4444);
		} catch(Exception e){
			
		}
	}
	
	public void run(){
		while(true){
			try{
				client = server.accept(); //auf Verbindung warten
				if(client != null){
					clientList.add(client);
					System.out.println("Spieler beigetreten");
				}
				System.out.println(clientList);
				ServerThread handleClient = new ServerThread(client,this);
				handleClient.start();
			} catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	public void sendAll(BufferedReader in){
		
	}
}