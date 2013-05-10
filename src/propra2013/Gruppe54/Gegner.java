package propra2013.Gruppe54;

import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Gegner extends Rectangle {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static int x=200,y=20;  
	public static boolean aktiv=false; 
	/**
	 * @param args
	 */
	
	public Gegner() {
		setBounds(x,y,32,32);
		aktiv = true;		//gibt an ob der Gegner im Spiel ist
	}
	
	
	public void draw(Graphics g){
		g.drawImage(new ImageIcon("pics/gegner2.png").getImage(), x, y, 32, 32, null); //zeichnet den Gegner an (x,y)
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
