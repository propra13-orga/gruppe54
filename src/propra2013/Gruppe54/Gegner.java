package propra2013.Gruppe54;

import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Gegner extends Rectangle {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static boolean unten=false; // gibt an, ob der Gegener unten an eine Wand stößt
	public static boolean rechts=false; // gibt an, ob der Gegener rechts an eine Wand stößt
	public static int x=200,y=20;  
	public static boolean aktiv=false; 
	public static int ID;
	/**
	 * @param args
	 */
	
	public Gegner(int ID) {
		setBounds(x,y,32,32);
		aktiv = true;		//gibt an ob der Gegner im Spiel ist
		this.ID = ID;
	}
	
	
	public void draw(Graphics g){
		
			g.drawImage(new ImageIcon("pics/gegner.png").getImage(), Gegner.x, Gegner.y, 32, 32, null); //zeichnet den Gegner an (x,y)
		
		/*if (Gegner.ID == 0){
			g.drawImage(new ImageIcon("pics/gegner2.png").getImage(), x, y, 32, 32, null); //zeichnet den Gegner an (x,y)

		}*/


	}
	
	
	public static void lauf(){
		if (ID == 1){
			if ((unten==false) &&(Spielfeld.getBlockID(Gegner.x+16, Gegner.y+2+32)!=1)){
				Gegner.y+=2;
				} else unten = true;
			if ((unten==true)&& (Spielfeld.getBlockID(Gegner.x+16, Gegner.y-2+16)!=1)){
				Gegner.y-=2;
				} else unten = false;	
		} 
		if (ID == 0){
        	 if ((rechts==false) &&(Spielfeld.getBlockID(Gegner.x+24, Gegner.y+16)!=1)){
        		 Gegner.x+=2;
        	 } else rechts = true;
        	 if ((rechts==true)&& (Spielfeld.getBlockID(Gegner.x-2, Gegner.y+16)!=1)){
       		 Gegner.x-=2;
        	 } else rechts = false;  
		} 
}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
