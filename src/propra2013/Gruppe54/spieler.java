package propra2013.Gruppe54;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class spieler extends Rectangle {

	public double x=Raum.Startpunkt[Spielfeld.current_lvl-1].getX();

	public double y=Raum.Startpunkt[Spielfeld.current_lvl-1].getY(); 
	
	public double speed = 0.5;
	public int gold = 500;
	public int current_schwert = 0;   //gibt an welches Schwert der Spieler gerade hat
	public boolean schwert = true;
	public int waffe = 0,ausrüstung = 1;
	public int item_trank = 0,item_mana = 0;
	public static int ruestung = 50;
	public static int mana = 100;			  //Zauberkraft
	public static int leben = 100,superleben = 3;
	public static boolean aktiv = false;
	public static Point checkpoint;
	public static int check_room = 1;
	
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
