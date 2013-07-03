package propra2013.Gruppe54;

import java.io.*;
import java.net.*;

public class ServerThread extends Thread {
	private Socket s,s2;
	private Server server;
	private PrintWriter out = null,out2 = null;
	private BufferedReader in = null;
	
	public ServerThread(Socket s,Server server) {
		this.s = s;
		this.server = server;
	}
 
	public void run() {
		try {
			// lesen
			if(!s.isClosed()){
				in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				out = new PrintWriter(s.getOutputStream(),true);
			}
			while(in != null){
				if(in != null){
				String incoming = in.readLine();
				if(incoming.equals("serverdown".toString())){		//wenn der Server beendet wird
					out.println(s.getLocalPort()+";serverdown;");	//beiden Clients mitteilen
					out2.println(s.getLocalPort()+";serverdown;");
					s.close();										//alles schließen
					out.close();
					out2.close();
					this.interrupt();
					break;
				}
				out.println(incoming.toString()); //zurück schicken
				if(Spielfeld.server.clientList.size() == 2){	//richtigen client aus der Liste aussuchen
					if(server.clientList.get(0) == s){
						s2 = server.clientList.get(1);
					} else if(server.clientList.get(1) == s) {
						s2 = server.clientList.get(0);
					}
					out2 = new PrintWriter(s2.getOutputStream(),true);
					out2.println(incoming.toString());
				}
			  }
			}
			out.close();
			out2.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}