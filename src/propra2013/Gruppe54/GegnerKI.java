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
		} else
		if(Fall==10){
			nächsterSchrittX=0;
			nächsterSchrittY=1;
		}else
		if(Fall==11){
			nächsterSchrittX=-1;
			nächsterSchrittY=0;
		} else
		if(Fall==12){
			nächsterSchrittX=1;
			nächsterSchrittY=0;
		}else
		if(Fall==13){
			nächsterSchrittX=0;
			nächsterSchrittY=-1;
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
	public static void checkfree(int Case){
		a=0;zehn=0;elf=0;zwölf=0;dreizehn=0;
		switch (Case){
		//Spieler steht unten links vom Gegner
		case 1: 
		  for (int i=1;i<17;i++){
			//senkrecht links nach unten verschoben
			if ((Spielfeld.getBlockID(GegnerKI.StartX-1, GegnerKI.StartY+15+i)!=1)&&(Spielfeld.getBlockID(GegnerKI.StartX-1, GegnerKI.StartY+15+i)!=4)&&(Spielfeld.getBlockID(GegnerKI.StartX-1, GegnerKI.StartY+15+i)!=41)&&(Spielfeld.getBlockID(GegnerKI.StartX-1, GegnerKI.StartY+15+i)!=42)){
				if(a==0){
				frei=true;
				} 
			} else a=1;
		  }
		  for (int i=1;i<33;i++){
			//waagerecht unten nach links verschoben
			if ((Spielfeld.getBlockID(GegnerKI.StartX-2+i, GegnerKI.StartY+32)!=1)&&(Spielfeld.getBlockID(GegnerKI.StartX-2+i, GegnerKI.StartY+32)!=4)&&(Spielfeld.getBlockID(GegnerKI.StartX-2+i, GegnerKI.StartY+32)!=41)&&(Spielfeld.getBlockID(GegnerKI.StartX-2+i, GegnerKI.StartY+32)!=42)){
				if(a==0){
					frei=true;
				} 
			} else a=1;
		  }	
		  if (a==1){//wenn diagonal nicht geht alternativen prüfen
			for (int i=1;i<33;i++){//ist unten frei?
				if((Spielfeld.getBlockID(GegnerKI.StartX-1+i, GegnerKI.StartY+32)!=1)&&(Spielfeld.getBlockID(GegnerKI.StartX-1+i, GegnerKI.StartY+32)!=4)&&(Spielfeld.getBlockID(GegnerKI.StartX-1+i, GegnerKI.StartY+32)!=41)&&(Spielfeld.getBlockID(GegnerKI.StartX-1+i, GegnerKI.StartY+32)!=42)){
					zehn=1;
					a=0;
					frei=true;
				} else 
					for(int j=1;j<18;j++){//ist links frei?
					if((Spielfeld.getBlockID(GegnerKI.StartX-1, GegnerKI.StartY+14+j)!=1)&&(Spielfeld.getBlockID(GegnerKI.StartX-1, GegnerKI.StartY+14+j)!=4)&&(Spielfeld.getBlockID(GegnerKI.StartX-1, GegnerKI.StartY+14+j)!=41)&&(Spielfeld.getBlockID(GegnerKI.StartX-1, GegnerKI.StartY+14+j)!=42)){
						elf=1;
						a=0;
						frei=true;
					}
			}
		  }}
		  
		  break;
		
		
		//Spieler steht unten rechts
		case 2:
		   for (int i=1;i<17;i++){
			 //senkrecht rechts nach unten verschoben
			 if((Spielfeld.getBlockID(GegnerKI.StartX+32, GegnerKI.StartY+15+i)!=1)&&(Spielfeld.getBlockID(GegnerKI.StartX+32, GegnerKI.StartY+15+i)!=4)&&(Spielfeld.getBlockID(GegnerKI.StartX+32, GegnerKI.StartY+15+i)!=41)&&(Spielfeld.getBlockID(GegnerKI.StartX+32, GegnerKI.StartY+15+i)!=42)){
				 if(a==0){
					 frei=true;
				 }
			 } else a=1;}
		   for (int i=1;i<33;i++){
			 //waagerecht unten nach rechts verschoben
			 if ((Spielfeld.getBlockID(GegnerKI.StartX+i, GegnerKI.StartY+32)!=1)&&(Spielfeld.getBlockID(GegnerKI.StartX+i, GegnerKI.StartY+32)!=4)&&(Spielfeld.getBlockID(GegnerKI.StartX+i, GegnerKI.StartY+32)!=41)&&(Spielfeld.getBlockID(GegnerKI.StartX+i, GegnerKI.StartY+32)!=42)){
					if(a==0){
						frei=true;
					} 
				} else a=1;
		   }
		   if (a==1){//wenn diagonal nicht geht alternativen prüfen
				for (int i=1;i<33;i++){//ist unten frei?
					if((Spielfeld.getBlockID(GegnerKI.StartX-1+i, GegnerKI.StartY+32)!=1)&&(Spielfeld.getBlockID(GegnerKI.StartX-1+i, GegnerKI.StartY+32)!=4)&&(Spielfeld.getBlockID(GegnerKI.StartX-1+i, GegnerKI.StartY+32)!=41)&&(Spielfeld.getBlockID(GegnerKI.StartX-1+i, GegnerKI.StartY+32)!=42)){
						zehn=1;
						a=0;
						frei=true;
					} else 
						for(int j=1;j<18;j++){//ist rechts frei?
						if((Spielfeld.getBlockID(GegnerKI.StartX+32, GegnerKI.StartY+14+j)!=1)&&(Spielfeld.getBlockID(GegnerKI.StartX+32, GegnerKI.StartY+14+j)!=4)&&(Spielfeld.getBlockID(GegnerKI.StartX+32, GegnerKI.StartY+14+j)!=41)&&(Spielfeld.getBlockID(GegnerKI.StartX+32, GegnerKI.StartY+14+j)!=42)){
							zwölf=1;
							a=0;
							frei=true;
						}}
				}
			}
		   break;
		
		//Spieler steht oben rechts
		case 3:
		  for (int i=1;i<18;i++){
			  //senkrecht rechts nach oben verschoben
			  if((Spielfeld.getBlockID(GegnerKI.StartX+32, GegnerKI.StartY+13+i)!=1)&&(Spielfeld.getBlockID(GegnerKI.StartX+32, GegnerKI.StartY+13+i)!=4)&&(Spielfeld.getBlockID(GegnerKI.StartX+32, GegnerKI.StartY+13+i)!=41)&&(Spielfeld.getBlockID(GegnerKI.StartX+32, GegnerKI.StartY+13+i)!=42)){
				if(a==0){
				 frei=true;
				}
			  } else a=1;}
		  for(int i=1;i<33;i++){
			  //waagerecht oben nach rechts verschoben
			  if ((Spielfeld.getBlockID(GegnerKI.StartX+i, GegnerKI.StartY+14)!=1)&&(Spielfeld.getBlockID(GegnerKI.StartX+i, GegnerKI.StartY+14)!=4)&&(Spielfeld.getBlockID(GegnerKI.StartX+i, GegnerKI.StartY+14)!=41)&&(Spielfeld.getBlockID(GegnerKI.StartX+i, GegnerKI.StartY+14)!=42)){
				  if(a==0){
					frei=true;
				  } 
			  } else a=1;
		   }
		   if (a==1){//wenn diagonal nicht geht alternativen prüfen
				for (int i=1;i<33;i++){//ist oben frei?
					if((Spielfeld.getBlockID(GegnerKI.StartX-1+i, GegnerKI.StartY+14)!=1)&&(Spielfeld.getBlockID(GegnerKI.StartX-1+i, GegnerKI.StartY+14)!=4)&&(Spielfeld.getBlockID(GegnerKI.StartX-1+i, GegnerKI.StartY+14)!=41)&&(Spielfeld.getBlockID(GegnerKI.StartX-1+i, GegnerKI.StartY+14)!=42)){
						dreizehn=1;
						a=0;
						frei=true;
					} else 
						for(int j=1;j<18;j++){//ist rechts frei?
						if((Spielfeld.getBlockID(GegnerKI.StartX-1, GegnerKI.StartY+14+j)!=1)&&(Spielfeld.getBlockID(GegnerKI.StartX-1, GegnerKI.StartY+14+j)!=4)&&(Spielfeld.getBlockID(GegnerKI.StartX-1, GegnerKI.StartY+14+j)!=41)&&(Spielfeld.getBlockID(GegnerKI.StartX-1, GegnerKI.StartY+14+j)!=42)){
							zwölf=1;
							a=0;
							frei=true;
						}}
				}
			}
		  break;
		
		
		//Spieler steht oben links
		case 4:
			for (int i=1;i<18;i++){
				 //senkrecht links nach oben verschoben
				 if((Spielfeld.getBlockID(GegnerKI.StartX-1, GegnerKI.StartY+13+i)!=1)&&(Spielfeld.getBlockID(GegnerKI.StartX-1, GegnerKI.StartY+13+i)!=4)&&(Spielfeld.getBlockID(GegnerKI.StartX-1, GegnerKI.StartY+13+i)!=41)&&(Spielfeld.getBlockID(GegnerKI.StartX-1, GegnerKI.StartY+13+i)!=42)){
					 if(a==0){
					   frei=true;
					 }
				 } else a=1;}
			for (int i=1;i<33;i++){
				 //waagerecht oben nach links verschoben
				 if ((Spielfeld.getBlockID(GegnerKI.StartX-2+i, GegnerKI.StartY+14)!=1)&&(Spielfeld.getBlockID(GegnerKI.StartX-2+i, GegnerKI.StartY+14)!=4)&&(Spielfeld.getBlockID(GegnerKI.StartX-2+i, GegnerKI.StartY+14)!=41)&&(Spielfeld.getBlockID(GegnerKI.StartX-2+i, GegnerKI.StartY+14)!=42)){
					if(a==0){
				   	frei=true;
					} 
				} else a=1;
			}
		   if (a==1){//wenn diagonal nicht geht alternativen prüfen
				for (int i=1;i<33;i++){//ist oben frei?
					if((Spielfeld.getBlockID(GegnerKI.StartX-1+i, GegnerKI.StartY+14)!=1)&&(Spielfeld.getBlockID(GegnerKI.StartX-1+i, GegnerKI.StartY+14)!=4)&&(Spielfeld.getBlockID(GegnerKI.StartX-1+i, GegnerKI.StartY+14)!=41)&&(Spielfeld.getBlockID(GegnerKI.StartX-1+i, GegnerKI.StartY+14)!=42)){
						dreizehn=1;
						a=0;
						frei=true;
					} else 
						for(int j=1;j<18;j++){//ist links frei?
						if((Spielfeld.getBlockID(GegnerKI.StartX-1, GegnerKI.StartY+14+j)!=1)&&(Spielfeld.getBlockID(GegnerKI.StartX-1, GegnerKI.StartY+14+j)!=4)&&(Spielfeld.getBlockID(GegnerKI.StartX-1, GegnerKI.StartY+14+j)!=41)&&(Spielfeld.getBlockID(GegnerKI.StartX-1, GegnerKI.StartY+14+j)!=42)){
							elf=1;
							a=0;
							frei=true;
						}}
				}
			}
		   break;
			
		//Spieler steht unten mitte
		case 5:
		  for (int i=1;i<33;i++){
			 //waagerecht unten
			 if ((Spielfeld.getBlockID(GegnerKI.StartX-1+i, GegnerKI.StartY+32)!=1)&&(Spielfeld.getBlockID(GegnerKI.StartX-1+i, GegnerKI.StartY+32)!=4)&&(Spielfeld.getBlockID(GegnerKI.StartX-1+i, GegnerKI.StartY+32)!=41)&&(Spielfeld.getBlockID(GegnerKI.StartX-1+i, GegnerKI.StartY+32)!=42)){
				if(a==0){
			   	frei=true;
				} 
			 } else a=1;
		   } 
		break;
			
		//Spieler steht rechts mitte
		case 6:
		  for (int i=1;i<18;i++){
			 //senkrecht rechts
			 if ((Spielfeld.getBlockID(GegnerKI.StartX+32, GegnerKI.StartY+14+i)!=1)&&(Spielfeld.getBlockID(GegnerKI.StartX+32, GegnerKI.StartY+14+i)!=4)&&(Spielfeld.getBlockID(GegnerKI.StartX+32, GegnerKI.StartY+14+i)!=5)&&(Spielfeld.getBlockID(GegnerKI.StartX+32, GegnerKI.StartY+14+i)!=6)){
				if(a==0){
			   	frei=true;
				} 
			 }else a=1;
		   } 
		break;
		
		//Spieler steht oben mitte
		case 7:
		  for (int i=1;i<33;i++){
			 //waagerecht oben
			 if ((Spielfeld.getBlockID(GegnerKI.StartX-1+i, GegnerKI.StartY+14)!=1)&&(Spielfeld.getBlockID(GegnerKI.StartX-1+i, GegnerKI.StartY+14)!=4)&&(Spielfeld.getBlockID(GegnerKI.StartX-1+i, GegnerKI.StartY+14)!=41)&&(Spielfeld.getBlockID(GegnerKI.StartX-1+i, GegnerKI.StartY+14)!=42)){
				if(a==0){
			   	frei=true;
				} 
			 } else a=1;
		   } 	
		break;
			
		//Spieler steht links mitte
		case 8:
		  for (int i=1;i<18;i++){
			 //senkrecht links
			 if ((Spielfeld.getBlockID(GegnerKI.StartX-1, GegnerKI.StartY+14+i)!=1)&&(Spielfeld.getBlockID(GegnerKI.StartX-1, GegnerKI.StartY+14+i)!=4)&&(Spielfeld.getBlockID(GegnerKI.StartX-1, GegnerKI.StartY+14+i)!=41)&&(Spielfeld.getBlockID(GegnerKI.StartX-1, GegnerKI.StartY+14+i)!=42)){
				if(a==0){
			   	frei=true;
				} 
			 } else a=1;
		   } 	
		break;
		
		//Spieler steht da wo Gegner steht
		case 9:
			frei=true;
			/*
			 * Hier soll er sich nicht bewegen
			 * dehalb brauchen wir auch nichts prüfen
			 */
		break;
		}
		if(a==1){
			frei=false;
		}
	}
	

	
	//Gibt an wie sich der Gegner bewegt
	public static void lauf(){
		if(laufen){ 
		checkFall();				//überprüfen wo der Gegner steht (im Bezug zum Spieler)	
		checkfree(Fall);			//prüfen ob der Weg frei ist
		if(zehn==1){Fall=10;} else 	//Überprüfen ob er vielleicht einen "besondern" Fall nutzen muss (z.B. diagonal geht nicht aber erst runter dann links)
		if(elf==1){Fall=11;} else 
		if (zwölf==1){Fall=12;}else 
		if (dreizehn==1){Fall=13;}
		nächsterSchritt();			//der nächste schritt wird berechnet
		if (frei){					//wenn frei ist laufen
			StartX+=nächsterSchrittX;
			StartY+=nächsterSchrittY;
		}
		Kollision();				//Kollision abfragen

		}
		
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
	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

