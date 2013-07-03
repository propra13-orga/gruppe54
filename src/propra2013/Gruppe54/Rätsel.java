package propra2013.Gruppe54;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Rätsel extends Rectangle {

	private static final long serialVersionUID = 1L;
	
	public int reihenfolge =0;	//gibt an an welcher stelle man es berühren muss
	public boolean aktiv=true;	//true = rot , false=blau
	public int X;
	public int Y;
	public boolean berührung=false;
	public static int next=0;
	public static boolean geschafft=false;
	public static boolean vorhanden=false;
	
	/**
	 * Konstruktor
	 * @param startX
	 * @param startY
	 */
	public Rätsel(int Reihenfolge){
		setBounds(X,Y,32,32);
		aktiv=true;
		next=1;
		reihenfolge=Reihenfolge;
	}
	/**
	 * Zeichnet das Rätsel
	 * @param g
	 */
	public void draw(Graphics g){
		if (aktiv==true){
			g.drawImage(new ImageIcon("pics/rätsel_rot.png").getImage(),X,Y,32,32,null);
		} else if (aktiv==false){
			g.drawImage(new ImageIcon("pics/rätsel_blau.png").getImage(),X,Y,32,32,null);
		}
	}	
	
	public static void setFalse(){
		Spielfeld.rätsel1.berührung=false;
		Spielfeld.rätsel2.berührung=false;
		Spielfeld.rätsel3.berührung=false;
		Spielfeld.rätsel4.berührung=false;
	}
	
	public static void reset(){
		Spielfeld.rätsel1.aktiv=true;
		Spielfeld.rätsel2.aktiv=true;
		Spielfeld.rätsel3.aktiv=true;
		Spielfeld.rätsel4.aktiv=true;
		next=1;
		geschafft=false;
		vorhanden=false;
	}
	public void aktion(){
		if (aktiv==false){
			//passiert nicht
		} else if (aktiv==true){
			if (next==reihenfolge){
				if(next<4){
					aktiv=false;
					next+=1;
				} else if (next==4){
					aktiv=false;
					next=5;
					geschafft=true;
				}
			} else if (next!=reihenfolge){
				Spielfeld.rätsel1.aktiv=true;
				Spielfeld.rätsel2.aktiv=true;
				Spielfeld.rätsel3.aktiv=true;
				Spielfeld.rätsel4.aktiv=true;
				next=1;
			}
			
		}
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
