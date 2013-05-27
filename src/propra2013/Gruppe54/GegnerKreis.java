package propra2013.Gruppe54;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class GegnerKreis extends Rectangle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */
	public static boolean unten=false; // gibt an, ob der Gegener unten an eine Wand stößt
	public static boolean rechts=false; // gibt an, ob der Gegener rechts an eine Wand stößt
	public static boolean links=false; // gibt an, ob der Gegener links an eine Wand stößt
	public static boolean oben=false; // gibt an, ob der Gegener oben an eine Wand stößt
	
	public static boolean aktiv=false; 
	
	//Sagt in welche Richtung der Gegner sich bewegt
	public static boolean gehtRunter=false;
	public static boolean gehtHoch=true;
	public static boolean gehtLinks=false;
	public static boolean gehtRechts=false;
	
	public static boolean u = false;
	public static boolean r = false;
	public static int StartX;
	public static int StartY;
	public static int x,y;
	
	public GegnerKreis() {
		setBounds(StartX,StartY,32,32);
		aktiv = true;		//gibt an ob der Gegner im Spiel ist
	}
	
	public void draw(Graphics g){
		g.drawImage(new ImageIcon("pics/gegnerKreis.png").getImage(), StartX, StartY, 32, 32, null); //zeichnet den Gegner an (x,y)
	}
	
	//Gibt die Position des Gegners in Abhängigkeit der Blöcke um ihn herum an
	public static void position(){
		if ((Spielfeld.getBlockID(GegnerKreis.StartX, GegnerKreis.StartY+32+2)!=1)&&
				(Spielfeld.getBlockID(GegnerKreis.StartX+32, GegnerKreis.StartY+32+2)!=1)&&
				(Spielfeld.getBlockID(GegnerKreis.StartX, GegnerKreis.StartY+32+2)!=2)&&
				(Spielfeld.getBlockID(GegnerKreis.StartX+32, GegnerKreis.StartY+32+2)!=2)){
			unten = false;
		} else unten = true;
		
		if ((Spielfeld.getBlockID(GegnerKreis.StartX, GegnerKreis.StartY-2)!=1)&&
				(Spielfeld.getBlockID(GegnerKreis.StartX+32, GegnerKreis.StartY-2)!=1)&&
				(Spielfeld.getBlockID(GegnerKreis.StartX, GegnerKreis.StartY-2)!=2)&&
				(Spielfeld.getBlockID(GegnerKreis.StartX+32, GegnerKreis.StartY-2)!=2)){
			oben = false;
		} else oben = true;
		
		if ((Spielfeld.getBlockID(GegnerKreis.StartX+32+2, GegnerKreis.StartY)!=1)&&
				(Spielfeld.getBlockID(GegnerKreis.StartX+32+2, GegnerKreis.StartY+32)!=1)&&
				(Spielfeld.getBlockID(GegnerKreis.StartX+32+2, GegnerKreis.StartY)!=2)&&
				(Spielfeld.getBlockID(GegnerKreis.StartX+32+2, GegnerKreis.StartY+32)!=2)){
			rechts = false;
		} else rechts = true;
		
		if ((Spielfeld.getBlockID(GegnerKreis.StartX-2, GegnerKreis.StartY)!=1)&&
				(Spielfeld.getBlockID(GegnerKreis.StartX-2, GegnerKreis.StartY+32)!=1)&&
				(Spielfeld.getBlockID(GegnerKreis.StartX-2, GegnerKreis.StartY)!=2)&&
				(Spielfeld.getBlockID(GegnerKreis.StartX-2, GegnerKreis.StartY+32)!=2)){
			links = false;
		} else links = true;
	}
	
	public static void lauf(){
		//Gegner läuft immer außen entlang
		/*		
		 * 		<-------^
		 * 		|		|
		 * 		|		|
		 * 		v------>
		 * 
		 */

		GegnerKreis.position();// so weiß ich wie die Umgebung des Gegner aussieht
		
		System.out.println(unten+""+rechts+""+links+""+oben);
		//Problem wenn er im Raum steht
		
		//Geht nach Unten
		if ((gehtRunter=true)&&(links==false)){
			gehtRunter=false;
			gehtLinks=true;
		} else if ((gehtRunter==true)&&(links==true)&&(unten==false)){
			GegnerKreis.StartY+=1;
		} else if ((gehtRunter==true)&&(links==true)&&(unten==true)&&(rechts==false)){
			gehtLinks=false;
			gehtRechts=true;
		} else if ((gehtRunter==true)&&(links==true)&&(unten==true)&&(rechts==true)){
			gehtRunter=false;
			gehtHoch=true;
		}
		
		//Geht nach Links
		if ((gehtLinks=true)&&(oben==false)){
			gehtHoch=true;
			gehtLinks=false;
		} else if ((gehtLinks==true)&&(oben==true)&&(links==false)){
			GegnerKreis.StartX-=1;
		} else if ((gehtLinks==true)&&(oben==true)&&(links==true)&&(unten==false)){
			gehtLinks=false;
			gehtRunter=true;
		} else if((gehtLinks==true)&&(oben==true)&&(links==true)&&(unten==true)){
			gehtLinks=false;
			gehtRechts=true;
		}
		
		//Geht nach Oben
		if ((gehtHoch==true)&&(rechts==false)){
			gehtHoch=false;
			gehtRechts=true;
		} else if ((gehtHoch ==true)&&(rechts==true)&& (oben==false)){
			GegnerKreis.StartY-=1;
		} else if((gehtHoch=true)&&(rechts==true)&&(oben==true)&&(links==false)){
			gehtLinks=true;
			gehtHoch=false;
		} else if ((gehtHoch=true)&&(rechts==true)&&(oben==true)&&(links==true)){
			gehtRunter=true;
			gehtHoch=false;
		} 
		
		//Geht nach Rechts
		if ((gehtRechts==true)&&(unten==false)){
			gehtRechts=false;
			gehtRunter=true;
		} else if ((gehtRechts==true)&&(unten==true)&&(rechts==false)){
			GegnerKreis.StartX+=1;
		} else if((gehtRechts==true)&&(unten==true)&&(rechts==true)&&(oben==false)){
			gehtHoch=true;
			gehtRechts=false;
		} else if ((gehtRechts==true)&&(unten==true)&&(rechts==true)&&(oben==true)){
			gehtLinks=true;
			gehtRechts=false;
		}
		
		
		
	
	} 
	
	//Sagt was passieren soll wenn ein Gegener mit dem Spieler zusammen trifft
	public static void Kollision(){

			if( (GegnerKreis.StartX+31 >= Spielfeld.spieler.x)     && 
				(GegnerKreis.StartX <= Spielfeld.spieler.x+31)  &&
				(GegnerKreis.StartY+31 >= Spielfeld.spieler.y)  &&
				(GegnerKreis.StartY <= Spielfeld.spieler.y+31)){		
				
				spieler.leben = spieler.leben -1;
				
			}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
