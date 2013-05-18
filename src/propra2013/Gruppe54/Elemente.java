package propra2013.Gruppe54;

import java.io.File;

public class Elemente {
	
	public static boolean beruehrung = false;

	public static void Aufruf(int ID,Block block){
		if(ID < 6){
			
			Falle(ID);
		
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
	
	public static void Falle(int ID){
		if(ID == 3){  		   //Falle_Loch - Spieler soll auf den Startpunkt zurück fallen
			spieler.x = Raum.Startpunkt[Spielfeld.current_lvl-1].x;
			spieler.y = Raum.Startpunkt[Spielfeld.current_lvl-1].y;
		} else if(ID == 4){    //Falle_Feuer
			beruehrung = true;					//da z.B. beim hochlaufen zwei Punkte auf Berührung überprüft werden, soll gespeichert werden,
			spieler.leben-=1;					//ob bereits eine Falle ausgelöst wurde damit die Punkte nicht doppelt abgezogen werden
		} else if(ID == 5){    //Falle_Speer
			beruehrung = true;
			spieler.leben-=3;
		}
	}
	
	//block muss mit übergeben werden, da das Item ja entfernt werden soll
	public static void Item(int ID,Block block){
		if((ID == 6)&&(spieler.leben<100)){           //Zaubertrank
			spieler.leben+=25;
			if(spieler.leben>100){
				spieler.leben = 100;
			}
			block.ID = 0;
		} else if((ID == 7)&&(spieler.leben<100)){
			spieler.leben+=10;
			if(spieler.leben>100){
				spieler.leben = 100;
			}
			block.ID = 0;
		} else if(ID == 8){
			spieler.beweglich = false;
			block.ID = 0;
		} else if(ID == 9){
			
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
