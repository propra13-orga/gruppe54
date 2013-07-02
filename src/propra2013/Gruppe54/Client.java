package propra2013.Gruppe54;

import java.io.*;
import java.net.*;
import java.rmi.Naming;
import java.util.regex.Pattern;

public class Client extends Thread{

	Socket socket = null;
	PrintWriter out = null;
	BufferedReader in = null;
	
	public Client() throws UnknownHostException{
		try{
			if(Spielfeld.host){
				socket = new Socket("localhost", 4444);
			} else if((Spielfeld.multiplayer)&(!Spielfeld.host)){
				socket = new Socket(InetAddress.getByName(Spielfeld.ip), 4444);
			}
			out = new PrintWriter(socket.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void send(String text){
		out.println(text);
	}
	
	public void run(){
		while(true){
			String incoming;
			try{
				incoming = in.readLine();				//blockiert bis Nachricht empfangen
				Pattern p = Pattern.compile("[;]");		//nachricht zerlegen
				if(incoming != null){
				
				String[] input = p.split(incoming.toString());
				if(!input[0].equals(Integer.toString(socket.getLocalPort()))){		//wenn die Nachricht nicht von einem selber kam	
					if(input[1].equals("spieler".toString())){			//Position von Spieler2
						Spielfeld.spieler2.x = Double.parseDouble(input[2]);
						Spielfeld.spieler2.y = Double.parseDouble(input[3]);
					} else if(input[1].equals("blick".toString())){		//Blickrichtung des Spielers
						if(input[2].equals("rechts".toString())){
							Spielfeld.spieler2.rechts = true;
							Spielfeld.spieler2.links = false;
							Spielfeld.spieler2.hoch = false;
							Spielfeld.spieler2.runter = false;
						} else if(input[2].equals("links".toString())){
							Spielfeld.spieler2.rechts = false;
							Spielfeld.spieler2.links = true;
							Spielfeld.spieler2.hoch = false;
							Spielfeld.spieler2.runter = false;
						} else if(input[2].equals("hoch".toString())){
							Spielfeld.spieler2.rechts = false;
							Spielfeld.spieler2.links = false;
							Spielfeld.spieler2.hoch = true;
							Spielfeld.spieler2.runter = false;
						} else if(input[2].equals("runter".toString())){
							Spielfeld.spieler2.rechts = false;
							Spielfeld.spieler2.links = false;
							Spielfeld.spieler2.hoch = false;
							Spielfeld.spieler2.runter = true;
						}
					} else if(input[1].equals("gegner".toString())){		//Gegner
						if(input[2].equals("1".toString())){//GegnerRL
							Spielfeld.gegnerRL.leben -= Double.parseDouble(input[3]);
						} else if(input[2].equals("2".toString())){//GegnerOU
							Spielfeld.gegnerOU.leben -= Double.parseDouble(input[3]);
						} else if(input[2].equals("3".toString())){//GegnerKI
							Spielfeld.gegnerKI.leben -= Double.parseDouble(input[3]);
						} else if(input[2].equals("4".toString())){//Endgegner
							Endgegner.leben -= Integer.parseInt(input[3]);
						}
					} else if(input[1].equals("schuss1".toString())){		//Schuss1
			        	Spielfeld.schuss_spieler2.sichtbar=true;
			        	Spielfeld.schuss_spieler2.setPos=false;
			        	Spielfeld.schuss_spieler2.setPos();
						Spielfeld.schuss_spieler2.checkRichtung();
						Spielfeld.schuss_spieler2.Schuss();
						Spielfeld.counter_schuss = 0;
					} else if(input[1].equals("schuss2".toString())){		//Schuss2
						Spielfeld.schuss2_spieler2.sichtbar=true;
			        	Spielfeld.schuss2_spieler2.setPos=false;
			        	Spielfeld.schuss2_spieler2.setPos();
						Spielfeld.schuss2_spieler2.checkRichtung();
						Spielfeld.counter_schuss2 = 0;
					} else if(input[1].equals("besiegt".toString())){		//Spieler wurde besiegt
						Spielfeld.spieler2.aktiv = false;
					} else if(input[1].equals("erwacht".toString())){		//Spieler wurde wieder zum Leben erweckt
						Spielfeld.spieler2.aktiv = true;
					}
				}
				//TODO: Raumwechsel, Levelwechsel, Waffen, Pfeile, Elementen muss Spieler übergeben werden
				
				}
			} catch(IOException e){
				e.printStackTrace();
			}
		}
	}
}










