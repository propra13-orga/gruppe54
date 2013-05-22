package propra2013.Gruppe54;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class spieler extends Rectangle {

	public static double x=Raum.Startpunkt[Spielfeld.current_lvl-1].x,y=Raum.Startpunkt[Spielfeld.current_lvl-1].y; 
	
	public static int leben;
	public static double speed = 0.5;
	public static boolean aktiv = false,beweglich = false,rechts = false,links = false,hoch = false,runter = false; 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public spieler(){
		setBounds((int)x,(int)y,32,32);
		leben = 100;		//Lebenspunkte
		aktiv = true;		//gibt an ob der Spieler im Spiel ist
		beweglich = true;
	}
	
	public void draw(Graphics g){
		g.drawImage(Frame.image,(int)x,(int)y,32,32,null);
		//g.drawImage(new ImageIcon("pics/spieler"+Spielfeld.current_player+".png").getImage(), x, y, 32, 32, null); //zeichnet den Spieler an (x,y)
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
