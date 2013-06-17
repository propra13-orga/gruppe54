package propra2013.Gruppe54;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class GegnerOU extends Rectangle{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @param args
	 */
	public static boolean unten=false; // gibt an, ob der Gegener unten an eine Wand stößt
	public static boolean rechts=false; // gibt an, ob der Gegener rechts an eine Wand stößt
	public static int leben; 
	public static double StartX;
	public static double StartY;
	public static double speed = 0.4;
	public static int counter_kollision = 0;
	public static int x,y;
	public static int StartLeben;
	public static int Faktor; //zum zeichen der Lebensanzeige
	public static boolean aktiv;
	/**
	 * @param args
	 */
	
	public GegnerOU() {
		setBounds((int)StartX,(int)StartY,32,32);
		if (Spielfeld.current_lvl==1){
			StartLeben=100;
			Faktor=3;
		} else if(Spielfeld.current_lvl==2){
			StartLeben=200;
			Faktor=6;
		} else if(Spielfeld.current_lvl==3){
			StartLeben=300;
			Faktor=10;
		} else if(Spielfeld.current_lvl==4){
			StartLeben=300;
			Faktor=10;
		}
		leben=StartLeben;		
		aktiv=false;
	}
	
	public void draw(Graphics g){
		if((GegnerOU.aktiv)&&(GegnerOU.StartX!=0)&&(GegnerOU.StartY!=0)){
			g.drawImage(new ImageIcon("pics/gegner2_"+Spielfeld.current_lvl+".png").getImage(), (int)StartX, (int)StartY, 32, 32, null); //zeichnet den Gegner an (x,y)
		}
	}
	
	public static void lauf(){
		//Gegner läuft hoch und runter
			if ((unten==false)&&(Spielfeld.getBlockID(GegnerOU.StartX+16, GegnerOU.StartY+2+32)!=1)&&(Spielfeld.getBlockID(GegnerOU.StartX+16, GegnerOU.StartY+2+32)!=41)
					&&(Spielfeld.getBlockID(GegnerOU.StartX+16, GegnerOU.StartY+2+32)!=42)&&(Spielfeld.getBlockID(GegnerOU.StartX+16, GegnerOU.StartY+2+32)!=43)
					&&(Spielfeld.getBlockID(GegnerOU.StartX+16, GegnerOU.StartY+2+32)!=4)){
				Kollision();
				GegnerOU.StartY+=1*speed;
				} else unten = true;
			if ((unten==true)&&(Spielfeld.getBlockID(GegnerOU.StartX+16, GegnerOU.StartY-2+16)!=1)&&(Spielfeld.getBlockID(GegnerOU.StartX+16, GegnerOU.StartY-2+16)!=41)
					&&(Spielfeld.getBlockID(GegnerOU.StartX+16, GegnerOU.StartY-2+16)!=42)&&(Spielfeld.getBlockID(GegnerOU.StartX+16, GegnerOU.StartY-2+16)!=43)
					&&(Spielfeld.getBlockID(GegnerOU.StartX+16, GegnerOU.StartY+2+32)!=4)){
				Kollision();
				GegnerOU.StartY-=1*speed;
				} else unten = false;	
			} 
	

	//Sagt was passieren soll wenn ein Gegener mit dem Spieler zusammen trifft
	public static void Kollision(){
		if( (GegnerOU.StartX+26 >= Spielfeld.spieler.x)&&(GegnerOU.StartX <= Spielfeld.spieler.x+26)&&
			(GegnerOU.StartY+26 >= Spielfeld.spieler.y)&&(GegnerOU.StartY <= Spielfeld.spieler.y+26)){		
			counter_kollision ++;
			if ((spieler.ruestung>0)&&(counter_kollision == 5)){  //counter_kollision damit nicht zuviel Leben abgezogen wird
				spieler.ruestung-=1;
				counter_kollision = 0;
			} else if((spieler.ruestung <= 0)&&(counter_kollision == 5)) {
				spieler.leben -= 1;
				counter_kollision = 0;
			}
		}
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
