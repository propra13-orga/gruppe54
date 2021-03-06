package propra2013.Gruppe54;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class GegnerOU extends Rectangle{

	private static final long serialVersionUID = 1L;
	
	public boolean unten=false;  // gibt an, ob der Gegener unten an eine Wand stößt
	public boolean rechts=false; // gibt an, ob der Gegener rechts an eine Wand stößt
	public boolean setItem = false;
	public int leben; 
	public double StartX;
	public double StartY;
	public double speed = 0.3;
	public int counter_kollision = 0;
	public static int StartLeben;
	public int Faktor; //zum zeichen der Lebensanzeige
	public boolean aktiv,hoch = false;
	public Image image;
	
	/**
	 * Konstruktor
	 */
	public GegnerOU() {
		setBounds((int)StartX,(int)StartY,32,32);
		if (Spielfeld.current_lvl==1){
			StartLeben=100;
			Faktor=3;
		} else if(Spielfeld.current_lvl==2){
			StartLeben=200;
			Faktor=6;
		} else if(Spielfeld.current_lvl==3){
			StartLeben=300;
			Faktor=10;
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
		if((aktiv)&&(StartX!=0)&&(StartY!=0)){
			if(hoch){
				g.drawImage(new ImageIcon("pics/gegner2_"+Spielfeld.current_lvl+"_hoch.png").getImage(), (int)StartX, (int)StartY, 32, 32, null); //zeichnet den Gegner an (x,y)
			} else if(!hoch){
				g.drawImage(new ImageIcon("pics/gegner2_"+Spielfeld.current_lvl+".png").getImage(), (int)StartX, (int)StartY, 32, 32, null); //zeichnet den Gegner an (x,y)
			}
		}
	}
	/**
	 * legt ein Item ab wenn der Gegner besiegt wurde
	 */
	public void setItem(){
		if(Spielfeld.getBlock(StartX+16,StartY+16).ID == 0){
			Spielfeld.getBlock(StartX+16,StartY+16).ID = 32;			
		}
		setItem = true;
	}
	
	/**
	 * Bewegung des Gegners
	 */
	public void lauf(){
		//Gegner läuft hoch und runter
			if ((unten==false)&&(Spielfeld.getBlockID(StartX+16, StartY+2+32)!=2)&&(Spielfeld.getBlockID(StartX+16, StartY+2+32)!=1)&&(Spielfeld.getBlockID(StartX+16, StartY+2+32)!=41)
					&&(Spielfeld.getBlockID(StartX+16, StartY+2+32)!=42)&&(Spielfeld.getBlockID(StartX+16, StartY+2+32)!=43)
					&&(Spielfeld.getBlockID(StartX+16, StartY+2+32)!=4)&&(Spielfeld.getBlockID(StartX+16, StartY+2+32)!=2)&&(Spielfeld.getBlockID(StartX+16, StartY+2+32)!=56)&&(Spielfeld.getBlockID(StartX+16, StartY+2+32)!=57)&&(Spielfeld.getBlockID(StartX+16, StartY+2+32)!=58)){
				Kollision();
				StartY+=1*speed;
				hoch = false;
				} else {
					unten = true;
				}
			if ((unten==true)&&(Spielfeld.getBlockID(StartX+16, StartY-2+16)!=2)&&(Spielfeld.getBlockID(StartX+16, StartY-2+16)!=1)&&(Spielfeld.getBlockID(StartX+16, StartY-2+16)!=41)
					&&(Spielfeld.getBlockID(StartX+16, StartY-2+16)!=42)&&(Spielfeld.getBlockID(StartX+16, StartY-2+16)!=43)
					&&(Spielfeld.getBlockID(StartX+16, StartY+2+32)!=4)&&(Spielfeld.getBlockID(StartX+16, StartY-2+16)!=2)&&(Spielfeld.getBlockID(StartX+16, StartY-2+16)!=56)&&(Spielfeld.getBlockID(StartX+16, StartY-2+16)!=57)&&(Spielfeld.getBlockID(StartX+16, StartY-2+16)!=58)){
				Kollision();
				StartY-=1*speed;
				hoch = true;
				} else {
					unten = false;	
				}
			} 
	

	/**
	 * Kollision, bestimmt was passieren soll wenn ein Gegner mit dem Spieler zusammen trifft
	 */
	public void Kollision(){
		if( (StartX+26 >= Spielfeld.spieler.x)&&(StartX <= Spielfeld.spieler.x+26)&&
			(StartY+26 >= Spielfeld.spieler.y)&&(StartY <= Spielfeld.spieler.y+26)){		
			counter_kollision ++;
			if(counter_kollision == 5) {
				Spielfeld.spieler.leben -= 1;
				if(Spielfeld.multiplayer){
					Spielfeld.client.send(Spielfeld.client.socket.getLocalPort()+";spielerleben;"+Spielfeld.spieler.leben+";");
				}
				counter_kollision = 0;
			}
		}
	}
	public static void main(String[] args) {}

}
