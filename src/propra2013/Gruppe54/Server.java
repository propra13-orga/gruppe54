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
	
	/**
	 * Konstruktor
	 * @throws IOException
	 */
	public Server() throws IOException{
		try{
		server = new ServerSocket(4444);
		System.out.println(server.getInetAddress());
		} catch(Exception e){
			
		}
	}
	
	/**
	 * Thread
	 * Server wartet auf Verbindungen und leitet bei eingehendem Client weiter an den ServerThread
	 */
	public void run(){
		while(true){
			try{
				client = server.accept(); //auf Verbindung warten
				if(client != null){
					clientList.add(client);
					System.out.println("Spieler beigetreten");
				}
				ServerThread handleClient = new ServerThread(client,this);
				handleClient.start();
			} catch(IOException e){
				e.printStackTrace();
			}
		}
	}
}