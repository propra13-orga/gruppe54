package propra2013.Gruppe54;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class GegnerRL extends Rectangle  {

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
	
	public GegnerRL() {
		setBounds(StartX,StartY,32,32);
		aktiv = true;		//gibt an ob der Gegner im Spiel ist
	}
	
	
	public  void draw(Graphics g){
	
			
			g.drawImage(new ImageIcon("pics/gegner1_"+Spielfeld.current_lvl+".png").getImage(), StartX, StartY, 32, 32, null); //zeichnet den Gegner 

	
	}
	
	
	
	public static void lauf(){
		//Gegner läuft von rechts nach links
	
        	 if ((rechts==false) &&(Spielfeld.getBlockID(GegnerRL.StartX+24, GegnerRL.StartY+16)!=1)){
        		Kollision2();
        		GegnerRL.StartX+=1;
        	 } else rechts = true;
        	 if ((rechts==true)&& (Spielfeld.getBlockID(GegnerRL.StartX-2, GegnerRL.StartY+16)!=1)){
        		Kollision2();
        		 GegnerRL.StartX-=1;
        	 } else rechts = false;  
		} 
	

//Sagt was passieren soll wenn ein Gegener mit dem Spieler zusammen trifft
public static void Kollision2(){

		if( (GegnerRL.StartX+31 >= Spielfeld.spieler.x)     && 
			(GegnerRL.StartX <= Spielfeld.spieler.x+31)  &&
			(GegnerRL.StartY+31 >= Spielfeld.spieler.y)  &&
			(GegnerRL.StartY <= Spielfeld.spieler.y+31)){		
			
			spieler.leben -= 1;
			
		}
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
