package propra2013.Gruppe54;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Schuss2_Spieler extends Rectangle {

	private static final long serialVersionUID = 1L;
	public boolean setPos=false;
	public double StartX,StartY;
	public double speed = 0.7;
	public int schaden = 50;
	public boolean sichtbar=false;
	public boolean checkRichtung = false;
	public int Richtung = 0;

	
	public Schuss2_Spieler() {
		setBounds((int)StartX,(int)StartY,32,32);
		sichtbar=false;
	}

	//schaut nach wo der Spieler steht 
		public void setPos(){
			if(setPos == false){
			StartX=Spielfeld.spieler.x;
			StartY=Spielfeld.spieler.y;
			setPos=true;
			}
		}
		
	//Zeichnet einen Schuss
	public void draw(Graphics g){
		g.drawImage(new ImageIcon("pics/schuss_feuer_1.png").getImage(), (int)StartX, (int)StartY, 32, 32, null); 
	}
	public int checkRichtung(){
		if (Spielfeld.spieler.rechts==true){
			return 1;
		} else if(Spielfeld.spieler.links==true){
			return 3;
		} else if (Spielfeld.spieler.hoch==true){
			return 4;
		} else if (Spielfeld.spieler.runter==true){
			return 2;
		} else {
			return 0;
		}
	}
		
		//prüft an 4 Punkten ob sich dort ein Objekt befindet durch das der Schuss nicht fliegen darf
		public boolean check(int ID){
			if((Spielfeld.getBlockID(StartX+10,StartY+27)!=ID)&& //unten links
				(Spielfeld.getBlockID(StartX+25,StartY+27)!=ID)&& //unten rechts
				(Spielfeld.getBlockID(StartX+10,StartY+30)!=ID)&& //oben links
				(Spielfeld.getBlockID(StartX+20,StartY+30)!=ID)&&//oben rechts
				(StartX>0)&&(StartX<800)&&(StartY>0)&&(StartY<480)){ 
				return true;
			} else { 
				return false;
			}
		}
		//Wie der Schuss sich bewegt wenn der Spieler nach rechts schaut
		public void Schuss(){
			if(Spielfeld.counter_schuss2 == 0){
				Richtung = checkRichtung();
			}
			if ((Richtung==1)&&check(1)&&check(2)&&check(4)&&check(6)&&check(10)&&check(15)&&check(41)&&check(42)&&check(43)){
				StartX+=1*speed;
				Kollision();
			} else if(Richtung==1){
				sichtbar=false;
			} else if((Richtung==2)&&check(1)&&check(2)&&check(4)&&check(6)&&check(10)&&check(15)&&check(41)&&check(42)&&check(43)){
				StartY+=1*speed;
				Kollision();
			} else if(Richtung==2){
				sichtbar=false;
			} else if((Richtung==3)&&check(1)&&check(2)&&check(4)&&check(6)&&check(10)&&check(15)&&check(41)&&check(42)&&check(43)){
				StartX-=1*speed;
				Kollision();
			} else if (Richtung==3){
				sichtbar=false;	
			} else if((Richtung==4)&&check(1)&&check(2)&&check(4)&&check(6)&&check(10)&&check(15)&&check(41)&&check(42)&&check(43)){
				StartY-=1*speed;
				Kollision();
			} else if (Richtung==4){
				sichtbar=false;
			}
		}
			
	public void Kollision(){
		//Endgegner
		if((StartX+31 >= Endgegner.StartX)&&(StartX <= Endgegner.StartX+31)&&
		   (StartY+31 >= Endgegner.StartY)&&(StartY <= Endgegner.StartY+31)){		
			sichtbar=false;
		//GegnerRL
		} else if((StartX+31 >= Spielfeld.gegnerRL.StartX)&&(StartX <= Spielfeld.gegnerRL.StartX+31)  &&
		      (StartY+31 >= Spielfeld.gegnerRL.StartY)&&(StartY <= Spielfeld.gegnerRL.StartY+31)){			
			sichtbar=false;
		//GegnerOU
		} else if((StartX+31 >= Spielfeld.gegnerOU.StartX)&&(StartX <= Spielfeld.gegnerOU.StartX+31)&&
        	  (StartY+31 >= Spielfeld.gegnerOU.StartY)&&(StartY <= Spielfeld.gegnerOU.StartY+31)){		
			sichtbar=false;	
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
			Spielfeld.gegnerKI.leben-=schaden;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
