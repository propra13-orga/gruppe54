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
	public static int StartX;
	public static int StartY;
	public static int x,y;
	public static int StartLeben;
	public static int Faktor; //zum zeichen der Lebensanzeige
	public static boolean aktiv;
	/**
	 * @param args
	 */
	
	public GegnerOU() {
		setBounds(StartX,StartY,32,32);
		if (Spielfeld.current_lvl==1){
			StartLeben=100;
			Faktor=3;
		} else 
			if(Spielfeld.current_lvl==2){
				StartLeben=200;
				Faktor=6;
			} else
				if(Spielfeld.current_lvl==3){
					StartLeben=300;
					Faktor=10;
				}
		leben=StartLeben;		
		aktiv=false;
	}
	
	public void draw(Graphics g){
		g.drawImage(new ImageIcon("pics/gegner2_"+Spielfeld.current_lvl+".png").getImage(), StartX, StartY, 32, 32, null); //zeichnet den Gegner an (x,y)
	}
	
	public static void lauf(){
		//Gegner läuft hoch und runter
			if ((unten==false) &&(Spielfeld.getBlockID(GegnerOU.StartX+16, GegnerOU.StartY+2+32)!=1)){
				Kollision2();
				GegnerOU.StartY+=1;
				} else unten = true;
			if ((unten==true)&& (Spielfeld.getBlockID(GegnerOU.StartX+16, GegnerOU.StartY-2+16)!=1)){
				Kollision2();
				GegnerOU.StartY-=1;
				} else unten = false;	
			} 
	

//Sagt was passieren soll wenn ein Gegener mit dem Spieler zusammen trifft
public static void Kollision2(){

		if( (GegnerOU.StartX+26 >= Spielfeld.spieler.x)     && 
			(GegnerOU.StartX <= Spielfeld.spieler.x+26)  &&
			(GegnerOU.StartY+26 >= Spielfeld.spieler.y)  &&
			(GegnerOU.StartY <= Spielfeld.spieler.y+26)){		
			
			if (spieler.ruestung>0){
				spieler.ruestung-=1;
			} else spieler.leben -= 1;
			
		}
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
