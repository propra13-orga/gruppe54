package propra2013.Gruppe54;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Endgegner extends Rectangle{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int ID; //ID==0 normaler Engegener ID==1 letzte Endgegner
	public static int StartX;
	public static int StartY;
	public static int leben;
	public static boolean unten=false;//gibt an ob der Gegner unten an einer Mauer steht
	public static int schritte=0;

	/**
	 * @param args
	 */
	public Endgegner(int ID){
		setBounds(StartX,StartY,32,32);
		Endgegner.ID= ID;
		leben = 300;
	}
	
	//Zeichnet den Gegner
	//muss noch ein anderes Bild eingefügt werden
	public void draw(Graphics g){
		g.drawImage(new ImageIcon("pics/gegner3_"+Spielfeld.current_lvl+".png").getImage(), StartX, StartY, 32, 32, null); //zeichnet den Gegner an (x,y)
	}
	
	//Gibt an wie sich der Gegner bewegt
	public static void lauf(){
		if (ID==0){
			if ((unten==false) &&(Spielfeld.getBlockID(Endgegner.StartX+16, Endgegner.StartY+2+32)!=1)){
				Kollision();
				Endgegner.StartY+=1;
				} else unten = true; 
			if ((unten==true)&& (Spielfeld.getBlockID(Endgegner.StartX+16, Endgegner.StartY-2+16)!=1)){
				Kollision();
				Endgegner.StartY-=1;
				} else unten = false; 
			
		} else 
			if(ID==1){
			/* fehlt noch*/
			}
	}
	

	
	//Sagt was passieren soll wenn ein Gegener mit dem Spieler zusammen trifft
	public static void Kollision(){

			if( (Endgegner.StartX+31 >= Spielfeld.spieler.x)     && 
				(Endgegner.StartX <= Spielfeld.spieler.x+31)  &&
				(Endgegner.StartY+31 >= Spielfeld.spieler.y)  &&
				(Endgegner.StartY <= Spielfeld.spieler.y+31)){		
				
				spieler.leben -= 1;
				
			}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
