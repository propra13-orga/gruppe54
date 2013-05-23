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
	public static boolean aktiv=false; 
	public static int StartX;
	public static int StartY;
	public static int x,y;
	/**
	 * @param args
	 */
	
	public GegnerOU() {
		setBounds(StartX,StartY,32,32);
		aktiv = true;		//gibt an ob der Gegner im Spiel ist
	}
	
	public void draw(Graphics g){
		g.drawImage(new ImageIcon("pics/gegner2.png").getImage(), StartX, StartY, 32, 32, null); //zeichnet den Gegner an (x,y)
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

		if( (GegnerOU.StartX+31 >= spieler.x)     && 
			(GegnerOU.StartX <= spieler.x+31)  &&
			(GegnerOU.StartY+31 >= spieler.y)  &&
			(GegnerOU.StartY <= spieler.y+31)){		
			
			spieler.leben = spieler.leben -1;
			
		}
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
