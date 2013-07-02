package propra2013.Gruppe54;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Schuss_Spieler extends Rectangle {

	private static final long serialVersionUID = 1L;
	public boolean setPos=false;
	public double StartX,StartY;
	public double speed = 1.5;
	public int schaden = 20;
	public boolean sichtbar=false;
	public boolean checkRichtung = false;
	public int Richtung = 0;
	public Spieler spieler;
	
	public Schuss_Spieler(Spieler spieler) {
		setBounds((int)StartX,(int)StartY,32,32);
		this.sichtbar=false;
		this.spieler = spieler;
	}

	/**
	 * setzt die Koordinaten des Schusses auf die des Spielers
	 */
	public void setPos(){
		if(setPos == false){
			StartX = spieler.x;
			StartY = spieler.y;
			setPos=true;
		}
	}
	
	/**
	 * Bestimmt den Schaden an Hand der Erfahrung des Spielers
	 * @param spieler
	 */
	public void setSchaden(Spieler spieler){
		if(spieler.xp>=275){
			schaden=45;
		} else
		if (spieler.xp>=225){
			schaden=40;
		} else
		if(spieler.xp>=175){
			schaden=35;
		} else 
		if(spieler.xp>=125){
			schaden=30;
		} else
		if(spieler.xp>=75){
			schaden=25;
		}
	}
	/**
	 * Zeichnet den Schuss
	 */
	public void draw(Graphics g){
		g.drawImage(new ImageIcon("pics/schuss1.png").getImage(), (int)StartX, (int)StartY, 32, 32, null); 
	}
	/**
	 * prüft in welche Richtung der Spieler gerade schaut
	 * @return	Integer-Wert für die Richtung
	 */
	public int checkRichtung(){
		if (spieler.rechts==true){
			return 1;
		} else if(spieler.links==true){
			return 3;
		} else if (spieler.hoch==true){
			return 4;
		} else if (spieler.runter==true){
			return 2;
		} else {
			return 0;
		}
	}
		
	/**
	 * prüft an 4 Punkten ob sich dort ein Objekt befindet durch das der Schuss nicht fliegen darf
	 * @param ID des Blocks der geprüft werden soll
	 * @return true, wenn der Weg "frei" ist und false wenn nicht
	 */
	public boolean check(int ID){
		if((Spielfeld.getBlockID(StartX+10,StartY+27)!=ID)&& //unten links
			(Spielfeld.getBlockID(StartX+25,StartY+27)!=ID)&& //unten rechts
			(Spielfeld.getBlockID(StartX+10,StartY+30)!=ID)&& //oben links
			(Spielfeld.getBlockID(StartX+20,StartY+30)!=ID)&&//oben rechts
			(StartX>0)&&(StartX<Raum.worldWidth*Raum.blockSize)&&(StartY>0)&&(StartY<Raum.worldHeight*Raum.blockSize)){ 
			return true;
		} else { 
			return false;
		}
	}
	/**
	 * Bewegung des Schusses
	 */
	public void Schuss(){
		if(Spielfeld.counter_schuss == 0){
			Richtung = checkRichtung();
		}
		if ((Richtung==1)&&check(1)&&check(2)&&check(4)&&check(6)&&check(10)&&check(15)&&check(41)&&check(42)&&check(43)&&check(51)){
			StartX+=1*speed;
			Kollision();
		} else if(Richtung==1){
			sichtbar=false;
		} else if((Richtung==2)&&check(1)&&check(2)&&check(4)&&check(6)&&check(10)&&check(15)&&check(41)&&check(42)&&check(43)&&check(51)){
			StartY+=1*speed;
			Kollision();
		} else if(Richtung==2){
			sichtbar=false;
		} else if((Richtung==3)&&check(1)&&check(2)&&check(4)&&check(6)&&check(10)&&check(15)&&check(41)&&check(42)&&check(43)&&check(51)){
			StartX-=1*speed;
			Kollision();
		} else if (Richtung==3){
			sichtbar=false;	
		} else if((Richtung==4)&&check(1)&&check(2)&&check(4)&&check(6)&&check(10)&&check(15)&&check(41)&&check(42)&&check(43)&&check(51)){
			StartY-=1*speed;
			Kollision();
		} else if (Richtung==4){
			sichtbar=false;
		}
	}
	/**
	 * Kollisionsabfrage		
	 */
	public void Kollision(){
		//Endgegner
		if((StartX+31 >= Endgegner.StartX)&&(StartX <= Endgegner.StartX+31)&&
		   (StartY+31 >= Endgegner.StartY)&&(StartY <= Endgegner.StartY+31)){		
			sichtbar=false;
			Endgegner.leben-=schaden;
			if(Spielfeld.multiplayer){
				Spielfeld.client.send(Spielfeld.client.socket.getLocalPort()+";gegner;4;"+schaden);
			}
		//GegnerRL
		} else if((StartX+31 >= Spielfeld.gegnerRL.StartX)&&(StartX <= Spielfeld.gegnerRL.StartX+31)  &&
		      (StartY+31 >= Spielfeld.gegnerRL.StartY)&&(StartY <= Spielfeld.gegnerRL.StartY+31)){			
			sichtbar=false;
		//GegnerOU
		} else if((StartX+31 >= Spielfeld.gegnerOU.StartX)&&(StartX <= Spielfeld.gegnerOU.StartX+31)&&
        	  (StartY+31 >= Spielfeld.gegnerOU.StartY)&&(StartY <= Spielfeld.gegnerOU.StartY+31)){		
			sichtbar=false;	
			Spielfeld.gegnerOU.leben-=schaden;
			if(Spielfeld.multiplayer){
				Spielfeld.client.send(Spielfeld.client.socket.getLocalPort()+";gegner;2;"+schaden);
			}
		//Schuss_Endgegner
		}else if((StartX+31 >= Spielfeld.schuss_endgegner.StartX)&&(StartX <= Spielfeld.schuss_endgegner.StartX+31)  &&
			 (StartY+31 >= Spielfeld.schuss_endgegner.StartY)&&(StartY <= Spielfeld.schuss_endgegner.StartY+31)){		
			sichtbar=false;	
			Spielfeld.schuss_endgegner.StartX=(int)Endgegner.StartX;
			Spielfeld.schuss_endgegner.StartY=(int)Endgegner.StartY;
		//Bewegliche Falle
		} else if((StartX+31 >= Falle.StartX)&&(StartX <= Falle.StartX+31)  &&
			 (StartY+31 >= Falle.StartY)&&(StartY <= Falle.StartY+31)){		
			sichtbar=false;	
		//GegnerKI
		} else if((StartX+31 >= Spielfeld.gegnerKI.StartX)&&(StartX <= Spielfeld.gegnerKI.StartX+31)  &&
			  (StartY+31 >= Spielfeld.gegnerKI.StartY)&&(StartY <= Spielfeld.gegnerKI.StartY+31)){		
			sichtbar=false;	
		}
	}
	public static void main(String[] args) {}

}
