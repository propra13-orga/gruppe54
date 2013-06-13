package propra2013.Gruppe54;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Schuss_Spieler extends Rectangle {

	private static final long serialVersionUID = 1L;
	public static boolean checkPos=false;
	public static int StartX,StartY;
	public static boolean sichtbar=false;
	public static int Richtung=0;

	
	public Schuss_Spieler() {
		setBounds(StartX,StartY,32,32);
		sichtbar=false;
	}

	//schaut nach wo der Spieler steht 
		public static void checkPos(){
			StartX=(int)Spielfeld.spieler.x;
			StartY=(int)Spielfeld.spieler.y;
			checkPos=true;
		}
		
	//Zeichnet einen Schuss
	//muss noch ein anderes Bild eingefügt werden
		public void draw(Graphics g){
			g.drawImage(new ImageIcon("pics/schuss1.png").getImage(), StartX, StartY, 32, 32, null); 
		
		}
		public static void checkRichtung(){
			if (Spielfeld.spieler.rechts==true){
				Richtung=1;
			} else if(Spielfeld.spieler.links==true){
				Richtung=3;
			} else if (Spielfeld.spieler.hoch==true){
				Richtung=4;
			} else if (Spielfeld.spieler.runter==true){
				Richtung=2;
			}
		}
		
		//prüft an 4 Punkten ob sich dort ein Objekt befindet durch das der Schuss nicht fliegen darf
		public static boolean check(int ID){
			if((Spielfeld.getBlockID(Schuss_Spieler.StartX+10,Schuss_Spieler.StartY+27)!=ID)&& //unten links
				(Spielfeld.getBlockID(Schuss_Spieler.StartX+27,Schuss_Spieler.StartY+27)!=ID)&& //unten rechts
				(Spielfeld.getBlockID(Schuss_Spieler.StartX+10,Schuss_Spieler.StartY+27)!=ID)&& //oben links
				(Spielfeld.getBlockID(Schuss_Spieler.StartX+27,Schuss_Spieler.StartY+27)!=ID)){ //oben rechts
				return true;
			} else { 
				return false;
			}
		}
		//Wie der Schuss sich bewegt wenn der Spieler nach rechts schaut
		public static void SchussRechts(){
			if ((Richtung==1)&&(check(1))&&check(2)&&(check(4))&&(check(6)&&(check(10))&&(check(15))&&(check(41))&&(check(42))&&(check(43)))){
				StartX+=2;
				Kollision();
			} else if(Richtung==1){
				sichtbar=false;
			}
		}
		//Wie der Schuss sich bewegt wenn der Spieler nach links schaut
		public static void SchussLinks(){
			if((Richtung==3)&&(check(1))&&check(2)&&(check(4))&&(check(6)&&(check(10))&&(check(15))&&(check(41))&&(check(42))&&(check(43)))){
				StartX-=2;
				Kollision();
			} else if (Richtung==3){
				sichtbar=false;	
			}
		}
		//Wie der Schuss sich bewegt wenn der Spieler nach oben schaut
		public static void SchussOben(){
			if((Richtung==4)&&(check(1))&&check(2)&&(check(4))&&(check(6)&&(check(10))&&(check(15))&&(check(41))&&(check(42))&&(check(43)))){
				StartY-=2;
				Kollision();
			} else if (Richtung==4){
				sichtbar=false;
			}
		}
		//Wie der Schuss sich bewegt wenn der Spieler nach unten schaut
		public static void SchussUnten(){
			if((Richtung==2)&&(check(1))&&check(2)&&(check(4))&&(check(6)&&(check(10))&&(check(15))&&(check(41))&&(check(42))&&(check(43)))){
				StartY+=2;
				Kollision();
			} else if(Richtung==2){
				sichtbar=false;
			}
		}	

			
	public static void Kollision(){
		//Endgegner
		if((Schuss_Spieler.StartX+31 >= Endgegner.StartX)&&(Schuss_Spieler.StartX <= Endgegner.StartX+31)&&
		   (Schuss_Spieler.StartY+31 >= Endgegner.StartY)&&(Schuss_Spieler.StartY <= Endgegner.StartY+31)){		
			sichtbar=false;
			Endgegner.leben-=25;
		//GegnerRL
		} else if((Schuss_Spieler.StartX+31 >= GegnerRL.StartX)&&(Schuss_Spieler.StartX <= GegnerRL.StartX+31)  &&
		          (Schuss_Spieler.StartY+31 >= GegnerRL.StartY)&&(Schuss_Spieler.StartY <= GegnerRL.StartY+31)){			
			sichtbar=false;
			GegnerRL.leben-=25;
		//GegnerOU
		} else if((Schuss_Spieler.StartX+31 >= GegnerOU.StartX)&&(Schuss_Spieler.StartX <= GegnerOU.StartX+31)&&
        		  (Schuss_Spieler.StartY+31 >= GegnerOU.StartY)&&(Schuss_Spieler.StartY <= GegnerOU.StartY+31)){		
				sichtbar=false;	
				GegnerOU.leben-=25;
		//Schuss_Endgegner
		}else if((Schuss_Spieler.StartX+31 >= Schuss_Endgegner.StartX)&&(Schuss_Spieler.StartX <= Schuss_Endgegner.StartX+31)  &&
				 (Schuss_Spieler.StartY+31 >= Schuss_Endgegner.StartY)&&(Schuss_Spieler.StartY <= Schuss_Endgegner.StartY+31)){		
			sichtbar=false;	
			Schuss_Endgegner.StartX=Endgegner.StartX;
			Schuss_Endgegner.StartY=Endgegner.StartY;
		//Bewegliche Falle
		} else if((Schuss_Spieler.StartX+31 >= Falle.StartX)&&(Schuss_Spieler.StartX <= Falle.StartX+31)  &&
				 (Schuss_Spieler.StartY+31 >= Falle.StartY)&&(Schuss_Spieler.StartY <= Falle.StartY+31)){		
			sichtbar=false;	
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
