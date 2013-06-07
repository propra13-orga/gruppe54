package propra2013.Gruppe54;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Endgegner extends Rectangle{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int StartX;
	public static int StartY;
	public static int leben;
	public static boolean unten=false;//gibt an ob der Gegner unten an einer Mauer steht
	public static boolean rechts=false;//gibt an ob der Gegner rechts an einer Mauer steht
	public static int schritte=0;
	public static int StartLeben;
	public static int Faktor;

	/**
	 * @param args
	 */
	public Endgegner(){
		setBounds(StartX,StartY,32,32);
		if (Spielfeld.current_lvl==1){
			StartLeben=300;
			Faktor=10;
		} else 
			if(Spielfeld.current_lvl==2){
				StartLeben=400;
				Faktor=12;
			} else
				if(Spielfeld.current_lvl==3){
					StartLeben=500;
					Faktor=16;
				}
		leben=StartLeben;
	}
	
	//Zeichnet den Gegner
	//muss noch ein anderes Bild eingefügt werden
	public void draw(Graphics g){
		g.drawImage(new ImageIcon("pics/gegner3_"+Spielfeld.current_lvl+".png").getImage(), StartX, StartY, 32, 32, null); //zeichnet den Gegner an (x,y)
	}
	
	//Gibt an wie sich der Gegner bewegt
	public static void lauf(){
		//Hoch-runter
			if ((unten==false) &&(Spielfeld.getBlockID(Endgegner.StartX+16, Endgegner.StartY+2+32)!=1)){
				Kollision();
				Endgegner.StartY+=1;
				} else unten = true; 
			if ((unten==true)&& (Spielfeld.getBlockID(Endgegner.StartX+16, Endgegner.StartY-2+16)!=1)){
				Kollision();
				Endgegner.StartY-=1;
				} else unten = false; 
	}
	

	
	//Sagt was passieren soll wenn ein Gegener mit dem Spieler zusammen trifft
	public static void Kollision(){

			if( (Endgegner.StartX+31 >= Spielfeld.spieler.x)     && 
				(Endgegner.StartX <= Spielfeld.spieler.x+31)  &&
				(Endgegner.StartY+31 >= Spielfeld.spieler.y)  &&
				(Endgegner.StartY <= Spielfeld.spieler.y+31)){		
				
				if (spieler.ruestung>0){
					spieler.ruestung-=1;
				} else spieler.leben -= 1;
				
			}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
