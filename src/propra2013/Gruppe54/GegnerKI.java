package propra2013.Gruppe54;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class GegnerKI extends Rectangle{
/*
 * Dieser Gegner soll den Spieler verfolgen
 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int StartX;
	public static int StartY;
	public static int leben;
	public static boolean unten=false;//gibt an ob der Gegner unten an einer Mauer steht
	public static boolean rechts=false;//gibt an ob der Gegner rechts an einer Mauer steht
	public static int StartLeben;	//Leben dass er bei neu erzeugen hat
	public static int Faktor;		//Zum zeichen der Lebensanzeige
	public static boolean aktiv;
	public static int Schaden;		//Schaden im verhältnis zum level
	public static int nächsterSchrittX=0;
	public static int nächsterSchrittY=0;
	public static int counter_kollision = 0;
	public static int a=0,zehn=0,elf=0,zwölf=0,dreizehn=0; //zum prüfen in check
	public static int Fall=0;
	public static boolean frei=false;
	public static boolean laufen=false;
	public static int counter_gegnerKI=0;
	
	/**
	 * @param args
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
			Schaden=2;
		} else if(Spielfeld.current_lvl==3){
			StartLeben=300;
			Faktor=10;
			Schaden=3;
		} else if(Spielfeld.current_lvl==4){
			StartLeben=300;
			Faktor=10;
			Schaden=3;
		}
		leben=StartLeben;
		aktiv=false;
	}
	
	//Zeichnet den Gegner
	public void draw(Graphics g){
		if((GegnerKI.aktiv)&&(GegnerKI.StartX!=0)&&(GegnerKI.StartY!=0)){
			g.drawImage(new ImageIcon("pics/GegnerKI_1"/*+Spielfeld.current_lvl*/+".png").getImage(), StartX, StartY, 32, 32, null); //zeichnet den Gegner an (x,y)
		}
	}
	
	//Funktion die den nächsten schritt berechnet
	public static void nächsterSchritt(){
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
		}/* else
		if(Fall==10){			//unten
			nächsterSchrittX=0;
			nächsterSchrittY=1;
		}else
		if(Fall==11){			//links
			nächsterSchrittX=-1;
			nächsterSchrittY=0;
		} else
		if(Fall==12){			//rechts
			nächsterSchrittX=1;
			nächsterSchrittY=0;
		}else				
		if(Fall==13){			//oben
			nächsterSchrittX=0;
			nächsterSchrittY=-1;
		}*/
		
	}
	
	//Überprüft wo sich der Spieler befindet, wenn Gegner in 9 steht
	/*
	 * 4		7		3
	 * 
	 * 8		9		6
	 * 
	 * 1		5		2
	 *  
	 */
	public static void checkFall(){
		
		if ((Spielfeld.spieler.x<GegnerKI.StartX)&&(Spielfeld.spieler.y>GegnerKI.StartY)){
			Fall=1;
		}
		if ((Spielfeld.spieler.x>GegnerKI.StartX)&&(Spielfeld.spieler.y>GegnerKI.StartY)){
			Fall=2;
		}
		if ((Spielfeld.spieler.x>GegnerKI.StartX)&&(Spielfeld.spieler.y<GegnerKI.StartY)){
			Fall=3;
		}
		if ((Spielfeld.spieler.x<GegnerKI.StartX)&&(Spielfeld.spieler.y<GegnerKI.StartY)){
			Fall=4;
		}
		if ((Spielfeld.spieler.x==GegnerKI.StartX)&&(Spielfeld.spieler.y>GegnerKI.StartY)){
			Fall=5;
		}
		if ((Spielfeld.spieler.x>GegnerKI.StartX)&&(Spielfeld.spieler.y==GegnerKI.StartY)){
			Fall=6;
		}
		if ((Spielfeld.spieler.x==GegnerKI.StartX)&&(Spielfeld.spieler.y<GegnerKI.StartY)){
			Fall=7;
		}
		if ((Spielfeld.spieler.x<GegnerKI.StartX)&&(Spielfeld.spieler.y==GegnerKI.StartY)){
			Fall=8;
		}
		if ((Spielfeld.spieler.x==GegnerKI.StartX)&&(Spielfeld.spieler.y==GegnerKI.StartY)){
			Fall=9;
		}
	}

	public static void lauf(){
		if (laufen){
			checkFall();
			nächsterSchritt();
			StartX+=nächsterSchrittX;
			StartY+=nächsterSchrittY;
			Kollision();
		}
	}
	
	public static boolean linksfrei(){
		boolean frei=false;
		for (int i=1;i<16;i++){
			if((Spielfeld.getBlockID(GegnerKI.StartX-1, GegnerKI.StartY+15+i)!=1)&&(Spielfeld.getBlockID(GegnerKI.StartX-1, GegnerKI.StartY+15+i)!=2)&&(Spielfeld.getBlockID(GegnerKI.StartX-1, GegnerKI.StartY+15+i)!=4)&&(Spielfeld.getBlockID(GegnerKI.StartX-1, GegnerKI.StartY+15+i)!=41)&&(Spielfeld.getBlockID(GegnerKI.StartX-1, GegnerKI.StartY+15+i)!=42)){
				frei=true;
			} else frei=false; break;
		}
		return frei;
		
	}
	public static boolean rechtsfrei(){
		boolean frei=false;
		for (int i=1;i<16;i++){
			if((Spielfeld.getBlockID(GegnerKI.StartX+32, GegnerKI.StartY+15+i)!=1)&&(Spielfeld.getBlockID(GegnerKI.StartX+32, GegnerKI.StartY+15+i)!=2)&&(Spielfeld.getBlockID(GegnerKI.StartX+32, GegnerKI.StartY+15+i)!=4)&&(Spielfeld.getBlockID(GegnerKI.StartX+32, GegnerKI.StartY+15+i)!=41)&&(Spielfeld.getBlockID(GegnerKI.StartX+32, GegnerKI.StartY+15+i)!=42)){
				frei=true;
			} else frei=false; break;
		}
		return frei;
		
	}
	public static boolean obenfrei(){
		boolean frei=false;
		for (int i=1;i<29;i++){
			if((Spielfeld.getBlockID(GegnerKI.StartX+1+i, GegnerKI.StartY+15)!=1)&&(Spielfeld.getBlockID(GegnerKI.StartX+1+i, GegnerKI.StartY+15)!=2)&&(Spielfeld.getBlockID(GegnerKI.StartX+1+i, GegnerKI.StartY+15)!=4)&&(Spielfeld.getBlockID(GegnerKI.StartX+1+i, GegnerKI.StartY+15)!=41)&&(Spielfeld.getBlockID(GegnerKI.StartX+1+i, GegnerKI.StartY+15)!=42)){
				frei=true;
			} else frei=false; break;
		}
		return frei;
		
	}
	public static boolean untenfrei(){
		boolean frei=false;
		for (int i=1;i<29;i++){
			if((Spielfeld.getBlockID(GegnerKI.StartX+1+i, GegnerKI.StartY+32)!=1)&&(Spielfeld.getBlockID(GegnerKI.StartX+1+i, GegnerKI.StartY+32)!=2)&&(Spielfeld.getBlockID(GegnerKI.StartX+1+i, GegnerKI.StartY+32)!=4)&&(Spielfeld.getBlockID(GegnerKI.StartX+1+i, GegnerKI.StartY+32)!=41)&&(Spielfeld.getBlockID(GegnerKI.StartX+1+i, GegnerKI.StartY+32)!=42)){
				frei=true;
			} else frei=false; break;
		}
		return frei;
	}
	
	
	//Sagt was passieren soll wenn ein Gegener mit dem Spieler zusammen trifft
	public static void Kollision(){
		if( (GegnerKI.StartX+26 >= Spielfeld.spieler.x)&&(GegnerKI.StartX <= Spielfeld.spieler.x+26)&&
			(GegnerKI.StartY+26 >= Spielfeld.spieler.y)&&(GegnerKI.StartY <= Spielfeld.spieler.y+26)){	
			counter_kollision ++;
			if ((spieler.ruestung>0)&&(counter_kollision == 4)){	//counter_kollision damit nicht zuviel Leben abgezogen wird
				spieler.ruestung-=Schaden;
				counter_kollision = 0;
			} else if((spieler.ruestung<=0)&&(counter_kollision == 4)) {
				spieler.leben -= Schaden;
				counter_kollision = 0;
			}
		}
	}
	
/*
 * Prüft ob er in einer mauer steht dann geht er nicht weiter
 * sonst geht er nächsten schritt
 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

