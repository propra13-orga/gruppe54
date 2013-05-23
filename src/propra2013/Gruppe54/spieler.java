package propra2013.Gruppe54;

import java.awt.Graphics;
import java.awt.Rectangle;

public class spieler extends Rectangle {

	public double x=Raum.Startpunkt[Spielfeld.current_lvl-1].getX();

	public double y=Raum.Startpunkt[Spielfeld.current_lvl-1].getY(); 
	
	public double speed = 0.5;
	//public int sprint = 100;
	public static int leben;
	public static boolean aktiv = false;

	public boolean beweglich = false;

	public boolean rechts = false;

	public boolean links = false;

	public boolean hoch = false;

	public boolean runter = false; 
	
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
