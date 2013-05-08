package propra2013.Gruppe54;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class spieler extends Rectangle {
	
	//bei einem Rectangle ist der Startpunkt immer ober links, d.h. hier wäre anfangs (100,20)
	//die linke obere Ecke

	public static int x=100,y=20;    //Startkoordinaten, später müssten die jeweils dem Startpunkt des Raumes entsprechend
									 //hab hier jetzt einfach mal (100,20) genommen
	public static int leben;
	public static boolean aktiv=false; //Spieler ist im Spiel
	
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
