package propra2013.Gruppe54;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class spieler extends Rectangle {
	
	//bei einem Rectangle ist der Startpunkt immer ober links, d.h. hier wäre anfangs (0,0)
	//die linke obere Ecke

	public static int x=Raum.Startpunkt[Spielfeld.current_lvl-1].x,y=Raum.Startpunkt[Spielfeld.current_lvl-1].y; 
	
	public static int leben;
	public static boolean aktiv=false; 
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public spieler(){
		setBounds(x,y,32,32);
		leben = 100;		//Lebenspunkte
		aktiv = true;		//gibt an ob der Spieler im Spiel ist
	}
	
	public void draw(Graphics g){
		g.drawImage(new ImageIcon("pics/spieler.png").getImage(), x, y, 32, 32, null); //zeichnet den Spieler an (x,y)
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
