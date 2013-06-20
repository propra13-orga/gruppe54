package propra2013.Gruppe54;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Endgegner extends Rectangle{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static double StartX;
	public static double StartY;
	public static double speed = 0.4;
	public static int leben;
	public static boolean unten=false;//gibt an ob der Gegner unten an einer Mauer steht
	public static boolean rechts=false;//gibt an ob der Gegner rechts an einer Mauer steht
	public static int schritte=0;
	public static int StartLeben;
	public static int Faktor;
	public static boolean aktiv;

	/**
	 * @param args
	 */
	public Endgegner(){
		setBounds((int)StartX,(int)StartY,32,32);
		if (Spielfeld.current_lvl==1){
			StartLeben=300;
			Faktor=10;
		} else if(Spielfeld.current_lvl==2){
			StartLeben=400;
			Faktor=12;
		} else if(Spielfeld.current_lvl==3){
			StartLeben=500;
			Faktor=16;
		} else if(Spielfeld.current_lvl==4){
			StartLeben=300;
			Faktor=10;
		}
		leben=StartLeben;
		aktiv=false;
	}
	
	//Zeichnet den Gegner
	//muss noch ein anderes Bild eingefügt werden
	public void draw(Graphics g){
		g.drawImage(new ImageIcon("pics/gegner3_"+Spielfeld.current_lvl+".png").getImage(), (int)StartX, (int)StartY, 32, 32, null); //zeichnet den Gegner an (x,y)
	}
	
	//Gibt an wie sich der Gegner bewegt
	public static void lauf(){
		//Hoch-runter
		if ((unten==false)&&(Spielfeld.getBlockID(Endgegner.StartX+16, Endgegner.StartY+2+32)!=1)&&(Spielfeld.getBlockID(Endgegner.StartX+16, Endgegner.StartY+2+32)!=41)
				&&(Spielfeld.getBlockID(Endgegner.StartX+16, Endgegner.StartY+2+32)!=42)&&(Spielfeld.getBlockID(Endgegner.StartX+16, Endgegner.StartY+2+32)!=43)
				&&(Spielfeld.getBlockID(Endgegner.StartX+16, Endgegner.StartY+2+32)!=4)){
			Kollision();
			Endgegner.StartY+=1*speed;
		} else {
			unten = true; 
		}
		if ((unten==true)&&(Spielfeld.getBlockID(Endgegner.StartX+16, Endgegner.StartY-2+16)!=1)&&(Spielfeld.getBlockID(Endgegner.StartX+16, Endgegner.StartY-2+16)!=41)
				&&(Spielfeld.getBlockID(Endgegner.StartX+16, Endgegner.StartY-2+16)!=42)&&(Spielfeld.getBlockID(Endgegner.StartX+16, Endgegner.StartY-2+16)!=43)
				&&(Spielfeld.getBlockID(Endgegner.StartX+16, Endgegner.StartY-2+16)!=4)){
			Kollision();
			Endgegner.StartY-=1*speed;
		} else {
			unten = false; 
		}
	}
	
	//Sagt was passieren soll wenn ein Gegener mit dem Spieler zusammen trifft
	public static void Kollision(){
		if((Endgegner.StartX+31 >= Spielfeld.spieler.x)&&(Endgegner.StartX <= Spielfeld.spieler.x+31)&&
			(Endgegner.StartY+31 >= Spielfeld.spieler.y)&&(Endgegner.StartY <= Spielfeld.spieler.y+31)){		
			if (Spielfeld.spieler.ruestung>0){
				Spielfeld.spieler.ruestung-=1;
			} else {
				Spielfeld.spieler.leben -= 1;
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
