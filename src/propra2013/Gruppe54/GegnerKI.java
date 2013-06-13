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
	public static int a=0; //zum prüfen in check
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
		} else 
			if(Spielfeld.current_lvl==2){
				StartLeben=200;
				Faktor=6;
				Schaden=2;
			} else
				if(Spielfeld.current_lvl==3){
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
	}}
	
	//Funktion die den nächsten schritt berechnet
	public static void nächsterSchritt(){
		if (Fall==1){
			nächsterSchrittX=-1;
			nächsterSchrittY=1;
		} else
		if (Fall==2){
			nächsterSchrittX=1;
			nächsterSchrittY=1;
		} else
		if (Fall==3){
			nächsterSchrittX=1;
			nächsterSchrittY=-1;
		} else
		if (Fall==4){
			nächsterSchrittX=-1;
			nächsterSchrittY=-1;
		} else
		if (Fall==5){
			nächsterSchrittY=1;
			nächsterSchrittX=0;
		}else
		if (Fall==6){
			nächsterSchrittX=1;
			nächsterSchrittY=0;
		}else
		if (Fall==7){
			nächsterSchrittX=0;
			nächsterSchrittY=-1;
		}else
		if (Fall==8){
			nächsterSchrittX=-1;
			nächsterSchrittY=0;
		}else
		if (Fall==9){
			nächsterSchrittX=0;
			nächsterSchrittY=0;
		}
		
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
	
	//Gibt aus ob der weg, je nach fall frei ist
	public static void check(int ID, int Case){
		a=0;
		//Spieler steht unten links vom Gegner
		if(Case==1){ 
		  for (int i=1;i<33;i++){
			//senkrecht links nach unten verschoben
			if (Spielfeld.getBlockID(GegnerKI.StartX-1, GegnerKI.StartY+i)!=ID){
				if(a==0){
				frei=true;
				} 
			} else a=1;
			//waagerecht unten nach links verschoben
			if (Spielfeld.getBlockID(GegnerKI.StartX-2+i, GegnerKI.StartY+32)!=ID){
				if(a==0){
					frei=true;
				} 
			} else a=1;
		  }	
		} else
		
		//Spieler steht unten rechts
		if(Case==2){
		   for (int i=1;i<33;i++){
			 //senkrecht rechts nach unten verschoben
			 if(Spielfeld.getBlockID(GegnerKI.StartX+32, GegnerKI.StartY+i)!=ID){
				 if(a==0){
					 frei=true;
				 }
			 } else a=1;
			 //waagerecht unten nach rechts verschoben
			 if (Spielfeld.getBlockID(GegnerKI.StartX+i, GegnerKI.StartY+32)!=ID){
					if(a==0){
						frei=true;
					} 
				} else a=1;
		   }
		} else
		
		//Spieler steht oben rechts
		if(Case==3){
		  for (int i=1;i<33;i++){
			  //senkrecht rechts nach oben verschoben
			  if(Spielfeld.getBlockID(GegnerKI.StartX+32, GegnerKI.StartY-2+i)!=ID){
				if(a==0){
				 frei=true;
				}
			  } else a=1;
			  //waagerecht oben nach rechts verschoben
			  if (Spielfeld.getBlockID(GegnerKI.StartX+i, GegnerKI.StartY-1)!=ID){
				  if(a==0){
					frei=true;
				  } 
			  } else a=1;
		   }
		} else
		
		//Spieler steht oben links
		if (Case==4){
			for (int i=1;i<33;i++){
				 //senkrecht links nach oben verschoben
				 if(Spielfeld.getBlockID(GegnerKI.StartX-1, GegnerKI.StartY-2+i)!=ID){
					 if(a==0){
					   frei=true;
					 }
				 } else a=1;
				 //waagerecht oben nach links verschoben
				 if (Spielfeld.getBlockID(GegnerKI.StartX-2+i, GegnerKI.StartY-1)!=ID){
					if(a==0){
				   	frei=true;
					} 
				} else a=1;
			}	
		} else
			
		//Spieler steht unten mitte
		if (Case==5){
		  for (int i=1;i<33;i++){
			 //waagerecht unten
			 if (Spielfeld.getBlockID(GegnerKI.StartX-+i, GegnerKI.StartY+32)!=ID){
				if(a==0){
			   	frei=true;
				} 
			 } else a=1;
		   } 
		} else
			
		//Spieler steht rechts mitte
		if(Case==6){
		  for (int i=1;i<33;i++){
			 //senkrecht rechts
			 if (Spielfeld.getBlockID(GegnerKI.StartX+32, GegnerKI.StartY-1+i)!=ID){
				if(a==0){
			   	frei=true;
				} 
			 }else a=1;
		   } 
		} else
		
		//Spieler steht oben mitte
		if(Case==7){
		  for (int i=1;i<33;i++){
			 //waagerecht oben
			 if (Spielfeld.getBlockID(GegnerKI.StartX-1+i, GegnerKI.StartY-1)!=ID){
				if(a==0){
			   	frei=true;
				} 
			 } else a=1;
		   } 	
		} else
			
		//Spieler steht links mitte
		if(Case==8){
		  for (int i=1;i<33;i++){
			 //senkrecht links
			 if (Spielfeld.getBlockID(GegnerKI.StartX-1, GegnerKI.StartY-1+i)!=ID){
				if(a==0){
			   	frei=true;
				} 
			 } else a=1;
		   } 	
		} else
		
		//Spieler steht da wo Gegner steht
		if(Case==9){
			frei=true;
			/*
			 * Hier soll er sich nicht bewegen
			 * dehalb brauchen wir auch nichts prüfen
			 */
		}
		if(a==1){
			frei=false;
		}
	}
	

	
	//Gibt an wie sich der Gegner bewegt
	public static void lauf(){
		if(laufen){
		checkFall();	//wo ist der Spieler?
		check(1,Fall);		//Kann ich in seine Richtung gehen?
		nächsterSchritt();
		if (frei){
			StartX+=nächsterSchrittX;
			StartY+=nächsterSchrittY;
		}
		Kollision();
		
		//System.out.println(frei+" "+Fall+" "+nächsterSchrittX+nächsterSchrittY+StartX+StartY+" "+aktiv);
		}
	}
	

	
	//Sagt was passieren soll wenn ein Gegener mit dem Spieler zusammen trifft
	public static void Kollision(){

			if( (GegnerKI.StartX+31 >= Spielfeld.spieler.x)     && 
				(GegnerKI.StartX <= Spielfeld.spieler.x+31)  &&
				(GegnerKI.StartY+31 >= Spielfeld.spieler.y)  &&
				(GegnerKI.StartY <= Spielfeld.spieler.y+31)){		
				
				if (spieler.ruestung>0){
					spieler.ruestung-=Schaden;
				} else spieler.leben -= Schaden;
				
			}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

