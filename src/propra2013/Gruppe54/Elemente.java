package propra2013.Gruppe54;

import java.io.File;

public class Elemente {
	
	public static boolean beruehrung = false;
	public static int feuer = 0,speer = 0; //es soll nicht jeder Schritt über das Feuer Schaden geben, es soll aber Schaden genommen werden wenn der Spieler stehen bleibt

	public static void Aufruf(int ID,Block block){
		if(ID < 7){
			
			Falle(ID,block);
		
		} if((ID == 2)&&(Spielfeld.current_room!=3)) {  //Ausgang
			Spielfeld.current_room+=1;
			Spielfeld.level.loadLevel(new File("level/level"+Spielfeld.current_lvl+"_"+Spielfeld.current_room+".lvl"));
			//Spieler auf den Startpunkt des jeweiligen Levels setzen
			spieler.x = Raum.Startpunkt[Spielfeld.current_lvl-1].x;
			spieler.y = Raum.Startpunkt[Spielfeld.current_lvl-1].y;
		
		} else {
		
			Item(ID,block);
		
		}
	}
	
	public static void Falle(int ID,Block block){
		if(ID == 3){  		   //Falle_Loch - Spieler soll auf den Startpunkt zurück fallen
			spieler.x = Raum.Startpunkt[Spielfeld.current_lvl-1].x;
			spieler.y = Raum.Startpunkt[Spielfeld.current_lvl-1].y;
		} else if(ID == 4){    //Falle_Feuer
			feuer++;
			if(feuer==5){
				beruehrung = true;		//da z.B. beim hochlaufen zwei Punkte auf Berührung überprüft werden, soll gespeichert werden,
				spieler.leben -= 1;		//ob bereits eine Falle ausgelöst wurde damit die Punkte nicht doppelt abgezogen werden
				feuer = 0;
			}
		} else if(ID == 5){    //Falle_Speer
			speer++;
			if(speer==5){
				beruehrung = true;
				spieler.leben -= 2;
				block.Falle = 1;	//lädt das aktive Bild der Falle
				speer = 0;
			}
		} else if(ID == 6){    //Falle_Monster
			beruehrung = true;
			spieler.leben-=5;
			if(spieler.rechts){
				spieler.x-=12;
			} else if(spieler.links){
				spieler.x+=12;
			} else if(spieler.hoch){
				spieler.y+=12;
			} else if(spieler.runter){
				spieler.y-=12;
			}
			
		}
	}
	
	//block muss mit übergeben werden, da das Item ja vom Block entfernt werden soll
	public static void Item(int ID,Block block){
		if((ID == 7)&&(spieler.leben<100)){           //trank1
			spieler.leben+=40;
			if(spieler.leben>100){
				spieler.leben = 100;
			}
			block.ID = 0;
		} else if((ID == 8)&&(spieler.leben<100)){	  //trank2
			spieler.leben+=20;
			if(spieler.leben>100){
				spieler.leben = 100;
			}
			block.ID = 0;
		} else if(ID == 9){						      //zepter
			spieler.beweglich = false;
			block.ID = 0;
			if(Spielfeld.current_lvl<3){
				Frame.nextLevel.setVisible(true);
			}
			Frame.neustart.setVisible(true);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
