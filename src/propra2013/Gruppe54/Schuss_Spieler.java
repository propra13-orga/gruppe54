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
			StartX= (int)Spielfeld.spieler.x;
			StartY=(int)Spielfeld.spieler.y;
			checkPos=true;
		}
		
	//Zeichnet einen Schuss
	//muss noch ein anderes Bild eingefügt werden
		public void draw(Graphics g){
			g.drawImage(Frame.image2, StartX, StartY, 32, 32, null); 
		
		}
		public static void checkRichtung(){
			if (Spielfeld.spieler.rechts==true){
				Richtung=1;
			} else
				if(Spielfeld.spieler.links==true){
					Richtung=3;
				} else
					if (Spielfeld.spieler.hoch==true){
						Richtung=4;
					} else
						if (Spielfeld.spieler.runter==true){
							Richtung=2;
						}
		}
		
		//Wie der Schuss sich bewegt wenn der Spieler nach rechts schaut
		public static void SchussRechts(){
	       	 if ((Richtung==1)&&(Spielfeld.getBlockID(StartX+32,StartY+16)!=1)&&(Spielfeld.getBlockID(StartX+32,StartY+16)!=2)){
	       		 StartX+=1;
	       		 Kollision();
	       	 } else 
	       		 if(Richtung==1){
	       			 sichtbar=false; 			
	       		 }
		}
		
		//Wie der Schuss sich bewegt wenn der Spieler nach links schaut
		public static void SchussLinks(){
	       	 if((Richtung==3)&&(Spielfeld.getBlockID(StartX,StartY+16)!=1)&&(Spielfeld.getBlockID(StartX+32,StartY+16)!=2)){
	       		 StartX-=1;
	       		 Kollision();
	       	 } else 
	       		 if (Richtung==3){
	       			 sichtbar=false;			
	       		 }
		}
		
		//Wie der Schuss sich bewegt wenn der Spieler nach oben schaut
		public static void SchussOben(){
	       	 if((Richtung==4)&&(Spielfeld.getBlockID(StartX+16,StartY)!=1)&&(Spielfeld.getBlockID(StartX+32,StartY+16)!=2)){
	       		 StartY-=1;
	       		 Kollision();
	       	 } else 
	       		 if (Richtung==4){
	       			 sichtbar=false;
	       		 }
		}
		
		//Wie der Schuss sich bewegt wenn der Spieler nach unten schaut
		public static void SchussUnten(){
	       	 if((Richtung==2)&&(Spielfeld.getBlockID(StartX+16,StartY+32)!=1)&&(Spielfeld.getBlockID(StartX+32,StartY+16)!=2)){
	       		 StartY+=1;
	       		 Kollision();
	       	 } else 
	       		 if(Richtung==2){
	       			 sichtbar=false;
	       		 }
		}
			
			
	public static void Kollision(){
		//Kollision funktioniert noch nicht
		if( (Schuss_Spieler.StartX+31 >= Endgegner.StartX)     && 
			(Schuss_Spieler.StartX <= Endgegner.StartX+31)  &&
			(Schuss_Spieler.StartY+31 >= Endgegner.StartY)  &&
			(Schuss_Spieler.StartY <= Endgegner.StartY+31)){		
			
			
			Endgegner.leben-=1;
			
		} else
		
		if( (Schuss_Spieler.StartX+31 >= GegnerRL.StartX)     && 
				(Schuss_Spieler.StartX <= GegnerRL.StartX+31)  &&
				(Schuss_Spieler.StartY+31 >= GegnerRL.StartY)  &&
				(Schuss_Spieler.StartY <= GegnerRL.StartY+31)){		
				
			
			GegnerRL.aktiv=false;
			
			} else
		if( (Schuss_Spieler.StartX+31 >= GegnerOU.StartX)     && 
				(Schuss_Spieler.StartX <= GegnerOU.StartX+31)  &&
				(Schuss_Spieler.StartY+31 >= GegnerOU.StartY)  &&
				(Schuss_Spieler.StartY <= GegnerOU.StartY+31)){		
				
					
				GegnerOU.aktiv=false;
			}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
