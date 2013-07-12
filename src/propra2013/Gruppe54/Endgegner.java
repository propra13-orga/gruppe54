package propra2013.Gruppe54;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Endgegner extends Rectangle{

	private static final long serialVersionUID = 1L;
	public static double StartX;
	public static double StartY;
	public static double speed = 0.4;
	public static int leben;
	public static boolean unten=false;//gibt an ob der Gegner unten an einer Mauer steht
	public static boolean rechts=false;//gibt an ob der Gegner rechts an einer Mauer steht
	public static boolean setItem = false;
	public static int schritte=0;
	public static int StartLeben;
	public static int Faktor;
	public static boolean aktiv;

	/**
	 * Konstruktor
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
	
	
	/**
	 * Zeichnet den Gegner
	 * @param g
	 */
	public void draw(Graphics g){
		g.drawImage(new ImageIcon("pics/gegner3_"+Spielfeld.current_lvl+".png").getImage(), (int)StartX, (int)StartY, 32, 32, null); //zeichnet den Gegner an (x,y)
	}
	
	
	/**
	 * setItem legt ein Item ab wenn der Gegner besiegt wurde 
	 */
	public static void setItem(){
		if(Spielfeld.getBlock(StartX+16,StartY+16).ID == 0){
			Spielfeld.getBlock(StartX+16,StartY+16).ID = 32;			
		}
		setItem = true;
	}
	
	/**
	 * lauf gibt die Bewegung des Endgegners an
	 */
	public static void lauf(){
	/*	//Hoch-runter
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
		}*/
		Kollision();
	}
	/**
	 * Kollision
	 * gibt an was passieren soll wenn ein Gegner mit dem Spieler zusammen trifft
	 * 
	 */
	public static void Kollision(){
		if((Endgegner.StartX+31 >= Spielfeld.spieler.x)&&(Endgegner.StartX <= Spielfeld.spieler.x+31)&&
			(Endgegner.StartY+31 >= Spielfeld.spieler.y)&&(Endgegner.StartY <= Spielfeld.spieler.y+31)){		
			if (Spielfeld.spieler.ruestung>0){
				Spielfeld.spieler.ruestung-=1;
			} else {
				Spielfeld.spieler.leben -= 1;
				if(Spielfeld.multiplayer){
					Spielfeld.client.send(Spielfeld.client.socket.getLocalPort()+";spielerleben;"+Spielfeld.spieler.leben+";");
				}
			}
		}
	}
	public static void main(String[] args) {}
}
