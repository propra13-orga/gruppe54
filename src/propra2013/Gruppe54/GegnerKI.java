package propra2013.Gruppe54;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class GegnerKI extends Rectangle{
/*
 * Dieser Gegner soll den Spieler verfolgen
 */
	private static final long serialVersionUID = 1L;
	public int StartX;
	public int StartY;
	public int leben;
	public boolean unten=false;//gibt an ob der Gegner unten an einer Mauer steht
	public boolean rechts=false;//gibt an ob der Gegner rechts an einer Mauer steht
	public int StartLeben;	//Leben dass er bei neu erzeugen hat
	public int Faktor;		//Zum zeichen der Lebensanzeige
	public boolean aktiv;
	public int Schaden;		//Schaden im verhältnis zum level
	public int nächsterSchrittX=0;
	public int nächsterSchrittY=0;
	public int counter_kollision = 0;
	public int a=0,zehn=0,elf=0,zwölf=0,dreizehn=0; //zum prüfen in check
	public int Fall=0;
	public boolean frei=false;
	public boolean laufen=false;
	public int counter_gegnerKI=0;
	
	/**
	 * Konstruktor
	 */
	public GegnerKI(){
		setBounds(StartX,StartY,32,32);
		if (Spielfeld.current_lvl==1){
			StartLeben=100;
			Faktor=3;
			Schaden=1;
		} else if(Spielfeld.current_lvl==2){
			StartLeben=200;
			Faktor=6;
			Schaden=1;
		} else if(Spielfeld.current_lvl==3){
			StartLeben=300;
			Faktor=10;
			Schaden=2;
		} else if(Spielfeld.current_lvl==4){
			StartLeben=300;
			Faktor=10;
			Schaden=2;
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
			g.drawImage(new ImageIcon("pics/GegnerKI_1.png").getImage(), StartX, StartY, 32, 32, null); //zeichnet den Gegner an (x,y)
		}
	}
	
	/**
	 * berechnet die Richtung des nächsten Schrittes
	 */
	public void nächsterSchritt(){
		if (Fall==1){
			if(linksfrei()){
			nächsterSchrittX=-1;
			} else nächsterSchrittX=0;
			if(untenfrei()){
			nächsterSchrittY=1;
			} else nächsterSchrittY=0;
		} else
		if (Fall==2){
			if (rechtsfrei()){
			nächsterSchrittX=1;
			} else nächsterSchrittX=0;
			if (untenfrei()){
			nächsterSchrittY=1;
			} else nächsterSchrittY=0;
		} else
		if (Fall==3){
			if(rechtsfrei()){
			nächsterSchrittX=1;
			} else nächsterSchrittX=0;
			if(obenfrei()){
			nächsterSchrittY=-1;
			} else nächsterSchrittY=0;
		} else
		if (Fall==4){
			if(linksfrei()){
			nächsterSchrittX=-1;
			}else nächsterSchrittX=0;
			if(obenfrei()){
			nächsterSchrittY=-1;
			} else nächsterSchrittY=0;
		} else
		if (Fall==5){
			if(untenfrei()){
			nächsterSchrittY=1;
			} else nächsterSchrittY=0;
			nächsterSchrittX=0;
		}else
		if (Fall==6){
			if(rechtsfrei()){
			nächsterSchrittX=1;
			}else nächsterSchrittX=0;
			nächsterSchrittY=0;
		}else
		if (Fall==7){
			nächsterSchrittX=0;
			if(obenfrei()){
			nächsterSchrittY=-1;
			} else nächsterSchrittY=0;
		}else
		if (Fall==8){
			if(linksfrei()){
			nächsterSchrittX=-1;
			} else nächsterSchrittX=0;
			nächsterSchrittY=0;
		}else
		if (Fall==9){
			nächsterSchrittX=0;
			nächsterSchrittY=0;
		}		
	}
	
	/**Überprüft wo sich der Spieler befindet, wenn Gegner in 9 steht
	 *
	 * 4		7		3
	 * 
	 * 8		9		6
	 * 
	 * 1		5		2
	 *  
	 */
	public void checkFall(){
		
		if ((Spielfeld.spieler.x<StartX)&&(Spielfeld.spieler.y>StartY)){
			Fall=1;
		}
		if ((Spielfeld.spieler.x>StartX)&&(Spielfeld.spieler.y>StartY)){
			Fall=2;
		}
		if ((Spielfeld.spieler.x>StartX)&&(Spielfeld.spieler.y<StartY)){
			Fall=3;
		}
		if ((Spielfeld.spieler.x<StartX)&&(Spielfeld.spieler.y<StartY)){
			Fall=4;
		}
		if ((Spielfeld.spieler.x==StartX)&&(Spielfeld.spieler.y>StartY)){
			Fall=5;
		}
		if ((Spielfeld.spieler.x>StartX)&&(Spielfeld.spieler.y==StartY)){
			Fall=6;
		}
		if ((Spielfeld.spieler.x==StartX)&&(Spielfeld.spieler.y<StartY)){
			Fall=7;
		}
		if ((Spielfeld.spieler.x<StartX)&&(Spielfeld.spieler.y==StartY)){
			Fall=8;
		}
		if ((Spielfeld.spieler.x==StartX)&&(Spielfeld.spieler.y==StartY)){
			Fall=9;
		}
	}
	
	/**
	 * Bewegung des GegnerKI
	 */
	public void lauf(){
		if (laufen){
			checkFall();
			nächsterSchritt();
			StartX+=nächsterSchrittX;
			StartY+=nächsterSchrittY;
			Kollision();
		}
	}
	
	/**
	 * links vom Gegner frei?
	 * @return true oder false
	 */
	public boolean linksfrei(){
		boolean frei=false;
		for (int i=1;i<16;i++){
			if((Spielfeld.getBlockID(StartX-1, StartY+15+i)!=1)&&(Spielfeld.getBlockID(StartX-1, StartY+15+i)!=2)&&(Spielfeld.getBlockID(StartX-1, StartY+15+i)!=4)&&(Spielfeld.getBlockID(StartX-1, StartY+15+i)!=41)&&(Spielfeld.getBlockID(StartX-1, StartY+15+i)!=42)&&(Spielfeld.getBlockID(StartX-1, StartY+15+i)!=33)&&(Spielfeld.getBlockID(StartX-1, StartY+15+i)!=56)&&(Spielfeld.getBlockID(StartX-1, StartY+15+i)!=57)&&(Spielfeld.getBlockID(StartX-1, StartY+15+i)!=58)){
				frei=true;
			} else {
				frei=false;
				break;
			}
		}
		return frei;
		
	}
	/**
	 * rechts vom Gegner frei?
	 * @return true oder false
	 */
	public boolean rechtsfrei(){
		boolean frei=false;
		for (int i=1;i<16;i++){
			if((Spielfeld.getBlockID(StartX+32, StartY+15+i)!=1)&&(Spielfeld.getBlockID(StartX+32, StartY+15+i)!=2)&&(Spielfeld.getBlockID(StartX+32, StartY+15+i)!=33)&&(Spielfeld.getBlockID(StartX+32, StartY+15+i)!=4)&&(Spielfeld.getBlockID(StartX+32, StartY+15+i)!=41)&&(Spielfeld.getBlockID(StartX+32, StartY+15+i)!=42)&&(Spielfeld.getBlockID(StartX+32, StartY+15+i)!=56)&&(Spielfeld.getBlockID(StartX+32, StartY+15+i)!=57)&&(Spielfeld.getBlockID(StartX+32, StartY+15+i)!=58)){
				frei=true;
			} else {
				frei=false;
				break;
			}
		}
		return frei;
		
	}
	/**
	 * oben vom Gegner frei?
	 * @return true oder false
	 */
	public boolean obenfrei(){
		boolean frei=false;
		for (int i=1;i<29;i++){
			if((Spielfeld.getBlockID(StartX+1+i, StartY+15)!=1)&&(Spielfeld.getBlockID(StartX+1+i, StartY+15)!=2)&&(Spielfeld.getBlockID(StartX+1+i, StartY+15)!=33)&&(Spielfeld.getBlockID(StartX+1+i, StartY+15)!=4)&&(Spielfeld.getBlockID(StartX+1+i, StartY+15)!=41)&&(Spielfeld.getBlockID(StartX+1+i, StartY+15)!=42)&&(Spielfeld.getBlockID(StartX+1+i, StartY+15)!=56)&&(Spielfeld.getBlockID(StartX+1+i, StartY+15)!=57)&&(Spielfeld.getBlockID(StartX+1+i, StartY+15)!=58)){
				frei=true;
			} else {
				frei=false;
				break;
			}
		}
		return frei;
		
	}
	/**
	 * unten vom Gegner frei?
	 * @return true oder false
	 */
	public boolean untenfrei(){
		boolean frei=false;
		for (int i=1;i<29;i++){
			if((Spielfeld.getBlockID(StartX+1+i, StartY+32)!=1)&&(Spielfeld.getBlockID(StartX+1+i, StartY+32)!=2)&&(Spielfeld.getBlockID(StartX+1+i, StartY+32)!=33)&&(Spielfeld.getBlockID(StartX+1+i, StartY+32)!=4)&&(Spielfeld.getBlockID(StartX+1+i, StartY+32)!=41)&&(Spielfeld.getBlockID(StartX+1+i, StartY+32)!=42)&&(Spielfeld.getBlockID(StartX+1+i, StartY+32)!=56)&&(Spielfeld.getBlockID(StartX+1+i, StartY+32)!=57)&&(Spielfeld.getBlockID(StartX+1+i, StartY+32)!=58)){
				frei=true;
			} else {
				frei=false;
				break;
			}
		}
		return frei;
	}
	
	/**
	 * bestimmt was passieren soll wenn ein Gegner mit dem Spieler zusammen trifft
	 */
	public void Kollision(){
		if( (StartX+26 >= Spielfeld.spieler.x)&&(StartX <= Spielfeld.spieler.x+26)&&
			(StartY+26 >= Spielfeld.spieler.y)&&(StartY <= Spielfeld.spieler.y+26)){	
			counter_kollision ++;
			if ((Spielfeld.spieler.ruestung>0)&&(counter_kollision == 4)){	//counter_kollision damit nicht zuviel Leben abgezogen wird
				Spielfeld.spieler.ruestung-=Schaden;
				counter_kollision = 0;
			} else if((Spielfeld.spieler.ruestung<=0)&&(counter_kollision == 4)) {
				Spielfeld.spieler.leben -= Schaden;
				if(Spielfeld.multiplayer){
					Spielfeld.client.send(Spielfeld.client.socket.getLocalPort()+";spielerleben;"+Spielfeld.spieler.leben+";");
				}
				counter_kollision = 0;
			}
		}
		if((StartX+26 >= Falle.StartX)&&(StartX <= Falle.StartX+26)&&(StartY+26 >= Falle.StartY)&&(StartY <= Falle.StartY+26)){	
			leben=0;
		}
	}
	public static void main(String[] args) {}

}

