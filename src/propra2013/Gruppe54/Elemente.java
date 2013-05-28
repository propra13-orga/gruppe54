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
			Spielfeld.spieler.x = Raum.Startpunkt[Spielfeld.current_lvl-1].x;
			Spielfeld.spieler.y = Raum.Startpunkt[Spielfeld.current_lvl-1].y;
		
		} else {
		
			Item(ID,block);
		
		}
	}
	
	public static void Falle(int ID,Block block){
		if(ID == 3){  		   //Falle_Loch - Spieler soll auf den Startpunkt zurück fallen
			Spielfeld.spieler.x = Raum.Startpunkt[Spielfeld.current_lvl-1].x;
			Spielfeld.spieler.y = Raum.Startpunkt[Spielfeld.current_lvl-1].y;
		} else if(ID == 4){    //Falle_Feuer
			feuer++;
			if(feuer==5){
				beruehrung = true;		  //da z.B. beim hochlaufen zwei Punkte auf Berührung überprüft werden, soll gespeichert werden,
				if(spieler.ruestung<=0){  //ob bereits eine Falle ausgelöst wurde damit die Punkte nicht doppelt abgezogen werden
					spieler.leben-=1;
				} else {
					spieler.ruestung-=1;
				}		
				feuer = 0;
			}
		} else if(ID == 5){    //Falle_Speer
			speer++;
			if(speer==5){
				beruehrung = true;
				if(spieler.ruestung<=0){
					spieler.leben-=2;
				} else {
					spieler.ruestung-=2;
				}
				block.Zustand = 1;	//lädt das aktive Bild der Falle
				speer = 0;
			}
		} else if(ID == 6){    //Falle_Monster
			beruehrung = true;
			if(spieler.ruestung<=0){
				spieler.leben-=5;
			} else {
				spieler.ruestung-=5;
			}
			if(Spielfeld.spieler.rechts){
				Spielfeld.spieler.x-=12;
			} else if((Spielfeld.spieler.links)&&(Spielfeld.spieler.rechts==false)){
				Spielfeld.spieler.x+=12;
			} else if((Spielfeld.spieler.hoch)&&(Spielfeld.spieler.links==false)&&(Spielfeld.spieler.rechts==false)){
				Spielfeld.spieler.y+=12;
			} else if((Spielfeld.spieler.runter)&&(Spielfeld.spieler.hoch==false)&&(Spielfeld.spieler.links==false)&&(Spielfeld.spieler.rechts==false)){
				Spielfeld.spieler.y-=12;
			}
		}
	}
	
	//block muss mit übergeben werden, da das Item ja vom Block entfernt werden soll
	public static void Item(int ID,Block block){
		if(ID == 7){           //trank1
			if(spieler.leben == 100){
				Spielfeld.spieler.item_trank += 1;
			} else {
				spieler.leben+=40;
				if(spieler.leben>100){
					spieler.leben = 100;
				}
			}
			block.ID = 0;
		} else if(ID == 8){           //trank1
			if(spieler.mana == 100){
				Spielfeld.spieler.item_mana += 1;
			} else {
				spieler.mana+=40;
				if(spieler.mana>100){
					spieler.mana = 100;
				}
			}
			block.ID = 0;
		} else if((ID == 9)&&(spieler.leben<100)&&(block.Zustand==0)){	  //brunnen
			spieler.leben = 100;
			if(spieler.leben>100){
				spieler.leben = 100;
			}
			block.Zustand = 1;
		} else if(ID == 14){						      //zepter
			Spielfeld.spieler.beweglich = false;
			block.ID = 0;
			if(Spielfeld.current_lvl<3){
				Frame.nextLevel.setVisible(true);
			}
			Frame.neustart.setVisible(true);
		} else if(ID == 15){						      //Shopbesitzer
			Spielfeld.shop = true;			//wenn der Benutzer Enter drückt wird der "Shop" betreten
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
